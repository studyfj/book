package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/27 9:45
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class UserServiceTest {

    private UserService  userService= new UserServiceImpl();
    @Test
    public void registerUser() {
        userService.registerUser(new User(null, "admin", "admin", "admin@atguigu.com"));
    }

    @Test
    public void loginUser() {
        User user = userService.loginUser(new User(null, "admin", "admin", "admin@atguigu.com"));
        System.out.println(user);
    }

    @Test
    public void existsUsername() {
        boolean existsUsername = userService.existsUsername("admin");
        System.out.println(existsUsername);
    }
}