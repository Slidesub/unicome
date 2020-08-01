package org.unicome.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.unicome.oauth.security.domain.GroupRole;

import java.util.List;

@Repository
public interface GroupRoleRepository extends MongoRepository<GroupRole, String> {

    List<GroupRole> findByGroupIdIn(List<String> groupIds);
}
