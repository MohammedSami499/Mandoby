package com.example.mandoby.ui.posts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mandoby.R;
import com.example.mandoby.ui.Dashboard;
import com.example.mandoby.ui.MainActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class AddPost extends AppCompatActivity {
    ImageView product_photo;
    Button take_photo;
    ImageView imgAddPost;
    TextView txtAddPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        product_photo =findViewById(R.id.imv_product_pic);
        take_photo = findViewById(R.id.btn_takephoto);
        imgAddPost=findViewById(R.id.add_post);
        txtAddPost=findViewById(R.id.add_post_text);


        // action when the button of take a photo is clicked
        take_photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(AddPost.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

    imgAddPost.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(AddPost.this, ClientPosts.class);
            startActivity(intent);
        }
    });

        txtAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPost.this, ClientPosts.class);
                startActivity(intent);
            }
        });




    }


    // method to store the photo which captured in the image view
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        product_photo.setImageURI(uri);
    }
}