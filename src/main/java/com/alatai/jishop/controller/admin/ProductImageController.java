package com.alatai.jishop.controller.admin;

import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.ProductImage;
import com.alatai.jishop.service.ProductImageService;
import com.alatai.jishop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/15 15:11
 */
@RestController("productImageController")
@RequestMapping("/admin/data")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private ProductService productService;

    @GetMapping("/products/{pid}/images")
    public List<ProductImage> list(@PathVariable("pid") Integer pid,
                                   @RequestParam("type") String type) {
        Product product = productService.findById(pid);

        if (productImageService.TYPE_SINGLE.equals(type)) {
            return productImageService.findSingleProductImages(product);
        } else if (productImageService.TYPE_DETAIL.equals(type)) {
            return productImageService.findDetailProductImages(product);
        } else {
            return null;
        }
    }

    @PostMapping("/images")
    public void add(Integer pid, String type, HttpServletRequest request, MultipartFile image) {
        productImageService.uploadAndInsert(pid, type, request, image);
    }

    @DeleteMapping("/images/{id}")
    public void delete(@PathVariable("id") Integer id) {
        productImageService.deleteById(id);
    }

}
