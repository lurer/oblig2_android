package com.example.s198599.s198599_mappe2;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TimePicker;

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


    public static class PrefFragment extends PreferenceFragment
            implements TimePickerDialog.OnTimeSetListener{


        private Preference timeButton;
        private Preference startServiceButton;
        private Preference stopServiceButton;

        static final String PREF_HOUR = "serviceTimeHour";
        static final String PREF_MINUTE = "serviceTimeMinute";

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
                    return false;
                }
            });


            stopServiceButton = findPreference("prefStopService");
            stopServiceButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    return false;
                }
            });

        }


        private void showTimePicker(){

            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            int hour = sharedPrefs.getInt(PREF_HOUR, 12);
            int minute = sharedPrefs.getInt(PREF_MINUTE, 0);

            new TimePickerDialog(getActivity(), this, hour, minute, true).show();

        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putInt(PREF_HOUR, hourOfDay);
            editor.putInt(PREF_MINUTE, minute);
            editor.commit();

            Log.d("Birthday", "New time: " + hourOfDay + ":" + minute);
        }
    }
}
