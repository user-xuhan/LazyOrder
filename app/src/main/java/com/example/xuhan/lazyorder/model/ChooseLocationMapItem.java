package com.example.xuhan.lazyorder.model;

import com.amap.api.maps.model.LatLng;

/**
 * Created by xuhan on 2017/4/26.
 */

public class ChooseLocationMapItem {

    private String poiName;
    private String detailAddress;
    private LatLng latLng;

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
