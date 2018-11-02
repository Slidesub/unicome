package org.unicome.oauth.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.unicome.oauth.filter.MobilePasswordAuthenticationFilter;
import org.unicome.oauth.security.MobilePasswordAuthenticationProvider;

//@Component
public class MobilePasswordAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        MobilePasswordAuthenticationFilter mobileAuthenticationFilter = new MobilePasswordAuthenticationFilter();
        mobileAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
//        mobileAuthenticationFilter.setAuthenticationSuccessHandler(successAuthenticationHandler);
//        mobileAuthenticationFilter.setAuthenticationFailureHandler(failAuthenticationHandler);

        MobilePasswordAuthenticationProvider mobileAuthenticationProvider = new MobilePasswordAuthenticationProvider();
//        mobileAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(mobileAuthenticationProvider)
                .addFilterAfter(mobileAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
