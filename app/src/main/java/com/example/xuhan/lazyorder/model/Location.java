package com.example.xuhan.lazyorder.model;

/**
 * Created by xuhan on 2017/4/8.
 */

public class Location {
    String location, phone, name, sex;

    public Location(String location, String phone, String name, String sex) {
        this.location = location;
        this.phone = phone;
        this.name = name;
        this.sex = sex;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
