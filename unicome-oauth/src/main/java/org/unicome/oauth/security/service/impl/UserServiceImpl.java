package org.unicome.oauth.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.unicome.oauth.security.domain.*;
import org.unicome.oauth.security.repository.*;
import org.unicome.oauth.security.service.GroupService;
import org.unicome.oauth.security.service.RoleService;
import org.unicome.oauth.security.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    private final String delimiter = ",";

    private UserRepository userRepository;
    private RoleService roleService;
    private GroupService groupService;

    @Autowired
    @Qualifier("userRepository")
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    @Qualifier("roleService")
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    @Qualifier("groupService")
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            if (!StringUtils.isEmpty(username)) {
                User user = userRepository.findByUsername(username);
                // 获取用户角色
                if (null != user) {
                    List<GrantedAuthority> authorities = this.grantedAuthorities(user.getId());
                    if (!CollectionUtils.isEmpty(authorities)) {
                        user.setAuthorities(authorities);
                    }
                }
                return user;
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
        return null;
    }

    @Override
    public User loadUserByEmail(String email) throws UsernameNotFoundException {
        try {
            if (!StringUtils.isEmpty(email)) {
                User user = userRepository.findByEmail(email);
                // 获取用户角色
                if (null != user) {
                    List<GrantedAuthority> authorities = this.grantedAuthorities(user.getId());
                    if (!CollectionUtils.isEmpty(authorities)) {
                        user.setAuthorities(authorities);
                    }
                }
                return user;
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
        return null;
    }

    @Override
    public User loadUserByMobile(String mobile) throws UsernameNotFoundException {
        try {
            if (!StringUtils.isEmpty(mobile)) {
                User user = userRepository.findByMobile(mobile);
                // 获取用户角色
                if (null != user) {
                    List<GrantedAuthority> authorities = this.grantedAuthorities(user.getId());
                    if (!CollectionUtils.isEmpty(authorities)) {
                        user.setAuthorities(authorities);
                    }
                }
                return user;
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
        return null;
    }

    protected List<GrantedAuthority> grantedAuthorities(String userId) {
        if (!StringUtils.isEmpty(userId)) {
            List<Group> groupList = groupService.listByUserId(userId);
            if (!CollectionUtils.isEmpty(groupList)) {
                List<String> groupIdList = groupList.parallelStream().map(group -> group.getId()).collect(Collectors.toList());
                List<Role> userGroupRoleList = roleService.listByGroupIdIn(groupIdList);
                List<Role> userRolesList = roleService.listByUserId(userId);
                Set<Role> roleSet = new HashSet<>();
                roleSet.addAll(userGroupRoleList);
                roleSet.addAll(userRolesList);
                if (!CollectionUtils.isEmpty(roleSet)) {
                    String roleNames = roleSet.parallelStream().map(role -> role.getName()).collect(Collectors.joining(this.delimiter));
                    if(!StringUtils.isEmpty(roleNames)) {
                        return AuthorityUtils.commaSeparatedStringToAuthorityList(roleNames);
                    }
                }
            }
        }
        return Collections.emptyList();
    }
}
