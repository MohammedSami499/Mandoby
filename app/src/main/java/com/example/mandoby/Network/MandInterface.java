package com.example.mandoby.Network;
import com.example.mandoby.model.LoginPostingData;
import com.example.mandoby.model.RegisterData;
import com.example.mandoby.model.UserInfo;
import com.example.mandoby.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface MandInterface {


    @POST("verify")
    public Call<UserInfo> loginToApi(@Body UserInfo userInfo);

    @POST("verify")
    public Call<LoginPostingData> loginConfirmation(@Body LoginPostingData loginPostingData);

    @POST("verify")
    public Call<RegisterData> registerConfirmation(@Body RegisterData registerData);

}
