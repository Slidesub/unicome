package org.unicome.cms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.unicome.cms.po.Article;

import java.util.List;

public interface ArticleRepository extends MongoRepository<Article, String> {
    public List<Article> findAll();
    public Article insert(Article article);
    public void deleteById(String id);
}
