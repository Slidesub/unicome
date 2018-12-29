package org.unicome.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unicome.cms.service.ArticleService;
import org.unicome.cms.vo.ArticleVO;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    ArticleService articleService;

    /**
     * 查询列表
     * @param uid
     * @param index
     * @param size
     * @return
     */
    @GetMapping("/{uid}/articles")
    public List<ArticleVO> list(@PathVariable String uid,
                                @RequestParam(name="page", required=false, defaultValue="0") int index,
                                @RequestParam(name="per_page", required=false, defaultValue="10") int size) {
        return ArticleVO.convert2VOList(articleService.listByUser(uid, index, size));
    }

    /**
     * 查询详情
     * @param uid
     * @param aid
     * @return
     */
    @GetMapping("/{uid}/articles/{aid}")
    public ArticleVO get(@PathVariable String uid, @PathVariable String aid) {
        return ArticleVO.convert(articleService.getByUser(uid, aid));
    }

    /**
     * 创建
     * @param uid
     * @param vo
     * @return
     */
    @PostMapping("/{uid}/articles")
    public ArticleVO create(@PathVariable String uid, @RequestBody ArticleVO vo) {
        return ArticleVO.convert(articleService.createByUser(uid, vo));
    }

    /**
     * 更新
     * @param uid
     * @param vo
     * @return
     */
    @PutMapping("/{uid}/articles")
    public long update(@PathVariable String uid, @RequestBody ArticleVO vo) {
        return articleService.updateByUser(uid, vo);
    }

    /**
     * 删除
     * @param uid
     * @param aid
     * @return
     */
    @DeleteMapping("/{uid}/articles/{aid}")
    public long delete(@PathVariable String uid, @PathVariable String aid) {
        return articleService.deleteByUser(uid, aid);
    }

    /**
     * 批量创建
     * @param uid
     * @param vos
     * @return
     */
//    @PostMapping("/{uid}/articles")
//    public long batchCreate(@PathVariable String uid, @RequestBody List<ArticleVO> vos) {
//        return articleService.batchCreateByUser(uid, vos);
//    }

    /**
     * 批量更新
     * @param uid
     * @param vos
     * @return
     */
//    @PutMapping("/{uid}/articles")
//    public long batchUpdate(@PathVariable String uid, @RequestBody List<ArticleVO> vos) {
//        return articleService.batchUpdateByUser(uid, vos);
//    }

    /**
     * 批量删除
     * @param uid
     * @param aids
     * @return
     */
    @DeleteMapping("/{uid}/articles/")
    public long batchDelete(@PathVariable String uid, @RequestBody List<String> aids) {
        return articleService.batchDeleteByUser(uid, aids);
    }
}
