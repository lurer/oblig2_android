package com.example.s198599.s198599_mappe2.services;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.s198599.s198599_mappe2.lib.StaticLib;

import java.util.Calendar;

public class BirthdayServiceSchedule extends Service {
    public BirthdayServiceSchedule() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Birthday", "BirthdayServiceSchedule - Starting...");
        Toast.makeText(getBaseContext(), "BirthdayServiceSchedule - Starting...", Toast.LENGTH_SHORT).show();


        Calendar runTime = Calendar.getInstance();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        runTime.set(Calendar.HOUR_OF_DAY, sharedPrefs.getInt(StaticLib.PREF_HOUR, 12));
        runTime.set(Calendar.MINUTE, sharedPrefs.getInt(StaticLib.PREF_MINUTE, 0));
        //runTime.set(Calendar.MILLISECOND, 0);

        PendingIntent pi = PendingIntent.getService(getBaseContext(), 0,
                new Intent(getBaseContext(), BirthdayNotificationService.class),
                PendingIntent.FLAG_UPDATE_CURRENT);


        AlarmManager am = (AlarmManager) getBaseContext().getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, runTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);



        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getBaseContext(), "BirthdayServiceSchedule - Stopping...", Toast.LENGTH_SHORT).show();
        Log.d("Birthday", "BirthdayServiceSchedule - Stopping...");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d("Birthday", "BirthdayServiceSchedule - onStart");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // do something when the service is created
    }
}
