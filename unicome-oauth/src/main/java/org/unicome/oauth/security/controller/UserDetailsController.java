package org.unicome.oauth.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unicome.oauth.security.entity.User;
import org.unicome.oauth.security.service.UserService;
import org.unicome.oauth.security.verifycode.ImgVerifyCode;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UserDetailsController {

    @GetMapping("/user_details")
    public Authentication getUserDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
