package org.unicome.oauth.security.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.Set;

/**
 * 角色主表
 */
@Data
@Document(collection = "role")
public class Role extends Base implements GrantedAuthority {

    @Id
    private String id;
    private String name; // ROLE_
    private Boolean enabled;
    private String remark;

    @Transient
    private Set<Authority> authorities;

    @PersistenceConstructor
    public Role(String id, String name, Boolean enabled, String remark, Date createDate, Date updateDate) {
        super(createDate, updateDate);
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.remark = remark;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
