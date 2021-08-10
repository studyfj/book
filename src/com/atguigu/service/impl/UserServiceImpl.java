package com.atguigu.service.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/27 9:40
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        int i = userDao.saveUser(user);
        System.out.println(i);
    }

    @Override
    public User loginUser(User user) {
        return userDao.queryUserByNameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        User user = userDao.queryUserByNames(username);
        if (user != null) {
            return true;
        }

        return false;

    }
}
