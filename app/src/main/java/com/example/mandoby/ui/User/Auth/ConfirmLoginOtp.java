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
import com.example.mandoby.model.LoginPostingData;
import com.example.mandoby.model.RegisterData;
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

public class ConfirmLoginOtp extends AppCompatActivity {

    public static String BASE_URL = "https://mandoop-auth-service.herokuapp.com/";
    PinView pinViewTOP;
    LoginPostingData loginPostingData;
    Button confirmationButton;
    ProgressBar progressBar;
    TextView ResendOTP;
    String OTPVerificationID;
    String userEnteredOTP;


    public boolean connectionToServer = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_login_otp);

        //hooks
        pinViewTOP = findViewById(R.id.otp_pinView_login);
        confirmationButton = findViewById(R.id.login_confirm_btn);
        progressBar = findViewById(R.id.otp_login_progressbar);
        ResendOTP = findViewById(R.id.resendOTPLogin);



        Intent intent = getIntent();
        String phone = intent.getStringExtra("phone");
        String userName = intent.getStringExtra("name");
        String waledOTP = intent.getStringExtra("Waled_otp");
        OTPVerificationID = getIntent().getStringExtra("origin_otp");

        confirmationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (pinViewTOP.getText().toString().isEmpty() ) {
                    Toast.makeText(ConfirmLoginOtp.this, "Enter a valid OTP", Toast.LENGTH_SHORT).show();
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
                                            retrofitPostingConfirmationData(phone , waledOTP );

                                            if(connectionToServer){
                                                confirmationButton.setVisibility(View.VISIBLE);
                                                progressBar.setVisibility(View.GONE);

                                                Intent intent = new Intent(getApplicationContext() , AddPost.class);
                                                intent.putExtra("user_phone" , phone);
                                                intent.putExtra("user_name" , userName);
                                                startActivity(intent);
                                                finish();
                                            }else{
                                                Toast.makeText(ConfirmLoginOtp.this, "an error occurred", Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            confirmationButton.setVisibility(View.VISIBLE);
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(ConfirmLoginOtp.this, "Enter a valid otp", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                    }
                }

            }
        });

    }

    private void retrofitPostingConfirmationData(String phone , String otp ) {

        loginPostingData = new LoginPostingData(phone , otp );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MandInterface registerInterface = retrofit.create(MandInterface.class);
        Call<LoginPostingData> userInfoCall = registerInterface.loginConfirmation(loginPostingData);
        userInfoCall.enqueue(new Callback<LoginPostingData>() {
            @Override
            public void onResponse(Call<LoginPostingData> call, Response<LoginPostingData> response) {
                if (response.isSuccessful()){
                    Toast.makeText(ConfirmLoginOtp.this, "You are done", Toast.LENGTH_SHORT).show();
                }else {
                    connectionToServer = false;
                }

            }

            @Override
            public void onFailure(Call<LoginPostingData> call, Throwable t) {
                connectionToServer = false;
            }
        });

    }
}