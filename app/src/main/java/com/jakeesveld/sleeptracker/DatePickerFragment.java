package com.jakeesveld.sleeptracker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public static final String DATE_EXTRA_KEY = "Date";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String result = Integer.toString(year)
                + "-" + Integer.toString(month)
                + "-" + Integer.toString(day);

        getTargetFragment().onActivityResult(
                getTargetRequestCode(), Activity.RESULT_OK,
                new Intent().putExtra(DATE_EXTRA_KEY, result));
    }
}
