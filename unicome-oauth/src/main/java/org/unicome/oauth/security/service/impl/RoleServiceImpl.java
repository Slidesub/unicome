package org.unicome.oauth.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.unicome.oauth.security.domain.GroupRole;
import org.unicome.oauth.security.domain.Role;
import org.unicome.oauth.security.domain.UserRole;
import org.unicome.oauth.security.repository.GroupRoleRepository;
import org.unicome.oauth.security.repository.RoleRepository;
import org.unicome.oauth.security.repository.UserRoleRepository;
import org.unicome.oauth.security.service.RoleService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private UserRoleRepository userRoleRepository;
    private GroupRoleRepository groupRoleRepository;

    @Autowired
    @Qualifier("roleRepository")
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Autowired
    @Qualifier("groupRoleRepository")
    public void setGroupRoleRepository(GroupRoleRepository groupRoleRepository) {
        this.groupRoleRepository = groupRoleRepository;
    }

    @Override
    public List<Role> listByGroupIdIn(List<String> groupIds) {
        List<GroupRole> groupRoleList = groupRoleRepository.findByGroupIdIn(groupIds);
        if (!CollectionUtils.isEmpty(groupRoleList)) {
            return groupRoleList.parallelStream().map(groupRole -> groupRole.getRole()).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<Role> listByUserId(String userId) {
        List<UserRole> userRoleList = userRoleRepository.findByUserId(userId);
        if (!CollectionUtils.isEmpty(userRoleList)) {
            return userRoleList.parallelStream().map(userRole -> userRole.getRole()).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
