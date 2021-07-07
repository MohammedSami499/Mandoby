package com.example.mandoby.ui.User.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.mandoby.Network.MandInterface;
import com.example.mandoby.R;
import com.example.mandoby.model.RegisterData;
import com.example.mandoby.model.UserInfo;
import com.example.mandoby.ui.posts.AddPost;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterScreen extends AppCompatActivity {

    public static String BASE_URL = "https://mandoop-auth-service.herokuapp.com/";
    TextInputEditText user_name;
    PinView pinViewTOP;
    Button confirmationButton;
    RegisterData registerData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hooks
        setContentView(R.layout.activity_register_screen);
        user_name = (TextInputEditText) findViewById(R.id.user_name);
        confirmationButton = (Button)  findViewById(R.id.register_confirm_btn);
        pinViewTOP =(PinView)findViewById(R.id.otp_pinView);

        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        String otp = intent.getStringExtra("otp");


        confirmationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("PinView", "pinView Data : "+ pinViewTOP.getText().toString());


                if(pinViewTOP.getText().toString().equals(otp)){

                    Log.i("OTP ", otp);
                    Log.i("Phone ", phone);
                    Log.i("Name ", user_name.getText().toString());

                    retrofitPostingConfirmationData(phone , otp , user_name.getText().toString());
                }else {
                    Log.i("Data error", pinViewTOP.getText().toString());
                    Log.i("Original ", otp);
                }
            }
        });


    }

    private void retrofitPostingConfirmationData(String phone , String otp , String userName) {

        registerData = new RegisterData(phone , otp , userName);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MandInterface registerInterface = retrofit.create(MandInterface.class);
        Call<RegisterData> userInfoCall = registerInterface.registerConfirmation(registerData);
        userInfoCall.enqueue(new Callback<RegisterData>() {
            @Override
            public void onResponse(Call<RegisterData> call, Response<RegisterData> response) {
                if (response.isSuccessful()){

                    Log.i("On success", response.message());
                    Intent intent = new Intent(getApplicationContext() , AddPost.class);
                    intent.putExtra("phone" , phone);
                    intent.putExtra("name" , userName);
                    startActivity(intent);

                }else {
                    Toast.makeText(RegisterScreen.this, "Enter the right otp", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RegisterData> call, Throwable t) {
                Log.i("On Failure", t.getMessage());
            }
        });

    }
}