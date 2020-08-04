package org.unicome.oauth.security.service;

import org.unicome.oauth.security.domain.Authority;

import java.util.List;

public interface AuthorityService {

    List<Authority> listByRoleIdIn(List<String> roleIds);
}
