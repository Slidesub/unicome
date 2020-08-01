package org.unicome.oauth.security.domain;

import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

@Data
public class Base implements Serializable {
    @Field("create_date")
    private Date createDate;
    @Field("update_date")
    private Date updateDate;

    @PersistenceConstructor
    public Base(Date createDate, Date updateDate) {
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

}
