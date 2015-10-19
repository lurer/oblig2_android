package com.example.s198599.s198599_mappe2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.s198599.s198599_mappe2.MyFriends;
import com.example.s198599.s198599_mappe2.PreferenceActivity;
import com.example.s198599.s198599_mappe2.R;
import com.example.s198599.s198599_mappe2.fragments.PersonRegistrationFragment;
import com.example.s198599.s198599_mappe2.models.Person;

public class EditPerson extends AppCompatActivity {

    private PersonRegistrationFragment regForm;
    private Person updatingPerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);


        Log.d("Birthday", "I EditPerson");
        Intent thisIntent = this.getIntent();
        updatingPerson = (Person)thisIntent.getSerializableExtra("person");

        regForm = (PersonRegistrationFragment)getFragmentManager().
                findFragmentById(R.id.person_fragment);

        if(regForm != null)
            regForm.setPersonInView(updatingPerson);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_person, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i;
        switch (item.getItemId()){
            case R.id.action_back:
                i = new Intent(this, MyFriends.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            case R.id.action_add_person:
                getUpdatedPerson();
                break;
            case R.id.action_settings:
                i = new Intent(this, PreferenceActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public void getUpdatedPerson(){
        updatingPerson = regForm.getUpdatedPerson();

        Intent i = new Intent(this, MyFriends.class);
        i.putExtra("updatedPerson", updatingPerson);
        setResult(RESULT_OK, i);
        finish();
    }

}
