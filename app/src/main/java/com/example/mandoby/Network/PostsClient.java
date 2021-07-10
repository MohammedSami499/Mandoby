package com.example.mandoby.Network;

import com.example.mandoby.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsClient {

    private static final String BASE_URL = "https://gp-mandoob-orders.herokuapp.com/";
    private PostInterface postInterface;
    private static PostsClient Instance;

    public PostsClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postInterface = retrofit.create(PostInterface.class);
    }

    public static PostsClient getInstance() {
        if (null == Instance){
            Instance=new PostsClient();
        }
        return Instance;
    }

    public Call<List<Post>> getUsersPosts(){

        return postInterface.getUsersPosts();
    }

    public Call<List<Post>> getPosts(String phone) {
        return postInterface.getPosts(phone);
    }

}
