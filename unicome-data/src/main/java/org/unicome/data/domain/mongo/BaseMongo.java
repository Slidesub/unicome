package org.unicome.data.domain.mongo;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public abstract class BaseMongo<T extends Serializable> {
    @Id
    protected String id;
    @Field("created_at")
    protected Date createdAt;
    @Field("updated_at")
    protected Date updatedAt;
    @Field("created_by")
    protected String createdBy;
    @Field("updated_by")
    protected String updatedBy;
}