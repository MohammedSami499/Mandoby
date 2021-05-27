package com.example.mandoby.Network;

import com.example.mandoby.model.UserInfo;
import com.example.mandoby.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Interface {
    @POST("Users/phone")
    public Call<UserInfo> login(@Body UserInfo userInfo);

    @GET("response")
    public Call<UserResponse> loginResponse();

}
