package com.alatai.jishop.service;

import com.alatai.jishop.entity.Category;
import com.alatai.jishop.util.PageResult;

import java.util.List;

/**
 * CategoryService
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/12 18:14
 */
public interface CategoryService {

    List<Category> findAll();

    PageResult<Category> findAll(Integer start, Integer size, Integer displayPages);

    Category findById(Integer id);

    Category insert(Category category);

    Category update(Category category);

    void deleteById(Integer id);
}
