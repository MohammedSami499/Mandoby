package com.example.mandoby.Network;

import com.example.mandoby.model.Post;
import com.example.mandoby.model.UploadedPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface PostInterface {

    @GET("getPostsForUser/mandop")
   public  Call<List<Post>> getMandopPosts();

    @GET("getPostsForUser/user")
    public Call<List<Post>> getUsersPosts();




    @POST("getPostsForUser/user")
    public Call <UploadedPost> uploadClientPost(@Body UploadedPost post);
}
