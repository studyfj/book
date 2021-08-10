package com.atguigu.dao;

import com.atguigu.utils.JdbcUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/27 8:57
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public abstract class BaseDao {

    // 使用DbUtil操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 执行insert update delete语句
     * @return -1执行失败,其他影响行数
     */
    public int update(String sql, Object...args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     * 查询返回一个javaBean的sql语句
     * @param type 返回的对象类型
     * @param sql sql语句
     * @param args sql参数
     * @param <T> 返回类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type, String sql, Object ...args) {
        Connection connection = JdbcUtils.getConnection();

        try {
            return queryRunner.query(connection,sql, new BeanHandler<T>(type), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);

        }
    }

    public <T>List<T> queryForList(Class<T> type, String sql, Object ...args) {
        Connection connection = JdbcUtils.getConnection();

        try {
            return queryRunner.query(connection,sql, new BeanListHandler<T>(type), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new RuntimeException(throwables);

            }
        }
    }

    /**
     * 执行一行一列的sql
     * @param sql sql语句
     * @param args 对应的参数
     * @return
     */
    public Object queryForStringValue(String sql, Object...args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new ScalarHandler() ,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }

    }
}
