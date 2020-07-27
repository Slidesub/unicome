package org.unicome.oauth.security.constant;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("classpath:config/security.properties")
@ConfigurationProperties(prefix = "authentication")
@Data
public class SecurityConsts {

    @Value("${default.login-url:/sign_in}")
    public  String defaultLoginUrl;
    @Value("${default.login-page-url:/sign_in}")
    public  String defaultLoginPageUrl;
    @Value("${default.failure-url:/sign_in?error}")
    public  String defaultFailureUrl;


    @Value("${email.login-url:/email/sign_in}")
    public  String emailLoginUrl;
    @Value("${email.login-page-url:/email/sign_in}")
    public  String emailLoginPageUrl;
    @Value("${email.failure-url:/email/sign_in?error}")
    public  String emailFailureUrl;

    @Value("${mobile.login-url:/mobile/sign_in}")
    public  String mobileLoginUrl;
    @Value("${mobile.login-page-url:/mobile/sign_in}")
    public  String mobileLoginPageUrl;
    @Value("${mobile.failure-url:/mobile/sign_in?error}")
    public  String mobileFailureUrl;

    @Value("${sms.login-url:/sms/sign_in}")
    public  String smsLoginUrl;
    @Value("${sms.login-page-url:/sms/sign_in}")
    public  String smsLoginPageUrl;
    @Value("${sms.failure-url:/sms/sign_in?error}")
    public  String smsFailureUrl;

    @Value("${logout-url:/sign_out}")
    public  String logoutUrl;

    @Value("${session.maximum:1}")
    public  Integer sessionMaximum;
    @Value("${remember-me.timeout:60}")
    public  Integer rememberMeTimeout;


    public String[] whiteList() {
        return new String[] {
                this.getDefaultLoginUrl(),
                this.getEmailLoginUrl(),
                this.getMobileLoginUrl(),
                "/oauth/token",
                "/oauth/token_key",
                "/oauth/check_token",
                "/oauth/confirm_access",
                "/oauth/authorize",
                "/img/code"
        };
    }
}
