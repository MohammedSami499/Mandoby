package com.example.mandoby.model;

public class UserInfo {
    private String phone;
    private String otp;
    private String name;

    public UserInfo(String phone ) {
        this.phone = phone;
    }
    public UserInfo(String phone , String name , String otp){
        this.name = name;
        this.phone = phone;
        this.otp = otp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
