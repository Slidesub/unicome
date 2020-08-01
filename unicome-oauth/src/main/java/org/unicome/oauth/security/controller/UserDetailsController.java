package org.unicome.oauth.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
