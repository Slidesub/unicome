package org.unicome.oauth.security.auth.sms;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class SmsAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    public SmsAuthenticationFailureHandler() {
        super();
    }

    public SmsAuthenticationFailureHandler(String failureUrl) {
        super(failureUrl);
    }
}
