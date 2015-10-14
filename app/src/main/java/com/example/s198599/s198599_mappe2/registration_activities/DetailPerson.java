package com.example.s198599.s198599_mappe2.registration_activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.s198599.s198599_mappe2.R;
import com.example.s198599.s198599_mappe2.fragments.PersonRegistrationFragment;

public class DetailPerson extends AppCompatActivity {

    private PersonRegistrationFragment regForm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_person);

        regForm = (PersonRegistrationFragment)getFragmentManager().
                findFragmentById(R.id.person_fragment);



        //regForm.setTextBoxesUneditable();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_person, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_back:
                break;

            case R.id.action_settings:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
