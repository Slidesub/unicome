package org.unicome.oauth.security.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 中间表：角色 - 权限 表
 */
@Data
@Document(collection = "role_authority")
public class RoleAuthority extends Base {

    @Id
    private String id;
    @DBRef
    @Field("role_id")
    private Role role;
    @DBRef
    @Field("authority_id")
    private Authority authority;
    private Boolean enabled;
    private String remark;

    @PersistenceConstructor
    public RoleAuthority(String id, Role role, Authority authority,  Boolean enabled, String remark) {
        super();
        this.id = id;
        this.role = role;
        this.authority = authority;
        this.enabled = enabled;
        this.remark = remark;
    }
}
