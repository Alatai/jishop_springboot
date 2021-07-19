package com.alatai.jishop.controller.admin;

import com.alatai.jishop.entity.Product;
import com.alatai.jishop.service.ProductService;
import com.alatai.jishop.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ProductController
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/15 13:43
 */
@RestController("productController")
@RequestMapping("/admin/data")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/categories/{cid}/products")
    public PageResult<Product> list(@PathVariable("cid") Integer cid,
                                    @RequestParam(value = "start", defaultValue = "0") Integer start,
                                    @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start < 0 ? 0 : start;
        PageResult<Product> pageResult = productService.findAll(cid, start, size, 5);
        productService.associateFirstImage(pageResult.getContent());

        return pageResult;
    }

    @GetMapping("/products/{id}")
    public Product getOne(@PathVariable("id") Integer id) {
        return productService.findById(id);
    }

    @PostMapping("/products")
    public void add(@RequestBody Product product) {
        productService.insert(product);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable("id") Integer id) {
        productService.deleteById(id);
    }

    @PutMapping("/products")
    public void update(@RequestBody Product product) {
        productService.update(product);
    }
}
