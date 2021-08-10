package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/27 9:27
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class UserDaoTest {

    private UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByNames() {
        User user = userDao.queryUserByNames("admin");
        System.out.println(user);
    }

    @Test
    public void saveUser() {
        int count = userDao.saveUser(new User(null, "scott", "scott", "scott@qq.com"));
        System.out.println(count);
    }

    @Test
    public void queryUserByNameAndPassword() {
        User user = userDao.queryUserByNameAndPassword("admin", "admin");
        System.out.println(user);
    }
}