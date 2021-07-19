package com.alatai.jishop.service;

import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.Review;
import com.alatai.jishop.entity.User;

import java.util.List;

/**
 * ReviewService
 *
 * @author M20W0324 saihou
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

    void insertReviews(List<Review> reviews, User user);
}
