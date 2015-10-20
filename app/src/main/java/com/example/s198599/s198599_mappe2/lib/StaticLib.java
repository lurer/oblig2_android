package com.example.s198599.s198599_mappe2.lib;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by espen on 10/14/15.
 */
public class StaticLib {

    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final String PREF_HOUR = "serviceTimeHour";
    public static final String PREF_MINUTE = "serviceTimeMinute";
    public static final String FIRSTNAME_VALID = "[A-ZÆØÅa-zæøå\\.-]{2,20}";
    public static final String LASTNAME_VALID = "[A-ZÆØÅa-zæøå\\.-]{2,20}";
    public static final String PHONE_VALID = "[1-9]{1}[0-9]{7}";

    public static String getDateAsString(Calendar cal){
        DATE_FORMAT.setCalendar(cal);
        return DATE_FORMAT.format(cal.getTime());
    }





}
