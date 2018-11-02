package org.unicome.oauth.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.unicome.oauth.entity.User;

public interface UserService extends UserDetailsService  {
    User findByMobile(String mobile) throws UsernameNotFoundException;;
}
