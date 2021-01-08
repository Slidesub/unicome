package org.unicome.data.domain.mysql.role;

import lombok.*;
import org.unicome.data.domain.mysql.BaseMysql;
import org.unicome.data.domain.mysql.authority.Authority;
import org.unicome.data.domain.mysql.group.Group;
import org.unicome.data.domain.mysql.user.User;

import java.io.Serializable;
import java.util.Set;

@Data
@ToString(callSuper = true)
public class Role extends BaseMysql<Role> implements Serializable {
    protected String name;
    protected String code;
    protected String parentCode;
    protected String description;
    protected Boolean enabled;
    protected String extend;
}
