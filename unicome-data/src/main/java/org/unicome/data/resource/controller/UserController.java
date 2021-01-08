package org.unicome.data.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unicome.data.domain.mysql.group.Group;
import org.unicome.data.resource.service.GroupService;
import org.unicome.data.domain.mysql.role.Role;
import org.unicome.data.resource.service.RoleService;
import org.unicome.data.domain.mysql.user.User;
import org.unicome.data.resource.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    GroupService groupService;
    @Autowired
    RoleService roleService;

//    @GetMapping("/users")
//    public List<User> listAll(@RequestParam(defaultValue = "1") int pageIndex,
//                              @RequestParam(defaultValue = "10") int pageSize) {
//        List<User> users = userService.listAll(pageIndex, pageSize);
//        return users;
//    }

    @GetMapping("/users")
    public User getByUsername(@RequestParam(defaultValue = "") String username,
            @RequestParam(defaultValue = "") String email,
            @RequestParam(defaultValue = "") String mobile) {
        if (!StringUtils.isEmpty(username)) {
            return userService.getByUsername(username);
        } else if (!StringUtils.isEmpty(email)) {
            return userService.getByEmail(email);
        } else if (!StringUtils.isEmpty(mobile)) {
            return userService.getByMobile(mobile);
        }
        return null;
    }

    @GetMapping("/users/{id}")
    public User getById(@PathVariable(required = true) int id) {
        User user = userService.getById(id);
        return user;
    }

    @GetMapping("/users/{id}/groups")
    public List<Group> listGroupByUserId(@PathVariable("id") int id) {
        List<Group> groups = groupService.listAllByUserId(id);
        return groups;
    }

    @GetMapping("/users/{id}/roles")
    public List<Role> listRoleByUserId(@PathVariable("id") int id) {
        List<Role> roles = roleService.listAllByUserId(id);
        return roles;
    }
}
