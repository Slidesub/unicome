package org.unicome.oauth.security.client;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class ClientDetailsImpl implements ClientDetails {
    private String clientId;
    private String clientSecret;
    private Set<String> resourceIds;
    private Set<String> scopes;
    private Set<String> autoApproveScopes;
    private Set<String> authorizedGrantTypes;
    private Set<String> registeredRedirectUris;
    private Set<String> authorities;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private Map<String, Object> additionalInformation;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String clientId;
        private String clientSecret;
        private Set<String> resourceIds;
        private Set<String> scopes;
        private Set<String> autoApproveScopes;
        private Set<String> authorizedGrantTypes;
        private Set<String> registeredRedirectUris;
        private Set<String> authorities;
        private Integer accessTokenValiditySeconds;
        private Integer refreshTokenValiditySeconds;

        public ClientDetailsImpl build() {
            ClientDetailsImpl clientDetails =  new ClientDetailsImpl();
            clientDetails.setClientId(this.clientId);
            clientDetails.setClientSecret(this.clientSecret);
            clientDetails.setResourceIds(this.resourceIds);
            clientDetails.setScopes(this.scopes);
            clientDetails.setAutoApproveScopes(this.autoApproveScopes);
            clientDetails.setAuthorizedGrantTypes(this.authorizedGrantTypes);
            clientDetails.setRegisteredRedirectUris(this.registeredRedirectUris);
            clientDetails.setAuthorities(this.authorities);
            clientDetails.setAccessTokenValiditySeconds(this.accessTokenValiditySeconds);
            clientDetails.setRefreshTokenValiditySeconds(this.refreshTokenValiditySeconds);
            return clientDetails;
        }

        Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }
        Builder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }
        Builder resourceIds(Set<String> resourceId) {
            this.resourceIds = resourceIds;
            return this;
        }
        Builder scopes(Set<String> scopes) {
            this.scopes = scopes;
            return this;
        }
        Builder autoApproveScopes(Set<String> autoApproveScopes) {
            this.autoApproveScopes = autoApproveScopes;
            return this;
        }
        Builder authorizedGrantTypes(Set<String> authorizedGrantTypes) {
            this.authorizedGrantTypes = authorizedGrantTypes;
            return this;
        }
        Builder registeredRedirectUris(Set<String> registeredRedirectUris) {
            this.registeredRedirectUris = registeredRedirectUris;
            return this;
        }
        Builder authorities(Set<String> authorities) {
            this.authorities = authorities;
            return this;
        }
        Builder accessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
            this.accessTokenValiditySeconds = accessTokenValiditySeconds;
            return this;
        }
        Builder refreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
            this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
            return this;
        }
    }

    @Override
    public boolean isSecretRequired() {
        return !StringUtils.isEmpty(this.clientSecret);
    }

    @Override
    public boolean isScoped() {
        return !CollectionUtils.isEmpty(this.scopes);
    }

    @Override
    public Set<String> getScope() {
        return CollectionUtils.isEmpty(this.scopes) ? Collections.EMPTY_SET : this.scopes;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return CollectionUtils.isEmpty(this.authorizedGrantTypes) ? Collections.EMPTY_SET : this.authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return CollectionUtils.isEmpty(this.registeredRedirectUris) ? Collections.EMPTY_SET : this.registeredRedirectUris;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        if (!CollectionUtils.isEmpty(this.authorities)) {
            AuthorityUtils.createAuthorityList(this.authorities.parallelStream().collect(Collectors.joining(",")));
        }
        return Collections.EMPTY_SET;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        if (CollectionUtils.isEmpty(this.autoApproveScopes)) {
            return false;
        } else {
            Iterator var2 = this.autoApproveScopes.iterator();

            String auto;
            do {
                if (!var2.hasNext()) {
                    return false;
                }

                auto = (String)var2.next();
            } while(!auto.equals("true") && !scope.matches(auto));

            return true;
        }
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return this.additionalInformation;
    }
}
