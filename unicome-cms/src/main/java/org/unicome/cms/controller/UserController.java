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

    @GetMapping("/{uid}/articles")
    public List<ArticleVO> list(@PathVariable String uid,
                                @RequestParam(required=false, defaultValue="0") int index,
                                @RequestParam(required=false, defaultValue="10") int size) {
        return ArticleVO.convert2VOList(articleService.listByUser(uid, index, size));
    }

    @GetMapping("/{uid}/articles/{aid}")
    public ArticleVO get(@PathVariable String uid, @PathVariable String aid) {
        return ArticleVO.convert2VO(articleService.getByUser(uid, aid));
    }

    @PostMapping("/{uid}/articles")
    public ArticleVO create(@PathVariable String uid, @RequestBody ArticleVO vo) {
        return ArticleVO.convert2VO(articleService.createByUser(uid, vo));
    }

    @PutMapping("/{uid}/articles")
    public long update(@PathVariable String uid, @RequestBody ArticleVO vo) {
        return articleService.updateByUser(uid, vo);
    }

    @DeleteMapping("/{uid}/articles/{aid}")
    public long delete(@PathVariable String uid, @PathVariable String aid) {
        return articleService.deleteByUser(uid, aid);
    }

    @DeleteMapping("/{uid}/articles/")
    public long delete(@PathVariable String uid, @RequestBody List<String> aids) {
        return articleService.batchDeleteByUser(uid, aids);
    }
}
