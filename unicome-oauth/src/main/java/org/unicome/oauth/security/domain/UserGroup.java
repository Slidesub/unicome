package org.unicome.oauth.security.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 中间表：用户 - 组 表
 */
@Data
@Document(collection = "user_group")
public class UserGroup extends Base {

    @Id
    private String id;
    @DBRef
    @Field("user_id")
    private User user;
    @DBRef
    @Field("group_id")
    private Group group;
    private Boolean enabled;
    private String remark;

    @PersistenceConstructor
    public UserGroup(String id, User user, Group group, Boolean enabled, String remark, Date createDate, Date updateDate) {
        super(createDate, updateDate);
        this.id = id;
        this.user = user;
        this.group = group;
        this.enabled = enabled;
        this.remark = remark;
    }
}
