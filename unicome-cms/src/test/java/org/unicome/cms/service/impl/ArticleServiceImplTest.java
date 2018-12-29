package org.unicome.cms.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.unicome.cms.UnicomeCmsApplication;
import org.unicome.cms.po.Article;
import org.unicome.cms.po.Tag;
import org.unicome.cms.service.ArticleService;
import org.unicome.cms.vo.ArticleVO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UnicomeCmsApplication.class)
public class ArticleServiceImplTest {

    @Autowired
    ArticleService articleService;
    @Test
    public void list() {
//        List<Article> result = articleService.list();
//        System.out.println(result);
    }

    @Test
//    @Transactional
//    @Rollback(true)
    public void insert() {
//        Article article = new Article();
//        article.setTitle("1234");
//        Tag tag = new Tag();
//        tag.setTitle("cec");
////        List<Tag> tags = new ArrayList<Tag>();
////        tags.add(tag);
////        article.setTags(tags);
//        article = articleService.insert(article);
//        System.out.println(article);
    }

    @Test
    public void update() {
//        ArticleVO vo = new ArticleVO();
//        vo.setId("5c1c8944447d8436ccfc073c");
//        vo.setTitle("测试");
//        articleService.update(vo);
    }
}