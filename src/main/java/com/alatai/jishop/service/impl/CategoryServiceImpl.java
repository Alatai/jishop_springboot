package com.alatai.jishop.service.impl;

import com.alatai.jishop.dao.CategoryDao;
import com.alatai.jishop.entity.Category;
import com.alatai.jishop.service.CategoryService;
import com.alatai.jishop.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/12 18:15
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public PageResult<Category> findAll(int start, int size, int displayPages) {
        Pageable pageable = PageRequest.of(start, size);
        Page<Category> page = categoryDao.findAll(pageable);

        return new PageResult<>(page, displayPages);
    }

    @Override
    public Category findById(int id) {
        return categoryDao.getById(id);
    }

    @Override
    public Category insert(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public Category update(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public void deleteById(int id) {
        categoryDao.deleteById(id);
    }
}
