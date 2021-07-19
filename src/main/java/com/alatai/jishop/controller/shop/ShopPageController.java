package com.alatai.jishop.controller.shop;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 处理前台页面跳转
 *
 * @author Alatai
 * @version 1.0
 * @date 2021/07/16 15:50
 */
@Controller("shopPageController")
public class ShopPageController {

    /**
     * 首页
     */
    @GetMapping("/index")
    public String index() {
        return "shop/home/index";
    }

    /**
     * 注册页面
     */
    @GetMapping("/register")
    public String register() {
        return "shop/user/register";
    }

    /**
     * 登录页面
     */
    @GetMapping("/login")
    public String login() {
        return "shop/user/login";
    }

    /**
     * 退出登录，跳转到首页
     */
    @GetMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated()) {
            subject.logout();
        }

        return "redirect:/index";
    }

    /**
     * 商品详情页
     */
    @GetMapping("/products")
    public String productDetail() {
        return "shop/product/detail";
    }

    /**
     * 商品搜索结果页
     */
    @GetMapping("/products/search")
    public String productsByKeyword() {
        return "shop/product/result";
    }

    /**
     * 结算页面
     */
    @GetMapping("/order/account")
    public String order() {
        return "shop/order/order";
    }

    /**
     * 购物车页面
     */
    @GetMapping("/order/mycart")
    public String cart() {
        return "shop/order/cart";
    }

    /**
     * 支付页面
     */
    @GetMapping("/order/payment")
    public String pay() {
        return "shop/order/pay";
    }

    /**
     * 完成支付页面
     */
    @GetMapping("/order/hasPaid")
    public String hasPaid() {
        return "shop/order/hasPaid";
    }

    /**
     * 所有订单页面
     */
    @GetMapping("/order/orders")
    public String orders() {
        return "shop/order/orders";
    }

    /**
     * 商品评价页面
     */
    @GetMapping("/review/myreview")
    public String reviews() {
        return "shop/review/review";
    }
}
