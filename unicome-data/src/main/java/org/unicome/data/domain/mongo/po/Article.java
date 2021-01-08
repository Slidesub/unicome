package org.unicome.data.domain.mongo.po;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.unicome.data.domain.mongo.BaseMongo;
import java.io.Serializable;


@Data
@Document(collection = "article")
public class Article extends BaseMongo<Article> implements Serializable {
    private String title;
    private String description;
    private String body;
//    @DBRef
//    private FileEntry icon;
//    @DBRef
//    private List<Tag> tags;
    @Field("published")
    private Boolean published;
}