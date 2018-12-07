package org.unicome.cms.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

public enum Singleton {

    INSTANCE;

    private String name;
    private Singleton() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        try {
            Class s = Class.forName("org.unicome.cms.demo.Singleton");
            Constructor ct = s.getDeclaredConstructor();
            ct.setAccessible(true);
            System.out.println(ct.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Singleton instance = Singleton.INSTANCE;

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Singleton.INSTANCE"));
            objectOutputStream.writeObject(instance);
            objectOutputStream.close();

            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Singleton.INSTANCE"));

            Singleton instance2 = (Singleton)objectInputStream.readObject();
            System.out.println(instance == instance2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
