package org.unicome.cms.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.BulkOperationException;
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
    public Article get(String id) {
        return mongoTemplate.findById(new ObjectId(id), Article.class);
    }

    @Override
    public List<Article> list() {
        return articleRepository.findAll();
    }

    @Override
    public Article create(ArticleVO articleVO)  {
        Article article = ArticleVO.convert(articleVO);
        return articleRepository.insert(article);
    }

    @Override
    public long batchCreate(List<ArticleVO> articleVOs) {
        List<Article> articles = ArticleVO.convert2List(articleVOs);
        BulkOperations operation = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Article.class).insert(articles);
        return operation.execute().getInsertedCount();
    }

    @Override
    public long update(ArticleVO articleVO) {
        Article article = ArticleVO.convert(articleVO);
        Query query = new Query(Criteria.where("id").is(articleVO.getId()));
        Update update = new Update()
                .set("title", article.getTitle())
                .set("tags", article.getTags());

        return mongoTemplate.updateFirst(query, update, Article.class).getModifiedCount();
    }

    @Override
    public long delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, Article.class).getDeletedCount();
    }

    @Override
    public long batchUpdate(List<ArticleVO> articleVOs) {
        return 0;
    }

    @Override
    public long batchDelete(List<String> ids) {
        return 0;
    }



    @Override
    public List<Article> listByUser(String uid, int index, int size) {
        Pageable pageable = PageRequest.of(index, size, new Sort(Sort.Direction.DESC, new String[] {"updateDate", "createDate"}));
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
        article.setAuthor(uid);
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
        Query query = new Query(Criteria.where("author").is(new ObjectId("uid"))
                .and("id").is(new ObjectId((aid))));
        return mongoTemplate.remove(query, Article.class).getDeletedCount();
    }

    @Override
    public long batchDeleteByUser(String uid, List<String> aids) {
        Query query = new Query(Criteria.where("author").is(new ObjectId("uid"))
                .and("id").in(aids));
        return mongoTemplate.remove(query, Article.class).getDeletedCount();
    }
}
