package org.unicome.oauth.security.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
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
    @Field("role_id")
    private String roleId;
    @Field("authority_id")
    private String authorityId;
    private Boolean enabled;
    private String remake;

    @PersistenceConstructor
    public RoleAuthority(String id, String roleId, String authorityId,  Boolean enabled, String remake) {
        super();
        this.id = id;
        this.roleId = roleId;
        this.authorityId = authorityId;
        this.enabled = enabled;
        this.remake = remake;
    }
}
