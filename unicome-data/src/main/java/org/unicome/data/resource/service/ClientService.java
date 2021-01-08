package org.unicome.data.resource.service;

import org.unicome.data.domain.mysql.client.Client;

public interface ClientService {
    Client getById(int id);
    Client getByClientId(String clientId);
}
