package com.alatai.jishop.service;

import com.alatai.jishop.entity.*;

import java.util.List;

/**
 * OrderItemService
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/16 11:45
 */
public interface OrderItemService {

    List<OrderItem> findAll();

    OrderItem findById(Integer id);

    List<OrderItem> findByOrder(Order order);

    List<OrderItem> findByProduct(Product product);

    List<OrderItem> findByUser(User user);

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

    int getSaleCount(Product product);
}
