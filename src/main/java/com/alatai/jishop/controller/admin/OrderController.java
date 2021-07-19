package com.alatai.jishop.controller.admin;

import com.alatai.jishop.entity.Order;
import com.alatai.jishop.service.OrderItemService;
import com.alatai.jishop.service.OrderService;
import com.alatai.jishop.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * OrderController
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/16 14:33
 */
@RestController("orderController")
@RequestMapping("/admin/data")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/orders")
    public PageResult<Order> list(@RequestParam(value = "start", defaultValue = "0") Integer start,
                                  @RequestParam(value = "size", defaultValue = "5") Integer size) {
        start = start < 0 ? 0 : start;
        PageResult<Order> pageResult = orderService.findAll(start, size, 5);
        orderItemService.calculateAll(pageResult.getContent());

        return pageResult;
    }

    @PutMapping("/orders/{id}")
    public void deliver(@PathVariable("id") Integer id) {
        orderService.deliver(id);
    }

}
