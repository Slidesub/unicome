package org.unicome.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.unicome.oauth.security.domain.UserRole;

import java.util.List;

@Repository("userRoleRepository")
public interface UserRoleRepository extends MongoRepository<UserRole, String> {

    List<UserRole> findByUserId(String userId);
}
