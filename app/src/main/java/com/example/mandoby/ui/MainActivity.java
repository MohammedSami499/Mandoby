package com.example.mandoby.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mandoby.R;

public class MainActivity extends AppCompatActivity {

    //Hooks
    ImageView SplashImage;
    TextView TitleTV;
    TextView BottomTitleTV;

    //The time of the splash screen
    private static int SPLASH_SCREEN = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        SplashImage = (ImageView) findViewById(R.id.splashScreen);
        TitleTV = (TextView) findViewById(R.id.splash_title);
        BottomTitleTV = (TextView) findViewById(R.id.bottom_title);

        Animation imgUtils = AnimationUtils.loadAnimation(this , R.anim.top_to_down_anime);
        Animation txtUtils = AnimationUtils.loadAnimation(this , R.anim.top_to_down_anime);


        SplashImage.setAnimation(imgUtils);
        TitleTV.setAnimation(txtUtils);
        BottomTitleTV.setAnimation(txtUtils);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this , Dashboard.class);
                startActivity(intent);
                finish();
            }
        } ,SPLASH_SCREEN);

    }
}