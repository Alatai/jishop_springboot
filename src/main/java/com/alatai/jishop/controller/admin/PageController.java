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

    @GetMapping("/categories")
    public String listCategories() {
        return "admin/category/list";
    }
}
