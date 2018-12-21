package org.unicome.cms.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Base implements Serializable {
    private String author;
    private Date createDate;
    private Date updateDate;
}
