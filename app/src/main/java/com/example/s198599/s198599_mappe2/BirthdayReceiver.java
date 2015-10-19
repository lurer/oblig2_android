package com.example.s198599.s198599_mappe2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.s198599.s198599_mappe2.services.BirthdayServiceSchedule;

public class BirthdayReceiver extends BroadcastReceiver {
    public BirthdayReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Birthday", "BirthdayReceiver - startet");

        Intent i = new Intent(context, BirthdayServiceSchedule.class);
        context.startService(i);

        Log.d("Birthday", "BirthdayReceiver - ferdig med init");

    }
}
