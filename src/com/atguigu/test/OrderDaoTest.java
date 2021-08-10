package com.atguigu.test;

import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/08/02 8:50
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("123456789", new Date(), new BigDecimal(100), 0, 1));

    }
}