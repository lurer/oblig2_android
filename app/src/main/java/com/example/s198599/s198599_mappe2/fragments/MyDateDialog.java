package com.example.s198599.s198599_mappe2.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import com.example.s198599.s198599_mappe2.lib.StaticLib;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by espen on 10/15/15.
 */
public class MyDateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    private int day;
    private int month;
    private int year;
    private Calendar date;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        day = 15;
        month = 6;
        year = 1995;

        return new DatePickerDialog(getActivity(), this, year, month, day);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;

//        Log.d("Birthday", "New date set: " + StaticLib.getDateAsString(getDate()));
    }

    public Calendar getDate(){
        date = new GregorianCalendar(year, month, day);
        return date;
    }

}