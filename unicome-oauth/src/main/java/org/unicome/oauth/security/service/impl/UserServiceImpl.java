package org.unicome.oauth.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.unicome.oauth.security.entity.*;
import org.unicome.oauth.security.repository.*;
import org.unicome.oauth.security.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    private final String delimiter = ",";

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserGroupRepository userGroupRepository;

    @Autowired
    GroupRoleRepository groupRoleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
//        try {
            User user = userRepository.findByUsername(username);
            if (null == user) throw new UsernameNotFoundException("user[] is not exists.");
//            Assert.notNull(user, "user is null");
            Set<Role> roles = new HashSet<>();
            List<UserGroup> userGroupList = userGroupRepository.findByUserId(user.getId());
            List<String> groupIds = userGroupList.stream().map(userGroup -> userGroup.getGroup().getId()).collect(Collectors.toList());
            List<GroupRole> groupRoleList = groupRoleRepository.findByGroupIdIn(groupIds);
            List<Role> roleList = groupRoleList.stream().map(groupRole -> groupRole.getRole()).collect(Collectors.toList());

            List<UserRole> userRoleList = userRoleRepository.findByUserId(user.getId());
            roleList.addAll(userRoleList.stream().map(userRole -> userRole.getRole()).collect(Collectors.toList()));
            roles.addAll(roleList);

            String roleNames = roles.parallelStream().map(role -> role.getName()).collect(Collectors.joining(this.delimiter));
            if(!StringUtils.isEmpty(roleNames)) {
                user.getAuthorities().addAll(AuthorityUtils.commaSeparatedStringToAuthorityList(roleNames));
            }
            return user;
//        } catch (UsernameNotFoundException e) {
//            log.error("", e);
//            throw e;
//        }
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
