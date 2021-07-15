package com.alatai.jishop.service;

import com.alatai.jishop.entity.Category;
import com.alatai.jishop.entity.Property;
import com.alatai.jishop.util.PageResult;

import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/15 9:47
 */
public interface PropertyService {

    List<Property> findAll();

    PageResult<Property> findAll(int cid, int start, int size, int displayPages);

    List<Property> findByCategory(Category category);

    Property findById(int id);

    Property insert(Property property);

    Property update(Property property);

    void deleteById(int id);

}
