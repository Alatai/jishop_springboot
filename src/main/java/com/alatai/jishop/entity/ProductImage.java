package com.alatai.jishop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * ProductImage
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/15 14:33
 */
@Entity
@Table(name = "ProductImage")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ProductImage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String type;

    private String name;

    @ManyToOne
    @JoinColumn(name = "pid")
    @JsonBackReference // fix infinite recursion
    private Product product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
