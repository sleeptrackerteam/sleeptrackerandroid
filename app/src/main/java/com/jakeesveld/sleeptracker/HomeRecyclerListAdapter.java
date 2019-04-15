package com.jakeesveld.sleeptracker;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeRecyclerListAdapter extends RecyclerView.Adapter<HomeRecyclerListAdapter.ViewHolder> {
    ArrayList<SleepEntry> dataList;

    public HomeRecyclerListAdapter(ArrayList<SleepEntry> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_list_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final SleepEntry data = dataList.get(i);

        viewHolder.textWakeTime.setText(data.getWakeTime());
        viewHolder.textTiredRating.setText(data.getTiredRating());
        viewHolder.textMoodRating.setText(data.getWakeMoodRating());
        viewHolder.textEntryDate.setText(data.getDate());
        viewHolder.textBedTime.setText(data.getBedTime());

        viewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewHolder.parent.getContext(), BreakdownActivity.class);
                intent.putExtra("Sleep Entry", data);
                ((Activity)viewHolder.parent.getContext()).startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View parent;
        TextView textMoodRating, textTiredRating, textBedTime, textWakeTime, textEntryDate;
        ImageView imageViewGraph;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.layout_parent);
            textBedTime = itemView.findViewById(R.id.text_bed_time);
            textEntryDate = itemView.findViewById(R.id.text_view_entry_date);
            textMoodRating = itemView.findViewById(R.id.text_mood_rating);
            textTiredRating = itemView.findViewById(R.id.text_tired_rating);
            textWakeTime = itemView.findViewById(R.id.text_wake_time);
            imageViewGraph = itemView.findViewById(R.id.image_view_graph);
        }
    }
}