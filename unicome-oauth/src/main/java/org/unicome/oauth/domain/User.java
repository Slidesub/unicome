package org.unicome.oauth.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@Document(collection = "user")
public class User implements UserDetails, Serializable {

//    默认字段
    private String username;
    private String password;
    private Collection<GrantedAuthority> authorities;
//    自定义字段
    private String userId;
    private String mobile;
    private Date expiredDate;
    private Date createDate;
    private Date updateDate;



    // 判断账户是否过期
    @Override
    public boolean isAccountNonExpired() {
        if (null == expiredDate || expiredDate.after(new Date())) {
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
}
