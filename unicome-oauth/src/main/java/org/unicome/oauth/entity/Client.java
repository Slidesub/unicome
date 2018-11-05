package org.unicome.oauth.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.unicome.oauth.util.AuthorityComparator;

import java.io.Serializable;
import java.util.*;

@Data
@Document(collection = "client")
public class Client extends Base implements ClientDetails, Serializable {

    @Id
    private String id;
    private String clientId; // origin
    private String clientSecret; // origin
    private String clientName;
    private String description;
    private Set<String> resourceIds; // origin
    private Set<String> authorizedGrantTypes; // origin
    private Set<String> registeredRedirectUri; // origin
    private Set<String> scope; // origin
    private Integer accessTokenValiditySeconds; // origin
    private Integer refreshTokenValiditySeconds; // origin
    private Map<String, Object> additionalInformation; // origin
    @DBRef
    private Set<Authority> authorities;

    @PersistenceConstructor
    public Client(String id,
                  String clientId,
                  String clientSecret,
                  String clientName,
                  String description,
                  Set<String> resourceIds,
                  Set<String> authorizedGrantTypes,
                  Set<String> registeredRedirectUri,
                  Set<String> scope,
                  Integer accessTokenValiditySeconds,
                  Integer refreshTokenValiditySeconds,
                  Set<Authority> authorities) {
        this.id = id;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientName = clientName;
        this.description = description;
        this.resourceIds = resourceIds;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.registeredRedirectUri = registeredRedirectUri;
        this.scope = scope;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.authorities = Collections.unmodifiableSet(AuthorityComparator.sortAuthorities(authorities));
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(new AuthorityComparator());
        for (GrantedAuthority grantedAuthority : this.authorities) {
            sortedAuthorities.add(grantedAuthority);
        }
        return sortedAuthorities;
    }

    @Override
    public boolean isSecretRequired() {
        return null != this.clientSecret;
    }

    @Override
    public boolean isScoped() {
        return !CollectionUtils.isEmpty(this.scope);
    }

    @Override
    public boolean isAutoApprove(String scope) {
        if (CollectionUtils.isEmpty(this.scope)) {
            return false;
        }
        for (String auto : this.scope) {
            // matched/all scopes autoapproved
            if (auto.matches(scope) || "true".equals(auto)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object clt) {
        if (clt instanceof Client) {
            return id.equals(((Client) clt).id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
