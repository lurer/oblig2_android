package com.example.s198599.s198599_mappe2.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BirthdayNotificationService extends Service {
    public BirthdayNotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO: Ekstra funksjonalitet her.
        return super.onStartCommand(intent, flags, startId);
    }
}
