package org.unicome.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unicome.cms.po.Article;
import org.unicome.cms.service.ArticleService;
import org.unicome.cms.vo.ArticleVO;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticleService articleService;

//    创建
    @PostMapping("")
    public void create(@RequestBody ArticleVO articleVO) {
        articleService.create(articleVO);
    }

//    删除
    @DeleteMapping("/{id}")
    public long delete(@PathVariable String id) {
        return articleService.delete(id);
    }

//    批量删除
    @DeleteMapping("")
    public long batchDelete(@RequestBody List<String> ids) {
        return articleService.batchDelete(ids);
    }

//    修改
    @PutMapping("")
    public long update(@RequestBody ArticleVO articleVO) {
        return articleService.update(articleVO);
    }

//    查询
    @GetMapping("/{id}")
    public ArticleVO get(@PathVariable String id) {
        Article article = articleService.get(id);
        return ArticleVO.convert2VO(article);
    }

//    查询全部
    @GetMapping("")
    public List<ArticleVO> list() {
        List<Article> articles = articleService.list();
        return ArticleVO.convert2VOList(articles);
    }
}
