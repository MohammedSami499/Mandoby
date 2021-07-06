package com.example.mandoby.ui.User.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.mandoby.R;
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

        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        String otp = intent.getStringExtra("top");

        confirmationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("PinView", "pinView Data : "+ pinViewTOP.getText().toString());
                Toast.makeText(RegisterScreen.this,"Hello : "+ pinViewTOP.getText().toString(), Toast.LENGTH_SHORT).show();
                if(pinViewTOP.getText().toString().equals(otp)){
                    Log.i("Data correct", otp);
                }else {
                    Log.i("Data error", pinViewTOP.getText().toString());
                    Log.i("Original ", otp);
                }
            }
        });


    }
}