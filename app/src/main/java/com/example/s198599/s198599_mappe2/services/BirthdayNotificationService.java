package com.example.s198599.s198599_mappe2.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.example.s198599.s198599_mappe2.R;
import com.example.s198599.s198599_mappe2.db_orm.PersonDAO;
import com.example.s198599.s198599_mappe2.lib.StaticLib;
import com.example.s198599.s198599_mappe2.models.Person;

import java.util.Calendar;
import java.util.List;

public class BirthdayNotificationService extends Service {

    private Notification noti;
    private NotificationManager notificationManager;
    private int notificationId;

    public BirthdayNotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        notificationManager =
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);


        noti = new Notification.Builder(this)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text))
                .setSmallIcon(R.drawable.birthday).build();

        noti.flags = Notification.FLAG_AUTO_CANCEL;

        notificationId = 0;
        notificationManager.notify(notificationId, noti);

        Log.d("Birthday", "Running BirthdayNotificationService");


        checkBirthdaysToday();



        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        notificationManager.cancel(notificationId);
        Log.i("Birthday", "Finished sending SMS");
    }

    private void checkBirthdaysToday(){
        Log.d("Birthday", "Checking birthdays today");

        List<Person> birthdayPeople = PersonDAO.getBirthdaysToday(this);

        Log.d("Birthday", "People with birthday today: " + birthdayPeople.size());

        sendSMS(birthdayPeople);;




    }


    private void sendSMS(List<Person> birthdays){

        SmsManager manager = SmsManager.getDefault();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String greeting = sharedPref.getString("sms_text", "");

        Log.d("Birthday", "Greeting from Preference file: " + greeting);

        for(Person p : birthdays){
            Log.i("Birthday", "Skal i gang med sending av SMS");
            String phoneNumber = p.getPhoneNr();
            manager.sendTextMessage(phoneNumber, null, greeting, null, null);

            Log.i("Birthday", p.getFirstName() + " " + p.getLastName() + ", " + greeting);
            Toast.makeText(getBaseContext(), p.getFirstName() + " "
                    + p.getLastName() + ", " + greeting, Toast.LENGTH_SHORT).show();
        }

        onDestroy();
    }
}
