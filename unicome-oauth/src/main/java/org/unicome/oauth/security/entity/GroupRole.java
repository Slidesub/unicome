package org.unicome.oauth.security.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
    @DBRef
    @Field("group_id") // 引用了另一个文档，该字段存储引用文档的 _id（ObjectId）
    private Group group;
    @DBRef
    @Field("role_id")
    private Role role;
    private Boolean enabled;
    private String remake;

    @PersistenceConstructor
    public GroupRole(String id, Group group, Role role, Boolean enabled, String remake) {
        super();
        this.id = id;
        this.group = group;
        this.role = role;
        this.enabled = enabled;
        this.remake = remake;
    }
}
