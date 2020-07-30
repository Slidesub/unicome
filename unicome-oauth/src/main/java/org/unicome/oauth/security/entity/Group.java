package org.unicome.oauth.security.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
/**
 * 组主表
 */
@Data
@Document(collection = "group")
public class Group extends Base {

    @Id
    private String id;
    private String name;
    private Boolean enabled;
    private String remark;

    private Set<Role> roles; // 组拥有的角色

    @PersistenceConstructor
    public Group(String id, String name, Boolean enabled, String remark) {
        super();
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.remark = remark;
    }
}
