package org.unicome.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.unicome.oauth.filter.MobilePasswordAuthenticationFilter;
import org.unicome.oauth.security.MobilePasswordAuthenticationProvider;
import org.unicome.oauth.security.UsernamePasswordAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends  WebSecurityConfigurerAdapter {

    @Autowired
    MobilePasswordAuthenticationProvider mobilePasswordAuthenticationProvider;
    @Autowired
    UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;


    @Autowired
    @Qualifier("userService")
    UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/oauth/**", "/login/mobile").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();

        http.addFilterAfter(mobilePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(usernamePasswordAuthenticationProvider);
        auth.authenticationProvider(mobilePasswordAuthenticationProvider);
    }

    @Bean
    public MobilePasswordAuthenticationFilter mobilePasswordAuthenticationFilter() throws Exception {
        MobilePasswordAuthenticationFilter filter = new MobilePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

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
//    public static class FormLoginWebSecurityConfig extends  WebSecurityConfigurerAdapter {
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            http
//                .authorizeRequests()
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin();
//        }
//        @Bean
//        public UserDetailsService userDetailsService() {
//            User.UserBuilder users = User.withDefaultPasswordEncoder();
//            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//            manager.createUser(users.username("admin").password("admin").roles("USER","ADMIN").build());
//            return manager;
//        }
//    }
}
