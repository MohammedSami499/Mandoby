package com.example.mandoby.Network;

import com.example.mandoby.model.UserInfo;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserClients {

    private static final String BASE_URL = "https://gp-mandoob-users.herokuapp.com/";
    private PostInterface postInterface;
    private static UserClients Instance;

    public UserClients() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postInterface = retrofit.create(PostInterface.class);
    }

    public static UserClients getInstance() {
        if (null == Instance){
            Instance=new UserClients();
        }
        return Instance;
    }

    public Call<UserInfo> getUser(String phone) {
        return postInterface.getUser(phone);
    }

    public Call<UserInfo> storeImage(UserInfo userInfo) { return postInterface.storeImage(userInfo); }
}
