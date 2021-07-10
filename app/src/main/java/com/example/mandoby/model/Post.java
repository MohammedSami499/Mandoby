package com.example.mandoby.model;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.Date;
import java.util.Random;

public class Post {

    int PostID, amount;
    String phone;

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    String name;
    String productType;
    String productName;
    String unit;
    String imageurl;
    String government;
    String userType;
    String area;
    String Date;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Post(int postID, int amount, String phone, String name
            , String productType, String productName, String unit,
                String government, String userType, String area, String date)
    {
        PostID = postID;
        this.amount = amount;
        this.phone = phone;
        this.name = name;
        this.productType = productType;
        this.productName = productName;
        this.unit = unit;
        this.government = government;
        this.userType = userType;
        this.area = area;
        Date = date;
    }

    public int getPostID() {
        return PostID;
    }

    public void setPostID(int postID) {
        PostID = postID;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getGovernment() {
        return government;
    }

    public void setGovernment(String government) {
        this.government = government;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }


}
