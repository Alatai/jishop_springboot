package com.alatai.jishop.service;

import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.Property;
import com.alatai.jishop.entity.PropertyValue;

import java.util.List;

/**
 * PropertyValueService
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/15 21:50
 */
public interface PropertyValueService {

    List<PropertyValue> findAll();

    PropertyValue findById(Integer id);

    List<PropertyValue> findByProduct(Product product);

    PropertyValue findByProductAndProperty(Product product, Property property);

    PropertyValue insert(PropertyValue propertyValue);

    PropertyValue update(PropertyValue propertyValue);

    void deleteById(Integer id);

    void init(Product product);

}
