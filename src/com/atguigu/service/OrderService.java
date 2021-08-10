package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/08/02 9:00
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public interface OrderService {

    String createOrder(Cart cart, Integer userId);


}
