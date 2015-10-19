package com.example.s198599.s198599_mappe2;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TimePicker;

import com.example.s198599.s198599_mappe2.lib.StaticLib;
import com.example.s198599.s198599_mappe2.services.BirthdayServiceSchedule;

/**
 * Created by espen on 10/18/15.
 */
public class PreferenceActivity extends Activity {





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preference);


        getFragmentManager().beginTransaction().
                replace(android.R.id.content, new PrefFragment()).
                commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_preference, menu);
        getActionBar().setDisplayShowTitleEnabled(false);

        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_back:
                Intent i = new Intent(this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }






    public static class PrefFragment extends PreferenceFragment
            implements TimePickerDialog.OnTimeSetListener{


        private Preference timeButton;
        private Preference startServiceButton;
        private Preference stopServiceButton;





        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.preferences);


            timeButton = findPreference("prefTimePicker");
            timeButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    showTimePicker();
                    return false;
                }
            });

            startServiceButton = findPreference("prefStartService");
            startServiceButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    getActivity().startService(new Intent(getActivity(), BirthdayServiceSchedule.class));
                    return false;
                }
            });


            stopServiceButton = findPreference("prefStopService");
            stopServiceButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    getActivity().stopService(new Intent(getActivity(), BirthdayServiceSchedule.class));
                    return false;
                }
            });

        }


        private void showTimePicker(){

            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            int hour = sharedPrefs.getInt(StaticLib.PREF_HOUR, 12);
            int minute = sharedPrefs.getInt(StaticLib.PREF_MINUTE, 0);

            new TimePickerDialog(getActivity(), this, hour, minute, true).show();

        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putInt(StaticLib.PREF_HOUR, hourOfDay);
            editor.putInt(StaticLib.PREF_MINUTE, minute);
            editor.commit();

            Log.d("Birthday", "New time: " + hourOfDay + ":" + minute);
        }
    }
}
