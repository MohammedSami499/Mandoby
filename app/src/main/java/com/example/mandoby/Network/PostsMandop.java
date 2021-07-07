package com.example.mandoby.Network;

import com.example.mandoby.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsMandop {

    private static final String BASE_URL = "https://gp-mandoob-orders.herokuapp.com/";
    private PostInterface postInterface;
    private static PostsMandop Instance;

    public PostsMandop() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postInterface = retrofit.create(PostInterface.class);
    }

    public static PostsMandop getInstance() {
        if (null == Instance){
            Instance=new PostsMandop();
        }
        return Instance;
    }

    public Call<List<Post>> getMandopPosts(){

        return postInterface.getMandopPosts();
    }

}
