package com.example.xuhan.lazyorder.model;

/**
 * Created by xuhan on 2017/4/7.
 */

public class Shop {

    String shopName, shopLocation, shopPhone, shopDetail, shopSaleVolume, shopStartPrice, shopDistachPrice,
        shopOpenTime, shopImage, shopCompleteTime, shopDistance, shopStar;

    public Shop(String shopName, String shopSaleVolume, String shopStartPrice, String shopDistachPrice, String shopImage, String shopCompleteTime, String shopDistance, String shopStar) {
        this.shopName = shopName;
        this.shopSaleVolume = shopSaleVolume;
        this.shopStartPrice = shopStartPrice;
        this.shopDistachPrice = shopDistachPrice;
        this.shopImage = shopImage;
        this.shopCompleteTime = shopCompleteTime;
        this.shopDistance = shopDistance;
        this.shopStar = shopStar;
    }

    public String getShopStar() {
        return shopStar;
    }

    public void setShopStar(String shopStar) {
        this.shopStar = shopStar;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getShopDetail() {
        return shopDetail;
    }

    public void setShopDetail(String shopDetail) {
        this.shopDetail = shopDetail;
    }

    public String getShopSaleVolume() {
        return shopSaleVolume;
    }

    public void setShopSaleVolume(String shopSaleVolume) {
        this.shopSaleVolume = shopSaleVolume;
    }

    public String getShopStartPrice() {
        return shopStartPrice;
    }

    public void setShopStartPrice(String shopStartPrice) {
        this.shopStartPrice = shopStartPrice;
    }

    public String getShopDistachPrice() {
        return shopDistachPrice;
    }

    public void setShopDistachPrice(String shopDistachPrice) {
        this.shopDistachPrice = shopDistachPrice;
    }

    public String getShopOpenTime() {
        return shopOpenTime;
    }

    public void setShopOpenTime(String shopOpenTime) {
        this.shopOpenTime = shopOpenTime;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getShopCompleteTime() {
        return shopCompleteTime;
    }

    public void setShopCompleteTime(String shopCompleteTime) {
        this.shopCompleteTime = shopCompleteTime;
    }

    public String getShopDistance() {
        return shopDistance;
    }

    public void setShopDistance(String shopDistance) {
        this.shopDistance = shopDistance;
    }
}
