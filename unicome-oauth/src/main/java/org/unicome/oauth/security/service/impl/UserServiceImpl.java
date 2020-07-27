package org.unicome.oauth.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.unicome.oauth.security.entity.User;
import org.unicome.oauth.security.repository.UserRepository;
import org.unicome.oauth.security.service.UserService;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);
            return user;
        } catch (UsernameNotFoundException e) {
            log.error("", e);
            throw e;
        }
    }

    @Override
    public User loadUserByEmail(String email) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(email);
            return user;
        } catch (UsernameNotFoundException e) {
            log.error("", e);
            throw e;
        }
    }

    @Override
    public User loadUserByMobile(String mobile) throws UsernameNotFoundException {
        if (!StringUtils.isEmpty(mobile)) {
            User user = userRepository.findByMobile(mobile);
            return user;
        }
        return null;
    }
}
