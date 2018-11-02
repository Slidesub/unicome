package org.unicome.oauth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.unicome.oauth.entity.Client;

public interface ClientRepository extends MongoRepository<Client, String> {
    Client findByClientId(String clientId);
}
