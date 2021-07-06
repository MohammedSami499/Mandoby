package com.example.mandoby.viewModels;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.mandoby.Network.PostsClient;
import com.example.mandoby.model.Post;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    public MutableLiveData<List<Post>> postMutableLiveData = new MutableLiveData<>();

    public void getPosts(){

        PostsClient.getInstance().getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                postMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                if (t instanceof IOException) {
                    System.out.println("this is an actual network failure :( inform the user and possibly retry");

                }
                else {
                    System.out.println("conversion issue! big problems :(");

                }
            }
        });

    }
}
