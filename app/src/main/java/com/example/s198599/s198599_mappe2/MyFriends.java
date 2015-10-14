package com.example.s198599.s198599_mappe2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.s198599.s198599_mappe2.fragments.PersonListFragment;
import com.example.s198599.s198599_mappe2.models.Person;
import com.example.s198599.s198599_mappe2.registration_activities.AddPerson;
import com.example.s198599.s198599_mappe2.registration_activities.DetailPerson;
import com.example.s198599.s198599_mappe2.registration_activities.EditPerson;

import java.util.ArrayList;
import java.util.List;

public class MyFriends extends AppCompatActivity
        implements PersonListFragment.PersonAdapterListener{

    private List<Integer> checkedItems;
    private MenuItem deleteMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friends);
        checkedItems = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_my_friends, menu);
        deleteMenuItem = menu.findItem(R.id.deleteperson);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i;

        switch (item.getItemId()){

            case R.id.action_back:
                i = new Intent(this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            case R.id.addperson:
                i = new Intent(this, AddPerson.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            case R.id.deleteperson:
                break;
            case R.id.action_settings:

                break;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemclicked(Person p) {
        Log.d("Birthday", "Item clicked in ListView");

        Intent i = new Intent(this, DetailPerson.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public void onCheckBoxClicked(Person p, int position, boolean isChecked) {
        Log.d("Birthday", "Checkbox for " +
                p.getFirstName() + " " +
                p.getLastName() + " is " + isChecked);

        if(checkedItems == null)
            checkedItems = new ArrayList<>();


        if(isChecked){
            checkedItems.add(position);
            if(!deleteMenuItem.isVisible())
                deleteMenuItem.setVisible(true);
        }else{
            try {
                checkedItems.remove(position);
            }catch (Exception e){
                checkedItems.clear();
            }
            if(checkedItems.size() == 0){
                deleteMenuItem.setVisible(false);
            }
        }
        Log.d("Birthday", "Checked items " + checkedItems.size());

    }


    @Override
    public void onEditPersonClicked(Person p) {
        Log.d("Birthday", "Editing " + p.getFirstName() + " " + p.getLastName());

        Intent i = new Intent(this, EditPerson.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }


    private void updateCheckedItemList(int position, boolean isAdding){

    }


}
