package com.example.mandoby.model;

public class UploadedPost {
    int PostID , amount;
    String phone,
            name,
            productType,
            productName,
            unit,
            imageUrl,
            government,
            userType,
            area,
            Date;

    public UploadedPost(int postID, int amount, String phone, String name, String productType, String productName, String unit, String imageUrl, String government, String userType, String area, String date) {
        PostID = postID;
        this.amount = amount;
        this.phone = phone;
        this.name = name;
        this.productType = productType;
        this.productName = productName;
        this.unit = unit;
        this.imageUrl = imageUrl;
        this.government = government;
        this.userType = userType;
        this.area = area;
        Date = date;
    }
}
