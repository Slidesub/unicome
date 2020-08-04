package org.unicome.oauth.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.unicome.oauth.security.domain.Group;
import org.unicome.oauth.security.domain.UserGroup;
import org.unicome.oauth.security.repository.UserGroupRepository;
import org.unicome.oauth.security.service.GroupService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("groupService")
public class GroupServiceImpl implements GroupService {

    private UserGroupRepository userGroupRepository;

    @Autowired
    @Qualifier("userGroupRepository")
    public void setUserGroupRepository(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }
    @Override
    public List<Group> listByUserId(String userId) {
        List<UserGroup> userGroupList = userGroupRepository.findByUserId(userId);
        if (!CollectionUtils.isEmpty(userGroupList)) {
            return userGroupList.parallelStream().map(userGroup -> userGroup.getGroup()).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
