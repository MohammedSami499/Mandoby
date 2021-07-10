package com.example.mandoby.ui.posts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mandoby.R;
import com.example.mandoby.adabters.MandopPostsAdapter;
import com.example.mandoby.model.Post;
import com.example.mandoby.viewModels.ClientPostViewModel;
import com.example.mandoby.viewModels.MandopPostViewModel;

import java.util.List;

public class MandopPosts extends AppCompatActivity {

    MandopPostViewModel postViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandop_posts);

        postViewModel = ViewModelProviders.of(this).get(MandopPostViewModel.class);
        postViewModel.getPosts();
        RecyclerView recyclerView= findViewById(R.id.RV_posts);
        MandopPostsAdapter myAdapter= new MandopPostsAdapter();
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