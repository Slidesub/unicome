package org.unicome.data.resource.service;

import org.unicome.data.domain.mysql.authority.Authority;

import java.util.List;

public interface AuthorityService {
    Authority getById(int id);
    List<Authority> listAll(int pageIndex, int pageSize);
    List<Authority> listAllByRoleId(int roleId);
}
