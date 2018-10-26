package org.unicome.oauth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.unicome.oauth.domain.Client;
import org.unicome.oauth.service.ClientService;
import org.unicome.oauth.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client findByClientId(String clientId) {
        if (!StringUtils.isEmpty(clientId)) {
            return clientRepository.findByClientId(clientId);
        }
        return null;
    }
}
