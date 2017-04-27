package com.example.xuhan.lazyorder.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xuhan on 2017/4/6.
 */

public class Good implements Parcelable{
    private int imageId;
    private String goodName;
    private String goodSaleVolume;
    private String goodPrice;
    private String goodProjectId;
    private String goodProjectTitle;
    private int goodSelectedNumber = 0;

    public int getGoodSelectedNumber() {
        return goodSelectedNumber;
    }

    public void setGoodSelectedNumber(int goodSelectedNumber) {
        this.goodSelectedNumber = goodSelectedNumber;
    }

    public Good() {
    }

    public Good(int imageId, String goodName, String goodSaleVolume, String goodPrice, String goodProjectId, String goodProjectTitle) {
        this.imageId = imageId;
        this.goodName = goodName;
        this.goodSaleVolume = goodSaleVolume;
        this.goodPrice = goodPrice;
        this.goodProjectId = goodProjectId;
        this.goodProjectTitle = goodProjectTitle;
    }

    public Good(int imageId, String goodName, String goodSaleVolume, String goodPrice, String goodProjectId, String goodProjectTitle, int goodSelectedNumber) {
        this.imageId = imageId;
        this.goodName = goodName;
        this.goodSaleVolume = goodSaleVolume;
        this.goodPrice = goodPrice;
        this.goodProjectId = goodProjectId;
        this.goodProjectTitle = goodProjectTitle;
        this.goodSelectedNumber = goodSelectedNumber;
    }

    public String getGoodProjectTitle() {
        return goodProjectTitle;
    }

    public void setGoodProjectTitle(String goodProjectTitle) {
        this.goodProjectTitle = goodProjectTitle;
    }

    public String getGoodProjectId() {
        return goodProjectId;
    }

    public void setGoodProjectId(String goodProjectId) {
        this.goodProjectId = goodProjectId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodSaleVolume() {
        return goodSaleVolume;
    }

    public void setGoodSaleVolume(String goodSaleVolume) {
        this.goodSaleVolume = goodSaleVolume;
    }

    public String getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
        dest.writeString(goodName);
        dest.writeString(goodSaleVolume);
        dest.writeString(goodPrice);
        dest.writeString(goodProjectId);
        dest.writeString(goodProjectTitle);
        dest.writeInt(goodSelectedNumber);
    }

    public static final Parcelable.Creator<Good> CREATOR = new Parcelable.Creator<Good>(){

        @Override
        public Good createFromParcel(Parcel source) {
            Good good = new Good();
            good.imageId = source.readInt();
            good.goodName = source.readString();
            good.goodSaleVolume = source.readString();
            good.goodPrice = source.readString();
            good.goodProjectId = source.readString();
            good.goodProjectTitle = source.readString();
            good.goodSelectedNumber = source.readInt();
            return good;
        }

        @Override
        public Good[] newArray(int size) {
            return new Good[size];
        }
    };

}
