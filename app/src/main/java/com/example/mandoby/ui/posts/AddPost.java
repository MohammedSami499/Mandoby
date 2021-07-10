package com.example.mandoby.ui.posts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mandoby.Network.PostInterface;
import com.example.mandoby.Network.Sessions;
import com.example.mandoby.R;
import com.example.mandoby.model.Model;
import com.example.mandoby.model.UploadedPost;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddPost extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView product_photo,imgAddPost;
    Button take_photo,addPost;
    TextView productname,quantity, addedGovernment,addpostArea,producttype;
    RadioButton client, mandop;
    Spinner spinner;
    UploadedPost post;

    String phone="01033450442",name="Tarek",selectedItemSpinner ,Date = "55555" , area
            ,productType , productName , government , userType;

    String imageUrl ;
    int amount;
    Call<Void> call;
    Uri imageURI;
    private static final int IMAGE_REQUEST = 2;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Image");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();

    private static final String BASE_URL = "https://gp-mandoob-orders.herokuapp.com/";
    private PostInterface postInterface;
    private Retrofit retrofit ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        product_photo =findViewById(R.id.imv_product_pic);
        take_photo = findViewById(R.id.btn_takephoto);
        imgAddPost=findViewById(R.id.add_post);
        spinner=findViewById(R.id.spinner_type);
        addPost=findViewById(R.id.btn_addpost);
        productname=findViewById(R.id.add_post_product_name);
        producttype=findViewById(R.id.add_post_product_type);
        quantity=findViewById(R.id.add_post_quantity);
        addedGovernment =findViewById(R.id.add_post_Governorate);
        addpostArea=findViewById(R.id.add_post_Area);
        client=findViewById(R.id.rbClient);
        mandop=findViewById(R.id.rbMandop);

        // arranging the spinner body
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this
                ,R.array.Unit, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);


        // action when the button of take a photo is clicked
        take_photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                choosePhoto();
            }
        });

        // action when the button of add post is clicked

    addPost.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            amount = Integer.parseInt(quantity.getText().toString());

            government = addedGovernment.getText().toString();
            area = addpostArea.getText().toString();
            name = productname.getText().toString();
            //Date = DateFormat.getDateInstance().toString();
            productName = productname.getText().toString();
            productType=producttype.getText().toString();

            if(mandop.isChecked()){
                userType="mandop";
            }else if(client.isChecked()){
                userType="user";
            }

            //bringing the current user data
            Sessions sessionsUser = new Sessions(AddPost.this);
            HashMap<String , String> userDataFromSession = sessionsUser.getUserDetailsFromSession();
            String userName = userDataFromSession.get(Sessions.UserName);


            post = new UploadedPost(122,amount,"01033450442",userName,productType,productName,selectedItemSpinner
            ,imageUrl,government,userType,area,"");

             retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postInterface = retrofit.create(PostInterface.class);

            call = postInterface.uploadClientPost(post);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(getApplicationContext(),"Posted Successfully"   ,Toast.LENGTH_LONG ).show();
                    System.out.println("Successfully");

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Fail"   ,Toast.LENGTH_SHORT ).show();
                    System.out.println(t.getMessage().toString() + "Fail FAil FAil");

                }
            });


        }
    });

    }

    private void choosePhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data!=null&& data.getData()!= null){
            imageURI = data.getData();
            product_photo.setImageURI(imageURI);
            uploadPhoto();
        }
        else{
            System.out.println("Error Error Error ");
        }
    }

    private void uploadPhoto() {
        if(imageURI !=null){
            uploadToFirebase(imageURI);
        }else{
            Toast.makeText(getApplicationContext(),"please select an Image",Toast.LENGTH_LONG).show();
        }
    }

    private void uploadToFirebase(Uri uri ){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading image...");
        pd.show();


        final String randomKey = UUID.randomUUID().toString();
        StorageReference fileRef = reference.child("images/"+randomKey);
        fileRef.putFile(uri)
               .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Model model = new Model(uri.toString());
                        imageUrl = uri.toString();
                        String modelID = root.push().getKey();
                        root.child(modelID).setValue(model);
                        Toast.makeText(getApplicationContext(),"Uploading Successfully ",Toast.LENGTH_LONG).show();

                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull @NotNull UploadTask.TaskSnapshot taskSnapshot) {
                double progressPercent =(100.00 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                pd.setMessage("Percentage : "+(int) progressPercent +"%");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(getApplicationContext(),"Uploading Failed !!",Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        selectedItemSpinner= text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}