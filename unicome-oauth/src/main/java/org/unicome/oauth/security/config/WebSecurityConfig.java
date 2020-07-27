package org.unicome.oauth.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.SimpleRedirectSessionInformationExpiredStrategy;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.unicome.oauth.security.auth.email.EmailPasswordAuthenticationFilter;
import org.unicome.oauth.security.auth.email.EmailPasswordAuthenticationProvider;
import org.unicome.oauth.security.auth.mobile.MobilePasswordAuthenticationFilter;
import org.unicome.oauth.security.auth.mobile.MobilePasswordAuthenticationProvider;
import org.unicome.oauth.security.auth.sms.SmsAuthenticationFilter;
import org.unicome.oauth.security.auth.sms.SmsAuthenticationProvider;
import org.unicome.oauth.security.constant.SecurityConsts;
import org.unicome.oauth.security.repository.MongoTokenRepositoryImpl;
import org.unicome.oauth.security.service.UserService;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Resource
    FindByIndexNameSessionRepository sessionRepository;

    @Autowired
    SecurityConsts securityConsts;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(securityConsts.whiteList()).permitAll() // 以上配置的URL不需要认证就可以访问
            .anyRequest().authenticated() // 其他尚未匹配的URL都需进行认证
            // 开启表单登录
            .and()
                .formLogin()
                .loginPage(securityConsts.getDefaultLoginUrl())
                .loginProcessingUrl(securityConsts.getDefaultLoginUrl())
                .failureUrl(securityConsts.getDefaultFailureUrl())
            // 开启remember-me
            .and()
                .rememberMe()
                .tokenRepository(tokenRepository()) // 持久化
                .tokenValiditySeconds(securityConsts.getRememberMeTimeout()) // 过期时间，单位s
                .userDetailsService(username -> userService.loadUserByUsername(username)) // 自动登录
            .and()
                .sessionManagement()
                .invalidSessionUrl(securityConsts.getDefaultLoginUrl()) // session超时跳向的url
                .maximumSessions(securityConsts.getSessionMaximum()) // 最大并发数
                .maxSessionsPreventsLogin(true) // 超过最大并发后，阻止后面的登录
                .expiredSessionStrategy(new SimpleRedirectSessionInformationExpiredStrategy(securityConsts.getDefaultLoginUrl())) // 超过最大session并发时的策略
                .expiredUrl(securityConsts.getDefaultLoginUrl())
                .sessionRegistry(sessionRegistry())
            .and()
            // 开启logout
            .and()
                .logout()
                .logoutUrl(securityConsts.getLogoutUrl())
                .logoutSuccessUrl(securityConsts.getDefaultLoginUrl())
                .deleteCookies("JSESSIONID")
            .and()
                .httpBasic();

        // session配置
//        http.headers().frameOptions().disable(); // 解决不允许显示iFream的问题
//        http.addFilterAt(new ConcurrentSessionFilter(sessionRegistry(), sessionInformationExpiredStrategy()), ConcurrentSessionFilter.class);

//        // 配置异常处理
    //        http.exceptionHandling().defaultAuthenticationEntryPointFor();

//        http.addFilterAt(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(emailPasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(mobilePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(smsAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 用户名+密码
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(username -> userService.loadUserByUsername(username));
        auth.authenticationProvider(daoAuthenticationProvider);

        // 邮箱+密码
        EmailPasswordAuthenticationProvider emailPasswordAuthenticationProvider = new EmailPasswordAuthenticationProvider();
        emailPasswordAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        emailPasswordAuthenticationProvider.setUserDetailsService(email -> userService.loadUserByEmail(email));
        auth.authenticationProvider(emailPasswordAuthenticationProvider);

        // 手机号+密码
        MobilePasswordAuthenticationProvider mobilePasswordAuthenticationProvider = new MobilePasswordAuthenticationProvider();
        mobilePasswordAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        mobilePasswordAuthenticationProvider.setUserDetailsService(mobile -> userService.loadUserByMobile(mobile));
        auth.authenticationProvider(mobilePasswordAuthenticationProvider);

        // 手机号+短信验证码
//        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
//        smsAuthenticationProvider.setUserDetailsService(mobile -> userService.loadUserByMobile(mobile));
//        auth.authenticationProvider(smsAuthenticationProvider);
    }

    // 设置 session策略
//    @Bean
//    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
//        UsernamePasswordAuthenticationFilter filter = new CuzUsernamePasswordAuthenticationFilter();
//        filter.setUsernameParameter("u");
//        filter.setAuthenticationManager(authenticationManager());
//        filter.setSessionAuthenticationStrategy(new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry()));
//        return filter;
//    }

    @Bean
    public EmailPasswordAuthenticationFilter emailPasswordAuthenticationFilter() throws Exception {
        EmailPasswordAuthenticationFilter filter = new EmailPasswordAuthenticationFilter();
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(securityConsts.getDefaultFailureUrl()));
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean
    public MobilePasswordAuthenticationFilter mobilePasswordAuthenticationFilter() throws Exception {
        MobilePasswordAuthenticationFilter filter = new MobilePasswordAuthenticationFilter();
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(securityConsts.getMobileFailureUrl()));
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean
    public SmsAuthenticationFilter smsAuthenticationFilter() throws Exception {
        SmsAuthenticationFilter filter = new SmsAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(securityConsts.getSmsFailureUrl()));
        return filter;
    }

    /**
     * expose the AuthenticationManager from configure(AuthenticationManagerBuilder) as a bean
      */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true);
//        return jdbcTokenRepository;
        MongoTokenRepositoryImpl mongoTokenRepository = new MongoTokenRepositoryImpl();
        return mongoTokenRepository;
    }

//    @Bean
//    public SessionRegistry sessionRegistry() {
//        return new SessionRegistryImpl();
//    }

    @Bean
    public SpringSessionBackedSessionRegistry sessionRegistry() {
        return new SpringSessionBackedSessionRegistry(sessionRepository);
    }
//
//    @Bean
//    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
//        // TODO session过期后跳转
//        return new SimpleRedirectSessionInformationExpiredStrategy("/loign");
//    }

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
