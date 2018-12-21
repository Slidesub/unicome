package org.unicome.cms.service;

import org.unicome.cms.po.Article;
import org.unicome.cms.vo.ArticleVO;

import java.util.List;

public interface ArticleService {
    // common operation
    public Article get(String id);
    public List<Article> list();
    public Article create(ArticleVO articleVO);
    public long batchCreate(List<ArticleVO> articleVOs);
    public long update(ArticleVO articleVO);
    public long batchUpdate(List<ArticleVO> articleVOs);
    public long delete(String id);
    public long batchDelete(List<String> ids);

    // user operation
    public List<Article> listByUser(String uid, int index, int size);
    public Article getByUser(String uid, String aid);
    public Article createByUser(String uid, ArticleVO vo);
    public long updateByUser(String uid, ArticleVO vo);
    public long deleteByUser(String uid, String aid);
    public long batchDeleteByUser(String uid, List<String> aids);
}
