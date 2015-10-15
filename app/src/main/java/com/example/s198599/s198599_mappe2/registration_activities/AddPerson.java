package com.example.s198599.s198599_mappe2.registration_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.s198599.s198599_mappe2.MyFriends;
import com.example.s198599.s198599_mappe2.R;
import com.example.s198599.s198599_mappe2.fragments.PersonRegistrationFragment;
import com.example.s198599.s198599_mappe2.models.Person;

public class AddPerson extends AppCompatActivity {

    private PersonRegistrationFragment regForm;
    private Person newPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        regForm = (PersonRegistrationFragment)getFragmentManager().
                findFragmentById(R.id.person_fragment);

        Intent thisIntent = this.getIntent();
        newPerson = (Person)thisIntent.getSerializableExtra("person");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_person, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()){
                case R.id.action_back:
                    Intent i = new Intent(this, MyFriends.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    break;
                case R.id.action_add_person:
                    getNewPerson();
                    break;
                case R.id.action_settings:
                    break;
            }

        return super.onOptionsItemSelected(item);
    }


    public void getNewPerson(){
        Log.d("Birthday", "GetNewPerson - Skal hente new person fra regForm");
        newPerson = regForm.getUpdatedPerson();

        Intent i = new Intent(this, MyFriends.class);
        i.putExtra("newPerson", newPerson);
        setResult(RESULT_OK, i);
        Log.d("Birthday", "GetNewPerson - Rett før finish");
        finish();
    }


}
