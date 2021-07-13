package com.example.mandoby.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mandoby.Network.Sessions;
import com.example.mandoby.R;
import com.example.mandoby.adabters.MandopPostsAdapter;
import com.example.mandoby.model.Post;
import com.example.mandoby.model.UserInfo;
import com.example.mandoby.viewModels.UserViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class profile extends AppCompatActivity  {

    UserViewModel userViewModel;
    TextView userName;
    RecyclerView recyclerView;
    ImageView img_back;
    ImageView img_profile;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    UserInfo user;
    String imageUrl;
    String image;
    String phoneNum;
    ImageView img_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Sessions sessionsUser = new Sessions(profile.this);
        HashMap<String , String> userDataFromSession = sessionsUser.getUserDetailsFromSession();
        phoneNum = userDataFromSession.get(Sessions.PhoneNumber);

        Toast.makeText(this,"The phone is : "+  phoneNum, Toast.LENGTH_SHORT).show();

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userName = findViewById(R.id.tv_name);
        img_profile = findViewById(R.id.img_profile_user);

        userViewModel.getUser(phoneNum);

        userViewModel.userMutableLiveData.observe(this, new Observer<UserInfo>() {
            @Override
            public void onChanged(UserInfo userInfo) {
                userName.setText(userInfo.getName());
                image = userInfo.getImageUrl();
                Glide.with(getApplicationContext()).load(userInfo.getImageUrl()).into(img_profile);

            }
        });

        userViewModel.getPosts(phoneNum);
        img_user = findViewById(R.id.img_user);

        recyclerView = findViewById(R.id.recycler);
        MandopPostsAdapter adapter = new MandopPostsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        userViewModel.postMutableLiveData.observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                adapter.setPostsList(posts);
            }
        });

        img_back = findViewById(R.id.img_profile_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this , Dashboard.class);
                startActivity(intent);
            }
        });


        img_profile = findViewById(R.id.img_profile_user);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });

    }
    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent , 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            img_profile.setImageURI(imageUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();

        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/" + randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
//                                Model model = new Model(uri.toString());
                                imageUrl = uri.toString();
                                user = new UserInfo(userName.getText().toString() , imageUrl , phoneNum);
                                userViewModel.storeImage(user);
                                System.out.println(imageUrl + "url");
                            }
                        });
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content) , "image uploaded." , Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext() , "Failed to upload" , Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull @NotNull UploadTask.TaskSnapshot taskSnapshot) {
                        double progressPercent = (100.0 + taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        pd.setMessage("Percentage: " + (int) progressPercent + "%");
                    }
                });
    }

}