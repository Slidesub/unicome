package org.unicome.oauth.security.verifycode;

public interface IVerifyCode {

    public boolean isExpired();
    public void validate(String code) throws VerifyCodeException;
}
