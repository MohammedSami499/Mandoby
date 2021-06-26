package com.example.mandoby.ui.User.Auth;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.mandoby.Network.Interface;
import com.example.mandoby.R;
import com.example.mandoby.model.UserInfo;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterScreen extends AppCompatActivity {

    public static String BASE_URL = "url";
    TextInputEditText user_name;
    PinView pinViewTOP;
    Button confirmationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hooks
        setContentView(R.layout.activity_register_screen);
        user_name = (TextInputEditText) findViewById(R.id.user_name);
        confirmationButton = (Button)  findViewById(R.id.register_confirm_btn);
        pinViewTOP =(PinView)findViewById(R.id.otp_pinView);

        confirmationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("PinView", "pinView Data : "+ pinViewTOP.getText().toString());
                Toast.makeText(RegisterScreen.this,"Hello : "+ pinViewTOP.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}