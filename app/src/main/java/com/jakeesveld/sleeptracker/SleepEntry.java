package com.jakeesveld.sleeptracker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class SleepEntry implements Serializable {
    public static final String SLEEP_ENTRY_KEY = "Sleep Entry";
    private int id, tiredRating, wakeMoodRating, timeSlept;
    private String date;

    public SleepEntry(JSONObject json){
        /*        "timeSlept": 9,
        "wakeMood": 4,
        "sleepMood": 3,
        "date": "2019-04-13"*/
        try {
            this.date = json.getString("date");
            this.wakeMoodRating = json.getInt("wakeMood");
            this.tiredRating = json.getInt("sleepMood");
            this.timeSlept = json.getInt("timeSlept");
            this.id = json.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public SleepEntry(int id, int tiredRating, int wakeMoodRating, int averageMoodRating, int timeSlept, String date) {
        this.id = id;
        this.tiredRating = tiredRating;
        this.wakeMoodRating = wakeMoodRating;
        this.timeSlept = timeSlept;
        this.date = date;
    }

    public SleepEntry(int tiredRating, int wakeMoodRating, int averageMoodRating, int timeSlept, String date) {
        this.tiredRating = tiredRating;
        this.wakeMoodRating = wakeMoodRating;
        this.timeSlept = timeSlept;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTiredRating() {
        return tiredRating;
    }

    public void setTiredRating(int tiredRating) {
        this.tiredRating = tiredRating;
    }

    public int getWakeMoodRating() {
        return wakeMoodRating;
    }

    public void setWakeMoodRating(int wakeMoodRating) {
        this.wakeMoodRating = wakeMoodRating;
    }


    public int getTimeSlept() {
        return timeSlept;
    }

    public void setTimeSlept(int timeSlept) {
        this.timeSlept = timeSlept;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

/*    public JSONObject toJSON(){
        JSONObject entryJson = new JSONObject();
        entryJson.put("id", this.id);
        entryJson.put()
    }*/
}
/*sleep: {id, user_id, timeSlept, wakeMood, sleepMood, date-format(YYYY-MM-DD)}*/