package org.unicome.oauth.security.verifycode;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class VerifyCode implements IVerifyCode, Serializable {

    private String code; // 验证码
    private LocalDateTime expireDate; // 过期时间

    public VerifyCode(String code, LocalDateTime expireDate) {
        this.code = code;
        this.expireDate = expireDate;
    }

    public VerifyCode(String code, int expireInSeconds) {
        this.code = code;
        this.expireDate = LocalDateTime.now().plusSeconds(expireInSeconds);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expireDate);
    }

    public void validate(String code) throws VerifyCodeException {
        if (StringUtils.isEmpty(code)) {
            throw new VerifyCodeException("验证码为空！");
        } else if (code.equalsIgnoreCase(this.code)) {
            throw new VerifyCodeException("验证码错误！");
        }
    }
}
