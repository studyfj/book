package com.atguigu.test;


import com.atguigu.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/27 8:53
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class JdbcUtilsTest {

    @Test
    public void testConn() {
        Connection connection = JdbcUtils.getConnection();
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(connection);
    }
}
