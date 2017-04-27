package com.example.xuhan.lazyorder.db;

import org.litepal.crud.DataSupport;

/**
 * Created by xuhan on 2017/4/26.
 */

public class City extends DataSupport {
    private int id;
    private String name;
    private int provinceId;

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
