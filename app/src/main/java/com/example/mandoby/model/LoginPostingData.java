package com.example.mandoby.model;

public class LoginPostingData {
    private String phone;
    private String otp;

    public LoginPostingData(String phone , String otp) {
        this.phone = phone;
        this.otp = otp;
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
