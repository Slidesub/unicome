package org.unicome.oauth.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.unicome.oauth.security.domain.Group;

import java.util.List;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {

    List<Group> findFirstById(String id);
}
