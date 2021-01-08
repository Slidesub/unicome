package org.unicome.data.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unicome.data.domain.mysql.group.Group;
import org.unicome.data.resource.service.GroupService;
import org.unicome.data.domain.mysql.role.Role;
import org.unicome.data.resource.service.RoleService;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    GroupService groupService;
    @Autowired
    RoleService roleService;

    @GetMapping("/group")
    public List<Group> listAll(@RequestParam(defaultValue = "1") int pageIndex,
                               @RequestParam(defaultValue = "10") int pageSize) {
        List<Group> groups = groupService.listAll(pageIndex, pageSize);
        return groups;
    }

    @GetMapping("/group/{id}")
    public Group findOne(@PathVariable(name = "id") int id) {
        Group group = groupService.getById(id);
        return group;
    }

    @GetMapping("/group/{id}/role")
    public List<Role> listRoleByGroupId(@PathVariable("id") int id) {
        List<Role> roles = roleService.listAllByGroupId(id);
        return roles;
    }
}
