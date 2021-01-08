package org.unicome.data.resource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unicome.data.domain.mysql.group.Group;
import org.unicome.data.domain.mysql.group.GroupDao;
import org.unicome.data.resource.service.GroupService;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override
    public Group getById(int id) {
        Group group = groupDao.findById(id);
        return group;
    }

    @Override
    public List<Group> listAll(int pageIndex, int pageSize) {
        List<Group> groups = groupDao.findAll(pageIndex, pageSize);
        return groups;
    }

    @Override
    public List<Group> listAllByUserId(int userId) {
        List<Group> groups = groupDao.findAllByUserId(userId);
        return groups;
    }
}