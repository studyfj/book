package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/27 8:43
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static final Properties properties = new Properties();
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    static {
        try {
            InputStream input = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(input);
            // 创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            System.out.println(dataSource.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取数据库连接池对象
     * 返回null，获取连接失败
     */
    public static Connection getConnection() {
        Connection conn = conns.get();
        if (conn ==null) {
            try {
                // 数据库连接池取
                conn = dataSource.getConnection();
                // 保存
                conns.set(conn);
                // 直接设置手动管理
                conn.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 关闭连接，放回数据库连接池
     */
//    public static void close(Connection connection) {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException throwables) {
////                throwables.printStackTrace();
//            }
//        }
//    }

    /**
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose() {
        Connection connection = conns.get();
        if (connection != null) {
            // 如果不等于null，使用过连接
            try {
                connection.commit();
            } catch (SQLException throwables) {
//                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
                }

            }
        }
        // 一定执行remove，否则出错（Tomcat底层使用了线城池技术）
        conns.remove();
    }

    /**
     * 回滚事务，并关闭释放连接
     */
    public static void rollbackAndClose() {
        Connection connection = conns.get();
        if (connection != null) {
            // 如果不等于null，使用过连接
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        }
        // 一定执行remove，否则出错（Tomcat底层使用了线城池技术）
        conns.remove();
    }




    public static int parseInt(String strInt, int defaultValue) {
        try{
            return Integer.parseInt(strInt);
        }catch (Exception e){
//            e.printStackTrace();
        }
        return defaultValue;
    }


}
