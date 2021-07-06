package com.example.mandoby.ui.posts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mandoby.R;
import com.example.mandoby.model.ClientPostsAdapter;
import com.example.mandoby.model.Post;
import com.example.mandoby.viewModels.PostViewModel;
import java.util.List;

public class ClientPosts extends AppCompatActivity {

    PostViewModel  postViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_client_posts);

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        postViewModel.getPosts();
        RecyclerView recyclerView= findViewById(R.id.RV_posts);
        ClientPostsAdapter myAdapter= new ClientPostsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        postViewModel.postMutableLiveData.observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> post) {
                myAdapter.setPostsList( post);
            }
        });

    }

}