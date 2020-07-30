package org.unicome.oauth.security.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * 权限主表
 */
@Data
@Document(collection = "authority")
public class Authority implements GrantedAuthority {
    @Id
    private String id;
    private String name;
    private Boolean enabled;
    private String remark;

    @PersistenceConstructor
    public Authority(String id, String name, Boolean enabled, String remark) {
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
