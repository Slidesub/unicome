package org.unicome.oauth.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import org.unicome.oauth.security.MobilePasswordAuthenticationToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MobilePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String mobileParameter = "mobile";
    private String passwordParameter = "password";
    private boolean postOnly = true;

    public MobilePasswordAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login/mobile", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        // 手机号-密码登陆
        String mobile = obtainPatameter(request, mobileParameter);
        String password = obtainPatameter(request, passwordParameter);

        MobilePasswordAuthenticationToken authToken = new MobilePasswordAuthenticationToken(mobile.trim(), password);
        setDetails(request, authToken);

        return this.getAuthenticationManager().authenticate(authToken);
    }

    protected String obtainPatameter(HttpServletRequest request, String name) {
        String param = request.getParameter(name);
        if (param == null) {
            return "";
        }
        return param;
    }

    protected void setDetails(HttpServletRequest request, MobilePasswordAuthenticationToken authToken) {
        authToken.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setUsernameParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "Mobile parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getMobileParameter() {
        return mobileParameter;
    }

    public final String getPasswordParameter() {
        return passwordParameter;
    }
}
