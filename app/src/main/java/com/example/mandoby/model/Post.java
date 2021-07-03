package com.example.mandoby.model;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.Date;

public class Post {

String name;
String phonenum;
String prodType;
int amount;
String government;
String userType;
String area;
Date dateOfProduction ;
int prodImage;


    public Post(String name/*, String phonenum, String prodType, int amount, String government, String userType, String area, Date dateOfProduction, */,int prodImage) {
        this.name = name;
        /*this.phonenum = phonenum;
        this.prodType = prodType;
        this.amount = amount;
        this.government = government;
        this.userType = userType;
        this.area = area;
        this.dateOfProduction = dateOfProduction;
        */ this.prodImage = prodImage;
    }
}
