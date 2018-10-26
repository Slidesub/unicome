package org.unicome.oauth.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.unicome.oauth.domain.User;
import org.unicome.oauth.service.UserService;

public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       try {
           User user = userService.findByMobile(username);
           return null;
       } catch (Exception e) {
           throw new UsernameNotFoundException(e.getMessage(), e);
       }
    }
}
