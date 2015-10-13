package com.example.s198599.s198599_mappe2.services;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class BirthdayService extends Service {
    public BirthdayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Birthday", "BirthdayService - onStartCommand");


        //TODO: Notification
        //TODO: Start relevant klasse for monitorering av bursdager.

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d("Birthday", "BirthdayService - onStart");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // do something when the service is created
    }
}
