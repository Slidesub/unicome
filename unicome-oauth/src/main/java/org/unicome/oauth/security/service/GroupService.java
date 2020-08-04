package org.unicome.oauth.security.service;

import org.unicome.oauth.security.domain.Group;

import java.util.List;

public interface GroupService {
    List<Group> listByUserId(String userId);
}
