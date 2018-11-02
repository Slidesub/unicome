package org.unicome.oauth.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

@Slf4j
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
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
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

    private SortedSet<Authority> sortAuthorities(Collection<Authority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<Authority> sortedAuthorities = new TreeSet<>(new AuthorityComparator());
        for (Authority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority,
                    "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }
        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>,
            Serializable {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof User) {
            return id.equals(((User) rhs).id);
        }
        return false;
    }
}