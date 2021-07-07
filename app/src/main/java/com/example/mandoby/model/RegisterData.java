package com.example.mandoby.model;

public class RegisterData {

    private String phone;
    private String otp;
    private String name;

    public RegisterData(String phone, String otp, String name) {
        this.phone = phone;
        this.otp = otp;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
