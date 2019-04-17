package com.jakeesveld.sleeptracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class BreakdownActivity extends AppCompatActivity {
    ArrayList<SleepEntry> entryList;
    SleepGraph breakdownGraph;
    TextView textRecommended;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakdown);
        entryList = new ArrayList<>();
        breakdownGraph = findViewById(R.id.sleep_graph_breakdown);
        textRecommended = findViewById(R.id.text_reccommended_hours);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<SleepEntry> entries = HomeActivity.dao.getAllEntries();
                final ArrayList<Integer> averageList = HomeActivity.dao.getAverages(entries);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        entryList.addAll(entries);
                        breakdownGraph.setYEndings(averageList);
                        textRecommended.setText(String.valueOf(breakdownGraph.getRecommendedHours()));
                    }
                });
            }
        }).start();
    }
}
