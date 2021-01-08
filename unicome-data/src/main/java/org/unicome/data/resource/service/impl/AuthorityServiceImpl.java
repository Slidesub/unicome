package org.unicome.data.resource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unicome.data.domain.mysql.authority.Authority;
import org.unicome.data.domain.mysql.authority.AuthorityDao;
import org.unicome.data.resource.service.AuthorityService;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public Authority getById(int id) {
        Authority authority = authorityDao.findById(id);
        return authority;
    }

    @Override
    public List<Authority> listAll(int pageIndex, int pageSize) {
        List<Authority> authorities = authorityDao.findAll(pageIndex, pageSize);
        return authorities;
    }

    @Override
    public List<Authority> listAllByRoleId(int roleId) {
        List<Authority> authorities = authorityDao.findAllByRoleId(roleId);
        return authorities;
    }
}
