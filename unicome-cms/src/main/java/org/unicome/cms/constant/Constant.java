package org.unicome.cms.constant;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class Constant {
    public static final String CHARSET_UTF8 = "UTF-8";

    public static void main(String[] args) {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        a.add("3");
        List<String> b = a.subList(0, 1);
        b.add("4");
//        b.remove("1");
//        System.out.println(b);

        String[] c = new String[1];
        System.out.println(c);
        c = a.toArray(c);
//        System.out.println(a);
        System.out.println(c);

    }

    public static Object getObj(final Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return null;
                    }
                });
    }
}
