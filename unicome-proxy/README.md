# Spring Security

1. SpringSecurityFilterChain:
```
@EnableWebSecurity
```

2. 
* AbstractSecurityWebApplicationInitializer without Spring
```
import org.springframework.security.web.context.*;

public class SecurityWebApplicationInitializer
    extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(WebSecurityConfig.class);
    }
}
```

* AbstractSecurityWebApplicationInitializer with Spring
```
public class MvcWebApplicationInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { WebSecurityConfig.class };
    }

    // ... other overrides ...
}
```

3. WebSecurityConfigurerAdapter
```
protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .and()
        .httpBasic();
}
```
4. Form Login and authorize request
```
protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .permitAll();
}
```

```
protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()                                                                1
            .antMatchers("/resources/**", "/signup", "/about").permitAll()                  2
            .antMatchers("/admin/**").hasRole("ADMIN")                                      3
            .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")            4
            .anyRequest().authenticated()                                                   5
            .and()
        // ...
        .formLogin();
}
```
5. Logout
```
protected void configure(HttpSecurity http) throws Exception {
    http
        .logout()                                                                    1
            .logoutUrl("/my/logout")                                                 2
            .logoutSuccessUrl("/my/index")                                           3
            .logoutSuccessHandler(logoutSuccessHandler)                              4
            .invalidateHttpSession(true)                                             5
            .addLogoutHandler(logoutHandler)                                         6
            .deleteCookies(cookieNamesToClear)                                       7
            .and()
        ...
}
```

# Spring OAuth2 Login - Authorization Code Grant
* initial setup - client_id/client_secret
* setting the redirect URI - after they have authenticated, redirect
* Configute application.yml
* Boot up the application

1. ClientRegistration
```
public final class ClientRegistration {
    private String registrationId;                                  1
    private String clientId;                                        2
    private String clientSecret;                                    3
    private ClientAuthenticationMethod clientAuthenticationMethod;  4
    private AuthorizationGrantType authorizationGrantType;          5
    private String redirectUriTemplate;                             6
    private Set<String> scopes;                                     7
    private ProviderDetails providerDetails;
    private String clientName;                                      8

    public class ProviderDetails {
        private String authorizationUri;                            9
        private String tokenUri;                                    10
        private UserInfoEndpoint userInfoEndpoint;
        private String jwkSetUri;                                   11

        public class UserInfoEndpoint {
            private String uri;                                     12
            private String userNameAttributeName;                   13

        }
    }
}
```
2. ClientRegistrationRepository
> The default implementation of ClientRegistrationRepository is InMemoryClientRegistrationRepository

3 CommonOAuth2Provider
> CommonOAuth2Provider pre-defines a set of default client properties for a number of well known providers: Google, GitHub, Facebook, and Okta

4. Configuring Custom Provider Properties

5. Spring Boot2.0 Auto-configuration
> The Spring Boot 2.0 Auto-configuration class for OAuth Client support is OAuth2ClientAutoConfiguration

Overriding:
* Register a ClientRegistrationRepository @Bean
* Provide a WebSecurityConfigurerAdapter
* Completely Override the Auto-configuration

* OAuth2AuthorizedClientService @Bean

```
// Register a ClientRegistrationRepository @Bean
@Configuration
public class OAuth2LoginConfig {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
    }

    private ClientRegistration googleClientRegistration() {
        return ClientRegistration.withRegistrationId("google")
            .clientId("google-client-id")
            .clientSecret("google-client-secret")
            .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
            .scope("openid", "profile", "email", "address", "phone")
            .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
            .tokenUri("https://www.googleapis.com/oauth2/v4/token")
            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
            .userNameAttributeName(IdTokenClaimNames.SUB)
            .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
            .clientName("Google")
            .build();
    }
}
// Provide a WebSecurityConfigurerAdapter
@EnableWebSecurity
public class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .oauth2Login();
    }
}
```

# Authentication
* In-Memory Authentication
* JDBC Authentication
* LDAP Authentication

1. AuthenticationProvider

2. UserDetailsService