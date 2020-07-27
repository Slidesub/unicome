package org.unicome.oauth.security.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "group")
public class Group extends Base {

    @Id
    private String id;
    private String name;
    private Boolean enabled;
    private String remake;

    private Set<Role> roles; // 组拥有的角色

    @PersistenceConstructor
    public Group(String id, String name, Boolean enabled, String remake) {
        super();
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.remake = remake;
    }
}
