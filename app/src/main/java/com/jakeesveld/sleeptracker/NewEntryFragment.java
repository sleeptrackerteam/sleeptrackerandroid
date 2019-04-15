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


public class NewEntryFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    EditText editDate, editBedTime, editWakeTime;
    ImageView tired1, tired2, tired3, tired4, wake1, wake2, wake3, wake4, average1, average2, average3, average4;
    Button buttonDatePicker, buttonBedTimePicker, buttonWakeTimePicker, buttonSubmit;

    private OnFragmentInteractionListener mListener;

    public NewEntryFragment() {
        // Required empty public constructor
    }

    public static NewEntryFragment newInstance(String param1, String param2) {
        NewEntryFragment fragment = new NewEntryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
}
