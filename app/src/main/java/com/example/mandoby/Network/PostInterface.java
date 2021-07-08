package com.example.mandoby.Network;

import com.example.mandoby.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface PostInterface {

    @GET("getPostsForUser/mandop")
    Call<List<Post>> getMandopPosts();

    @GET("getPostsForUser/user")
    Call<List<Post>> getUsersPosts();


    @Multipart
    @FormUrlEncoded
    @POST("getPostsForUser/user")
    Call <Post> uploadClientPost(@Body Post post);
}
