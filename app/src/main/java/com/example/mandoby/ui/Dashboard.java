package com.example.mandoby.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.mandoby.R;
import com.example.mandoby.ui.User.Auth.Login;
import com.example.mandoby.ui.posts.AddPost;
import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Hooks
    ImageView AddPost;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView sideBarImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //declaration
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        sideBarImg = findViewById(R.id.side_menu);

        // Drawer Nav
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , drawerLayout,R.string.nav_open , R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);


        // the adding post details
        AddPost = (ImageView) findViewById(R.id.add_post);
        AddPost.setClickable(true);
        AddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, AddPost.class);
                startActivity(intent);
            }
        });
    }


    //Check if the drawable is open if true don't clos the app instead close the drawable
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
        return true;
    }
}