package com.alatai.jishop.controller.shop;

import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.PropertyValue;
import com.alatai.jishop.entity.Review;
import com.alatai.jishop.service.ProductService;
import com.alatai.jishop.service.PropertyValueService;
import com.alatai.jishop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alatai
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
}
