package org.unicome.oauth.security.user;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@ToString
@Data
public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
//        Set<String> authorities = new HashSet<String>();
//        if (!CollectionUtils.isEmpty(this.user.getRoles())) {
//            this.user.getRoles().stream().parallel().forEach(role -> {
//                authorities.add(role.getName());
//                if (!CollectionUtils.isEmpty(role.getAuthorities())) {
//                    role.getAuthorities().stream().parallel().forEach(authority -> authorities.add(authority.getName()));
//                }
//            });
//        }
//
//        if (!CollectionUtils.isEmpty(this.user.getGroups())) {
//            this.user.getGroups().stream().parallel().forEach(group -> {
//                if (!CollectionUtils.isEmpty(group.getRoles())) {
//                    group.getRoles().stream().parallel().forEach(role -> {
//                        authorities.add(role.getName());
//                        if (!CollectionUtils.isEmpty(role.getAuthorities())) {
//                            role.getAuthorities().stream().parallel().forEach(authority -> authorities.add(authority.getName()));
//                        }
//                    });
//                }
//            });
//        }
//        return AuthorityUtils.createAuthorityList(authorities.toArray(new String[authorities.size()]));
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String password;
        private Boolean accountNonExpired;
        private Boolean accountNonLocked;
        private Boolean credentialsNonExpired;
        private Boolean enabled;
        private Collection<? extends GrantedAuthority> authorities;

        public UserDetailsImpl build() {
            UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
            userDetailsImpl.setUsername(this.username);
            userDetailsImpl.setPassword(this.password);
            userDetailsImpl.setAccountNonExpired(this.accountNonExpired);
            userDetailsImpl.setAccountNonLocked(this.accountNonLocked);
            userDetailsImpl.setCredentialsNonExpired(this.credentialsNonExpired);
            userDetailsImpl.setEnabled(this.enabled);
            return userDetailsImpl;
        }
        public Builder username(String username) {
            this.username = username;
            return this;
        }
        public Builder passsword(String password) {
            this.password = password;
            return this;
        }
        public Builder accountNonExpired(Boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
            return this;
        }
        public Builder accountNonLocked(Boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
            return this;
        }
        public Builder credentialsNonExpired(Boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
            return this;
        }
        public Builder enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }
    }

}
