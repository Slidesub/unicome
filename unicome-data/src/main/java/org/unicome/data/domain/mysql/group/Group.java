package org.unicome.data.domain.mysql.group;

import lombok.Data;
import lombok.ToString;
import org.unicome.data.domain.mysql.BaseMysql;
import org.unicome.data.domain.mysql.role.Role;
import org.unicome.data.domain.mysql.user.User;
import java.io.Serializable;
import java.util.Set;

@Data
@ToString(callSuper = true)
public class Group extends BaseMysql<Group> implements Serializable {
    private String code;
    private String parentCode;
    private String hierarchyCode; // 代码层级
    private String name;
    private String description;
    private String level;
    private Integer sort; // 排序
    private String extend;
    protected Set<User> users;
    protected Set<Role> roles;
}
