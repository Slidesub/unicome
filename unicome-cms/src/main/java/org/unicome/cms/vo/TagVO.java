package org.unicome.cms.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.util.CollectionUtils;
import org.unicome.cms.po.Tag;

import java.util.ArrayList;
import java.util.List;

@Data
public class TagVO {
    @Id
    private String id;
    private String title;
    private String description;

    public static TagVO convert(Tag tag) {
        TagVO vo = new TagVO();
        vo.setId(tag.getId());
        vo.setTitle(tag.getTitle());
        vo.setDescription(tag.getDescription());
        return vo;
    }

    public static Tag convert(TagVO vo) {
        Tag tag = new Tag();
        tag.setTitle(vo.getTitle());
        tag.setDescription(vo.getDescription());
        return tag;
    }

    public static List<TagVO> convert2List(List<Tag> tags) {
        if (CollectionUtils.isEmpty(tags)) {
            return null;
        }
        List<TagVO> vos = new ArrayList<>();
        for (Tag tag : tags) {
            vos.add(convert(tag));
        }
        return vos;
    }
}
