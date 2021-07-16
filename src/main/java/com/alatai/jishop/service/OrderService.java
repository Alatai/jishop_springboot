package com.alatai.jishop.service;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/16 11:36
 */
public interface OrderService {

    String WAIT_PAY = "waitPay";
    String WAIT_DELIVER = "waitDelivery";
    String WAIT_CONFIRM = "waitConfirm";
    String WAIT_REVIEW = "waitReview";
    String FINISHED = "finish";
    String DELETED = "delete";


}
