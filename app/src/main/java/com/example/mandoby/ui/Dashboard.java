package com.example.mandoby.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mandoby.Network.Sessions;
import com.example.mandoby.R;
import com.example.mandoby.adabters.ClientPostsAdapter;
import com.example.mandoby.adabters.MandopPostsAdapter;
import com.example.mandoby.model.Post;
import com.example.mandoby.ui.User.Auth.Login;
import com.example.mandoby.ui.User.Auth.RegisterScreen;
import com.example.mandoby.ui.posts.AddPost;
import com.example.mandoby.ui.posts.ClientPosts;
import com.example.mandoby.ui.posts.MandopPosts;
import com.example.mandoby.viewModels.ClientPostViewModel;
import com.example.mandoby.viewModels.MandopPostViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static boolean isLoggedIn = false;

    //Hooks
    ImageView AddPost;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView sideBarImg;
    TextView mandopPosts, userPosts;

    RecyclerView clientsRecyclerView;
    RecyclerView mandopRecyclerView;
    ClientPostViewModel postViewModel;
    MandopPostViewModel mandopPostViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //RecyclerViews
        postViewModel = ViewModelProviders.of(this).get(ClientPostViewModel.class);
        postViewModel.getPosts();
        clientsRecyclerView = findViewById(R.id.dashboard_clients_recyclerview);
        ClientPostsAdapter myAdapter= new ClientPostsAdapter();
        clientsRecyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        clientsRecyclerView.setAdapter(myAdapter);
        postViewModel.postMutableLiveData.observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> post) {
                myAdapter.setPostsList( post);
            }
        });

        mandopPostViewModel = ViewModelProviders.of(this).get(MandopPostViewModel.class);
        mandopPostViewModel.getPosts();
        MandopPostsAdapter myMandopAdapter= new MandopPostsAdapter();
        mandopRecyclerView = findViewById(R.id.dashboard_mandop_recyclerview);
        mandopRecyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));
        mandopRecyclerView.setAdapter(myAdapter);
        mandopPostViewModel.postMutableLiveData.observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> post) {
                myMandopAdapter.setPostsList( post);
            }
        });

        //declaration
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        sideBarImg = findViewById(R.id.side_menu);
        mandopPosts = findViewById(R.id.TV_mandop_posts);
        userPosts = findViewById(R.id.TV_user_posts);

        // Drawer Nav
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home_nav);

        // the adding post details
        AddPost = (ImageView) findViewById(R.id.add_post);
        AddPost.setClickable(true);

        Sessions sessions = new Sessions(Dashboard.this);

        AddPost.setOnClickListener(new View.OnClickListener() {
            Intent intent;

            @Override
            public void onClick(View v) {
                if (sessions.checkLogin()) {
                    intent = new Intent(Dashboard.this, AddPost.class);
                } else {
                    intent = new Intent(Dashboard.this, Login.class);
                }
                startActivity(intent);
            }
        });

        // navigator to mandop posts page

        mandopPosts.setOnClickListener(new View.OnClickListener() {
            Intent intent;

            @Override
            public void onClick(View v) {
                intent = new Intent(Dashboard.this, MandopPosts.class);
                startActivity(intent);
            }
        });

        // navigator to mandop posts page

        userPosts.setOnClickListener(new View.OnClickListener() {
            Intent intent;

            @Override
            public void onClick(View v) {
                intent = new Intent(Dashboard.this, ClientPosts.class);
                startActivity(intent);
            }

        });

        Menu menu = navigationView.getMenu();
        if (sessions.checkLogin()){
            menu.findItem(R.id.login).setVisible(false);
            menu.findItem(R.id.profile).setVisible(true);
            menu.findItem(R.id.logout).setVisible(true);
        }else{
            menu.findItem(R.id.login).setVisible(true);
            menu.findItem(R.id.profile).setVisible(false);
            menu.findItem(R.id.logout).setVisible(false);
        }
    }

    @Override
    protected void onRestart() {
        Sessions sessions = new Sessions(Dashboard.this);
        Menu menu = navigationView.getMenu();

        if (sessions.checkLogin()){
            menu.findItem(R.id.login).setVisible(false);
            menu.findItem(R.id.profile).setVisible(true);
            menu.findItem(R.id.logout).setVisible(true);
        }else{
            menu.findItem(R.id.login).setVisible(true);
            menu.findItem(R.id.profile).setVisible(false);
            menu.findItem(R.id.logout).setVisible(false);
        }
        super.onRestart();
    }

    //Check if the drawable is open if true don't clos the app instead close the drawable
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent = new Intent(this, profile.class);
                startActivity(intent);
                break;
            case (R.id.add_post):
                Intent intent2 = new Intent(this, AddPost.class);
                startActivity(intent2);
                break;
            case (R.id.represents):
                Intent intent3 = new Intent(this, MandopPosts.class);
                startActivity(intent3);
                break;
            case (R.id.clients):
                Intent intent4 = new Intent(this, ClientPosts.class);
                startActivity(intent4);
                break;
            case (R.id.login):
            Intent intent5 = new Intent(this, Login.class);
            startActivity(intent5);
            break;

            case (R.id.logout):
                Sessions sessions = new Sessions(Dashboard.this);
                sessions.logoutUserFromSession();
                Menu menu = navigationView.getMenu();
                menu.findItem(R.id.login).setVisible(true);
                menu.findItem(R.id.profile).setVisible(false);
                menu.findItem(R.id.logout).setVisible(false);
                Toast.makeText(this, "You have logged out!!", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }


}