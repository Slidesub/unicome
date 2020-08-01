package org.unicome.oauth.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.unicome.oauth.security.domain.Client;
import org.unicome.oauth.security.repository.ClientRepository;
import org.unicome.oauth.security.service.ClientService;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client loadByClientId(String clientId) throws ClientRegistrationException {
        if (!StringUtils.isEmpty(clientId)) {
            return clientRepository.findByClientId(clientId);
        }
        return null;
    }
}
