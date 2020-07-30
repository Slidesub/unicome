package org.unicome.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.unicome.oauth.security.entity.GroupRole;
import org.unicome.oauth.security.entity.UserGroup;

import java.util.List;

@Repository
public interface GroupRoleRepository extends MongoRepository<GroupRole, String> {

    List<GroupRole> findByGroupIdIn(List<String> groupIds);
}
