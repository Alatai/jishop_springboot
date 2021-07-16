package com.alatai.jishop.dao;

import com.alatai.jishop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/16 11:38
 */
public interface OrderDao extends JpaRepository<Order, Integer> {


}
