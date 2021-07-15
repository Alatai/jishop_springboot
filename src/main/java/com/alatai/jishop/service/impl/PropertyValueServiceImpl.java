package com.alatai.jishop.service.impl;

import com.alatai.jishop.dao.PropertyValueDao;
import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.Property;
import com.alatai.jishop.entity.PropertyValue;
import com.alatai.jishop.service.ProductService;
import com.alatai.jishop.service.PropertyService;
import com.alatai.jishop.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/15 21:51
 */
@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    @Autowired
    private PropertyValueDao propertyValueDao;
    @Autowired
    private PropertyService propertyService;

    @Override
    public List<PropertyValue> findAll() {
        return propertyValueDao.findAll();
    }

    @Override
    public PropertyValue findById(int id) {
        return propertyValueDao.getById(id);
    }

    @Override
    public List<PropertyValue> findByProduct(Product product) {
        return propertyValueDao.findByProduct(product);
    }

    @Override
    public PropertyValue findByProductAndProperty(Product product, Property property) {
        return propertyValueDao.findByProductAndProperty(product, property);
    }

    @Override
    public PropertyValue insert(PropertyValue propertyValue) {
        return propertyValueDao.save(propertyValue);
    }

    @Override
    public PropertyValue update(PropertyValue propertyValue) {
        return propertyValueDao.save(propertyValue);
    }

    @Override
    public void deleteById(int id) {
        propertyValueDao.deleteById(id);
    }

    @Override
    public void init(Product product) {
        List<Property> properties = propertyService.findByCategory(product.getCategory());

        for (Property property : properties) {
            PropertyValue propertyValue = findByProductAndProperty(product, property);

            if (propertyValue == null) {
                propertyValue = new PropertyValue();
                propertyValue.setProduct(product);
                propertyValue.setProperty(property);

                insert(propertyValue);
            }
        }
    }
}
