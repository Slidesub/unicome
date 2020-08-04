package org.unicome.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.unicome.oauth.security.domain.UserGroup;

import java.util.List;

@Repository("userGroupRepository")
public interface UserGroupRepository extends MongoRepository<UserGroup, String> {

    List<UserGroup> findByUserId(String userId);
}
