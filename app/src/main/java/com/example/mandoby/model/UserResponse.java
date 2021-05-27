package com.example.mandoby.model;

public class UserResponse {
    private String phone_Num;
    private String user_Id ;
    private String token;
    private String user_Name;

    public String getPhone_Num() {
        return phone_Num;
    }

    public void setPhone_Num(String phone_Num) {
        this.phone_Num = phone_Num;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public UserResponse(String phone_Num, String user_Id, String token, String user_Name) {
        this.phone_Num = phone_Num;
        this.user_Id = user_Id;
        this.token = token;
        this.user_Name = user_Name;
    }
}
