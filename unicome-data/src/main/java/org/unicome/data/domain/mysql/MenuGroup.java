package org.unicome.data.domain.mysql;

import java.io.Serializable;

/**
 * 菜单组
 */
public class MenuGroup extends BaseMysql<MenuGroup> implements Serializable {
    protected String code;
    protected String name;
    protected Integer sort;
    protected String icon;
}
