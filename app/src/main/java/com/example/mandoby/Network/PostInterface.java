package com.example.mandoby.Network;

import com.example.mandoby.model.Post;
import com.example.mandoby.model.UploadedPost;
import com.example.mandoby.model.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostInterface {

    @GET("getPostsForUser/mandop")
   public  Call<List<Post>> getMandopPosts();

    @GET("getPostsForUser/user")
    public Call<List<Post>> getUsersPosts();

    @GET("getUser/{phone}")
    public Call<UserInfo> getUser(@Path("phone") String phone);

    @GET("getPostsWithPhone/{phone}")
    public Call<List<Post>> getPosts(@Path("phone") String phone);

    @PUT("updateUser")
    public Call<UserInfo> storeImage(@Body UserInfo userInfo);

    @POST("add")
    public Call <Void> uploadClientPost(@Body UploadedPost post);
}
