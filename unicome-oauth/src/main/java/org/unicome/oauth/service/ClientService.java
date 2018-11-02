package org.unicome.oauth.service;

import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.unicome.oauth.entity.Client;

public interface ClientService extends ClientDetailsService {
    Client findByClientId(String clientId);
}
