package org.unicome.oauth.security.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

@Data
@Document(collection = "authority")
public class Authority implements GrantedAuthority {
    @Id
    private String id;
    private String name;
    private Boolean enabled;
    private String remake;

    @PersistenceConstructor
    public Authority(String id, String name, Boolean enabled, String remake) {
        Assert.hasText(name, "A granted authority textual representation is required");
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.remake = remake;
    }

    @Override
    public String getAuthority() {
        return id;
    }
}
