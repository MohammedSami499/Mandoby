package com.example.mandoby.ui.posts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mandoby.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.IOException;

public class AddPost extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView product_photo,imgAddPost;
    Button take_photo,addPost;
    TextView txtAddPost;
    Spinner spinner;

    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        product_photo =findViewById(R.id.imv_product_pic);
        take_photo = findViewById(R.id.btn_takephoto);
        imgAddPost=findViewById(R.id.add_post);
        txtAddPost=findViewById(R.id.add_post_text);
        spinner=findViewById(R.id.spinner_type);
        addPost=findViewById(R.id.btn_addpost);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this
                ,R.array.Unit, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);


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
            Intent intent = new Intent(AddPost.this, MandopPosts.class);
            startActivity(intent);
        }
    });

        txtAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPost.this, MandopPosts.class);
                startActivity(intent);
            }
        });
    }


    // method to store the photo which captured in the image view
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();

        try {
            bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            product_photo.setImageBitmap(bitmap);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}