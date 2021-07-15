package com.alatai.jishop.dao;

import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/15 14:45
 */
public interface ProductImageDao extends JpaRepository<ProductImage, Integer> {

    List<ProductImage> findByProductAndType(Product product, String type);
}
