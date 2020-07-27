package org.unicome.oauth.security.verifycode;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Component
public class ImgVerifyCodeGenerator extends AbstractVerifyCodeGenerator {

    protected Integer width = 100;
    protected Integer height = 40;

    @Override
    public VerifyCode generate() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setColor(getRandomColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 38));
        g.setColor(getRandomColor(150, 200));

        Random random = new Random();
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(250);
            int yl = random.nextInt(250);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String randomStr = this.getRandomStr();
        for (int i = 0; i < this.length; i++) {
            g.setColor(new Color(10 + random.nextInt(110), 10 + random.nextInt(110), 10 + random.nextInt(110)));
            g.drawString(randomStr.substring(i), 18 * i + 12, 32);
            g.dispose();
        }
        return new ImgVerifyCode(image, randomStr, this.expireIn);
    }

}
