package com.baidu.oped.iop.m4.custom.filter.post;

import static com.baidu.oped.sia.boot.utils.Constrains.Header.USERNAME;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * @author mason
 */
@Component
public class CustomHeaderPostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 10000;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext con = RequestContext.getCurrentContext();
        Principal userPrincipal = con.getRequest()
                .getUserPrincipal();

        return userPrincipal != null && con.getZuulRequestHeaders()
                .containsKey(USERNAME);
    }

    @Override
    public Object run() {
        final RequestContext context = RequestContext.getCurrentContext();
        context.getZuulRequestHeaders().remove(USERNAME);
        return null;
    }
}
