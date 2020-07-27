package org.unicome.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.unicome.oauth.security.entity.Client;

public interface ClientRepository extends MongoRepository<Client, String> {
    Client findByClientId(String clientId);
}
