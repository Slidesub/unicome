package org.unicome.oauth.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.unicome.oauth.service.UserService;
import org.unicome.oauth.service.impl.UserServiceImpl;

@Slf4j
@Component
public class MobilePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    private PasswordEncoder passwordEncoder;

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private UserCache userCache = new NullUserCache();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(MobilePasswordAuthenticationToken.class, authentication,
                messages.getMessage(
                        "MobilePasswordAuthenticationProvider.onlySupports",
                        "Only MobilePasswordAuthenticationToken is supported"));
        MobilePasswordAuthenticationToken authenticationToken = (MobilePasswordAuthenticationToken) authentication;
        // Determine mobile
        String mobile = (authentication.getPrincipal() == null) ? "NONE_PROVIDED"
                : authentication.getName();


        boolean cacheWasUsed = true;
        UserDetails user = this.userCache.getUserFromCache(mobile);

        if (user == null) {
            cacheWasUsed = false;
            try {
                user = retrieveUser(mobile);
            }
            catch (Exception notFound) {
                log.debug("User '" + mobile + "' not found");
            }
        }

        MobilePasswordAuthenticationToken result = new MobilePasswordAuthenticationToken(mobile, authentication.getCredentials(), user.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (MobilePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }


    protected final UserDetails retrieveUser(String mobile)
            throws AuthenticationException {
        try {
            UserDetails loadedUser = this.userService.findByMobile(mobile);
            if (loadedUser == null) {
                throw new InternalAuthenticationServiceException(
                        "UserDetailsService returned null, which is an interface contract violation");
            }
            return loadedUser;
        }
        catch (InternalAuthenticationServiceException ex) {
            throw ex;
        }
        catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }
    }
}
