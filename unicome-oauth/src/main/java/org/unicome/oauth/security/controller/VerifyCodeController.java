package org.unicome.oauth.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unicome.oauth.security.constant.SecurityConstants;
import org.unicome.oauth.security.verifycode.ImgVerifyCode;
import org.unicome.oauth.security.verifycode.ImgVerifyCodeGenerator;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class VerifyCodeController {

    @Autowired
    ImgVerifyCodeGenerator imgVerifyCodeGenerator;
    @Autowired
    SecurityConstants securityConstants;

    @GetMapping("/verify/code")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        imgVerifyCodeGenerator.setLength(3);
        ImgVerifyCode g = (ImgVerifyCode) imgVerifyCodeGenerator.generate();
        ImageIO.write(g.getImage(), "jpeg", response.getOutputStream());
    }
}
