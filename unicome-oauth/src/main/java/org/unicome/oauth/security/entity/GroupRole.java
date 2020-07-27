package org.unicome.oauth.security.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 中间表：组 - 角色 表
 */
@Data
@Document(collection = "group_role")
public class GroupRole extends Base {

    @Id
    private String id;
    @Field("group_id")
    private String groupId;
    @Field("role_id")
    private String roleId;
    private Boolean enabled;
    private String remake;

    @PersistenceConstructor
    public GroupRole(String id, String groupId, String roleId, Boolean enabled, String remake) {
        super();
        this.id = id;
        this.groupId = groupId;
        this.roleId = roleId;
        this.enabled = enabled;
        this.remake = remake;
    }
}
