package com.alatai.jishop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理サイト表示ページ遷移
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/14 11:46
 */
@Controller("adminPageController")
@RequestMapping("/admin")
public class AdminPageController {

    /**
     * 管理サイトホームページ
     */
    @GetMapping
    public String admin() {
        return "redirect:/admin/categories";
    }

    /**
     * 分類ページ
     */
    @GetMapping("/categories")
    public String listCategories() {
        return "admin/category/list";
    }

    /**
     * 属性ページ
     */
    @GetMapping("/properties")
    public String listProperties() {
        return "admin/property/list";
    }

    /**
     * 商品ページ
     */
    @GetMapping("/products")
    public String listProducts() {
        return "admin/product/list";
    }

    /**
     * 商品写真ページ
     */
    @GetMapping("/images")
    public String listProductImages() {
        return "admin/image/list";
    }

    /**
     * 属性編集ページ
     */
    @GetMapping("/pvalues")
    public String listPropertyValues() {
        return "admin/pvalue/edit";
    }

    /**
     * ユーザページ
     */
    @GetMapping("/users")
    public String listUsers() {
        return "admin/user/list";
    }

    /**
     * オーダーページ
     */
    @GetMapping("/orders")
    public String listOrders() {
        return "admin/order/list";
    }
}
