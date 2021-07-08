package com.example.mandoby.ui.User.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mandoby.Network.MandInterface;
import com.example.mandoby.R;
import com.example.mandoby.model.UserInfo;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    Button nextButton;
    TextInputEditText phoneNumET;
    String phone ;
    ProgressBar progressBar;
    Intent intent;
    String WaledOTP;

    String LogInURL = "https://mandoop-auth-service.herokuapp.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        nextButton = (Button) findViewById(R.id.next_btn);
        phoneNumET = (TextInputEditText) findViewById(R.id.phone_num_ET);
        progressBar = (ProgressBar) findViewById(R.id.otp_register_progressbar);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phone = phoneNumET.getText().toString();

                if (phone != null) {


                    UserInfo userInfo = new UserInfo(phone);
                    nextButton.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                    //retrofit works
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(LogInURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    MandInterface logInInterface = retrofit.create(MandInterface.class);

                    Call<UserInfo> call = logInInterface.loginToApi(userInfo);
                    call.enqueue(new Callback<UserInfo>() {
                        @Override
                        public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {

                            if (response.isSuccessful()) {
                                Log.i("On success", response.body().getName() == null ? "null" : response.body().getOtp());
                                Log.i("On success", response.body().getOtp());
                                WaledOTP = response.body().getOtp();

                                    //Sending the verification message
                                    sendOTPtoUser(response.body().getPhone() , response.body().getName());

                                } else {

                                Log.i("On not success", response.message());
                                nextButton.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.INVISIBLE);
                            }

                        }

                        @Override
                        public void onFailure(Call<UserInfo> call, Throwable t) {
                            Log.i("On failure", t.getMessage());
                            nextButton.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });

                }else{
                    Toast.makeText(Login.this, "You must enter a number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        nextButton.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        super.onStop();
    }


    private void sendOTPtoUser(String phone, String name) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+20" + phone,
                60,
                TimeUnit.SECONDS,
                Login.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {
                        progressBar.setVisibility(View.GONE);
                        nextButton.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {
                        progressBar.setVisibility(View.GONE);
                        nextButton.setVisibility(View.VISIBLE);
                        Toast.makeText(Login.this, "There is an error with this phone", Toast.LENGTH_SHORT).show();
                        Log.i("Error :", e.getMessage());
                    }

                    @Override
                    public void onCodeSent(@NonNull @NotNull String sentOTP, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        progressBar.setVisibility(View.GONE);
                        nextButton.setVisibility(View.VISIBLE);
                        Toast.makeText(Login.this, "the name is " + name, Toast.LENGTH_SHORT).show();
                        if (name.isEmpty()){
                            //new user

                            Log.i("The name" , name);
                            intent.putExtra("phone", phone);
                            intent.putExtra("Waled_otp", WaledOTP);
                            intent.putExtra("origin_otp", sentOTP);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Login.this, "the name is " + name, Toast.LENGTH_SHORT).show();
                            //user is already exists
                            intent = new Intent(getApplicationContext(), ConfirmLoginOtp.class);
                            intent.putExtra("phone", phone);
                            intent.putExtra("name", name);
                            intent.putExtra("Waled_otp", WaledOTP);
                            intent.putExtra("origin_otp", sentOTP);
                            startService(intent);
                        }



                    }
                }
        );
    }
}