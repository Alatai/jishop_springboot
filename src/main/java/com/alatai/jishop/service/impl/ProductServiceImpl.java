package com.alatai.jishop.service.impl;

import com.alatai.jishop.dao.ProductDao;
import com.alatai.jishop.entity.Category;
import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.ProductImage;
import com.alatai.jishop.service.CategoryService;
import com.alatai.jishop.service.ProductImageService;
import com.alatai.jishop.service.ProductService;
import com.alatai.jishop.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/15 13:35
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductImageService productImageService;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public PageResult<Product> findAll(Integer cid, Integer start, Integer size, Integer displayPages) {
        Category category = categoryService.findById(cid);
        Pageable pageable = PageRequest.of(start, size);
        Page<Product> page = productDao.findByCategory(category, pageable);

        return new PageResult<>(page, displayPages);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productDao.findByCategory(category);
    }

    @Override
    public Product findById(Integer id) {
        return productDao.getById(id);
    }

    @Override
    public Product insert(Product product) {
        product.setCreatedDate(new Date());
        return productDao.save(product);
    }

    @Override
    public Product update(Product product) {
        return productDao.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        productDao.deleteById(id);
    }

    @Override
    public void loadFirstImage(List<Product> products) {
        for (Product product : products) {
            List<ProductImage> singleProductImages = productImageService.findSingleProductImages(product);
            if (!singleProductImages.isEmpty()) {
                product.setFirstImage(singleProductImages.get(0));
            }
        }
    }

    @Override
    public void fillRows(List<Category> categories) {
        int numberForRow = 4;

        for (Category category : categories) {
            List<Product> products = category.getProducts();
            List<List<Product>> productsByRow = new ArrayList<>();

            for (int i = 0; i < products.size(); i += numberForRow) {
                int size = i + numberForRow;
                size = Math.min(size, products.size());

                List<Product> productsForRow = products.subList(i, size);
                productsByRow.add(productsForRow);
            }

            category.setProductsByRow(productsByRow);
        }
    }

    @Override
    public void associateCategory(List<Category> categories) {
        for (Category category : categories) {
            List<Product> products = findByCategory(category);
            loadFirstImage(products);
            category.setProducts(products);
        }
    }
}
