package org.unicome.oauth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.unicome.oauth.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    User findByMobile(String mobile);
}
