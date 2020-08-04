package org.unicome.oauth.security.service;

import org.unicome.oauth.security.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> listByGroupIdIn(List<String> groupIds);
    List<Role> listByUserId(String userId);
}
