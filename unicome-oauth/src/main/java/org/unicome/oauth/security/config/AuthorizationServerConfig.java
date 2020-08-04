package org.unicome.oauth.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.unicome.oauth.security.constant.SecurityConstants;
import org.unicome.oauth.security.service.ClientService;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    SecurityConstants securityConstants;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ClientService clientService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientId -> clientService.loadByClientId(clientId));
//        clients.inMemory()
//                .withClient("client1")
//                .secret(passwordEncoder.encode("secret1"))
//                .scopes("app")
//                .authorizedGrantTypes("authorization_code", "password", "refresh_token", "client_credentials")
//                .redirectUris("/");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    /**
     * 令牌发放端点
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenServices(tokenServices());
    }

    /**
     * token存储
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        // jwt
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        KeyProperties keyProperties = new KeyProperties();
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyProperties.getKeyStore().getLocation(),
//                keyProperties.getKeyStore().getSecret().toCharArray());
//        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(keyProperties.getKeyStore().getAlias());
//        converter.setKeyPair(keyPair);
//        return new JwtTokenStore(converter);
        // redis
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        return redisTokenStore;
    }

    /**
     * token服务
     * @return
     */
    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        tokenServices.setClientDetailsService(clientId -> clientService.loadByClientId(clientId));
        return tokenServices;
    }
}
