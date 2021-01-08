package org.unicome.data.resource.service;

import org.unicome.data.domain.mysql.role.Role;

import java.util.List;

public interface RoleService {
    Role getById(int id);
    List<Role> listAll(int pageIndex, int pageSize);
    List<Role> listAllByUserId(int userId);
    List<Role> listAllByGroupId(int groupId);
}
