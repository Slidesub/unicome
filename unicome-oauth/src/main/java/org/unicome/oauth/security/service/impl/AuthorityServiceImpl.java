package org.unicome.oauth.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.unicome.oauth.security.domain.Authority;
import org.unicome.oauth.security.domain.RoleAuthority;
import org.unicome.oauth.security.repository.RoleAuthorityRepository;
import org.unicome.oauth.security.service.AuthorityService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {

    private RoleAuthorityRepository roleAuthorityRepository;

    @Autowired
    @Qualifier("roleAuthorityRepository")
    public void setRoleAuthorityRepository(RoleAuthorityRepository roleAuthorityRepository) {
        this.roleAuthorityRepository = roleAuthorityRepository;
    }

    @Override
    public List<Authority> listByRoleIdIn(List<String> roleIds) {
        List<RoleAuthority> roleAuthorityList = roleAuthorityRepository.findByRoleIdIn(roleIds);
        if (!CollectionUtils.isEmpty(roleAuthorityList)) {
            return roleAuthorityList.parallelStream().map(roleAuthority -> roleAuthority.getAuthority()).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}

