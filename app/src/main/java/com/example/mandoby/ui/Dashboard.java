package com.example.mandoby.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.mandoby.R;
import com.example.mandoby.ui.User.Auth.Login;
import com.example.mandoby.ui.posts.AddPost;

public class Dashboard extends AppCompatActivity {
    //Hooks
    ImageView AddPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AddPost = (ImageView) findViewById(R.id.add_post);
        AddPost.setClickable(true);
        AddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Login.class);
                startActivity(intent);
            }
        });
    }
}