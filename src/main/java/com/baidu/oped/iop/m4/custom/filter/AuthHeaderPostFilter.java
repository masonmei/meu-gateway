package com.baidu.oped.iop.m4.custom.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * @author mason
 */
@Component
public class AuthHeaderPostFilter extends ZuulFilter {
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
                .containsKey("username");
    }

    @Override
    public Object run() {
        final RequestContext context = RequestContext.getCurrentContext();
        context.getZuulRequestHeaders().remove("username");
        return null;
    }
}
