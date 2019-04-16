package com.jakeesveld.sleeptracker;

import org.json.JSONObject;

import java.io.Serializable;

public class SleepEntry implements Serializable {
    public static final String SLEEP_ENTRY_KEY = "Sleep Entry";
    private int id, tiredRating, wakeMoodRating;
    private Float timeSlept;
    private String date;

    public SleepEntry(int id, int tiredRating, int wakeMoodRating, int averageMoodRating, Float timeSlept, String date) {
        this.id = id;
        this.tiredRating = tiredRating;
        this.wakeMoodRating = wakeMoodRating;
        this.timeSlept = timeSlept;
        this.date = date;
    }

    public SleepEntry(int tiredRating, int wakeMoodRating, int averageMoodRating, Float timeSlept, String date) {
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


    public Float getTimeSlept() {
        return timeSlept;
    }

    public void setTimeSlept(Float timeSlept) {
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