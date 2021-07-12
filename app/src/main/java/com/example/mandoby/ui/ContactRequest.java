package com.example.mandoby.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mandoby.R;
import com.example.mandoby.viewModels.UserViewModel;

public class ContactRequest extends AppCompatActivity {

    UserViewModel userViewModel;
    ImageView img_profile , img_product;
    TextView productType , phoneNum , quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_request);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        img_profile = findViewById(R.id.user);
        img_product = findViewById(R.id.product);
        productType = findViewById(R.id.product_type);
        quantity = findViewById(R.id.quantity);



    }
}