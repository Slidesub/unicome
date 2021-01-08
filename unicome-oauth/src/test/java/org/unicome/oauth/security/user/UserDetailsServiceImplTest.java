package org.unicome.oauth.security.user;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.unicome.oauth.UnicomeOauthApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnicomeOauthApplication.class)
class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Test
    void loadUserByUsername() {
        userDetailsService.loadUserBy("username", "gd");
    }
}