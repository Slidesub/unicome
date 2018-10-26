package org.unicome.oauth.service;

import org.unicome.oauth.domain.Client;

public interface ClientService {
    Client findByClientId(String clientId);
}
