package org.unicome.proxy.config;


import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;

@EnableOAuth2Sso
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Primary
    public OAuth2ClientContextFilter OAuth2ClientContextFilter() {
        return new OAuth2ClientContextFilter2();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.headers().frameOptions().disable().and().csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/favicon.ico", "/js/**", "/css/**", "/webjars/**", "/login", "/auth/**").permitAll()
                .anyRequest().authenticated();
//                .and()
//                .csrf().disable();
//        super.configure(http);
    }
}
