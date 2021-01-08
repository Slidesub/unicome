package org.unicome.data.domain.mysql.authority;

import lombok.Data;
import lombok.ToString;
import org.unicome.data.domain.mysql.BaseMysql;
import org.unicome.data.domain.mysql.role.Role;

import java.io.Serializable;
import java.util.Set;

@Data
@ToString(callSuper = true)
public class Authority extends BaseMysql<Authority> implements Serializable {
    private String name;
    private Boolean enabled;
    private String remark;
    Set<Role> roles;
}
