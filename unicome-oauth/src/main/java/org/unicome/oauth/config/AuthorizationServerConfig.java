package org.unicome.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    @Qualifier("userService")
    UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("clientService")
    ClientDetailsService clientDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * defines the client details service
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("admin")
//                .secret("admin")
//                .authorizedGrantTypes("authorization_code", "refresh_token", "password");

        // 使用自定义的client details service
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * defines the security coonstraints on the token endpoint
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许使用表单验证
        security.allowFormAuthenticationForClients();
        // 允许/oauth/check端点
        security.checkTokenAccess("permitAll()").checkTokenAccess("permitAll()");
    }

    /**
     * defines the authorization and token endpoints and token services
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // password grant type need authenticationManager and userDetailsService
        endpoints.authenticationManager(authenticationManager);
        endpoints.userDetailsService(userDetailsService);

        DefaultTokenServices tokenServices = new DefaultTokenServices();
        // 设置tokenStore
        tokenServices.setTokenStore(new InMemoryTokenStore());
        // 设置支持refresh_token
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        endpoints.tokenServices(tokenServices);
    }

}
