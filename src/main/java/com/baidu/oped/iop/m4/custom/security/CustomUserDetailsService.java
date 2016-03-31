package com.baidu.oped.iop.m4.custom.security;

import static org.springframework.http.HttpMethod.GET;

import com.baidu.oped.sia.boot.client.http.HttpClient;
import com.baidu.oped.sia.boot.client.http.HttpRequestBuilder;
import com.baidu.oped.sia.boot.client.http.HttpRequestContext;
import com.baidu.oped.sia.boot.client.http.HttpTask;
import com.baidu.oped.sia.boot.common.BaseResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Meu User details service.
 *
 * @author mason
 */
public class CustomUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {
    private static final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final String userProductEndpoint;
    private final HttpClient client;

    public CustomUserDetailsService(String userProductEndpoint, HttpClient client) {
        Assert.hasLength(userProductEndpoint, "Endpoint of user's products must not be null.");
        this.userProductEndpoint = userProductEndpoint;
        this.client = client;
    }

    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
        String login = token.getPrincipal().toString();
        LOG.debug("Authenticating '{}'", login);

        AppUserDetails appUserDetails = retrieveUserProducts(login, "bdpassport");

        LOG.debug("Authenticating {} finished. Authorities: {}.",appUserDetails.getName(), Arrays.toString
                (appUserDetails.getAuthorities().toArray()));
        return appUserDetails;
    }

    private AppUserDetails retrieveUserProducts(String userName, String plat) {
        LOG.debug("Retrieving {}'s products.", userName);
        HttpRequestBuilder builder = HttpRequestBuilder.get(userProductEndpoint);
        builder.acceptJson()
                .path(userName)
                .header("userName", userName)
                .parameter("userName", userName)
                .parameter("plat", plat)
                .method(GET);
        HttpRequestContext<BaseResponse<AppUserDetails>> requestContext = new HttpRequestContext<>(builder);
        requestContext.setTypeReference(new ParameterizedTypeReference<BaseResponse<AppUserDetails>>() {
        });
        client.execute(new HttpTask<>(requestContext));
        BaseResponse<AppUserDetails> result = requestContext.result();
        LOG.debug("Retrieving {}'s products finished. Result: {}.", userName, result);
        return result.getData();
    }

}
