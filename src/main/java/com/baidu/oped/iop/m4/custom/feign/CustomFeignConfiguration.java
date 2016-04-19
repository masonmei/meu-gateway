package com.baidu.oped.iop.m4.custom.feign;

import static com.baidu.oped.sia.boot.utils.Constrains.Header.ACCEPT;
import static com.baidu.oped.sia.boot.utils.Constrains.Header.CONTENT_TYPE;
import static com.baidu.oped.sia.boot.utils.Constrains.Header.USERNAME;
import static com.baidu.oped.sia.boot.utils.Constrains.Request.TRACE_ID_HEADER;
import static com.baidu.oped.sia.boot.utils.Constrains.Request.TRACE_SOURCE_IP_HEADER;
import static com.baidu.oped.sia.boot.utils.Constrains.Request.TRACE_TIME_HEADER;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import static java.lang.String.format;

import com.baidu.oped.sia.boot.common.RequestInfoHolder;
import com.baidu.oped.sia.boot.utils.LocalInfoProvider;

import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.logging.LogLevel;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mason
 */
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class CustomFeignConfiguration {
    @Bean
    public RequestInterceptor headerInterceptor() {
        return template -> {
            template.header(ACCEPT, APPLICATION_JSON_UTF8_VALUE);
            template.header(CONTENT_TYPE, APPLICATION_JSON_VALUE);
        };
    }

//    @Bean
//    public Logger.Level loggerLevel(){
//        return Logger.Level.FULL;
//    }
//
//    @Bean
//    public Request.Options options() {
//        return new Request.Options(100 * 1000, 120 * 1000);
//    }

    @Bean
    public FilterRegistrationBean authHeaderFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE - 1);
        registrationBean.setFilter(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                    FilterChain filterChain) throws ServletException, IOException {
                try {
                    SecurityContext context = SecurityContextHolder.getContext();
                    if (context != null && context.getAuthentication() != null) {
                        String name = context.getAuthentication()
                                .getName();
                        RequestInfoHolder.setCurrentUser(name);
                    }
                    filterChain.doFilter(request, response);
                } finally {
                    RequestInfoHolder.removeCurrentUser();
                }
            }
        });
        return registrationBean;
    }

    @Bean
    public RequestInterceptor userHeaderInterceptor() {
        return template -> template.header(USERNAME, RequestInfoHolder.currentUser());
    }

    @Bean
    public RequestInterceptor traceHeaderInterceptor() {
        return template -> {
            template.header(TRACE_ID_HEADER, RequestInfoHolder.traceId());
            template.header(TRACE_TIME_HEADER, format("%d", RequestInfoHolder.traceTimestamp()));
            template.header(TRACE_SOURCE_IP_HEADER, LocalInfoProvider.getLocalIpAddress());
        };
    }

    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }
}

