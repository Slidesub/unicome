package org.unicome.oauth.security.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * 用户主表
 */
@Document(collection = "user")
@Data
public class User extends Base implements UserDetails {

    @Id
    private String id;
    private String username;
    private String nickname;
    private String mobile;
    private String email;
    private String password;
    private Boolean enabled;
    @Field("account_non_expired")
    private Boolean accountNonExpired;
    @Field("credentials_non_expired")
    private Boolean credentialsNonExpired;
    @Field("account_non_locked")
    private Boolean accountNonLocked;
    @Transient
    private List<GrantedAuthority> authorities = Collections.emptyList();

    @PersistenceConstructor
    public User(String id, String username, String nickname,
                String mobile, String email, String password,
                Boolean enabled, Boolean accountNonExpired, Boolean credentialsNonExpired, Boolean accountNonLocked,
                Date createDate, Date updateDate) {
        super(createDate, updateDate);
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.enabled = enabled == null ? false : enabled;
        this.accountNonExpired = accountNonExpired == null ? false : accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired == null ? false : credentialsNonExpired;
        this.accountNonLocked = accountNonLocked == null ? false : accountNonLocked;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities == null ?
                Collections.emptySet() : new ArrayList(AuthorityUtils.authorityListToSet(this.authorities));
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}