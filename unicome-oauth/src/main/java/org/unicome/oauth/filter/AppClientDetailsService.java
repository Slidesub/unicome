package org.unicome.oauth.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.unicome.oauth.domain.Client;
import org.unicome.oauth.service.impl.ClientServiceImpl;

@Slf4j
@Service
public class AppClientDetailsService implements ClientDetailsService {
    @Autowired
    ClientServiceImpl clientService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        try {
            Client client = clientService.findByClientId(clientId);
            return client;
        } catch (Exception e) {
            log.error("Error occurred!", e);
        }
        return null;
    }
}
