package com.example.mandoby;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView SplashImage;
    TextView TitleTV;
    TextView BottomTitleTV;

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

    }
}