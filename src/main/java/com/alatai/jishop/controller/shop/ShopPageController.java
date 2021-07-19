package com.alatai.jishop.controller.shop;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 一般的なユーザ向け、表示ページ遷移
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/16 15:50
 */
@Controller("shopPageController")
public class ShopPageController {

    /**
     * ホームページ
     */
    @GetMapping("/index")
    public String index() {
        return "shop/home/index";
    }

    /**
     * 新規登録ページ
     */
    @GetMapping("/register")
    public String register() {
        return "shop/user/register";
    }

    /**
     * ログインページ
     */
    @GetMapping("/login")
    public String login() {
        return "shop/user/login";
    }

    /**
     * ログアウトして，ホームページに戻る
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
     * 商品詳細ページ
     */
    @GetMapping("/products")
    public String productDetail() {
        return "shop/product/detail";
    }

    /**
     * 商品検索結果ページ
     */
    @GetMapping("/products/search")
    public String productsByKeyword() {
        return "shop/product/result";
    }

    /**
     * 会計ページ
     */
    @GetMapping("/order/account")
    public String order() {
        return "shop/order/order";
    }

    /**
     * ショッピングカートページ
     */
    @GetMapping("/order/mycart")
    public String cart() {
        return "shop/order/cart";
    }

    /**
     * 支払いページ
     */
    @GetMapping("/order/payment")
    public String pay() {
        return "shop/order/pay";
    }

    /**
     * 支払い成功ページ
     */
    @GetMapping("/order/hasPaid")
    public String hasPaid() {
        return "shop/order/hasPaid";
    }

    /**
     * ユーザオーダーページ（該当ユーザ、すべてのオーダー）
     */
    @GetMapping("/order/orders")
    public String orders() {
        return "shop/order/orders";
    }

    /**
     * 商品評価ページ
     */
    @GetMapping("/review/myreview")
    public String reviews() {
        return "shop/review/review";
    }
}
