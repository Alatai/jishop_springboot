package com.alatai.jishop.service;

import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.Review;

import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/17 10:52
 */
public interface ReviewService {

    List<Review> findAll();

    List<Review> findByProduct(Product product);

    Review findById(Integer id);

    Review insert(Review review);

    Review update(Review review);

    void deleteById(Integer id);

    int getReviewCount(Product product);
}