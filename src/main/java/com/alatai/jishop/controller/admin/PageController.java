package com.alatai.jishop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 处理页面跳转
 *
 * @author Alatai
 * @version 1.0
 * @date 2021/07/14 11:46
 */
@Controller
@RequestMapping("/admin")
public class PageController {

    /**
     * 管理首页
     */
    @GetMapping
    public String admin() {
        return "redirect:/admin/categories";
    }

    /**
     * 分类页
     */
    @GetMapping("/categories")
    public String listCategories() {
        return "admin/category/list";
    }

    /**
     * 属性页
     */
    @GetMapping("/properties")
    public String listProperties() {
        return "admin/property/list";
    }

    /**
     * 商品页
     */
    @GetMapping("/products")
    public String listProducts() {
        return "admin/product/list";
    }

    /**
     * 商品图片页
     */
    @GetMapping("/images")
    public String listProductImages() {
        return "admin/image/list";
    }
}
