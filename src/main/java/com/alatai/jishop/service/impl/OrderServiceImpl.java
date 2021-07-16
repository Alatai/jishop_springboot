package com.alatai.jishop.service.impl;

import com.alatai.jishop.dao.OrderDao;
import com.alatai.jishop.entity.Order;
import com.alatai.jishop.entity.OrderItem;
import com.alatai.jishop.service.OrderService;
import com.alatai.jishop.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/16 14:27
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public PageResult<Order> findAll(Integer start, Integer size, Integer displayPages) {
        Pageable pageable = PageRequest.of(start, size);
        Page<Order> page = orderDao.findAll(pageable);

        return new PageResult<>(page, displayPages);
    }

    @Override
    public Order findById(Integer id) {
        return orderDao.getById(id);
    }

    @Override
    public Order insert(Order order) {
        return orderDao.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderDao.save(order);
    }

    @Override
    public void deleteById(Integer id) {
        orderDao.deleteById(id);
    }

    @Override
    public void removeFromOrderItem(List<Order> orders) {
        for (Order order : orders) {
            removeFromOrderItem(order);
        }
    }

    @Override
    public void removeFromOrderItem(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(null);
        }
    }

    @Override
    public Order createOrder(Order order, List<OrderItem> orderItems) {
        return null;
    }

    @Override
    public void hasPaid(Integer id) {

    }

    @Override
    public void deliver(Integer id) {
        Order order = findById(id);
        order.setStatus(WAIT_CONFIRM);
        order.setDeliveredDate(new Date());

        update(order);
    }

    @Override
    public Order confirmOrder(Integer id) {
        return null;
    }

    @Override
    public void deleteOrder(Integer id) {

    }

    @Override
    public void hasReviewed(Integer id) {

    }
}
