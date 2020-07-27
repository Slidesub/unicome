package org.unicome.oauth.security.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CuzUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Resource
    private SessionRegistry sessionRegistry;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Authentication authentication = super.attemptAuthentication(request, response);
        // 认证通过后，注册session
        sessionRegistry.registerNewSession(request.getSession().getId(), authentication.getPrincipal());
        return authentication;
    }
}
