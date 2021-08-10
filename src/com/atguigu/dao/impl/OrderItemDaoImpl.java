package com.atguigu.dao.impl;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.BaseDao;
import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/08/02 8:47
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(name, count, price, total_price, order_id) values(?, ?, ?, ?, ?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

}
