package com.example.mandoby.model;

public class UploadedPost {
    int PostID;
    String phone,
            name,
            productType,
            productName,
            amount,
            imageUrl,
            government,
            userType,
            area,
            Date;

    public UploadedPost(int postID, String phone, String name, String productType, String productName, String amount, String imageUrl
            , String government, String userType, String area, String date) {
        PostID = postID;
        this.phone = phone;
        this.name = name;
        this.productType = productType;
        this.productName = productName;
        this.amount = amount;
        this.imageUrl = imageUrl;
        this.government = government;
        this.userType = userType;
        this.area = area;
        Date = date;
    }
}
