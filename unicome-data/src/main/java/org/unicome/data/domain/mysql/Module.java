package org.unicome.data.domain.mysql;

import java.io.Serializable;

public class Module extends BaseMysql<Module> implements Serializable {
    protected String code;
    protected String parentCode;
    protected String hierarchyCode;
    protected String name;
    protected String url;
    protected Integer sort;
    protected String icon;
}
