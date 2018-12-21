package org.unicome.cms.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tag")
public class Tag extends Base {
    @Id
    private String id;
    private String title;
    private String description;

    public Tag() {

    }
    public Tag(String id) {
        this.id = id;
    }

    @PersistenceConstructor
    public Tag(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
