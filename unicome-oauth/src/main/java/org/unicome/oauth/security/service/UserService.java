package org.unicome.oauth.security.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.unicome.oauth.security.domain.User;

public interface UserService {
    User loadUserByUsername(String username) throws UsernameNotFoundException;
    User loadUserByEmail(String email) throws UsernameNotFoundException;;
    User loadUserByMobile(String mobile) throws UsernameNotFoundException;
}
