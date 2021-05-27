package com.example.mandoby.ui.User.Auth;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;

import com.example.mandoby.Network.Interface;
import com.example.mandoby.R;
import com.example.mandoby.model.UserInfo;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterScreen extends AppCompatActivity {

    public static String BASE_URL = "url";
    TextInputEditText phone_num;
    String enteredPhoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //hooks
        setContentView(R.layout.activity_register_screen);
        phone_num = (TextInputEditText) findViewById(R.id.signup_phone_number);
        enteredPhoneNum = phone_num.getText().toString();

        //model
        UserInfo userInfo = new UserInfo(enteredPhoneNum);

        //network

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Interface logInInterface = retrofit.create(Interface.class);
        Call<UserInfo> posting = logInInterface.login(userInfo);


    }
}