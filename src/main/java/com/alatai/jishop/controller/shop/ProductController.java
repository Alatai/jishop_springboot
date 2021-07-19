package com.alatai.jishop.controller.shop;

import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.PropertyValue;
import com.alatai.jishop.entity.Review;
import com.alatai.jishop.service.CategoryService;
import com.alatai.jishop.service.ProductService;
import com.alatai.jishop.service.PropertyValueService;
import com.alatai.jishop.service.ReviewService;
import com.alatai.jishop.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ProductController
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/17 11:55
 */
@RestController("shopProductController")
@RequestMapping("/data")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private PropertyValueService propertyValueService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products/{id}")
    public Map<String, Object> productDetail(@PathVariable("id") Integer id) {
        Product product = productService.productDetail(productService.findById(id));
        productService.associateFirstImage(product);
        List<PropertyValue> propertyValues = propertyValueService.findByProduct(product);
        List<Review> reviews = reviewService.findByProduct(product);

        Map<String, Object> result = new HashMap<>();
        result.put("product", product);
        result.put("propertyValues", propertyValues);
        result.put("reviews", reviews);

        return result;
    }

    @GetMapping("/categories/{cid}/products")
    public PageResult<Product> searchByCategory(@PathVariable("cid") Integer cid,
                                                @RequestParam(value = "start", defaultValue = "0") Integer start,
                                                @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start < 0 ? 0 : start;
        PageResult<Product> pageResult = productService.findAll(cid, start, size, 5);
        productService.associateFirstImage(pageResult.getContent());

        return pageResult;
    }

    @GetMapping("/products")
    public PageResult<Product> searchByKeyword(@RequestParam("keyword") String keyword,
                                               @RequestParam(value = "start", defaultValue = "0") Integer start,
                                               @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start < 0 ? 0 : start;
        PageResult<Product> pageResult = productService.findByKeyword(keyword, start, size, 5);
        productService.associateFirstImage(pageResult.getContent());

        return pageResult;
    }

}
