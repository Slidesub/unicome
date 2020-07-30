package org.unicome.oauth.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.unicome.oauth.security.constant.SecurityConsts;
import org.unicome.oauth.security.service.UserService;

import java.security.KeyPair;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    SecurityConsts securityConsts;

    @Autowired
    UserService userService;

    @Autowired
    @Qualifier("clientService")
    ClientDetailsService clientDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetailsService);
        clients.inMemory()
                .withClient("client1")
                .secret(passwordEncoder().encode("secret1"))
                .scopes("app")
                .authorizedGrantTypes("authorization_code")
                .redirectUris("/");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许使用表单验证
//        security.allowFormAuthenticationForClients();
        // 允许/oauth/check端点
        security.checkTokenAccess("permitAll()").checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.userDetailsService(username -> {
            return userService.loadUserByUsername(username);
        });

        DefaultTokenServices tokenServices = new DefaultTokenServices();
        // 设置tokenStore
        tokenServices.setTokenStore(new InMemoryTokenStore());
        // 设置支持refresh_token
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        endpoints.tokenServices(tokenServices);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public TokenStore tokenStore() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        KeyProperties keyProperties = new KeyProperties();
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyProperties.getKeyStore().getLocation(),
//                keyProperties.getKeyStore().getSecret().toCharArray());
//        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(keyProperties.getKeyStore().getAlias());
//        converter.setKeyPair(keyPair);
//        return new JwtTokenStore(converter);
//    }

}
