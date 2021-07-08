package com.example.mandoby.ui.User.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.mandoby.Network.MandInterface;
import com.example.mandoby.R;
import com.example.mandoby.model.RegisterData;
import com.example.mandoby.model.UserInfo;
import com.example.mandoby.ui.posts.AddPost;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

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
    ProgressBar progressBar;
    TextView ResendOTP;
    String OTPVerificationID;
    String userEnteredOTP;

    public boolean connectionToServer = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hooks
        setContentView(R.layout.activity_register_screen);
        user_name = (TextInputEditText) findViewById(R.id.user_name);
        confirmationButton = (Button)  findViewById(R.id.register_confirm_btn);
        pinViewTOP =(PinView)findViewById(R.id.otp_pinView);
        progressBar = findViewById(R.id.otp_register_progressbar);
        ResendOTP = (TextView) findViewById(R.id.resendOTP);
        OTPVerificationID = getIntent().getStringExtra("origin_otp");


        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        String otp = intent.getStringExtra("Waled_otp");


        confirmationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (pinViewTOP.getText().toString().isEmpty() ) {
                    Toast.makeText(RegisterScreen.this, "Enter a valid OTP", Toast.LENGTH_SHORT).show();
                }else{

                    userEnteredOTP = pinViewTOP.getText().toString();

                    if(OTPVerificationID != null){
                        confirmationButton.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.VISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                OTPVerificationID,
                                userEnteredOTP
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                        confirmationButton.setVisibility(View.INVISIBLE);
                                        progressBar.setVisibility(View.VISIBLE);
                                        if (task.isSuccessful()){ // the entered code is right
                                            retrofitPostingConfirmationData(phone , otp ,user_name.getText().toString() );

                                            if(connectionToServer){
                                                confirmationButton.setVisibility(View.VISIBLE);
                                                progressBar.setVisibility(View.GONE);

                                                Intent intent = new Intent(getApplicationContext() , AddPost.class);
                                                intent.putExtra("user_phone" , phone);
                                                intent.putExtra("user_name" , user_name.getText().toString());
                                                startActivity(intent);
                                                finish();
                                            }else{
                                                Toast.makeText(RegisterScreen.this, "an error occurred", Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            confirmationButton.setVisibility(View.VISIBLE);
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(RegisterScreen.this, "Enter a valid otp", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                    }
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
                    Toast.makeText(RegisterScreen.this, "You are done", Toast.LENGTH_SHORT).show();
                }else {
                    connectionToServer = false;
                }

            }

            @Override
            public void onFailure(Call<RegisterData> call, Throwable t) {
                connectionToServer = false;
            }
        });

    }
}