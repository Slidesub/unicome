package org.unicome.cms.service;

import org.unicome.cms.po.Article;
import org.unicome.cms.vo.ArticleVO;

import java.util.List;

public interface ArticleService {
//    Common Operation
    public List<Article> list(int index, int size);
    public Article get(String id);

//    User Opertation
    public List<Article> listByUser(String uid, int index, int size);
    public Article getByUser(String uid, String aid);
    public Article createByUser(String uid, ArticleVO vo);
    public long updateByUser(String uid, ArticleVO vo);
    public long deleteByUser(String uid, String aid);
    public long batchCreateByUser(String uid, List<ArticleVO> vos);
    public long batchUpdateByUser(String uid, List<ArticleVO> vos);
    public long batchDeleteByUser(String uid, List<String> aids);

//    Admin Operation
}
