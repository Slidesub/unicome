package org.unicome.sample;

public class Demo {
    public static void main(String[] args) {
        System.out.println(getValue(2));
        Thread t = new Thread() {
            @Override
            public void run() {
                pong();
            }
        };
//        t.run();
        t.start();
        System.out.println("ping");
    }
    static void pong() {
        System.out.println("pong");
    }
    public static int getValue(int i) {
        int result = 0;
        switch (i) {
            case 1:
                result = result + i;
            case 2:
                result = result + i * 2;
            case 3:
                result = result + i * 3;
        }
        return result;
    }
}

class Person {
    private String name = "Person";
    int age = 0;
}
class Child extends Person {
    public String grade;

    public static void main(String[] args) {
        Person p = new Child();
//        System.out.println(p.name);
    }
}