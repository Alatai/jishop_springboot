package com.alatai.jishop.controller.admin;

import com.alatai.jishop.entity.Product;
import com.alatai.jishop.service.ProductService;
import com.alatai.jishop.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/15 13:43
 */
@RestController
@RequestMapping("/admin/data")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/categories/{cid}/products")
    public PageResult<Product> list(@PathVariable("cid") int cid,
                                    @RequestParam(value = "start", defaultValue = "0") int start,
                                    @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start < 0 ? 0 : start;
        PageResult<Product> pageResult = productService.findAll(cid, start, size, 5);
        productService.loadFirstImage(pageResult.getContent());

        return pageResult;
    }

    @PostMapping("/products")
    public void add(@RequestBody Product product) {
        productService.insert(product);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable("id") int id) {
        productService.deleteById(id);
    }

    @PutMapping("/products")
    public void update(@RequestBody Product product) {
        productService.update(product);
    }
}
