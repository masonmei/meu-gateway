package com.baidu.oped.iop.m4.configuration.security;

import static com.baidu.oped.sia.boot.utils.Constrains.Authorities.ADMIN;

import static java.lang.String.format;

import com.baidu.oped.iop.m4.configuration.properties.AppProperties;
import com.baidu.oped.iop.m4.custom.security.CustomUserDetailsService;
import com.baidu.oped.sia.boot.client.http.HttpClient;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Uuap configuration.
 *
 * @author mason
 */
@Configuration
@EnableWebSecurity
public class UuapSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(UuapSecurityConfiguration.class);

    @Autowired
    private AppProperties properties;

    @Bean
    public HttpClient httpClient() {
        HttpClient httpClient = new HttpClient();
        httpClient.setTimeout(1000);
        return httpClient;
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        casAuthenticationProvider.setAuthenticationUserDetailsService(customUserDetailsService());
        casAuthenticationProvider.setServiceProperties(serviceProperties());
        casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
        casAuthenticationProvider.setKey("meu_cas_id");
        return casAuthenticationProvider;
    }

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(properties.getUuap()
                .getCasServiceUrl());
        serviceProperties.setSendRenew(false);
        return serviceProperties;
    }

    @Bean
    public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
        return new Cas20ServiceTicketValidator(properties.getUuap()
                .getCasUrlPrefix());
    }

    @Bean
    public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> customUserDetailsService() {
        return new CustomUserDetailsService(properties.getUserProjectInfo(), httpClient());
    }

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter authenticationFilter = new CasAuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager());
        authenticationFilter.setSessionAuthenticationStrategy(sessionStrategy());
        authenticationFilter.setFilterProcessesUrl("/login");
        return authenticationFilter;
    }

    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
        CasAuthenticationEntryPoint authenticationEntryPoint = new CasAuthenticationEntryPoint();
        authenticationEntryPoint.setLoginUrl(properties.getUuap()
                .getCasUrlLogin());
        authenticationEntryPoint.setServiceProperties(serviceProperties());
        return authenticationEntryPoint;
    }

    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(properties.getUuap()
                .getCasUrlPrefix());
        return singleSignOutFilter;
    }

    @Bean
    public LogoutFilter requestCasGlobalLogoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter(format("%s?service=%s", properties.getUuap()
                .getCasUrlLogout(), properties.getUuap().getAppServiceHome()), new SecurityContextLogoutHandler());
        logoutFilter.setLogoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));

        return logoutFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(casAuthenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // @formatter:off
        web.ignoring()
                .antMatchers("/fonts/**")
                .antMatchers("/images/**")
                .antMatchers("/scripts/**")
                .antMatchers("/styles/**")
                .antMatchers("/views/**")
                .antMatchers("/i18n/**")
                .antMatchers("GET", "/products")
                .antMatchers("GET", "/users/**");
        // @formatter:off
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilter(casAuthenticationFilter())
                .addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class)
                .addFilterBefore(requestCasGlobalLogoutFilter(), LogoutFilter.class);

        http.exceptionHandling()
                .authenticationEntryPoint(casAuthenticationEntryPoint());

        http.headers().frameOptions().disable()
                .and().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login", "/logout", "/api/**").authenticated()
                .antMatchers("/api/admin/**").hasAuthority(ADMIN)
                .anyRequest().authenticated();

        http.logout().logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

    }

    @Bean
    public SessionAuthenticationStrategy sessionStrategy() {
        return new SessionFixationProtectionStrategy();
    }
}
