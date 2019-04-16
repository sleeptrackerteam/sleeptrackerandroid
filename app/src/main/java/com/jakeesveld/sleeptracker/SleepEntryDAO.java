package com.jakeesveld.sleeptracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SleepEntryDAO {
    private static final String URL_PREFIX = "https://sleeper-app.herokuapp.com/api/";
    private static final String URL_AUTH_PREFIX = "auth/";
    private static final String URL_GET_USERS = "user/";
    private static final String URL_REGISTER = "register";
    private static final String URL_LOGIN = "login";
    private static final String URL_LOGUOUT = "logout";
    private static String SESSION_TOKEN;
    private static Map<String, String> headerProperties;

    public SleepEntryDAO() {
        headerProperties = new HashMap<>();
        headerProperties.put("Content-Type", "application/json");
    }

    public void loginHandler(JSONObject userInfo) {
        String urlString = URL_PREFIX + URL_AUTH_PREFIX + URL_LOGIN;
        String result = NetworkAdapter.httpRequest(urlString, "POST", userInfo, headerProperties);
        try {
            JSONObject resultJson = new JSONObject(result);
            SESSION_TOKEN = resultJson.getString("token");
            String topIdString = resultJson.getString("message");
            String[] idStringArray = topIdString.split(",");
            String idString = idStringArray[1];
            HomeActivity.usersDao.setUserId(Integer.parseInt(idString));
            HomeActivity.usersDao.setUsername(userInfo.getString("username"));
            HomeActivity.usersDao.setPassword(userInfo.getString("password"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        headerProperties.put("Authorization", SESSION_TOKEN);
    }

    public void registerHandler(JSONObject userInfo) {
        String urlString = URL_PREFIX + URL_AUTH_PREFIX + URL_REGISTER;
        String result = NetworkAdapter.httpRequest(urlString, "POST", userInfo, headerProperties);
        try {
            JSONObject resultJson = new JSONObject(result);
            SESSION_TOKEN = resultJson.getString("token");
            int userId = resultJson.getInt("id");
            HomeActivity.usersDao.setUserId(userId);
            HomeActivity.usersDao.setUsername(userInfo.getString("username"));
            HomeActivity.usersDao.setPassword(userInfo.getString("password"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        headerProperties.put("Authorization", SESSION_TOKEN);
    }

    public ArrayList<SleepEntry> getAllEntries(){
        ArrayList<SleepEntry> entries = new ArrayList<>();
        String urlString = URL_PREFIX + URL_GET_USERS + HomeActivity.usersDao.getUserId();
        String result = NetworkAdapter.httpRequest(urlString, headerProperties);
        try {
            JSONArray resultArray = new JSONArray(result);
            for(int i = 0; i < resultArray.length(); ++i){
                JSONObject entry = resultArray.getJSONObject(i);
                SleepEntry sleepEntry = new SleepEntry(entry);
                entries.add(sleepEntry);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entries;
    }

/*
    public int getUserId(String username) {
        int id = 0;
        String urlString = URL_PREFIX + URL_GET_USERS;
        String result = NetworkAdapter.httpRequest(urlString, "GET", null, headerProperties);
        try {
            JSONArray resultJson = new JSONArray(result);
            for (int i = 0; i < resultJson.length(); ++i) {
                JSONObject object = resultJson.getJSONObject(i);
                String usernameJson = object.getString("username");
                if(usernameJson.equals(username)){
                    id = object.getInt("id");
                }
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return id;
    }*/

}

