package org.unicome.oauth.security.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.thymeleaf.util.ArrayUtils;

import java.util.*;

@Document(collection = "client")
@Data
public class Client extends Base implements ClientDetails {

    @Id
    private String id;
    @Field("client_id")
    private String clientId;
    @Field("client_secret")
    private String clientSecret;
    @Field("client_name")
    private String clientName;
    private String remark;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String[] authorities;
    @Getter(AccessLevel.NONE)
    private String[] scopes;
    @Getter(AccessLevel.NONE)
    @Field("resource_ids")
    private String[] resourceIds;
    @Getter(AccessLevel.NONE)
    @Field("authorized_grant_types")
    private String[] authorizedGrantTypes;
    @Getter(AccessLevel.NONE)
    @Field("registered_redirect_uris")
    private String[] registeredRedirectUris;
    @Getter(AccessLevel.NONE)
    @Field("auto_approve_scopes")
    private String[] autoApproveScopes;
    @Field("access_token_validity_seconds")
    private Integer accessTokenValiditySeconds;
    @Field("refresh_token_validity_seconds")
    private Integer refreshTokenValiditySeconds;
    @Getter(AccessLevel.NONE)
    @Field("additional_information")
    private Map<String, Object> additionalInformation = Collections.emptyMap();

    @PersistenceConstructor
    public Client(String id, String clientId, String clientSecret, String clientName, String remark,
                  String[] authorities, String[] scopes,  String[] resourceIds, String[] authorizedGrantTypes,
                  String[] registeredRedirectUris,  String[] autoApproveScopes,
                  Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds,
                  Map<String, Object> additionalInformation, Date createDate, Date updateDate) {
        super(createDate, updateDate);
        this.id = id;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.clientName = clientName;
        this.remark = remark;
        this.authorities = authorities;
        this.scopes = scopes;
        this.resourceIds = resourceIds;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.registeredRedirectUris =registeredRedirectUris;
        this.autoApproveScopes = autoApproveScopes;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.additionalInformation = additionalInformation;
    }

    @Override
    public Set<String> getResourceIds() {
        return ArrayUtils.isEmpty(this.resourceIds) ?
                Collections.emptySet() : new LinkedHashSet<>(Arrays.asList(this.resourceIds));
    }

    @Override
    public boolean isSecretRequired() {
        return this.clientSecret != null;
    }

    @Override
    public boolean isScoped() {
        return !ArrayUtils.isEmpty(this.scopes);
    }

    @Override
    public Set<String> getScope() {
        return ArrayUtils.isEmpty(this.scopes) ?
                Collections.emptySet() : new LinkedHashSet<>(Arrays.asList(this.scopes));
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return ArrayUtils.isEmpty(this.authorizedGrantTypes) ?
                Collections.emptySet() : new LinkedHashSet<>(Arrays.asList(this.authorizedGrantTypes));
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return ArrayUtils.isEmpty(this.registeredRedirectUris) ?
                Collections.emptySet() : new LinkedHashSet<>(Arrays.asList(this.registeredRedirectUris));
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(this.authorities);
    }

    @Override
    public boolean isAutoApprove(String scope) {
        if (ArrayUtils.isEmpty(this.autoApproveScopes)) {
            return false;
        } else {
            Iterator var2 = Arrays.asList(this.autoApproveScopes).iterator();

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
        return Collections.unmodifiableMap(this.additionalInformation);
    }
}
