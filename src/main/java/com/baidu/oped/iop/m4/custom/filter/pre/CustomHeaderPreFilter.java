package com.baidu.oped.iop.m4.custom.filter.pre;

import static com.baidu.oped.sia.boot.utils.Constrains.Header.ACCEPT;
import static com.baidu.oped.sia.boot.utils.Constrains.Header.USERNAME;
import static com.baidu.oped.sia.boot.utils.Constrains.Request.TRACE_ID_HEADER;
import static com.baidu.oped.sia.boot.utils.Constrains.Request.TRACE_SOURCE_IP_HEADER;
import static com.baidu.oped.sia.boot.utils.Constrains.Request.TRACE_TIME_HEADER;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import com.baidu.oped.sia.boot.common.RequestInfoHolder;
import com.baidu.oped.sia.boot.utils.Constrains;
import com.baidu.oped.sia.boot.utils.LocalInfoProvider;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * @author mason
 */
@Component
public class CustomHeaderPreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
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

        if (userPrincipal == null) {
            return false;
        }
        String username = userPrincipal.getName();
        return !con.containsKey(USERNAME) || con.get(USERNAME).equals(username);
    }

    @Override
    public Object run() {
        final RequestContext context = RequestContext.getCurrentContext();
        context.addZuulRequestHeader(USERNAME, context.getRequest()
                .getUserPrincipal()
                .getName());
        context.addZuulRequestHeader(TRACE_ID_HEADER, RequestInfoHolder.traceId());
        context.addZuulRequestHeader(TRACE_SOURCE_IP_HEADER, LocalInfoProvider.getLocalIpAddress());
        context.addZuulRequestHeader(TRACE_TIME_HEADER, RequestInfoHolder.traceTimestamp().toString());
        context.addZuulRequestHeader(ACCEPT, APPLICATION_JSON_UTF8_VALUE);
        return null;
    }
}
