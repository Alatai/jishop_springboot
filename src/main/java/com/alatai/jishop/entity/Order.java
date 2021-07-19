package com.alatai.jishop.entity;

import com.alatai.jishop.service.OrderService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Order
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/16 11:30
 */
@Entity
@Table(name = "Orders")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String orderCode;

    private String address;

    private String postCode;

    private String receiver;

    private String phoneNumber;

    private String remark;

    private Date createdDate;

    private Date paidDate;

    private Date deliveredDate;

    private Date confirmedDate;

    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;

    private String status;

    @Transient
    private Float amount; // 金額

    @Transient
    private Integer number; // 数

    @Transient
    private String statusDesc;

    @Transient
    private List<OrderItem> orderItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public Date getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(Date confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStatusDesc() {
        String desc;

        switch (status) {
            case OrderService.WAIT_PAY:
                desc = "支払い待ち";
                break;
            case OrderService.WAIT_DELIVER:
                desc = "出荷待ち";
                break;
            case OrderService.WAIT_CONFIRM:
                desc = "受け取り待ち";
                break;
            case OrderService.WAIT_REVIEW:
                desc = "評価待ち";
                break;
            case OrderService.FINISHED:
                desc = "完成";
                break;
            case OrderService.DELETED:
                desc = "削除";
                break;
            default:
                desc = "エラー";
        }

        return desc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
