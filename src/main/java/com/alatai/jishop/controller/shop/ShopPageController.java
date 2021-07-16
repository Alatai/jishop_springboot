package com.alatai.jishop.controller.shop;

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

    @GetMapping("/index")
    public String index() {
        return "shop/home/index";
    }
}
