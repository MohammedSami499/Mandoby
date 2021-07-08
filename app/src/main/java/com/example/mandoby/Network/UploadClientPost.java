package com.example.mandoby.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UploadClientPost {

    private static final String BASE_URL = "https://gp-mandoob-orders.herokuapp.com/";
    private PostInterface postInterface;
    private static UploadClientPost instance;
    private Retrofit retrofit ;

    private UploadClientPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postInterface = retrofit.create(PostInterface.class);
    }

    public static synchronized UploadClientPost getInstance(){

        if(instance==null){
            instance = new UploadClientPost();
        }

        return instance;
    }

    public PostInterface getApi(){

        return retrofit.create(PostInterface.class);
    }

}
