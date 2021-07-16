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

    PageResult<Product> findAll(Integer cid, Integer start, Integer size, Integer displayPages);

    Product findById(Integer id);

    Product insert(Product product);

    Product update(Product product);

    void deleteById(Integer id);

    void loadFirstImage(List<Product> products);
}
