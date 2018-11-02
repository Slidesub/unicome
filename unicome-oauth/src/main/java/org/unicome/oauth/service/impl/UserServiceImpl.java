package org.unicome.oauth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.unicome.oauth.entity.User;
import org.unicome.oauth.repository.UserRepository;
import org.unicome.oauth.service.UserService;

@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);
            return user;
        } catch (Exception e) {
            log.error("", e);
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
    }

    @Override
    public User findByMobile(String mobile) {
        if (!StringUtils.isEmpty(mobile)) {
            return userRepository.findByMobile(mobile);
        }
        return null;
    }
}
