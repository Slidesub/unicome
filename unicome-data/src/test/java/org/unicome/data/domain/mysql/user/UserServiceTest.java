package org.unicome.data.domain.mysql.user;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.unicome.data.UnicomeDataApplication;
import org.unicome.data.resource.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnicomeDataApplication.class)
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void getUserByUsername() {
//        String username = "gd";
//        User user = userService.getByUsername(username);
//        System.out.println(user.toString());
    }

    @Test
    void getUserByMobile() {
    }

    @Test
    void getUserByEmail() {
    }
}