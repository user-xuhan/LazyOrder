package com.example.xuhan.lazyorder.model;

/**
 * Created by xuhan on 2017/4/9.
 */

public class Order {
    String orderImage, orderName, orderState, orderDate, orderPrice;

    public Order(String orderImage, String orderName, String orderState, String orderDate, String orderPrice) {
        this.orderImage = orderImage;
        this.orderName = orderName;
        this.orderState = orderState;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
    }

    public String getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(String orderImage) {
        this.orderImage = orderImage;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }
}
