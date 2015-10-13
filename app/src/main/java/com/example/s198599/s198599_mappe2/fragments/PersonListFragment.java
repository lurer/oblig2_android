package com.example.s198599.s198599_mappe2.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.app.ListFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.s198599.s198599_mappe2.R;
import com.example.s198599.s198599_mappe2.dummy.DummyContent;
import com.example.s198599.s198599_mappe2.models.Person;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class PersonListFragment extends ListFragment {

    private ArrayAdapter<Person> adapter;
    private AppCompatActivity listener;

    public PersonListFragment() {
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ListView lv = (ListView)view.findViewById(R.id.person_list_view);

        lv.setAdapter(adapter);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.person_list_view, container, false);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Birthday", "PersonListFragment - onCreate");


        List<Person> dummyList = new ArrayList<>();
        dummyList.add(new Person("Espen", "Zaal", "98653942", Calendar.getInstance()));
        dummyList.add(new Person("Truls", "Pettersen", "90123456", Calendar.getInstance()));

        /*List<String> dummyList = new ArrayList<>();
        dummyList.add("Test1");
        dummyList.add("Test2");*/

        adapter = new ArrayAdapter<Person>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, dummyList);


    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (AppCompatActivity)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);


    }

}
