package com.example.s198599.s198599_mappe2.fragments;

import com.example.s198599.s198599_mappe2.lib.ListFragmentCallback;
import com.example.s198599.s198599_mappe2.models.PersonAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.s198599.s198599_mappe2.R;
import com.example.s198599.s198599_mappe2.models.Person;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class PersonListFragment extends ListFragment
        implements PersonAdapter.CheckboxChangedCallback,
                    PersonAdapter.EditPersonCallback{

    private PersonAdapter adapter;
    private PersonAdapterListener listener;
    private ListView lv;
    private List<Person> personList;


    private Person selctedPersonFromList;

    public PersonListFragment() {
    }




    /**
     * Interface som MyFriends.class implementerer, og som håndterer endring i ListView
     */
    public interface PersonAdapterListener{
        void onItemclicked(Person p);
        void onCheckBoxClicked(Person p, int position, boolean isChecked);
        void onEditPersonClicked(Person p);
    }

    public interface PersonListCallback{

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        lv = (ListView)view.findViewById(R.id.person_list_view);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selctedPersonFromList = adapter.getItem(position);
                listener.onItemclicked(selctedPersonFromList);
            }
        });
        adapter.setCheckboxChangedCallback(this);
        adapter.setEditPersonCallback(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.person_list_view, container, false);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(personList == null) {
            personList = new ArrayList<>();

            Calendar today = Calendar.getInstance();
            personList.add(new Person(0, "Espen", "Zaal", "98653942", today));
            personList.add(new Person(1, "Truls", "Pettersen", "90123456", today));
        }

        adapter = new PersonAdapter(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, personList);


    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (PersonAdapterListener)activity;

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



    /**
     * Denne metoden blir kalt fra PersonAdapter i det en checkbox endres
     * @param position
     * @param isChecked
     */
    @Override
    public void onCheckboxClicked(int position, boolean isChecked) {

        selctedPersonFromList = adapter.getItem(position);
        listener.onCheckBoxClicked(selctedPersonFromList, position, isChecked);
    }



    @Override
    public void onEditPersonClicked(int position) {
        selctedPersonFromList = adapter.getItem(position);
        listener.onEditPersonClicked(selctedPersonFromList);
    }

    public void addPersonToList(Person newPerson){
        personList.add(newPerson);
        adapter.notifyDataSetChanged();
    }

    public void updatePersonInList(Person updatedPerson){
        for(int i = 0; i < personList.size(); i++){
            if(personList.get(i).getPersonId() == updatedPerson.getPersonId()){
                personList.set(i, updatedPerson);
                break;
            }
        }
        adapter.notifyDataSetChanged();
    }

}
