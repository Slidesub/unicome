package org.unicome.oauth.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class VerifyCode {
    public static void main(String[] args) {
        Integer a = 1;
        int b = 1;
        if (a.equals(b)) {
            System.out.println("tt");
        }
        System.out.println(new BCryptPasswordEncoder().encode("secret1"));
    }
}
