package com.example.xuhan.lazyorder.model;

/**
 * Created by xuhan on 2017/4/14.
 */

public class PayWayItem {
    String payName, payDetail;
    int payImage;

    public PayWayItem(String payName, String payDetail, int payImage) {
        this.payName = payName;
        this.payDetail = payDetail;
        this.payImage = payImage;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPayDetail() {
        return payDetail;
    }

    public void setPayDetail(String payDetail) {
        this.payDetail = payDetail;
    }

    public int getPayImage() {
        return payImage;
    }

    public void setPayImage(int payImage) {
        this.payImage = payImage;
    }
}
