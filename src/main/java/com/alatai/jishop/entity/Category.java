package com.alatai.jishop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Category
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/12 17:54
 */
@Entity
@Table(name = "Category")
// リターンのJSONタイプデータ中に、以下のフィールドを排除する
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;

    @Transient
    private List<Product> products;

    @Transient
    private List<List<Product>> productsByRow;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<List<Product>> getProductsByRow() {
        return productsByRow;
    }

    public void setProductsByRow(List<List<Product>> productsByRow) {
        this.productsByRow = productsByRow;
    }
}
