package org.unicome.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.unicome.oauth.security.MobilePasswordAuthenticationProvider;

@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Http Basic
     */
//    @Configuration
//    @Order(1)
//    public static class HttpBasicWebSecurityConfig extends WebSecurityConfigurerAdapter {
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            http
//                .authorizeRequests()
//                    .anyRequest().authenticated()
//                    .and()
//                .httpBasic();
//        }
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.inMemoryAuthentication()
//                    .passwordEncoder(new BCryptPasswordEncoder())
//                    .withUser("test")
//                    .password(new BCryptPasswordEncoder().encode("test"))
//                    .roles("USER");
//        }
//    }

    /**
     * Form Login
     */
    public static class FormLoginWebSecurityConfig extends  WebSecurityConfigurerAdapter {
        @Autowired
        MobilePasswordAuthenticationProvider mobilePasswordAuthenticationProvider;

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                    .antMatchers("/oauth/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin();
        }
        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            // 增加自定义的provider
            auth.authenticationProvider(mobilePasswordAuthenticationProvider);
        }
        @Bean
        public UserDetailsService userDetailsService() {
            User.UserBuilder users = User.withDefaultPasswordEncoder();
            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
            manager.createUser(users.username("admin").password("admin").roles("USER","ADMIN").build());
            return manager;
        }

    }


}
