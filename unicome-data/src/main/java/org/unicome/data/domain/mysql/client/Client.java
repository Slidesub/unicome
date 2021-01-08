package org.unicome.data.domain.mysql.client;

import lombok.*;
import org.unicome.data.domain.mysql.BaseMysql;
import org.unicome.data.resource.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;

@Data
@ToString(callSuper = true)
public class Client extends BaseMysql<Client> implements Serializable {
    protected String clientId;
    protected String clientSecret;
    protected String clientName;
    protected String remark;
    protected Boolean enabled;
    protected Set<String> authorities;
    protected Set<String> scopes;
    protected Set<String> resourceIds;
    protected Set<String> authorizedGrantTypes;
    protected Set<String> registeredRedirectUris;
    protected Set<String> autoApproveScopes;
    protected Integer accessTokenValiditySeconds;
    protected Integer refreshTokenValiditySeconds;
    protected Map<String, Object> additionalInformation = Collections.emptyMap();

    public void setAuthorities(String authorities) {
        this.authorities = CollectionUtils.separatedStringToSet(authorities);
    }

    public void setScopes(String scopes) {
        this.scopes = CollectionUtils.separatedStringToSet(scopes);
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = CollectionUtils.separatedStringToSet(resourceIds);
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = CollectionUtils.separatedStringToSet(authorizedGrantTypes);
    }

    public void setRegisteredRedirectUris(String registeredRedirectUris) {
        this.registeredRedirectUris = CollectionUtils.separatedStringToSet(registeredRedirectUris);
    }

    public void setAutoApproveScopes(String autoApproveScopes) {
        this.autoApproveScopes = CollectionUtils.separatedStringToSet(autoApproveScopes);
    }
}
