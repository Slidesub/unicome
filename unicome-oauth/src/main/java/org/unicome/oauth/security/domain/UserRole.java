package org.unicome.oauth.security.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * 中间表：用户-角色 表
 */
@Data
@Document(collection = "user_role")
public class UserRole extends Base {

    @Id
    private String id;
    @DBRef
    @Field("user_id")
    private User user;
    @DBRef
    @Field("role_id")
    private Role role;
    private Boolean enabled;
    private String remark;

    @PersistenceConstructor
    public UserRole(String id, User user, Role role, Boolean enabled, String remark, Date createDate, Date updateDate) {
        super(createDate, updateDate);
        this.id = id;
        this.user = user;
        this.role = role;
        this.enabled = enabled;
        this.remark = remark;
    }
}
