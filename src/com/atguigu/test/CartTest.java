package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/08/01 10:33
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java", 2, new BigDecimal(1000 ), new BigDecimal(2000)));
        cart.addItem(new CartItem(2, "java", 2, new BigDecimal(1000 ), new BigDecimal(2000)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java", 2, new BigDecimal(1000 ), new BigDecimal(2000)));
        cart.addItem(new CartItem(2, "java", 2, new BigDecimal(1000 ), new BigDecimal(2000)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java", 2, new BigDecimal(1000 ), new BigDecimal(2000)));
        cart.addItem(new CartItem(2, "java", 2, new BigDecimal(1000 ), new BigDecimal(2000)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java", 2, new BigDecimal(1000 ), new BigDecimal(2000)));
        cart.addItem(new CartItem(2, "java", 2, new BigDecimal(1000 ), new BigDecimal(2000)));
        cart.deleteItem(1);
        cart.clear();
        cart.addItem(new CartItem(1, "java", 2, new BigDecimal(1000 ), new BigDecimal(2000)));
        cart.updateCount(1, 10);
        System.out.println(cart);
    }
}