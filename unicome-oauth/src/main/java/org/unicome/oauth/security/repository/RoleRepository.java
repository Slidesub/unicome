package org.unicome.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.unicome.oauth.security.entity.Role;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    List<Role> getByIdIn(Set<String> ids);
}
