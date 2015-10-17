package com.example.s198599.s198599_mappe2;

import android.app.ListFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.s198599.s198599_mappe2.db_orm.Dao;
import com.example.s198599.s198599_mappe2.db_orm.DataModel;
import com.example.s198599.s198599_mappe2.fragments.PersonListFragment;
import com.example.s198599.s198599_mappe2.models.Person;
import com.example.s198599.s198599_mappe2.registration_activities.AddPerson;
import com.example.s198599.s198599_mappe2.registration_activities.EditPerson;

import java.util.ArrayList;
import java.util.List;

public class MyFriends extends AppCompatActivity
        implements PersonListFragment.PersonAdapterListener{

    private List<Integer> checkedItems;
    private MenuItem deleteMenuItem;
    private final static int NEW_PERSON_REQUEST = 1;
    private final static int EDIT_PERSON_REQUEST = 2;


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

        switch (item.getItemId()){

            case R.id.action_back:
                Intent i = new Intent(this, MainActivity.class);
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

                break;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onItemclicked(Person p) {
        Log.d("Birthday", "Item clicked in ListView");

        //onEditPersonClicked(p);
    }


    public void onDeleteClicked(){
        PersonListFragment list = (PersonListFragment)getFragmentManager().findFragmentById(R.id.person_list_view_activity);


        DataModel db = null;
        try {

            db = Dao.getDataModel(getBaseContext());
            Log.d("Birthday", "Skal i gang med sletting..");
            List<Person> toBeDeleted = new ArrayList<>();
            for(int i = 0; i < checkedItems.size(); i++){

                int index = checkedItems.get(i);
                Person p = db.getObjectModel(Person.class).getFirst("personId=?",  index);
                toBeDeleted.add(p);
                Log.d("Birthday", "Sletter: " + p.toString());
                //db.getObjectModel(Person.class).delete(p);
            }
            db.getObjectModel(Person.class).deleteAll(toBeDeleted);


            list.notifyAdapterOnChange();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.disconnect();
                db = null;
            }
        }




    }




    @Override
    public void onCheckBoxClicked(Person p, int position, boolean isChecked) {
        Log.d("Birthday", "Checkbox for " +
                p.getFirstName() + " " +
                p.getLastName() + " is " + isChecked);

        if(checkedItems == null)
            checkedItems = new ArrayList<>();


        if(isChecked){
            checkedItems.add(p.getPersonId());
            if(!deleteMenuItem.isVisible())
                deleteMenuItem.setVisible(true);
        }else{
            try {
                checkedItems.remove(p.getPersonId());
            }catch (Exception e){
                checkedItems.clear();
            }
            if(checkedItems.size() == 0){
                deleteMenuItem.setVisible(false);
            }
        }
        Log.d("Birthday", "Checked items " + checkedItems.size());

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
            Person p = (Person)data.getSerializableExtra("updatedPerson");

            DataModel db = null;
            try {

                db = Dao.getDataModel(getBaseContext());
                db.getObjectModel(Person.class).update(p);

                list.notifyAdapterOnChange();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(db != null){
                    db.disconnect();
                    db = null;
                }
            }

            //list.updatePersonInList(p);
        }else{
            Log.d("Birthday", "New Person Finished");
            Person p = (Person)data.getSerializableExtra("newPerson");
            Log.d("Birthday", "Edited person: " + p.toString());


            DataModel db = null;
            try {

                db = Dao.getDataModel(getBaseContext());
                db.getObjectModel(Person.class).insert(p);

                list.notifyAdapterOnChange();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(db != null){
                    db.disconnect();
                    db = null;
                }
            }


        }
    }
}
