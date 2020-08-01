package org.unicome.oauth.security.constant;

import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.unicome.oauth.UnicomeOauthApplication;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UnicomeOauthApplication.class)
public class SecurityConstantsTest {

    @Autowired
    private SecurityConstants securityConstants;

    @Test
    public void grantTypes() {
        System.out.println(securityConstants.grantTypes());
    }
}