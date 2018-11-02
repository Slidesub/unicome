package org.unicome.oauth.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Data
@Document(collection = "client")
public class Client extends Base implements ClientDetails, Serializable {

    // 默认字段
    private String clientId;
    private String clientSecret;
    private Set<String> resourceIds;
    private Set<String> authorizedGrantTypes;
    private Set<String> registeredRedirectUri;
    private Set<String> scope;
    private Collection<GrantedAuthority> authorities;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private Map<String, Object> additionalInformation;
    // 自定义字段
    @Id
    private String id;
    private String clientName;
    private String description;

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
            // matched
            // all scopes autoapproved
            if (auto.matches(scope) || "true".equals(auto)) {
                return true;
            }
        }
        return false;
    }
}
