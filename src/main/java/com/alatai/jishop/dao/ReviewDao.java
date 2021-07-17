package com.alatai.jishop.dao;

import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/17 10:47
 */
public interface ReviewDao extends JpaRepository<Review, Integer> {

    List<Review> findByProduct(Product product);

    int countByProduct(Product product);

}
