package org.unicome.data.domain.mysql;

public enum ResourceTypeEnum {
    INNER_PAGE("内链页面"),
    OUTER_PAGE("外链页面"),
    BUTTON("按钮");

    private String value;
    ResourceTypeEnum(String value) {
        this.value = value;
    }
}
