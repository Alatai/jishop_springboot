package com.alatai.jishop.dao;

import com.alatai.jishop.entity.Order;
import com.alatai.jishop.entity.OrderItem;
import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/16 11:37
 */
public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrder(Order order);

    List<OrderItem> findByProduct(Product product);

    List<OrderItem> findByUserAndOrderIsNull(User user);
}
