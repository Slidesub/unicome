package org.unicome.data.domain.mysql;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
public class UserPassword extends BaseMysql<UserPassword> implements Serializable {
    protected String userCode;
    protected String password1;
    protected String password2;
}
