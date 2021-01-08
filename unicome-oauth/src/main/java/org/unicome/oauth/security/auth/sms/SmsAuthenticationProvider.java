package org.unicome.oauth.security.auth.sms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.util.Assert;

public class SmsAuthenticationProvider implements AuthenticationProvider, InitializingBean, MessageSourceAware {
    protected final Log logger = LogFactory.getLog(this.getClass());
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private UserCache userCache = new NullUserCache();
    private boolean forcePrincipalAsString = false;
    protected boolean hideUserNotFoundExceptions = true;
    private UserDetailsChecker preAuthenticationChecks = new SmsAuthenticationProvider.DefaultPreAuthenticationChecks();
    private UserDetailsChecker postAuthenticationChecks = new SmsAuthenticationProvider.DefaultPostAuthenticationChecks();
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    private UserDetailsService userDetailsService;

    public SmsAuthenticationProvider() {
    }

    protected void additionalAuthenticationChecks(UserDetails var1, SmsAuthenticationToken var2) throws AuthenticationException {

    }

    @Override
    public final void afterPropertiesSet() throws Exception {
        Assert.notNull(this.userCache, "A user cache must be set");
        Assert.notNull(this.messages, "A message source must be set");
        Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(SmsAuthenticationToken.class, authentication, this.messages.getMessage("SmsAuthenticationProvider.onlySupports", "Only SmsAuthenticationToken is supported"));
        String username = authentication.getPrincipal() == null ? "NONE_PROVIDED" : authentication.getName();
        boolean cacheWasUsed = true;
        UserDetails user = this.userCache.getUserFromCache(username);
        if (user == null) {
            cacheWasUsed = false;

            try {
                user = this.retrieveUser(username, (SmsAuthenticationToken)authentication);
            } catch (UsernameNotFoundException var6) {
                this.logger.debug("UserDO '" + username + "' not found");
                if (this.hideUserNotFoundExceptions) {
                    throw new BadCredentialsException(this.messages.getMessage("SmsAuthenticationProvider.badCredentials", "Bad credentials"));
                }

                throw var6;
            }

            Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract");
        }

        try {
            this.preAuthenticationChecks.check(user);
            this.additionalAuthenticationChecks(user, (SmsAuthenticationToken)authentication);
        } catch (AuthenticationException var7) {
            if (!cacheWasUsed) {
                throw var7;
            }

            cacheWasUsed = false;
            user = this.retrieveUser(username, (SmsAuthenticationToken)authentication);
            this.preAuthenticationChecks.check(user);
            this.additionalAuthenticationChecks(user, (SmsAuthenticationToken)authentication);
        }

        this.postAuthenticationChecks.check(user);
        if (!cacheWasUsed) {
            this.userCache.putUserInCache(user);
        }

        Object principalToReturn = user;
        if (this.forcePrincipalAsString) {
            principalToReturn = user.getUsername();
        }

        return this.createSuccessAuthentication(principalToReturn, authentication, user);
    }

    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        SmsAuthenticationToken result = new SmsAuthenticationToken(principal, this.authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());
        return result;
    }

    public UserCache getUserCache() {
        return this.userCache;
    }

    public boolean isForcePrincipalAsString() {
        return this.forcePrincipalAsString;
    }

    public boolean isHideUserNotFoundExceptions() {
        return this.hideUserNotFoundExceptions;
    }

    protected UserDetails retrieveUser(String username, SmsAuthenticationToken authentication) throws AuthenticationException {
        this.prepareTimingAttackProtection();

        try {
            UserDetails loadedUser = this.getUserDetailsService().loadUserByUsername(username);
            if (loadedUser == null) {
                throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
            } else {
                return loadedUser;
            }
        } catch (UsernameNotFoundException var4) {
            this.mitigateAgainstTimingAttack(authentication);
            throw var4;
        } catch (InternalAuthenticationServiceException var5) {
            throw var5;
        } catch (Exception var6) {
            throw new InternalAuthenticationServiceException(var6.getMessage(), var6);
        }
    }

    private void prepareTimingAttackProtection() {
//        if (this.userNotFoundEncodedPassword == null) {
//            this.userNotFoundEncodedPassword = this.passwordEncoder.encode("userNotFoundPassword");
//        }
    }

    private void mitigateAgainstTimingAttack(SmsAuthenticationToken authentication) {
        if (authentication.getCredentials() != null) {
            String presentedPassword = authentication.getCredentials().toString();
//            this.passwordEncoder.matches(presentedPassword, this.userNotFoundEncodedPassword);
        }

    }

    public void setForcePrincipalAsString(boolean forcePrincipalAsString) {
        this.forcePrincipalAsString = forcePrincipalAsString;
    }

    public void setHideUserNotFoundExceptions(boolean hideUserNotFoundExceptions) {
        this.hideUserNotFoundExceptions = hideUserNotFoundExceptions;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    public void setUserCache(UserCache userCache) {
        this.userCache = userCache;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }

    protected UserDetailsChecker getPreAuthenticationChecks() {
        return this.preAuthenticationChecks;
    }

    public void setPreAuthenticationChecks(UserDetailsChecker preAuthenticationChecks) {
        this.preAuthenticationChecks = preAuthenticationChecks;
    }

    protected UserDetailsChecker getPostAuthenticationChecks() {
        return this.postAuthenticationChecks;
    }

    public void setPostAuthenticationChecks(UserDetailsChecker postAuthenticationChecks) {
        this.postAuthenticationChecks = postAuthenticationChecks;
    }

    public void setAuthoritiesMapper(GrantedAuthoritiesMapper authoritiesMapper) {
        this.authoritiesMapper = authoritiesMapper;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    protected UserDetailsService getUserDetailsService() {
        return this.userDetailsService;
    }

    private class DefaultPostAuthenticationChecks implements UserDetailsChecker {
        private DefaultPostAuthenticationChecks() {
        }

        public void check(UserDetails user) {
            if (!user.isCredentialsNonExpired()) {
                SmsAuthenticationProvider.this.logger.debug("UserDO account credentials have expired");
                throw new CredentialsExpiredException(SmsAuthenticationProvider.this.messages.getMessage("SmsAuthenticationProvider.credentialsExpired", "UserDO credentials have expired"));
            }
        }
    }

    private class DefaultPreAuthenticationChecks implements UserDetailsChecker {
        private DefaultPreAuthenticationChecks() {
        }

        public void check(UserDetails user) {
            if (!user.isAccountNonLocked()) {
                SmsAuthenticationProvider.this.logger.debug("UserDO account is locked");
                throw new LockedException(SmsAuthenticationProvider.this.messages.getMessage("SmsAuthenticationProvider.locked", "UserDO account is locked"));
            } else if (!user.isEnabled()) {
                SmsAuthenticationProvider.this.logger.debug("UserDO account is disabled");
                throw new DisabledException(SmsAuthenticationProvider.this.messages.getMessage("SmsAuthenticationProvider.disabled", "UserDO is disabled"));
            } else if (!user.isAccountNonExpired()) {
                SmsAuthenticationProvider.this.logger.debug("UserDO account is expired");
                throw new AccountExpiredException(SmsAuthenticationProvider.this.messages.getMessage("SmsAuthenticationProvider.expired", "UserDO account has expired"));
            }
        }
    }
}
