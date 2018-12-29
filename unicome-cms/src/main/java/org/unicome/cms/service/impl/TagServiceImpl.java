package org.unicome.cms.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.unicome.cms.po.Tag;
import org.unicome.cms.repository.TagRespository;
import org.unicome.cms.service.TagService;
import org.unicome.cms.vo.TagVO;

import java.util.List;

public class TagServiceImpl implements TagService {

    @Autowired
    TagRespository tagRespository;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Tag> list(int index, int size) {
        Pageable pageable = PageRequest.of(index, size, new Sort(Sort.Direction.DESC, new String[] {"updateDate", "createDate"}));
        Query query = new Query();
        query.with(pageable);
        return mongoTemplate.find(query, Tag.class);
    }

    @Override
    public Tag get(String id) {
        Query query = new Query(Criteria.where("id").is(new ObjectId(id)));
        return mongoTemplate.findOne(query, Tag.class);
    }

    @Override
    public long delete(String id) {
        Query query = new Query(Criteria.where("id").is(new ObjectId(id)));
        return mongoTemplate.remove(query, Tag.class).getDeletedCount();
    }

    @Override
    public Tag create(TagVO vo) {
        Tag tag = TagVO.convert(vo);
//        article.setAuthor(uid);
        return tagRespository.insert(tag);
    }
}
