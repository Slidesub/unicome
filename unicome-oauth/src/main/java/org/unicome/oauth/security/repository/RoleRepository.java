package org.unicome.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.unicome.oauth.security.domain.Role;

@Repository("roleRepository")
public interface RoleRepository extends MongoRepository<Role, String> {
}
