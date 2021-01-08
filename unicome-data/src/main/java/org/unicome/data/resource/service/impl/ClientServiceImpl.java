package org.unicome.data.resource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unicome.data.domain.mysql.client.Client;
import org.unicome.data.domain.mysql.client.ClientDao;
import org.unicome.data.resource.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public Client getById(int id) {
        Client client = clientDao.findById(id);
        return client;
    }

    @Override
    public Client getByClientId(String clientId) {
        Client client = clientDao.findByClientId(clientId);
        return client;
    }
}
