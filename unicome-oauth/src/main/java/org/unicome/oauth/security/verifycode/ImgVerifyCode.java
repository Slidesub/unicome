package org.unicome.oauth.security.verifycode;

import lombok.Data;

import java.awt.image.BufferedImage;

@Data
public class ImgVerifyCode extends VerifyCode {

    private BufferedImage image;
    private Integer expireInSeconds;

    public ImgVerifyCode(BufferedImage image, String code, int expireInSeconds) {
        super(code, expireInSeconds);
        this.image = image;
    }
}
