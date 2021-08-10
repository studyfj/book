package com.atguigu.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/29 10:07
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class UserServletTest {

    public void login() {
        System.out.println("login方法调用了");
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method login = UserServletTest.class.getDeclaredMethod("login");
        login.invoke(new UserServletTest());


    }
}
