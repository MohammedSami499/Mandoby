package com.example.mandoby.Network;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Sessions {
    //variables
    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;

<<<<<<< HEAD
    public static final String isLoggedIn = "IsLoggedIn";
=======
    private static final String isLoggedIn = "IsLoggedIn";
>>>>>>> 5f0e975dcdca6227fee90c8acdac2d2a72344929
    public static final String UserName = "UserName";
    public static final String PhoneNumber = "PhoneNumber";

    public Sessions(Context context){
        this.context = context;
        userSession = context.getSharedPreferences("UserSession" , Context.MODE_PRIVATE);
        editor = userSession.edit();
    }

    public void createLoginSession(boolean isLoggedin , String userName , String phoneNumber){
        editor.putBoolean(isLoggedIn , isLoggedin);
        editor.putString(UserName , userName);
        editor.putString(PhoneNumber , phoneNumber);

        editor.commit();
    }

    public HashMap<String , String > getUserDetailsFromSession(){
        HashMap<String , String > userData = new HashMap<>();

        userData.put(UserName , userSession.getString(UserName , null));
        userData.put(PhoneNumber , userSession.getString(PhoneNumber , null));

        return userData;
    }

    public boolean checkLogin(){
        if(userSession.getBoolean(isLoggedIn , false)){
            return true;
        }else
            return false;
    }

    public void logoutUserFromSession(){
        editor.clear();
        editor.commit();
    }

}
