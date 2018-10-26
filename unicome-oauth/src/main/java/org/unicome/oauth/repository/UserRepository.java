package org.unicome.oauth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.unicome.oauth.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    User findByMobile(String mobile);
}
