package com.alatai.jishop.service.impl;

import com.alatai.jishop.dao.OrderDao;
import com.alatai.jishop.entity.Order;
import com.alatai.jishop.entity.OrderItem;
import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.User;
import com.alatai.jishop.service.OrderItemService;
import com.alatai.jishop.service.OrderService;
import com.alatai.jishop.service.ProductService;
import com.alatai.jishop.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * OrderService
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/16 14:27
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ProductService productService;

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public List<Order> findByUser(User user) {
        return orderDao.findByUserAndStatusNot(user, DELETED);
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

    // 添加事务
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    @Override
    public Order createOrder(Order order, List<OrderItem> orderItems) {
        String orderCode = UUID.randomUUID().toString().replace("-", "");

        order.setStatus(WAIT_PAY);
        order.setOrderCode(orderCode);
        order.setCreatedDate(new Date());

        insert(order);

        float amount = 0;

        // オーダーとオーダー詳細を関連する、総金額計算
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
            orderItemService.update(orderItem);

            amount += orderItem.getProduct().getPromotePrice() * orderItem.getNumber();
        }

        order.setAmount(amount);

        return order;
    }

    @Override
    public Order pay(Order order) {
        List<OrderItem> orderItems = orderItemService.findByOrder(order);
        float amount = 0;

        for (OrderItem orderItem : orderItems) {
            amount += orderItem.getProduct().getPromotePrice() * orderItem.getNumber();
        }

        order.setAmount(amount);

        return order;
    }

    @Override
    public void hasPaid(Integer id) {
        Order order = findById(id);
        order.setStatus(WAIT_DELIVER);
        order.setPaidDate(new Date());

        // 在庫数更新
        List<OrderItem> orderItems = orderItemService.findByOrder(order);

        for (OrderItem orderItem : orderItems) {
            Product product = orderItem.getProduct();

            Integer stock = product.getStock();
            stock -= orderItem.getNumber();

            System.out.println("stock: " + stock);

            product.setStock(stock);
            productService.update(product);
        }

        update(order);
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
        Order order = findById(id);
        order.setStatus(WAIT_REVIEW);
        order.setConfirmedDate(new Date());

        return update(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        Order order = findById(id);
        order.setStatus(DELETED);

        update(order);
    }

    @Override
    public void hasReviewed(Integer id) {
        Order order = findById(id);
        order.setStatus(FINISHED);

        update(order);
    }
}
