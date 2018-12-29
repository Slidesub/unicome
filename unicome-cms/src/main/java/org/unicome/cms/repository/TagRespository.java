package org.unicome.cms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.unicome.cms.po.Tag;

public interface TagRespository extends MongoRepository<Tag, String> {
}
