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
    private static final String URL_GET_USERS = "user";
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        headerProperties.put("Authorization", SESSION_TOKEN);
    }

    public void registerHandler(JSONObject userInfo) {
        String urlString = URL_PREFIX + URL_AUTH_PREFIX + URL_REGISTER;
        String result = NetworkAdapter.registerReqest(urlString, userInfo);
        try {
            JSONObject resultJson = new JSONObject(result);
            SESSION_TOKEN = resultJson.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        headerProperties.put("Authorization", SESSION_TOKEN);
    }

    public int getNextId() {
        int id = 0;
        String urlString = URL_PREFIX + URL_GET_USERS;
        String result = NetworkAdapter.httpRequest(urlString, "GET", null, headerProperties);
        try {
            JSONArray resultJson = new JSONArray(result);
            for (int i = 0; i < resultJson.length(); ++i) {
                JSONObject object = resultJson.getJSONObject(i);
                int nextId = object.getInt("id");
                if (nextId >= id) {
                    id = nextId + 1;
                }
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return id;
    }

}

