package com.alatai.jishop.service;

import com.alatai.jishop.entity.Category;
import com.alatai.jishop.entity.OrderItem;
import com.alatai.jishop.entity.Product;
import com.alatai.jishop.util.PageResult;

import java.util.List;

/**
 * ProductService
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/15 13:34
 */
public interface ProductService {

    List<Product> findAll();

    PageResult<Product> findAll(Integer cid, Integer start, Integer size, Integer displayPages);

    PageResult<Product> findByKeyword(String keyword, Integer start, Integer size, Integer displayPages);

    List<Product> findByCategory(Category category);

    Product findById(Integer id);

    Product insert(Product product);

    Product update(Product product);

    void deleteById(Integer id);

    void associateFirstImage(Product product);

    void associateFirstImage(List<Product> products);

    /**
     * 分類を基に、関連している商品を検索
     */
    void fillRows(List<Category> categories);

    /**
     * 商品と商品分類を関連する
     */
    void associateCategory(List<Category> categories);

    /**
     * 会計ページ、オーダー詳細を関連する
     */
    void associateOrderItem(List<OrderItem> orderItems);

    /**
     * 商品詳細ページのデータ
     */
    Product productDetail(Product product);
}
