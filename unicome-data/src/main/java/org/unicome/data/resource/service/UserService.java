package org.unicome.data.resource.service;

import org.unicome.data.domain.mysql.user.User;

import java.util.List;

public interface UserService {
    User getById(int id);
    List<User> listAll(int pageIndex, int pageSize);
    List<User> listAllByGroupId(int groupId);

    User getByUsername(String username);
    User getByMobile(String mobile);
    User getByEmail(String email);
}
