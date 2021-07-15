package com.alatai.jishop.service.impl;

import com.alatai.jishop.dao.PropertyDao;
import com.alatai.jishop.entity.Category;
import com.alatai.jishop.entity.Property;
import com.alatai.jishop.service.CategoryService;
import com.alatai.jishop.service.PropertyService;
import com.alatai.jishop.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/15 9:49
 */
@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyDao propertyDao;
    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Property> findAll() {
        return propertyDao.findAll();
    }

    @Override
    public PageResult<Property> findAll(int cid, int start, int size, int displayPages) {
        Category category = categoryService.findById(cid);
        Pageable pageable = PageRequest.of(start, size);
        Page<Property> page = propertyDao.findByCategory(category, pageable);

        return new PageResult<>(page, displayPages);
    }

    @Override
    public List<Property> findByCategory(Category category) {
        return propertyDao.findByCategory(category);
    }

    @Override
    public Property findById(int id) {
        return propertyDao.getById(id);
    }

    @Override
    public Property insert(Property property) {
        return propertyDao.save(property);
    }

    @Override
    public Property update(Property property) {
        return propertyDao.save(property);
    }

    @Override
    public void deleteById(int id) {
        propertyDao.deleteById(id);
    }
}
