package com.alatai.jishop.service;

import com.alatai.jishop.entity.Product;
import com.alatai.jishop.util.PageResult;

import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/15 13:34
 */
public interface ProductService {

    List<Product> findAll();

    PageResult<Product> findAll(int cid, int start, int size, int displayPages);

    Product findById(int id);

    Product insert(Product product);

    Product update(Product product);

    void deleteById(int id);

    void loadFirstImage(List<Product> products);
}
