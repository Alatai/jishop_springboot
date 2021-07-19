package com.alatai.jishop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Product
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/15 12:27
 */
@Entity
@Table(name = "Product")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;

    private String subtitle;

    private Float originalPrice;

    private Float promotePrice;

    private Integer stock;

    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "cid")
    private Category category;

    @Transient // 臨時の変数、データベースに書き込まない
    private ProductImage firstImage;

    @Transient
    private List<ProductImage> singleImages;

    @Transient
    private List<ProductImage> detailImages;

    @Transient
    private Integer saleCount;

    @Transient
    private Integer reviewCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Float getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(Float promotePrice) {
        this.promotePrice = promotePrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductImage getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(ProductImage firstImage) {
        this.firstImage = firstImage;
    }

    public List<ProductImage> getSingleImages() {
        return singleImages;
    }

    public void setSingleImages(List<ProductImage> singleImages) {
        this.singleImages = singleImages;
    }

    public List<ProductImage> getDetailImages() {
        return detailImages;
    }

    public void setDetailImages(List<ProductImage> detailImages) {
        this.detailImages = detailImages;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }
}
