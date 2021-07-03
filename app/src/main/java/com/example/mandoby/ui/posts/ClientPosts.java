package com.example.mandoby.ui.posts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mandoby.R;
import com.example.mandoby.model.Post;
import com.example.mandoby.model.PostAdapter;

import java.util.ArrayList;

public class ClientPosts extends AppCompatActivity {

        RecyclerView recyclerView;
        ArrayList<Post> posts;
        PostAdapter myAdapter;
        String[] headings;
        int[] imageResource;

        @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_client_posts);


        recyclerView = findViewById(R.id.RV_posts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        posts= new ArrayList<Post>();

        myAdapter = new PostAdapter(this,posts);
        recyclerView.setAdapter(myAdapter);

        headings = new String[]{
                "aaa aaaa vmvmvmvm rkmfdm elfm dvs ",
                "aaa aaaa vmvmvmvm rkmfdm elfm dvs ",
                "aaa aaaa vmvmvmvm rkmfdm elfm dvs ",
                "aaa aaaa vmvmvmvm rkmfdm elfm dvs ",
                "aaa aaaa vmvmvmvm rkmfdm elfm dvs ",
        };

        imageResource = new int []{
                R.drawable.add_post,
                R.drawable.add_post,
                R.drawable.add_post,
                R.drawable.add_post,
                R.drawable.add_post,

        };
            getData();

    }

    private void getData() {

    for (int i =0 ; i< headings.length;++i){
        Post post =new Post(headings[i],imageResource[i]);
        posts.add(post);
    }
    myAdapter.notifyDataSetChanged();

    }
}