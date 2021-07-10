package com.example.mandoby.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mandoby.R;
import com.example.mandoby.adabters.PostAdapter;
import com.example.mandoby.model.Post;
import com.example.mandoby.model.UserInfo;
import com.example.mandoby.viewModels.UserViewModel;

import java.util.List;

public class profile extends AppCompatActivity {

    UserViewModel userViewModel;
    TextView userName;
    RecyclerView recyclerView;
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userName = findViewById(R.id.tv_name);

        userViewModel.getUser("01018148645");

        userViewModel.userMutableLiveData.observe(this, new Observer<UserInfo>() {
            @Override
            public void onChanged(UserInfo userInfo) {
                userName.setText(userInfo.getName());
            }
        });

        userViewModel.getPosts("01204467121");

        recyclerView = findViewById(R.id.recycler);
        PostAdapter adapter = new PostAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        userViewModel.postMutableLiveData.observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                adapter.setList(posts);
            }
        });

        img_back = findViewById(R.id.img_profile_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this , Dashboard.class);
                startActivity(intent);
            }
        });

    }
}