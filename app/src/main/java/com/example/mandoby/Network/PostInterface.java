package com.example.mandoby.Network;

import com.example.mandoby.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {

    @GET("getPosts")
    public Call<List<Post>> getPosts();
}
