package com.alatai.jishop.service;

import com.alatai.jishop.entity.Category;
import com.alatai.jishop.entity.Order;
import com.alatai.jishop.entity.OrderItem;
import com.alatai.jishop.entity.User;

import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/16 11:45
 */
public interface OrderItemService {

    List<OrderItem> findAll();

    OrderItem findById(Integer id);

    List<OrderItem> findByOrder(Order order);

    OrderItem insert(OrderItem orderItem);

    OrderItem update(OrderItem orderItem);

    void deleteById(Integer id);

    /**
     * オーダー金額計算-list
     */
    void calculateAll(List<Order> orders);

    /**
     * オーダー金額計算
     */
    void calculate(Order order);

    /**
     * この商品は既に追加しているかを確認する
     */
    int checkOrderItem(User user, Integer pid, Integer num);

    int getSaleCount(Integer pid);
}
