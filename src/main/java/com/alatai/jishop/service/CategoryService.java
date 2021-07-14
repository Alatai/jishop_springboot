package com.alatai.jishop.service;

import com.alatai.jishop.entity.Category;
import com.alatai.jishop.util.PageResult;

import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/12 18:14
 */
public interface CategoryService {

    List<Category> findAll();

    PageResult<Category> findAll(int start, int size, int displayPages);

    Category findById(int id);

    Category insert(Category category);

    Category update(Category category);

    void deleteById(int id);

}
