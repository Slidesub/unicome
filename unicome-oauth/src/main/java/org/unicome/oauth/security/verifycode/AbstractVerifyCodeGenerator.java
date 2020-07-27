package org.unicome.oauth.security.verifycode;

import lombok.Data;

import java.awt.*;
import java.util.Random;

@Data
public abstract class AbstractVerifyCodeGenerator implements IVerifyCodeGenerator {
    protected Integer length = 4;
    protected Integer expireIn = 3600;

    public String getRandomStr() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            builder.append(rand);
        }
        return builder.toString();
    }

    public Color getRandomColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}