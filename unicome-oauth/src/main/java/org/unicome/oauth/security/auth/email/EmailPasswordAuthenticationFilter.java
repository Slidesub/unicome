package org.unicome.oauth.security.auth.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import org.unicome.oauth.security.constant.SecurityConsts;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmailPasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_EMAIL_KEY = "email";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

    private String emailParameter = "email";
    private String passwordParameter = "password";
    private boolean postOnly = true;

    @Autowired
    SecurityConsts securityConsts;

    /***
     * constructor
     * set properties
     * postConstruct
     * afterPropertiesSet
     * initMethod
     */
    public EmailPasswordAuthenticationFilter() {
        super("/login");
    }

    @PostConstruct
    protected void filterProcessesUrl() {
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(securityConsts.getEmailLoginUrl(), "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String email = this.obtain(request, this.emailParameter);
            String password = this.obtain(request, this.passwordParameter);
            EmailPasswordAuthenticationToken authRequest = new EmailPasswordAuthenticationToken(email.trim(), password);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    protected String obtain(HttpServletRequest request, String parameter) {
        String value = request.getParameter(parameter);
        if (value == null) {
            return "";
        }
        return value;
    }

    protected void setDetails(HttpServletRequest request, EmailPasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public String getEmailParameter() {
        return emailParameter;
    }

    public void setEmailParameter(String emailParameter) {
        Assert.hasText(emailParameter, "Email parameter must not be empty or null");
        this.emailParameter = emailParameter;
    }

    public String getPasswordParameter() {
        return passwordParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }

    public boolean isPostOnly() {
        return postOnly;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
