package org.unicome.oauth.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.unicome.oauth.util.AuthorityComparator;

import java.util.*;

@Data
@Document(collection = "user")
public class User extends Base implements UserDetails, CredentialsContainer {

    @Id
    private String id;
    private String userId;
    private String mobile;
    private String password; // origin
    private String username; // origin
    private Boolean enabled; // origin
    private Date expireDate;
    @DBRef
    private Set<Authority> authorities;

    @PersistenceConstructor
    public User(String id,
                String userId,
                String mobile,
                String username,
                String password,
                boolean enabled,
                Date expireDate,
                Set<Authority> authorities) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.mobile = mobile;
        this.password = password;
        this.enabled = enabled;
        this.expireDate = expireDate;
        this.authorities = Collections.unmodifiableSet(AuthorityComparator.sortAuthorities(authorities));
    }

    private static SortedSet<Authority> sortAuthorities(Collection<Authority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<Authority> sortedAuthorities = new TreeSet<>(new AuthorityComparator());
        for (Authority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority,
                    "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }
        return sortedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        if (null == expireDate || expireDate.after(new Date())) {
            return true;// not expired
        }
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (isAccountNonExpired()) {
            return true; // not locked
        }
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if (!StringUtils.isEmpty(password)) {
            return true; // not expired
        }
        return false;
    }

    @Override
    public boolean isEnabled() {
        if (isAccountNonExpired() && isCredentialsNonExpired()) {
            return true; // enabled
        }
        return false;
    }

    public void eraseCredentials() {
        password = null;
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof User) {
            return id.equals(((User) rhs).id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}