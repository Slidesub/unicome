package org.unicome.oauth.security.service;

import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.unicome.oauth.security.domain.Client;

public interface ClientService {
    Client loadByClientId(String clientId) throws ClientRegistrationException;
}
