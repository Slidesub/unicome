package org.unicome.oauth.security.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Data
@Document(collection = "role")
public class Role extends Base implements GrantedAuthority {

    @Id
    private String id;
    private String name; // ROLE_
    private Boolean enabled;
    private String remake;

    private Set<Authority> authorities; // 角色拥有的权限

    @PersistenceConstructor
    public Role(String id, String name, Boolean enabled, String remake) {
        super();
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.remake = remake;
    }

    @Override
    public String getAuthority() {
        // 返回角色的ID
        return id;
    }
}
