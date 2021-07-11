package com.example.mandoby.model;

public class UserInfo {
    private String phone;
    private String otp;
    private String name;
    private String imageUrl;

    public UserInfo(String phone ) {
        this.phone = phone;
    }

    public UserInfo(String name, String imageUrl , String phone) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.phone = phone;
    }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOtp() {
        return otp;
    }
    public void setOtp(String otp) { this.otp = otp; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
