package org.unicome.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.unicome.oauth.security.domain.Client;

@Repository("clientRepository")
public interface ClientRepository extends MongoRepository<Client, String> {
    Client findByClientId(String clientId);
}
