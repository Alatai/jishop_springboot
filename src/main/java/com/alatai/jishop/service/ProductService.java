package com.alatai.jishop.service;

import com.alatai.jishop.entity.Category;
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

    List<Product> findByCategory(Category category);

    Product findById(Integer id);

    Product insert(Product product);

    Product update(Product product);

    void deleteById(Integer id);

    void loadFirstImage(List<Product> products);

    /**
     * 分類を基に、関連している商品を検索
     */
    void fillRows(List<Category> categories);

    /**
     * 关联对应分类
     */
    void associateCategory(List<Category> categories);
}
