package org.unicome.cms.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "article")
public class Article extends Base {
    @Id
    private String id;
    private String title;
    private String description;
    private String body;
    @DBRef
    private FileEntry icon;
    @DBRef
    private List<Tag> tags;
    @Field("publish")
    private Boolean publish;
}
