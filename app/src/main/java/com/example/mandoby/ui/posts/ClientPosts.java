package com.example.mandoby.ui.posts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mandoby.R;
import com.example.mandoby.adabters.ClientPostsAdapter;
import com.example.mandoby.model.Post;
import com.example.mandoby.viewModels.ClientPostViewModel;

import java.util.List;

public class ClientPosts extends AppCompatActivity {

        ClientPostViewModel postViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_posts);

        postViewModel = ViewModelProviders.of(this).get(ClientPostViewModel.class);
        postViewModel.getPosts();
        RecyclerView recyclerView= findViewById(R.id.RV_posts);
        ClientPostsAdapter myAdapter= new ClientPostsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false));
        recyclerView.setAdapter(myAdapter);
        postViewModel.postMutableLiveData.observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> post) {
                myAdapter.setPostsList( post);
            }
        });
    }
}