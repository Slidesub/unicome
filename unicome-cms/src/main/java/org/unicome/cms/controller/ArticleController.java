package org.unicome.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unicome.cms.service.ArticleService;
import org.unicome.cms.vo.ArticleVO;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 查询列表
     * @param index
     * @param size
     * @return
     */
    @GetMapping()
    public List<ArticleVO> list(@RequestParam(name="page", required=false, defaultValue="0") int index,
                                @RequestParam(name="per_page", required=false, defaultValue="10") int size) {
        return ArticleVO.convert2VOList(articleService.list(index, size));
    }

    /**
     * 查询详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ArticleVO get(@PathVariable String id) {
        return ArticleVO.convert(articleService.get(id));
    }
}
