package com.alatai.jishop.dao;

import com.alatai.jishop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/12 18:08
 */
public interface CategoryDao extends JpaRepository<Category, Integer> {

}
