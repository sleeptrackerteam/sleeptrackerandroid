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

        viewHolder.textTiredRating.setText("Tired Rating: " + (String.valueOf(data.getTiredRating())));
        viewHolder.textMoodRating.setText("Mood Rating: " +(String.valueOf(data.getWakeMoodRating())));
        viewHolder.textEntryDate.setText(data.getDate());
        viewHolder.textTimeSlept.setText("Time Slept: " + data.getTimeSlept() + " hours");
        switch (data.getWakeMoodRating()){
            case 1:
                viewHolder.imageViewGraph.setImageDrawable(viewHolder.parent.getContext().getDrawable(R.drawable.frown));
                break;
            case 2:
                viewHolder.imageViewGraph.setImageDrawable(viewHolder.parent.getContext().getDrawable(R.drawable.meh));
                break;
            case 3:
                viewHolder.imageViewGraph.setImageDrawable(viewHolder.parent.getContext().getDrawable(R.drawable.smile));
                break;
            case 4:
                viewHolder.imageViewGraph.setImageDrawable(viewHolder.parent.getContext().getDrawable(R.drawable.big_smile));
        }

        viewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewHolder.parent.getContext(), BreakdownActivity.class);
                intent.putExtra(SleepEntry.SLEEP_ENTRY_KEY, data);
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
        TextView textMoodRating, textTiredRating, textEntryDate, textTimeSlept;
        ImageView imageViewGraph;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.layout_parent);
            textEntryDate = itemView.findViewById(R.id.text_view_entry_date);
            textMoodRating = itemView.findViewById(R.id.text_mood_rating);
            textTiredRating = itemView.findViewById(R.id.text_tired_rating);
            imageViewGraph = itemView.findViewById(R.id.image_view_graph);
            textTimeSlept = itemView.findViewById(R.id.text_time_slept);
        }
    }
}