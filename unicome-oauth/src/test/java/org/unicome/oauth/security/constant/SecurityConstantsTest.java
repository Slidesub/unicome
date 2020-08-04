package org.unicome.oauth.security.constant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.unicome.oauth.UnicomeOauthApplication;

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