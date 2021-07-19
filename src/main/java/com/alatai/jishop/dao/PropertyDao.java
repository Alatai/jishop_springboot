package com.alatai.jishop.dao;

import com.alatai.jishop.entity.Category;
import com.alatai.jishop.entity.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * PropertyDao
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/15 9:44
 */
public interface PropertyDao extends JpaRepository<Property, Integer> {

    Page<Property> findByCategory(Category category, Pageable pageable);

    List<Property> findByCategory(Category category);

}
