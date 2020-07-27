package org.unicome.oauth.security.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 中间表：用户-角色 表
 */
@Data
@Document(collection = "user_role")
public class UserRole extends Base {

    @Id
    private String id;
    @Field("user_id")
    private String userId;
    @Field("role_id")
    private String roleId;
    private Boolean enabled;
    private String remake;

    @PersistenceConstructor
    public UserRole(String id, String userId, String roleId, Boolean enabled, String remake) {
        super();
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
        this.enabled = enabled;
        this.remake = remake;
    }
}
