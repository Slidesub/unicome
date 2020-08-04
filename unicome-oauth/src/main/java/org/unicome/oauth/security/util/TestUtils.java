package org.unicome.oauth.security.util;

import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static void main(String[] args) throws Exception {
        Demo d = new Demo("GERALD", "abc123_");
        Demo d2 = new Demo("GERALD", "abc123_");
        Demo d3 = new Demo("GERALD", "abc123_");
        List<Demo> demoList = new ArrayList<>();
        demoList.add(d);
        demoList.add(d2);
        demoList.add(d3);
        try (OutputStream out = new FileOutputStream(new File("demo.txt"))

        ) {
            for (Demo demo : demoList) {
                out.write((demo.toString() + "\r\n").getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Data
    static class Demo implements Serializable {
        private String username;
        private String password;
        public Demo(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public String toString() {
            return username + "|" + password;
        }
    }
}
