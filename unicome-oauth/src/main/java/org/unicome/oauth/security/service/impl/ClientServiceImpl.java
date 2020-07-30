package org.unicome.oauth.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.unicome.oauth.security.entity.Client;
import org.unicome.oauth.security.repository.ClientRepository;
import org.unicome.oauth.security.service.ClientService;

@Slf4j
@Service(value = "clientService")
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        try {
            Client client = clientRepository.findByClientId(clientId);
            return client;
        } catch (Exception e) {
            log.error("Error occurred!", e);
        }
        return null;
    }

    @Override
    public Client findByClientId(String clientId) {
        if (!StringUtils.isEmpty(clientId)) {
            return clientRepository.findByClientId(clientId);
        }
        return null;
    }
}
