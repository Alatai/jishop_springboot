package com.alatai.jishop.controller.admin;

import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.PropertyValue;
import com.alatai.jishop.service.ProductService;
import com.alatai.jishop.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/15 22:07
 */
@RestController
@RequestMapping("/admin/data")
public class PropertyValueController {

    @Autowired
    private PropertyValueService propertyValueService;
    @Autowired
    private ProductService productService;

    @GetMapping("/products/{pid}/pvalues")
    public List<PropertyValue> list(@PathVariable("pid") int pid) {
        Product product = productService.findById(pid);
        // 商品を基に属性値を初期化する
        propertyValueService.init(product);

        return propertyValueService.findByProduct(product);
    }

    @PostMapping("/pvalues")
    public String update(@RequestBody List<PropertyValue> propertyValues) {
        for (PropertyValue propertyValue : propertyValues) {
            propertyValueService.update(propertyValue);
        }

        return "success";
    }
}
