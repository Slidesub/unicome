package org.unicome.data.domain.mysql;

import java.io.Serializable;

public class Menu extends BaseMysql<Menu> implements Serializable {
    protected String code;
    protected String parentCode;
    protected String hierarchyCode;
    protected String name;
    protected String url;
    protected MenuTypeEnum type;
    protected Integer sort;
    protected String icon;
}
