package com.example.xuhan.lazyorder.model;

/**
 * Created by xuhan on 2017/4/8.
 */

public class Comment {
    String userImage, userName, message, date, completeTime, star;

    public Comment(String userImage, String userName, String message, String date, String completeTime, String star) {
        this.userImage = userImage;
        this.userName = userName;
        this.message = message;
        this.date = date;
        this.completeTime = completeTime;
        this.star = star;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
