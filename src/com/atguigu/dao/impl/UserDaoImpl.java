package com.atguigu.dao.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.UserDao;
import com.atguigu.pojo.User;
import com.sun.javafx.binding.StringFormatter;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/27 9:19
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByNames(String username) {
        String sql = "select id, username, password, email from t_user where username = ?";
        return queryForOne(User.class, sql,username);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`) values(?, ?, ?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }

    @Override
    public User queryUserByNameAndPassword(String username, String password) {
        String sql = "select id, username, password, email from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql,username,password);

    }
}
