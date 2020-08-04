package org.unicome.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.unicome.oauth.security.domain.GroupRole;
import org.unicome.oauth.security.domain.RoleAuthority;

import java.util.List;

@Repository("roleAuthorityRepository")
public interface RoleAuthorityRepository extends MongoRepository<RoleAuthority, String> {

    List<RoleAuthority> findByRoleIdIn(List<String> roleIds);
}
