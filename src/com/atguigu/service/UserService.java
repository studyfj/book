package com.atguigu.service;

import com.atguigu.pojo.User;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/27 9:35
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登录用户
     * @param user
     * @return null登录失败，反之
     */
    public User loginUser(User user);

    /**
     * 检查用户名是否能用
     * @param username
     * @return true:不可用 ,false；可用
     */
    public boolean existsUsername(String username);



}
