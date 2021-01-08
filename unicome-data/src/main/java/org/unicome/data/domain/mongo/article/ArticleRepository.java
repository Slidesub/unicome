package org.unicome.data.domain.mongo.article;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.unicome.data.domain.mongo.po.Article;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
}
