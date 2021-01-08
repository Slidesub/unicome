package org.unicome.data.resource.service;

import org.unicome.data.domain.mysql.group.Group;

import java.util.List;

public interface GroupService {
    Group getById(int id);
    List<Group> listAll(int pageIndex, int pageSize);
    List<Group> listAllByUserId(int userId);
}
