package org.unicome.oauth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.unicome.oauth.domain.User;
import org.unicome.oauth.repository.UserRepository;
import org.unicome.oauth.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        if (!StringUtils.isEmpty(username)) {
            return userRepository.findByUsername(username);
        }
        return null;
    }

    @Override
    public User findByMobile(String mobile) {
        if (!StringUtils.isEmpty(mobile)) {
            return userRepository.findByMobile(mobile);
        }
        return null;
    }
}
