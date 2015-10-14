package com.example.s198599.s198599_mappe2.fragments;

import com.example.s198599.s198599_mappe2.models.PersonAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.s198599.s198599_mappe2.R;
import com.example.s198599.s198599_mappe2.models.Person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PersonListFragment extends ListFragment
        implements PersonAdapter.CheckboxChangedCallback, PersonAdapter.EditPersonCallback {

    private PersonAdapter adapter;
    private PersonAdapterListener listener;
    private ListView lv;


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


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        lv = (ListView)view.findViewById(R.id.person_list_view);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person p = adapter.getItem(position);
                listener.onItemclicked(p);
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

        List<Person> dummyList = new ArrayList<>();

        dummyList.add(new Person("Espen", "Zaal", "98653942", new Date()));
        dummyList.add(new Person("Truls", "Pettersen", "90123456", new Date()));

        adapter = new PersonAdapter(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, dummyList);


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

        Person p = adapter.getItem(position);
        listener.onCheckBoxClicked(p, position, isChecked);
    }



    @Override
    public void onEditPersonClicked(int position) {
        Person p = adapter.getItem(position);
        listener.onEditPersonClicked(p);
    }

}
