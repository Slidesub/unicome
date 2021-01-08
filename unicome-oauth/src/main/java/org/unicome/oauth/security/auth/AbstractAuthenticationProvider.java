package org.unicome.oauth.security.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.util.Assert;

@Slf4j
public abstract class AbstractAuthenticationProvider implements AuthenticationProvider, InitializingBean, MessageSourceAware {

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private UserCache userCache = new NullUserCache();
    private boolean forcePrincipalAsString = false;
    protected boolean hideUserNotFoundExceptions = true;

    public AbstractAuthenticationProvider() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.userCache, "A user cache must be set");
        Assert.notNull(this.messages, "A message source must be set");
        this.doAfterPropertiesSet();
    }

    protected abstract UserDetails retrieveUser(String principal, Authentication authentication) throws AuthenticationException;

    protected abstract void additionalAuthenticationChecks(UserDetails userDetails, Authentication authentication) throws AuthenticationException;

    protected abstract Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user);

    protected abstract void doAfterPropertiesSet() throws Exception;

    protected abstract void onlySupports(Authentication authentication);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        onlySupports(authentication);
        String principal = authentication.getPrincipal() == null ? "NONE_PROVIDED" : authentication.getName();
        boolean cacheWasUsed = true;
        UserDetails user = this.userCache.getUserFromCache(principal);
        if (user == null) {
            cacheWasUsed = false;

            try {
                user = this.retrieveUser(principal, authentication);
            } catch (UsernameNotFoundException var6) {
                log.debug("UserDO ['" + principal + "'] not found");
                if (this.hideUserNotFoundExceptions) {
                    throw new BadCredentialsException(this.messages.getMessage("AbstractAuthenticationProvider.badCredentials", "Bad credentials"));
                }

                throw var6;
            }

            Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract");
        }

        try {
            this.preAuthenticationChecks(user);
            this.additionalAuthenticationChecks(user, authentication);
        } catch (AuthenticationException var7) {
            if (!cacheWasUsed) {
                throw var7;
            }

            cacheWasUsed = false;
            user = this.retrieveUser(principal, authentication);
            this.preAuthenticationChecks(user);
            this.additionalAuthenticationChecks(user, authentication);
        }

        this.postAuthenticationChecks(user);
        if (!cacheWasUsed) {
            this.userCache.putUserInCache(user);
        }

        Object principalToReturn = user;
        if (this.forcePrincipalAsString) {
            principalToReturn = user.getUsername();
        }

        return this.createSuccessAuthentication(principalToReturn, authentication, user);
    }

    public void preAuthenticationChecks(UserDetails user) {
        new UserDetailsChecker() {
            @Override
            public void check(UserDetails userDetails) {
                if (!user.isCredentialsNonExpired()) {
                    log.debug("UserDO account credentials is expired");
                    throw new CredentialsExpiredException(messages.getMessage("AbstractAuthenticationProvider.credentialsExpired", "UserDO credentials is expired"));
                }
            }
        };
    }

    public void postAuthenticationChecks(UserDetails user) {
        new UserDetailsChecker() {
            @Override
            public void check(UserDetails user) {
                if (!user.isAccountNonLocked()) {
                    log.debug("UserDO account is locked");
                    throw new LockedException(messages.getMessage("AbstractAuthenticationProvider.locked", "UserDO account is locked"));
                } else if (!user.isEnabled()) {
                    log.debug("UserDO account is disabled");
                    throw new DisabledException(messages.getMessage("AbstractAuthenticationProvider.disabled", "UserDO account is disabled"));
                } else if (!user.isAccountNonExpired()) {
                    log.debug("UserDO account is expired");
                    throw new AccountExpiredException(messages.getMessage("AbstractAuthenticationProvider.expired", "UserDO account is expired"));
                }
            }
        };
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    public UserCache getUserCache() {
        return userCache;
    }

    public void setUserCache(UserCache userCache) {
        this.userCache = userCache;
    }

    public boolean isForcePrincipalAsString() {
        return forcePrincipalAsString;
    }

    public void setForcePrincipalAsString(boolean forcePrincipalAsString) {
        this.forcePrincipalAsString = forcePrincipalAsString;
    }

    public boolean isHideUserNotFoundExceptions() {
        return hideUserNotFoundExceptions;
    }

    public void setHideUserNotFoundExceptions(boolean hideUserNotFoundExceptions) {
        this.hideUserNotFoundExceptions = hideUserNotFoundExceptions;
    }

}
