package com.alatai.jishop.controller.shop;

import com.alatai.jishop.entity.Order;
import com.alatai.jishop.entity.OrderItem;
import com.alatai.jishop.entity.Review;
import com.alatai.jishop.entity.User;
import com.alatai.jishop.service.OrderItemService;
import com.alatai.jishop.service.OrderService;
import com.alatai.jishop.service.ProductService;
import com.alatai.jishop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/19 9:55
 */
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ProductService productService;


    @GetMapping("/review")
    public List<OrderItem> review(Integer oid) {
        List<OrderItem> orderItems = orderItemService.findByOrder(orderService.findById(oid));
        productService.associateOrderItem(orderItems);

        return orderItems;
    }

    @PostMapping("/doreview")
    public String doReview(HttpSession session, @RequestBody List<Review> reviews) {
        User user = (User) session.getAttribute("user");
        Integer oid = reviews.get(0).getOid();

        reviewService.insertReviews(reviews, user);

        orderService.hasReviewed(oid);

        return "success";
    }
}
