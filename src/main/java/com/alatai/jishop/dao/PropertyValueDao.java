package com.alatai.jishop.dao;

import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.Property;
import com.alatai.jishop.entity.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/15 21:45
 */
public interface PropertyValueDao extends JpaRepository<PropertyValue, Integer> {

    List<PropertyValue> findByProduct(Product product);

    PropertyValue findByProductAndProperty(Product product, Property property);
}
