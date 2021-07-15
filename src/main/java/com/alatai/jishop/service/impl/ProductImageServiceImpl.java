package com.alatai.jishop.service.impl;

import com.alatai.jishop.dao.ProductImageDao;
import com.alatai.jishop.entity.Product;
import com.alatai.jishop.entity.ProductImage;
import com.alatai.jishop.service.ProductImageService;
import com.alatai.jishop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/15 14:54
 */
@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageDao productImageDao;
    @Autowired
    private ProductService productService;

    @Override
    public List<ProductImage> findAll() {
        return productImageDao.findAll();
    }

    @Override
    public List<ProductImage> findSingleProductImages(Product product) {
        return productImageDao.findByProductAndType(product, TYPE_SINGLE);
    }

    @Override
    public List<ProductImage> findDetailProductImages(Product product) {
        return productImageDao.findByProductAndType(product, TYPE_DETAIL);
    }

    @Override
    public ProductImage findById(int id) {
        return productImageDao.getById(id);
    }

    @Override
    public ProductImage insert(ProductImage productImage) {
        return productImageDao.save(productImage);
    }

    @Override
    public ProductImage update(ProductImage productImage) {
        return productImageDao.save(productImage);
    }

    @Override
    public void deleteById(int id) {
        productImageDao.deleteById(id);
    }

    @Override
    public void uploadAndInsert(int pid, String type, HttpServletRequest request, MultipartFile image) {
        Product product = productService.findById(pid);
        ProductImage productImage = new ProductImage();
        productImage.setProduct(product);
        productImage.setType(type);

        // アップロードパス
        String path;

        // 二つのファイルを用意している
        if (ProductImageService.TYPE_SINGLE.equals(productImage.getType())) {
            path = request.getServletContext().getRealPath("/img/single");
        } else {
            path = request.getServletContext().getRealPath("/img/detail");
        }

        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }

        // ファイル名を設置する
        String filename = image.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "_" + filename;

        try {
            image.transferTo(new File(path, filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        productImage.setName(filename);
        insert(productImage);
    }
}
