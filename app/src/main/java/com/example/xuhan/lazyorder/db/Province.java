package com.example.xuhan.lazyorder.db;

import org.litepal.crud.DataSupport;

/**
 * Created by xuhan on 2017/4/26.
 */

public class Province extends DataSupport{

    private int id;
    private String name;

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
