package org.unicome.oauth.security.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 中间表：用户 - 组 表
 */
@Data
@Document(collection = "user_group")
public class UserGroup extends Base {

    @Id
    private String id;
    @Field("user_id")
    private String userId;
    @Field("group_id")
    private String groupId;
    private Boolean enabled;
    private String remake;

    @PersistenceConstructor
    public UserGroup(String id, String userId, String groupId, Boolean enabled, String remake) {
        super();
        this.id = id;
        this.userId = userId;
        this.groupId = groupId;
        this.enabled = enabled;
        this.remake = remake;
    }
}
