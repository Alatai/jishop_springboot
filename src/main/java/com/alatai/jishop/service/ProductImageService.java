package com.alatai.jishop.service;

import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ProductImageService
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/15 14:49
 */
public interface ProductImageService {

    String TYPE_SINGLE = "type_single"; // 商品写真タイプ
    String TYPE_DETAIL = "type_detail"; // 商品写真タイプ

    List<ProductImage> findAll();

    List<ProductImage> findSingleProductImages(Product product);

    List<ProductImage> findDetailProductImages(Product product);

    ProductImage findById(Integer id);

    ProductImage insert(ProductImage productImage);

    ProductImage update(ProductImage productImage);

    void deleteById(Integer id);

    void uploadAndInsert(Integer pid, String type, HttpServletRequest request, MultipartFile image);
}
