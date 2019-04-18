package com.jakeesveld.sleeptracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class HomeRecyclerListAdapter extends RecyclerView.Adapter<HomeRecyclerListAdapter.ViewHolder> {
    private SortedList<SleepEntry> dataList;

    public HomeRecyclerListAdapter() {
        dataList = new SortedList<>(SleepEntry.class, new SortedList.Callback<SleepEntry>() {
            @Override
            public int compare(SleepEntry o1, SleepEntry o2) {
                int result = o2.getYear().compareTo(o1.getYear());
                if (result == 0){
                    result = o2.getMonth().compareTo(o1.getMonth());
                }
                if(result == 0){
                    result = o2.getDay().compareTo(o1.getDay());
                }
                return result;
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(SleepEntry oldItem, SleepEntry newItem) {
                return oldItem.getDate().equals(newItem.getDate());
            }

            @Override
            public boolean areItemsTheSame(SleepEntry item1, SleepEntry item2) {
                return item1.getDate().equals(item2.getDate());
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    public void addAll(ArrayList<SleepEntry> entries) {
        dataList.beginBatchedUpdates();
        for (int i = 0; i < entries.size(); i++) {
            dataList.add(entries.get(i));
        }
        dataList.endBatchedUpdates();
    }

    public void clear() {
        dataList.beginBatchedUpdates();
        while (dataList.size() > 0) {
            dataList.removeItemAt(dataList.size() - 1);
        }
        dataList.endBatchedUpdates();
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
        setEnterAnimation(viewHolder.parent);
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
                AppCompatActivity activity = (AppCompatActivity)viewHolder.parent.getContext();
                Bundle arguments = new Bundle();
                arguments.putSerializable(SleepEntry.SLEEP_ENTRY_KEY, data);
                NewEntryFragment fragment = new NewEntryFragment();
                fragment.setArguments(arguments);
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    private void setEnterAnimation(View view){
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_in_left);
        view.startAnimation(animation);
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