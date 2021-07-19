package com.alatai.jishop.dao;

import com.alatai.jishop.entity.Order;
import com.alatai.jishop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/16 11:38
 */
public interface OrderDao extends JpaRepository<Order, Integer> {

    List<Order> findByUserAndStatusNot(User user, String status);
}
