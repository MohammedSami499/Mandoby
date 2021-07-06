package com.example.mandoby.ui.User.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mandoby.Network.MandInterface;
import com.example.mandoby.R;
import com.example.mandoby.model.UserInfo;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    Button nextButton;
    TextInputEditText phoneNumET;
    public String phone;

    String LogInURL = "https://mandoop-auth-service.herokuapp.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nextButton = (Button) findViewById(R.id.next_btn);
        phoneNumET = (TextInputEditText) findViewById(R.id.phone_num_ET);
        UserInfo userInfo = new UserInfo("01277067844" );

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = phoneNumET.getText().toString();

                Log.i("Before Runnable", "Before Runnable ");



                //retrofit works
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(LogInURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                MandInterface logInInterface = retrofit.create(MandInterface.class);

                Call<UserInfo> call = logInInterface.loginToApi(userInfo);
                call.enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(retrofit2.Call<UserInfo> call, Response<UserInfo> response) {
                        if (response.isSuccessful()){
                            Log.i("On success", response.body().getOtp());
                            Log.i("On success", response.body().getName()==null ? "null" : response.body().getName());
                            if (response.body().getName() ==null){
                                sendOTPtoUser(response.body().getOtp());
                                Intent intent = new Intent(getApplicationContext() , RegisterScreen.class);
                                intent.putExtra("phone" , response.body().getPhone());
                                intent.putExtra("top" , response.body().getOtp());
                                startActivity(intent);
                            }
                        }else {
                            Log.i("On not success", response.body().getOtp());
                        }

                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {
                        Log.i("On failure",t.getMessage());
                    }
                });

            }
        });
    }

    private void sendOTPtoUser(String otp) {

    }
}