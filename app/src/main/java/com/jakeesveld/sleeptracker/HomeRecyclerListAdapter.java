package com.jakeesveld.sleeptracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeRecyclerListAdapter extends RecyclerView.Adapter<HomeRecyclerListAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
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