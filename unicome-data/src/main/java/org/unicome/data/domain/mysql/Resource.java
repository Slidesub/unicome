package org.unicome.data.domain.mysql;

import java.io.Serializable;

public class Resource extends BaseMysql<Resource> implements Serializable {
    protected String code;
    protected String parentCode;
    protected String hierarchyCode;
    protected String name;
    protected String url;
    protected ResourceTypeEnum type;
    protected Integer sort;
    protected String icon;
}
