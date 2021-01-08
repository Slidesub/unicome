package org.unicome.data.domain.mysql;

public enum MenuTypeEnum {
    MODULE("模块资源"),
    MENU("菜单资源");

    private String value;
    MenuTypeEnum(String value) {
        this.value = value;
    }
}
