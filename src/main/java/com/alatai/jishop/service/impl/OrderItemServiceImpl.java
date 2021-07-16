package com.alatai.jishop.service.impl;

import com.alatai.jishop.dao.OrderItemDao;
import com.alatai.jishop.entity.Order;
import com.alatai.jishop.entity.OrderItem;
import com.alatai.jishop.entity.User;
import com.alatai.jishop.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/16 14:04
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public List<OrderItem> findAll() {
        return orderItemDao.findAll();
    }

    @Override
    public OrderItem findById(Integer id) {
        return orderItemDao.getById(id);
    }

    @Override
    public List<OrderItem> findByOrder(Order order) {
        return orderItemDao.findByOrder(order);
    }

    @Override
    public OrderItem insert(OrderItem orderItem) {
        return orderItemDao.save(orderItem);
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        return orderItemDao.save(orderItem);
    }

    @Override
    public void deleteById(Integer id) {
        orderItemDao.deleteById(id);
    }

    @Override
    public void calculateAll(List<Order> orders) {
        for (Order order : orders) {
            calculate(order);
        }
    }

    @Override
    public void calculate(Order order) {
        List<OrderItem> items = findByOrder(order);

        float amount = 0f;
        int number = 0;

        for (OrderItem item : items) {
            amount += item.getNumber() * item.getProduct().getPromotePrice();
            number += item.getNumber();
        }

        order.setAmount(amount);
        order.setNumber(number);
    }

    @Override
    public int checkOrderItem(User user, Integer pid, Integer num) {
        return 0;
    }

    @Override
    public int getSaleCount(Integer pid) {
        return 0;
    }
}
