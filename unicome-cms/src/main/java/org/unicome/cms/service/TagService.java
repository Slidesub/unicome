package org.unicome.cms.service;

import org.unicome.cms.po.Tag;
import org.unicome.cms.vo.TagVO;

import java.util.List;

public interface TagService {
    public List<Tag> list(int index, int size);
    public Tag get(String id);
    public long delete(String id);
    public Tag create(TagVO vo);
}
