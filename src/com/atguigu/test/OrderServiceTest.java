package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/08/02 9:10
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java", 2, new BigDecimal(1000 ), new BigDecimal(2000)));
        cart.addItem(new CartItem(2, "java", 2, new BigDecimal(1000 ), new BigDecimal(2000)));
        OrderServiceImpl orderService = new OrderServiceImpl();
        String order = orderService.createOrder(cart, 1);
        System.out.println(order);
    }
}