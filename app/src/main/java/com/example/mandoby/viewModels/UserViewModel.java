package com.example.mandoby.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mandoby.Network.PostsClient;
import com.example.mandoby.Network.UserClients;
import com.example.mandoby.model.Post;
import com.example.mandoby.model.UserInfo;
import com.google.firebase.firestore.auth.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    public MutableLiveData<UserInfo> userMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<Post>> postMutableLiveData = new MutableLiveData<>();

    public void getUser(String phone) {
        Call<UserInfo> call = UserClients.getInstance().getUser(phone);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                userMutableLiveData.setValue(response.body());
                System.out.println(response.body().getImageUrl() + " heeeeeey");
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });
    }

    public void getPosts(String phone) {
        PostsClient.getInstance().getPosts(phone).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                postMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    public void storeImage(UserInfo userInfo) {
        UserClients.getInstance().storeImage(userInfo).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                userMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });
    }
}
