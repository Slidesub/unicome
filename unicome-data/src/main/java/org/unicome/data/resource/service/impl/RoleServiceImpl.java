package org.unicome.data.resource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unicome.data.domain.mysql.role.Role;
import org.unicome.data.domain.mysql.role.RoleDao;
import org.unicome.data.resource.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role getById(int id) {
        Role role = roleDao.findById(id);
        return role;
    }

    @Override
    public List<Role> listAll(int pageIndex, int pageSize) {
        List<Role> roles = roleDao.findAll(pageIndex, pageSize);
        return roles;
    }

    @Override
    public List<Role> listAllByUserId(int userId) {
        List<Role> roles = roleDao.findAllByUserId(userId);
        return roles;
    }

    @Override
    public List<Role> listAllByGroupId(int groupId) {
        List<Role> roles = roleDao.findAllByGroupId(groupId);
        return roles;
    }
}
