package com.example.mandoby.ui.posts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mandoby.Network.PostInterface;
import com.example.mandoby.R;
import com.example.mandoby.model.UploadedPost;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddPost extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView product_photo,imgAddPost;
    Button take_photo,addPost;
    TextView productname,quantity, addedGovernment,addpostArea;
    RadioButton client, mandop;
    Spinner spinner;
    UploadedPost post;

    String phone="01033450442",name="Tarek",selectedItemSpinner ,amount ,imageUrl="https://gp-mandoob-users.herokuapp.com/" ,Date = "55555" , area
            ,productType ="Food" , productName , government , userType;
    int PostID= 155;
    Call<UploadedPost> call;
    private Bitmap bitmap;


    private static final String BASE_URL = "https://gp-mandoob-orders.herokuapp.com/";
    private PostInterface postInterface;
    private Retrofit retrofit ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        product_photo =findViewById(R.id.imv_product_pic);
        take_photo = findViewById(R.id.btn_takephoto);
        imgAddPost=findViewById(R.id.add_post);
        spinner=findViewById(R.id.spinner_type);
        addPost=findViewById(R.id.btn_addpost);
        productname=findViewById(R.id.add_post_product_name);
        quantity=findViewById(R.id.add_post_quantity);
        addedGovernment =findViewById(R.id.add_post_Governorate);
        addpostArea=findViewById(R.id.add_post_Area);
        client=findViewById(R.id.rbClient);
        mandop=findViewById(R.id.rbMandop);

        // arranging the spinner body
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

    addPost.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            uploadPost();

        }
    });


    }

    private void uploadPost() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postInterface = retrofit.create(PostInterface.class);


       // call = postInterface.uploadClientPost(post);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,75,byteArrayOutputStream);
        byte[] imageInByte= byteArrayOutputStream.toByteArray();

        String encodedImage = Base64.encodeToString(imageInByte,Base64.DEFAULT);

        amount = (quantity.getText().toString())+selectedItemSpinner;
        government = addedGovernment.getText().toString();
        area = addpostArea.getText().toString();
        name = productname.getText().toString();
        Date = DateFormat.getDateInstance().toString();
        productName = productname.getText().toString();

        if(client.isChecked()){
            userType = "client";
            post = new UploadedPost(122,"01033450442","Tarek","Food","Tiger",
                    "155 carton","https://gp-mandoob-users.herokuapp.com/","Gharbia","Client","Tanta"
                    ,"44-44-555");
            call = postInterface.uploadClientPost(post);

        }else if(mandop.isChecked()){
            userType = "mandop";
            post = new UploadedPost(122,"01033450442","Tarek","Food","Tiger",
                    "155 carton","https://gp-mandoob-users.herokuapp.com/","Gharbia","Client","Tanta"
            ,"44-44-555");
            call = postInterface.uploadClientPost(post);
        }
        else{
            Toast.makeText(AddPost.this, "Please select you are mandop or client",Toast.LENGTH_LONG);
            }

        call.enqueue(new Callback<UploadedPost>() {
            @Override
            public void onResponse(Call<UploadedPost> call, Response<UploadedPost> response) {
                Toast.makeText(AddPost.this,"Success",Toast.LENGTH_SHORT );
            }

            @Override
            public void onFailure(Call<UploadedPost> call, Throwable t) {

                System.out.println(t.getMessage().toString());

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
        selectedItemSpinner= text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}