package com.baidu.oped.iop.m4.custom.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.ExpressionBasedFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashMap;
import javax.annotation.PostConstruct;

/**
 * @author mason
 */
@Component
public class CustomFilterSecurityInterceptor extends FilterSecurityInterceptor {

    @Autowired
    private AccessDecisionManager accessDecisionManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostConstruct
    public void postConstruct() throws Exception{
        setObserveOncePerRequest(false);
        setAccessDecisionManager(accessDecisionManager);
        setAuthenticationManager(authenticationManager);
        setSecurityMetadataSource(securityMetadataSource());
    }

    private FilterInvocationSecurityMetadataSource securityMetadataSource() {
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = null;

        SecurityExpressionHandler<FilterInvocation> expressionHandler = null;
        //TODO:
        return  new ExpressionBasedFilterInvocationSecurityMetadataSource(requestMap, expressionHandler);
    }

    @Override
    public AccessDecisionManager getAccessDecisionManager() {
        return accessDecisionManager;
    }

    @Override
    public void setAccessDecisionManager(AccessDecisionManager accessDecisionManager) {
        this.accessDecisionManager = accessDecisionManager;
    }

    @Override
    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

}
