package com.jakeesveld.sleeptracker;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TimePicker;

import org.json.JSONObject;

import java.util.Calendar;


public class NewEntryFragment extends Fragment {

    public static final int BED_TIME_REQUEST = 11;
    public static final int WAKE_TIME_REQUEST = 12;
    public static final int DATE_REQUEST = 13;
    public static final String TIME_PICKER_TAG = "Time Picker";
    public static final String DATE_PICKER_TAG = "Date Picker";
    EditText editDate, editBedTime, editWakeTime;
    ImageView tired1, tired2, tired3, tired4, wake1, wake2, wake3, wake4;
    Button buttonDatePicker, buttonBedTimePicker, buttonWakeTimePicker, buttonSubmit;
    static int tiredRating, wakeRating;


    private OnFragmentInteractionListener mListener;

    public NewEntryFragment() {
        // Required empty public constructor
    }

    public static NewEntryFragment newInstance(String param1, String param2) {
        NewEntryFragment fragment = new NewEntryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_entry, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        editDate = view.findViewById(R.id.edit_date);
        editBedTime = view.findViewById(R.id.edit_bed_time);
        editWakeTime = view.findViewById(R.id.edit_wake_time);
        tired1 = view.findViewById(R.id.image_tired_1);
        tired2 = view.findViewById(R.id.image_tired_2);
        tired3 = view.findViewById(R.id.image_tired_3);
        tired4 = view.findViewById(R.id.image_tired_4);
        wake1 = view.findViewById(R.id.image_wake_1);
        wake2 = view.findViewById(R.id.image_wake_2);
        wake3 = view.findViewById(R.id.image_wake_3);
        wake4 = view.findViewById(R.id.image_wake_4);
        buttonDatePicker = view.findViewById(R.id.button_pick_date);
        buttonBedTimePicker = view.findViewById(R.id.button_bed_time);
        buttonWakeTimePicker = view.findViewById(R.id.button_wake_time);
        buttonSubmit = view.findViewById(R.id.button_submit);

        tired1.setOnClickListener(listener);
        tired2.setOnClickListener(listener);
        tired3.setOnClickListener(listener);
        tired4.setOnClickListener(listener);
        wake1.setOnClickListener(listener);
        wake2.setOnClickListener(listener);
        wake3.setOnClickListener(listener);
        wake4.setOnClickListener(listener);

        buttonBedTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new TimePickerFragment();
                fragment.setTargetFragment(NewEntryFragment.this, BED_TIME_REQUEST);
                if(getFragmentManager() != null){
                    fragment.show(getFragmentManager(), TIME_PICKER_TAG);
                }
            }
        });

        buttonWakeTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new TimePickerFragment();
                fragment.setTargetFragment(NewEntryFragment.this, WAKE_TIME_REQUEST);
                if(getFragmentManager() != null){
                    fragment.show(getFragmentManager(), TIME_PICKER_TAG);
                }
            }
        });

        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new DatePickerFragment();
                fragment.setTargetFragment(NewEntryFragment.this, DATE_REQUEST);
                if(getFragmentManager() != null){
                    fragment.show(getFragmentManager(), DATE_PICKER_TAG);
                }
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bedTimeString = editBedTime.getText().toString();
                String wakeTimeString = editWakeTime.getText().toString();
                Float bedTimeFloat = Float.parseFloat(bedTimeString.replace(":", "."));
                Float wakeTimeFloat = Float.parseFloat(wakeTimeString.replace(":", "."));
                Float timeSleptFloat = (24f - bedTimeFloat) + wakeTimeFloat;
                int timeSlept = Math.round(timeSleptFloat);
                final SleepEntry entry = new SleepEntry(tiredRating, wakeRating, timeSlept, editDate.getText().toString());
                final JSONObject entryJson = entry.toJson();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int entryId = HomeActivity.dao.createEntry(entryJson);
                        entry.setId(entryId);
                        if(getFragmentManager() != null){
                            getFragmentManager().beginTransaction().remove(NewEntryFragment.this).commit();
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == BED_TIME_REQUEST && resultCode == Activity.RESULT_OK){
            String bedTime = data.getStringExtra(TimePickerFragment.TIME_EXTRA_KEY);
            editBedTime.setText(bedTime);
        }else if(requestCode == WAKE_TIME_REQUEST && resultCode == Activity.RESULT_OK){
            String wakeTime = data.getStringExtra(TimePickerFragment.TIME_EXTRA_KEY);
            editWakeTime.setText(wakeTime);
        }else if(requestCode == DATE_REQUEST && resultCode == Activity.RESULT_OK){
            String date = data.getStringExtra(DatePickerFragment.DATE_EXTRA_KEY);
            editDate.setText(date);
        }
    }

    final ImageView.OnClickListener listener = new ImageView.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_tired_1:
                    tired1.setImageDrawable(getResources().getDrawable(R.drawable.frown_selected));
                    tired2.setImageDrawable(getResources().getDrawable(R.drawable.meh));
                    tired3.setImageDrawable(getResources().getDrawable(R.drawable.smile));
                    tired4.setImageDrawable(getResources().getDrawable(R.drawable.big_smile));
                    tiredRating = 1;
                    break;

                case R.id.image_tired_2:
                    tired1.setImageDrawable(getResources().getDrawable(R.drawable.frown));
                    tired2.setImageDrawable(getResources().getDrawable(R.drawable.meh_selected));
                    tired3.setImageDrawable(getResources().getDrawable(R.drawable.smile));
                    tired4.setImageDrawable(getResources().getDrawable(R.drawable.big_smile));
                    tiredRating = 2;
                    break;
                case R.id.image_tired_3:
                    tired1.setImageDrawable(getResources().getDrawable(R.drawable.frown));
                    tired2.setImageDrawable(getResources().getDrawable(R.drawable.meh));
                    tired3.setImageDrawable(getResources().getDrawable(R.drawable.smile_selected));
                    tired4.setImageDrawable(getResources().getDrawable(R.drawable.big_smile));
                    tiredRating = 3;
                    break;
                case R.id.image_tired_4:
                    tired1.setImageDrawable(getResources().getDrawable(R.drawable.frown));
                    tired2.setImageDrawable(getResources().getDrawable(R.drawable.meh));
                    tired3.setImageDrawable(getResources().getDrawable(R.drawable.smile));
                    tired4.setImageDrawable(getResources().getDrawable(R.drawable.big_smile_selected));
                    tiredRating = 4;
                    break;
                case R.id.image_wake_1:
                    wake1.setImageDrawable(getResources().getDrawable(R.drawable.frown_selected));
                    wake2.setImageDrawable(getResources().getDrawable(R.drawable.meh));
                    wake3.setImageDrawable(getResources().getDrawable(R.drawable.smile));
                    wake4.setImageDrawable(getResources().getDrawable(R.drawable.big_smile));
                    wakeRating = 1;
                    break;
                case R.id.image_wake_2:
                    wake1.setImageDrawable(getResources().getDrawable(R.drawable.frown));
                    wake2.setImageDrawable(getResources().getDrawable(R.drawable.meh_selected));
                    wake3.setImageDrawable(getResources().getDrawable(R.drawable.smile));
                    wake4.setImageDrawable(getResources().getDrawable(R.drawable.big_smile));
                    wakeRating = 2;
                    break;
                case R.id.image_wake_3:
                    wake1.setImageDrawable(getResources().getDrawable(R.drawable.frown));
                    wake2.setImageDrawable(getResources().getDrawable(R.drawable.meh));
                    wake3.setImageDrawable(getResources().getDrawable(R.drawable.smile_selected));
                    wake4.setImageDrawable(getResources().getDrawable(R.drawable.big_smile));
                    wakeRating = 3;
                    break;
                case R.id.image_wake_4:
                    wake1.setImageDrawable(getResources().getDrawable(R.drawable.frown));
                    wake2.setImageDrawable(getResources().getDrawable(R.drawable.meh));
                    wake3.setImageDrawable(getResources().getDrawable(R.drawable.smile));
                    wake4.setImageDrawable(getResources().getDrawable(R.drawable.big_smile_selected));
                    wakeRating = 4;
                    break;


            }
        }
    };
}
