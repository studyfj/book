package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/08/02 8:42
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public interface OrderDao {

    // 保存订单
    int saveOrder(Order order);

}
