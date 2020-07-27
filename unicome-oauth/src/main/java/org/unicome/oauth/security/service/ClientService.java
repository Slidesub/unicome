package org.unicome.oauth.security.service;

import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.unicome.oauth.security.entity.Client;

public interface ClientService extends ClientDetailsService {
    Client findByClientId(String clientId);
}
