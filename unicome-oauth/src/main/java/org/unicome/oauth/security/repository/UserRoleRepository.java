package org.unicome.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.unicome.oauth.security.entity.GroupRole;
import org.unicome.oauth.security.entity.User;
import org.unicome.oauth.security.entity.UserRole;

import java.util.List;

@Repository
public interface UserRoleRepository extends MongoRepository<UserRole, String> {

    List<UserRole> findByUserId(String userId);
}
