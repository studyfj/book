package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/08/01 10:19
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer, CartItem> items = new HashMap<>();

    /**
     * 添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        // 先查看是否添加过此商品，如果加过，数量和金额累加
        CartItem item = items.get(cartItem.getId());
        // null没添加过
        if (item == null) {
            items.put(cartItem.getId(), cartItem);
        }else {
            // 已经添加过，数量累加
            item.setCount(item.getCount() + 1);
            // 更新金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));

        }
    }

    /**
     * 删除商品项
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空商品项
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id, count
     */
    public void updateCount(Integer id, Integer count){
        // 查看购物车有此商品，有修改数量，更新金额
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            // 修改数量
            cartItem.setCount(count);
            // 更新金额
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }







    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry: items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItem> entry: items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
