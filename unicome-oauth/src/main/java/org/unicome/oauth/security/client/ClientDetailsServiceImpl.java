package org.unicome.oauth.security.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.unicome.oauth.security.constant.UrlConstants;

import java.util.Map;
import java.util.Set;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        String url = UrlConstants.CLIENT_URL + "?clientId=" + clientId;
        Map client = restTemplate.getForObject(url, Map.class);

        if (null == client) {
            throw new ClientRegistrationException("client not found");
        } else if (!(Boolean)client.get("enabled")) {
            throw new ClientRegistrationException("client is not enabled");
        }

        ClientDetailsImpl clientDetails = ClientDetailsImpl.builder()
                .clientId((String) client.get("clientId"))
                .clientSecret((String) client.get("clientSecret"))
                .resourceIds((Set<String>) client.get("resourceIds"))
                .scopes((Set<String>) client.get("scopes"))
                .autoApproveScopes((Set<String>) client.get("autoApproveScopes"))
                .authorizedGrantTypes((Set<String>) client.get("authorizedGrantTypes"))
                .registeredRedirectUris((Set<String>) client.get("registeredRedirectUris"))
                .authorities((Set<String>) client.get("authorities"))
                .accessTokenValiditySeconds((Integer) client.get("accessTokenValiditySeconds"))
                .refreshTokenValiditySeconds((Integer) client.get("refreshTokenValiditySeconds"))
                .build();
        return clientDetails;
    }
}
