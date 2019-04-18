package com.jakeesveld.sleeptracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SleepEntryDAO {
    private static final String URL_PREFIX = "https://sleeper-app.herokuapp.com/api/";
    private static final String URL_AUTH_PREFIX = "auth/";
    private static final String URL_GET_USERS = "user/";
    private static final String URL_REGISTER = "register";
    private static final String URL_LOGIN = "login";
    private static final String URL_SLEEP = "sleep/";
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

            HomeActivity.usersDao.setUserId(resultJson.getInt("user_id"));
            HomeActivity.usersDao.setUsername(userInfo.getString("username"));
            HomeActivity.usersDao.setPassword(userInfo.getString("password"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        headerProperties.put("Authorization", SESSION_TOKEN);
    }

    public void logoutHandler(){
        HomeActivity.usersDao.setUserId(0);
        HomeActivity.usersDao.setPassword("");
        HomeActivity.usersDao.setUsername("");
        headerProperties = new HashMap<>();
        headerProperties.put("Content-Type", "application/json");
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
            JSONObject userLoginInfo = new JSONObject();
            userLoginInfo.put("username", userInfo.getString("username"));
            userLoginInfo.put("password", userInfo.getString("password"));
            loginHandler(userLoginInfo);
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

    public int createEntry(JSONObject json){
        String urlString = URL_PREFIX + URL_SLEEP;
        int entryId = 0;
        String result = NetworkAdapter.httpRequest(urlString, "POST", json, headerProperties);
        try {
            JSONObject object = new JSONObject(result);
            entryId = object.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entryId;
    }

    public void updateEntry(JSONObject jsonObject, int id){
        String urlString = URL_PREFIX + URL_SLEEP + id;
        NetworkAdapter.httpRequest(urlString, "PUT", jsonObject, headerProperties);
    }

    public void deleteEntry(int id){
        String urlString = URL_PREFIX + URL_SLEEP + id;
        NetworkAdapter.httpRequest(urlString, "DELETE", null, headerProperties);
    }

    public ArrayList<Integer> getAverages(){
        ArrayList<SleepEntry> entries = getAllEntries();
        return getAverages(entries);
    }

    public ArrayList<Integer> getAverages(ArrayList<SleepEntry> entries){
        int avg4 = 0, avg5 = 0, avg6 = 0, avg7 = 0, avg8 = 0, avg9 = 0, avg10 = 0, avg11 = 0, avg12 = 0;
        for(SleepEntry entry: entries){
            switch(entry.getTimeSlept()){
                case 4:
                    if(entry.getWakeMoodRating() == 4){
                        avg4 += 2;
                    }else if(entry.getWakeMoodRating() == 3){
                        avg4 += 1;
                    }else if(entry.getWakeMoodRating() == 1){
                        avg4 -= 1;
                    }
                    break;
                case 5:
                    if(entry.getWakeMoodRating() == 4){
                        avg5 += 2;
                    }else if(entry.getWakeMoodRating() == 3){
                        avg5 += 1;
                    }else if(entry.getWakeMoodRating() == 1){
                        avg5 -= 1;
                    }
                    break;
                case 6:
                    if(entry.getWakeMoodRating() == 4){
                        avg6 += 2;
                    }else if(entry.getWakeMoodRating() == 3){
                        avg6 += 1;
                    }else if(entry.getWakeMoodRating() == 1){
                        avg6 -= 1;
                    }
                    break;
                case 7:
                    if(entry.getWakeMoodRating() == 4){
                        avg7 += 2;
                    }else if(entry.getWakeMoodRating() == 3){
                        avg7 += 1;
                    }else if(entry.getWakeMoodRating() == 1){
                        avg7 -= 1;
                    }
                    break;
                case 8:
                    if(entry.getWakeMoodRating() == 4){
                        avg8 += 2;
                    }else if(entry.getWakeMoodRating() == 3){
                        avg8 += 1;
                    }else if(entry.getWakeMoodRating() == 1){
                        avg8 -= 1;
                    }
                    break;
                case 9:
                    if(entry.getWakeMoodRating() == 4){
                        avg9 += 2;
                    }else if(entry.getWakeMoodRating() == 3){
                        avg9 += 1;
                    }else if(entry.getWakeMoodRating() == 1){
                        avg9 -= 1;
                    }
                    break;
                case 10:
                    if(entry.getWakeMoodRating() == 4){
                        avg10 += 2;
                    }else if(entry.getWakeMoodRating() == 3){
                        avg10 += 1;
                    }else if(entry.getWakeMoodRating() == 1){
                        avg10 -= 1;
                    }
                    break;
                case 11:
                    if(entry.getWakeMoodRating() == 4){
                        avg11 += 2;
                    }else if(entry.getWakeMoodRating() == 3){
                        avg11 += 1;
                    }else if(entry.getWakeMoodRating() == 1){
                        avg11 -= 1;
                    }
                    break;
                case 12:
                    if(entry.getWakeMoodRating() == 4){
                        avg12 += 2;
                    }else if(entry.getWakeMoodRating() == 3){
                        avg12 += 1;
                    }else if(entry.getWakeMoodRating() == 1){
                        avg12 -= 1;
                    }
                    break;
            }
        }
        ArrayList<Integer> averageList = new ArrayList<>();
        averageList.add(avg4);
        averageList.add(avg5);
        averageList.add(avg6);
        averageList.add(avg7);
        averageList.add(avg8);
        averageList.add(avg9);
        averageList.add(avg10);
        averageList.add(avg11);
        averageList.add(avg12);
        return averageList;
    }


}

