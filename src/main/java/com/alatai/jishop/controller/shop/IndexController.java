package com.alatai.jishop.controller.shop;

import com.alatai.jishop.entity.Category;
import com.alatai.jishop.service.CategoryService;
import com.alatai.jishop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * IndexController
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/16 15:51
 */
@RestController("indexController")
@RequestMapping("/data")
public class IndexController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/index")
    public List<Category> index() {
        List<Category> categories = categoryService.findAll();
        productService.associateCategory(categories);
        productService.fillRows(categories);

        return categories;
    }
}
