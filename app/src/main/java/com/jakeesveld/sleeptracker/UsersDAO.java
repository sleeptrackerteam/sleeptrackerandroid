package com.jakeesveld.sleeptracker;

import android.content.Context;
import android.content.SharedPreferences;

public class UsersDAO {
    private static final String USER_ID = "userId";
    private static final String PREFS_KEY = "user";
    SharedPreferences prefs;
    public UsersDAO(Context context) {
        prefs = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
    }

    public void setUserId(int userId) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(USER_ID, userId);
        editor.apply();
    }

    public int getUserId(){
        return prefs.getInt(USER_ID, 0);
    }

    public void setUsername(String username){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_ID + "username", username);
        editor.apply();
    }

    public void setPassword(String password){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_ID + "password", password);
        editor.apply();
    }

    public String getUsername(){
        return prefs.getString(USER_ID + "username", "");
    }

    public String getPassword(){
        return prefs.getString(USER_ID + "password", "");
    }

}
