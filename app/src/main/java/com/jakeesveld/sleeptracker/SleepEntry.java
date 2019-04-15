package com.jakeesveld.sleeptracker;

public class SleepEntry {
    private int id, tiredRating, wakeMoodRating, averageMoodRating;
    private Float timeSlept;
    private String date;

    public SleepEntry(int id, int tiredRating, int wakeMoodRating, int averageMoodRating, Float timeSlept, String date) {
        this.id = id;
        this.tiredRating = tiredRating;
        this.wakeMoodRating = wakeMoodRating;
        this.averageMoodRating = averageMoodRating;
        this.timeSlept = timeSlept;
        this.date = date;
    }

    public SleepEntry(int tiredRating, int wakeMoodRating, int averageMoodRating, Float timeSlept, String date) {
        this.tiredRating = tiredRating;
        this.wakeMoodRating = wakeMoodRating;
        this.averageMoodRating = averageMoodRating;
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

    public int getAverageMoodRating() {
        return averageMoodRating;
    }

    public void setAverageMoodRating(int averageMoodRating) {
        this.averageMoodRating = averageMoodRating;
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
}
