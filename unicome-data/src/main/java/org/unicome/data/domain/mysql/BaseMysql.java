package org.unicome.data.domain.mysql;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public abstract class BaseMysql<T extends Serializable > {
    protected Long id;
    protected Boolean deleted;
    protected String createdBy;
    protected String updatedBy;
    protected Date createdAt;
    protected Date updatedAt;
}
