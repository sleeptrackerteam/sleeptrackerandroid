package com.jakeesveld.sleeptracker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;


public class NewEntryFragment extends Fragment {

    EditText editDate, editBedTime, editWakeTime;
    ImageView tired1, tired2, tired3, tired4, wake1, wake2, wake3, wake4, average1, average2, average3, average4;
    Button buttonDatePicker, buttonBedTimePicker, buttonWakeTimePicker, buttonSubmit;
    static int tiredRating, wakeRating, averageRating;


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
        average1 = view.findViewById(R.id.image_average_1);
        average2 = view.findViewById(R.id.image_average_2);
        average3 = view.findViewById(R.id.image_average_3);
        average4 = view.findViewById(R.id.image_average_4);
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
        average1.setOnClickListener(listener);
        average2.setOnClickListener(listener);
        average3.setOnClickListener(listener);
        average4.setOnClickListener(listener);
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
                case R.id.image_average_1:
                    average1.setImageDrawable(getResources().getDrawable(R.drawable.frown_selected));
                    average2.setImageDrawable(getResources().getDrawable(R.drawable.meh));
                    average3.setImageDrawable(getResources().getDrawable(R.drawable.smile));
                    average4.setImageDrawable(getResources().getDrawable(R.drawable.big_smile));
                    averageRating = 1;
                    break;
                case R.id.image_average_2:
                    average1.setImageDrawable(getResources().getDrawable(R.drawable.frown));
                    average2.setImageDrawable(getResources().getDrawable(R.drawable.meh_selected));
                    average3.setImageDrawable(getResources().getDrawable(R.drawable.smile));
                    average4.setImageDrawable(getResources().getDrawable(R.drawable.big_smile));
                    averageRating = 2;
                    break;
                case R.id.image_average_3:
                    average1.setImageDrawable(getResources().getDrawable(R.drawable.frown));
                    average2.setImageDrawable(getResources().getDrawable(R.drawable.meh));
                    average3.setImageDrawable(getResources().getDrawable(R.drawable.smile_selected));
                    average4.setImageDrawable(getResources().getDrawable(R.drawable.big_smile));
                    averageRating = 3;
                    break;
                case R.id.image_average_4:
                    average1.setImageDrawable(getResources().getDrawable(R.drawable.frown));
                    average2.setImageDrawable(getResources().getDrawable(R.drawable.meh));
                    average3.setImageDrawable(getResources().getDrawable(R.drawable.smile));
                    average4.setImageDrawable(getResources().getDrawable(R.drawable.big_smile_selected));
                    averageRating = 4;
                    break;


            }
        }
    };
}
