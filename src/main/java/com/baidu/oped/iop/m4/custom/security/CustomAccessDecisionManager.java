package com.baidu.oped.iop.m4.custom.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * Custom Access Decision Manager.
 *
 * @author mason
 */
@Component
public class CustomAccessDecisionManager extends AbstractAccessDecisionManager {

    @Autowired
    protected CustomAccessDecisionManager(List<AccessDecisionVoter<? extends Object>> decisionVoters) {
        super(decisionVoters);
    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        if (!(object instanceof FilterInvocation)) {
            return;
        }

        FilterInvocation invocation = (FilterInvocation) object;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (CollectionUtils.isEmpty(authorities)) {
            throw new InsufficientAuthenticationException("");
        }

        //TODO:
    }
}
