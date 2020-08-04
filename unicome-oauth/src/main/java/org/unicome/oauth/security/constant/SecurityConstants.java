package org.unicome.oauth.security.constant;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
@PropertySource("classpath:config/security.properties")
@ConfigurationProperties(prefix = "authentication")
@Getter
public class SecurityConstants {

    public static final String demo = "";
    @Value("${default.login-url:/sign_in}")
    private  String defaultLoginUrl;
    @Value("${default.login-page-url:/sign_in}")
    private  String defaultLoginPageUrl;
    @Value("${default.failure-url:/sign_in?error}")
    private  String defaultFailureUrl;

    @Value("${email.login-url:/email/sign_in}")
    private  String emailLoginUrl;
    @Value("${email.login-page-url:/email/sign_in}")
    private  String emailLoginPageUrl;
    @Value("${email.failure-url:/email/sign_in?error}")
    private  String emailFailureUrl;

    @Value("${mobile.login-url:/mobile/sign_in}")
    private  String mobileLoginUrl;
    @Value("${mobile.login-page-url:/mobile/sign_in}")
    private  String mobileLoginPageUrl;
    @Value("${mobile.failure-url:/mobile/sign_in?error}")
    private  String mobileFailureUrl;

    @Value("${sms.login-url:/sms/sign_in}")
    private  String smsLoginUrl;
    @Value("${sms.login-page-url:/sms/sign_in}")
    private  String smsLoginPageUrl;
    @Value("${sms.failure-url:/sms/sign_in?error}")
    private  String smsFailureUrl;

    @Value("${logout-url:/sign_out}")
    private  String logoutUrl;

    @Value("${session.maximum:1}")
    private  Integer sessionMaximum;
    @Value("${remember-me.timeout:60}")
    private  Integer rememberMeTimeout;

    @Getter(AccessLevel.NONE)
    @Value("${authentication.oauth2.grant-types:authentication_code,password,refresh_code,client_credentials}")
    private String grantTypes;

    public Set<String> grantTypes() {
        Set<String> grantTypeList = StringUtils.commaDelimitedListToSet(grantTypes);
        if (!grantTypeList.isEmpty()) {
            return grantTypeList;
        }
        return new HashSet<String>(Arrays.asList("authorization_code", "implicit", "refresh_token", "password", "client_credentials"));
    }

    public String[] whiteList() {
        return new String[] {
                this.getDefaultLoginUrl(),
                this.getEmailLoginUrl(),
                this.getMobileLoginUrl(),
                "/static/**",
                "/oauth/**",
                "/img/code"
        };
    }
}
