package com.alatai.jishop.service;

import com.alatai.jishop.entity.Order;
import com.alatai.jishop.entity.OrderItem;
import com.alatai.jishop.util.PageResult;

import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/16 11:36
 */
public interface OrderService {

    String WAIT_PAY = "waitPay";
    String WAIT_DELIVER = "waitDelivery";
    String WAIT_CONFIRM = "waitConfirm";
    String WAIT_REVIEW = "waitReview";
    String FINISHED = "finish";
    String DELETED = "delete";

    List<Order> findAll();

    PageResult<Order> findAll(Integer start, Integer size, Integer displayPages);

    Order findById(Integer id);

    Order insert(Order order);

    Order update(Order order);

    void deleteById(Integer id);

    /**
     * 避免双向引用引起的无限循环
     */
    void removeFromOrderItem(List<Order> orders);

    void removeFromOrderItem(Order order);

    /**
     * オーダーの生成、状態付け（waitPay）
     */
    Order createOrder(Order order, List<OrderItem> orderItems);

    /**
     * 支払い成功、状態変更（waitDeliver)
     */
    void hasPaid(Integer id);

    /**
     * オーダー配達完成、状態変更（waitConfirm）
     */
    void deliver(Integer id);

    /**
     * オーダー確認完成、状態変更（waitReview）
     */
    Order confirmOrder(Integer id);

    /**
     * オーダー削除、状態変更（delete）
     */
    void deleteOrder(Integer id);

    /**
     * オーダー評価完成、状態変更（finish）
     */
    void hasReviewed(Integer id);
}
