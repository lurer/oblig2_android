package com.example.s198599.s198599_mappe2.lib;

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

    public static String getDateAsString(Calendar cal){
        DATE_FORMAT.setCalendar(cal);
        return DATE_FORMAT.format(cal.getTime());
    }



}
