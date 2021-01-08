package org.unicome.cms.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.unicome.cms.po.Article;
import org.unicome.cms.repository.ArticleRepository;
import org.unicome.cms.service.ArticleService;
import org.unicome.cms.vo.ArticleVO;

import java.util.List;

@Slf4j
@Service(value = "articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Article> list(int index, int size) {
        Pageable pageable = PageRequest.of(index, size, Sort.by(Sort.Direction.DESC, new String[] {"updateDate",
                "createDate"}));
        Query query = new Query(Criteria.where("publish").is(true));
        query.with(pageable);
        return mongoTemplate.find(query, Article.class);
    }

    @Override
    public Article get(String id) {
        Query query = new Query(Criteria.where("id").is(new ObjectId(id)).and("publish").is(true));
        return mongoTemplate.findOne(query, Article.class);
    }

    @Override
    public List<Article> listByUser(String uid, int index, int size) {
        Pageable pageable = PageRequest.of(index, size, Sort.by(Sort.Direction.DESC, new String[] {"updateDate", "createDate"}));
        Query query = new Query(Criteria.where("author").is(new ObjectId(uid)));
        query.with(pageable);
        return mongoTemplate.find(query, Article.class);
    }

    @Override
    public Article getByUser(String uid, String aid) {
        Query query = new Query(Criteria.where("author").is(new ObjectId(uid)).and("id").is(new ObjectId(aid)));
        return mongoTemplate.findOne(query, Article.class);
    }

    @Override
    public Article createByUser(String uid, ArticleVO vo) {
        Article article = ArticleVO.convert(vo);
        article.setCreateBy(uid);
        return articleRepository.insert(article);
    }

    @Override
    public long updateByUser(String uid, ArticleVO vo) {
        Article article = ArticleVO.convert(vo);
        Query query = new Query(Criteria.where("author").is(new ObjectId(uid))
                .and("id").is(new ObjectId(article.getId())));
        Update update = new Update()
                .set("title", article.getTitle())
                .set("description", article.getDescription())
                .set("body", article.getBody())
                .set("icon", article.getIcon())
                .set("tags", article.getTags())
                .set("publish", article.getPublish())
                .set("updateDate", article.getUpdateDate());
        return mongoTemplate.updateFirst(query, update, Article.class).getModifiedCount();
    }

    @Override
    public long deleteByUser(String uid, String aid) {
        Query query = new Query(Criteria.where("author").is(new ObjectId(uid))
                .and("id").is(new ObjectId((aid))));
        return mongoTemplate.remove(query, Article.class).getDeletedCount();
    }

    @Override
    public long batchCreateByUser(String uid, List<ArticleVO> vos) {
        List<Article> articles = ArticleVO.convert2List(vos);
        BulkOperations operation = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Article.class).insert(articles);
        return operation.execute().getInsertedCount();
    }

    @Override
    public long batchUpdateByUser(String uid, List<ArticleVO> vos) {
        List<Article> articles = ArticleVO.convert2List(vos);
        BulkOperations operations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Article.class);
        for (Article article : articles) {
            Update update = new Update()
                    .set("title", article.getTitle())
                    .set("description", article.getDescription())
                    .set("body", article.getBody())
                    .set("icon", article.getIcon())
                    .set("tags", article.getTags())
                    .set("publish", article.getPublish())
                    .set("updateDate", article.getUpdateDate());
            operations.updateOne(new Query(Criteria.where("author").is(new ObjectId(uid)).and("id").is(new ObjectId(article.getId()))), update);
        }
        return operations.execute().getModifiedCount();
    }

    @Override
    public long batchDeleteByUser(String uid, List<String> aids) {
        Query query = new Query(Criteria.where("author").is(new ObjectId(uid)).and("id").in(aids));
        BulkOperations operations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Article.class).remove(query);
        return operations.execute().getDeletedCount();
    }
}
