package org.unicome.oauth.security.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 用户主表
 */
@Document(collection = "user")
public class User extends org.springframework.security.core.userdetails.User {

    @Id
    private String id;
    private String nickname;
    private String mobile;
    private String email;
    @Field("create_date")
    private Date createDate;
    @Field("update_date")
    private Date updateDate;

    @PersistenceConstructor
    public User(String id, String nickname,
                String mobile, String email, Date createDate, Date updateDate,
                String username, String password,
                Boolean enabled, Boolean accountNonExpired, Boolean credentialsNonExpired,
                Boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password,
                enabled == null? true: enabled,
                accountNonExpired == null? true : accountNonExpired,
                credentialsNonExpired == null? true : credentialsNonExpired,
                accountNonLocked == null? true : accountNonLocked,
                new ArrayList<GrantedAuthority>());
        this.id = id;
        this.nickname = nickname;
        this.mobile = mobile;
        this.email = email;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}