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
    TextView productname,quantity, addedGovernment,addpostArea,producttype;
    RadioButton client, mandop;
    Spinner spinner;
    UploadedPost post;

    String phone="01033450442",name="Tarek",selectedItemSpinner  ,imageUrl="https://gp-mandoob-users.herokuapp.com/" ,Date = "55555" , area
            ,productType , productName , government , userType;

    String imageurl ="https://www.google.com/imgres?imgurl=https%3A%2F%2Fimages.theconversation.com%2Ffiles%2F334114%2Foriginal%2Ffile-20200511-49558-palxym.jpg%3Fixlib%3Drb-1.1.0%26q%3D45%26auto%3Dformat%26w%3D1200%26h%3D1200.0%26fit%3Dcrop&imgrefurl=https%3A%2F%2Ftheconversation.com%2Fcovid-19-pandemic-is-our-chance-to-learn-how-to-reuse-old-medicines-137671&tbnid=SvCuuruTmBnGwM&vet=12ahUKEwjNu6iGgdfxAhXJuqQKHasUB9wQ";
    int PostID= 155 , amount;
    Call<Void> call;
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
        producttype=findViewById(R.id.add_post_product_type);
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

            amount = Integer.parseInt(quantity.getText().toString());

            government = addedGovernment.getText().toString();
            area = addpostArea.getText().toString();
            name = productname.getText().toString();
            //Date = DateFormat.getDateInstance().toString();
            productName = productname.getText().toString();
            productType=producttype.getText().toString();

            if(mandop.isChecked()){
                userType="mandop";
            }else if(client.isChecked()){
                userType="user";
            }

            post = new UploadedPost(122,amount,"01033450442","ALi",productType,productName,selectedItemSpinner
            ,imageurl,government,userType,area,"");

             retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postInterface = retrofit.create(PostInterface.class);

            call = postInterface.uploadClientPost(post);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(getApplicationContext(),"Posted Successfully"   ,Toast.LENGTH_LONG ).show();
                    System.out.println("Successfully");

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Fail"   ,Toast.LENGTH_SHORT ).show();
                    System.out.println(t.getMessage().toString() + "Fail FAil FAil");

                }
            });


        }
    });

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