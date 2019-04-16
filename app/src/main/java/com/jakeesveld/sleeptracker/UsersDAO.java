package com.jakeesveld.sleeptracker;

import android.content.Context;
import android.content.SharedPreferences;

public class UsersDAO {
    public static final String USER_ID = "userId";
    public static final String PREFS_KEY = "user";
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

}
