package com.example.s198599.s198599_mappe2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.s198599.s198599_mappe2.db_orm.PersonDAO;
import com.example.s198599.s198599_mappe2.fragments.PersonListFragment;
import com.example.s198599.s198599_mappe2.models.Person;

import java.util.ArrayList;
import java.util.List;

public class MyFriends extends AppCompatActivity
        implements PersonListFragment.PersonAdapterListener{

    private List<Integer> personIdsSelected;
    private MenuItem deleteMenuItem;
    private final static int NEW_PERSON_REQUEST = 1;
    private final static int EDIT_PERSON_REQUEST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friends);
        personIdsSelected = new ArrayList<>();

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
                onNewPersonClicked();
                break;
            case R.id.deleteperson:
                onDeleteClicked();
                break;
            case R.id.action_settings:
                i = new Intent(this, PreferenceActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemclicked(Person p) {
        Log.d("Birthday", "Item clicked in ListView");

        //onEditPersonClicked(p);
    }


    @Override
    public void onCheckBoxClicked(Person p, int position, boolean isChecked) {
        Log.d("Birthday", "Checkbox for " +
                p.getFirstName() + " " +
                p.getLastName() + " is " + isChecked);

        if(personIdsSelected == null)
            personIdsSelected = new ArrayList<>();


        if(isChecked){
            personIdsSelected.add(p.getPersonId());
            if(!deleteMenuItem.isVisible())
                deleteMenuItem.setVisible(true);
        }else{
            try {
                personIdsSelected.remove(p.getPersonId());
            }catch (Exception e){
                personIdsSelected.clear();
            }
            if(personIdsSelected.size() == 0){
                deleteMenuItem.setVisible(false);
            }
        }
        Log.d("Birthday", "Checked items " + personIdsSelected.size());

    }


    public void onNewPersonClicked(){
        Intent i = new Intent(this, AddPerson.class);
        Person p = new Person();
        i.putExtra("person", p);
        startActivityForResult(i, NEW_PERSON_REQUEST);

    }


    @Override
    public void onEditPersonClicked(Person p) {
        Log.d("Birthday", "Editing " + p.getFirstName() + " " + p.getLastName());

        Intent i = new Intent(this, EditPerson.class);
        //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.putExtra("person", p);
        Log.d("Birthday", "Lagt til person i Intent");
        startActivityForResult(i, EDIT_PERSON_REQUEST);

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        PersonListFragment list = (PersonListFragment)getFragmentManager().findFragmentById(R.id.person_list_view_activity);

        if(requestCode == EDIT_PERSON_REQUEST){
            Log.d("Birthday", "Edit Person Finished");
            try {
                Person p = (Person) data.getSerializableExtra("updatedPerson");
                if (p != null) {
                    PersonDAO.updatePerson(getBaseContext(), p);
                    list.notifyAdapterOnChange();
                }
            }catch (NullPointerException e){
                Log.d("Birthday", "Person update was not completed successfully");
            }

        }else{
            Log.d("Birthday", "New Person Finished");
            try{
                Person p = (Person)data.getSerializableExtra("newPerson");
                if(p != null){
                    Log.d("Birthday", "Edited person: " + p.toString());

                    PersonDAO.addPerson(getBaseContext(), p);
                    list.notifyAdapterOnChange();
                }
            }catch (NullPointerException e){
                Log.d("Birthday", "Person registration was not completed successfully");
            }


        }
    }


    public void onDeleteClicked(){
        PersonListFragment list = (PersonListFragment)getFragmentManager().findFragmentById(R.id.person_list_view_activity);

        PersonDAO.deletePersons(getBaseContext(), personIdsSelected);
        list.notifyAdapterOnChange();
    }
}
