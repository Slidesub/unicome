package org.unicome.oauth.service;

import org.unicome.oauth.domain.User;

public interface UserService {
    User findByUsername(String username);
    User findByMobile(String mobile);
}
