package org.unicome.oauth.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

@Data
@Document(collection = "authority")
public class Authority implements GrantedAuthority {
    @Id
    private String id;
    private String role;
    private String description;

    @PersistenceConstructor
    public Authority(String id, String role, String description) {
        Assert.hasText(role, "A granted authority textual representation is required");
        this.id = id;
        this.role = role;
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Authority) {
            return role.equals(((Authority) obj).role);
        }
        return false;
    }
}
