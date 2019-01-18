package org.unicome.cms.vo;

import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.unicome.cms.po.Article;
import org.unicome.cms.po.FileEntry;
import org.unicome.cms.po.Tag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ArticleVO implements Serializable {
    private String id;
    private String title;
    private String description;
    private String body;
    private Map<String, String> icon; // id，url
    private Map<String, String> thumbnail;
    private List<String> tags; // id
    private String author;

    public static ArticleVO convert(Article article) {
        ArticleVO articleVO = new ArticleVO();
        articleVO.setId(article.getId());
        articleVO.setTitle(article.getTitle());
        articleVO.setDescription(article.getDescription());
        articleVO.setBody(article.getBody());
        articleVO.setAuthor(article.getCreateBy());
        if (null != article.getIcon()) {
            Map<String, String> icon = new HashMap<String, String>();
            icon.put(article.getIcon().getId(), article.getIcon().getUrl());
            articleVO.setIcon(icon);
            articleVO.setThumbnail(icon);
        }
        if (!CollectionUtils.isEmpty(article.getTags())) {
            List<String> tags = new ArrayList<>();
            for(Tag tag : article.getTags()) {
                tags.add(tag.getId());
            }
            articleVO.setTags(tags);
        }

        return articleVO;
    }

    public static Article convert(ArticleVO articleVO) {
        Article article = new Article();
        article.setId(articleVO.getId());
        article.setTitle(articleVO.getTitle());
        article.setDescription(articleVO.getDescription());
        article.setBody(articleVO.getBody());
        article.setCreateBy(articleVO.getAuthor());
        if (!CollectionUtils.isEmpty(articleVO.getIcon())) {
            FileEntry icon = new FileEntry();
            icon.setId(articleVO.getIcon().get("id"));
            article.setIcon(icon);
        }
        if (!CollectionUtils.isEmpty(articleVO.getTags())) {
            List<Tag> tags = new ArrayList<>();
            for (String id : articleVO.getTags()) {
                tags.add(new Tag(id));
            }
            article.setTags(tags);
        }

        return article;
    }

    public static List<ArticleVO> convert2VOList(List<Article> articles) {
        List<ArticleVO> articleVOs= null;
        if (!CollectionUtils.isEmpty(articles)) {
            articleVOs = new ArrayList<>();
            for (Article article : articles) {
                articleVOs.add(ArticleVO.convert(article));
            }
        }
        return articleVOs;
    }

    public static List<Article> convert2List(List<ArticleVO> articleVOs) {
        List<Article> articles= null;
        if (!CollectionUtils.isEmpty(articleVOs)) {
            articles = new ArrayList<>();
            for (ArticleVO articleVO : articleVOs) {
                articles.add(ArticleVO.convert(articleVO));
            }
        }
        return articles;
    }
}
