package com.atguigu.dao;

import com.atguigu.pojo.User;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/27 9:13
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 如果返回null，说明没有这个用户，反之
     */
    public User queryUserByNames(String username);

    /**
     * 保存用户信息
     * @param user 保存的用户信息
     * @return 影响的行数
     */
    public int saveUser(User user);

    /**
     * 根据用户名和密码查询
     * @param username 查询的姓名
     * @param password 查询的密码
     * @return 如果返回null，说明没有这个用户，反之
     */
    public User queryUserByNameAndPassword(String username, String password);
}
