package org.unicome.oauth.security.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

@Data
@Document(collection = "operation")
public class Operation implements GrantedAuthority {
    @Id
    private String id;
    private String name; // OP_
    private String remake;

    @PersistenceConstructor
    public Operation(String id, String name, String remake) {
        Assert.hasText(name, "A granted authority textual representation is required");
        this.id = id;
        this.name = name;
        this.remake = remake;
    }

    @Override
    public String getAuthority() {
        // 返回操作的ID
        return id;
    }
}
