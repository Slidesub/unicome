package org.unicome.data.domain.mysql.role;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.unicome.data.UnicomeDataApplication;
import org.unicome.data.resource.service.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnicomeDataApplication.class)
class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    void getById() {
//        int id = 1;
//        Role role = roleService.getById(id);
//        System.out.println(role.toString());
    }

    @Test
    void listAll() {
    }

    @Test
    void listAllByUserId() {
    }

    @Test
    void listAllByGroupId() {
    }
}