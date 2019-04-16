package com.jakeesveld.sleeptracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class BreakdownActivity extends AppCompatActivity {
    ArrayList<SleepEntry> entryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakdown);
        entryList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<SleepEntry> entries = HomeActivity.dao.getAllEntries();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        entryList.addAll(entries);
                    }
                });
            }
        }).start();
    }
}
