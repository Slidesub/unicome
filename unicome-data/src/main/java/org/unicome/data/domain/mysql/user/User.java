package org.unicome.data.domain.mysql.user;

import lombok.Data;
import lombok.ToString;
import org.unicome.data.domain.mysql.BaseMysql;
import org.unicome.data.domain.mysql.group.Group;
import org.unicome.data.domain.mysql.role.Role;

import java.io.Serializable;
import java.util.Set;

@Data
@ToString(callSuper = true)
public class User extends BaseMysql<User> implements Serializable {
    protected String userCode;
    protected String username;
    protected String nickname;
    protected String mobile;
    protected String email;
    protected String password;
    protected Boolean enabled;
    protected Set<Group> groups;
    protected Set<Role> roles;
}