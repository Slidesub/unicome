package org.unicome.oauth.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Base implements Serializable {
    private Date createDate;
    private Date updateDate;
}
