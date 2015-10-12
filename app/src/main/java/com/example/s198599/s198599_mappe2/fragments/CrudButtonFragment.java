package com.example.s198599.s198599_mappe2.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.s198599.s198599_mappe2.R;
import com.example.s198599.s198599_mappe2.registration_activities.AddPerson;
import com.example.s198599.s198599_mappe2.registration_activities.DetailPerson;
import com.example.s198599.s198599_mappe2.registration_activities.EditPerson;


/**
 * A simple {@link Fragment} subclass.
 */
public class CrudButtonFragment extends Fragment implements View.OnClickListener{


    public CrudButtonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crud_buttons, container, false);

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Birthday", "I onCreateView");

        getView().findViewById(R.id.btn_add).setOnClickListener(this);
        getView().findViewById(R.id.btn_edit).setOnClickListener(this);
        getView().findViewById(R.id.btn_delete).setOnClickListener(this);
        getView().findViewById(R.id.btn_details).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                Log.d("Birthday", "CLicked Add");
                addNewPerson(v);
                break;
            case R.id.btn_edit:
                editPerson(v);
                Log.d("Birthday", "CLicked Edit");
                break;
            case R.id.btn_delete:
                Log.d("Birthday", "CLicked Delete");
                break;
            case R.id.btn_details:
                detailsPerson(v);
                Log.d("Birthday", "CLicked Details");
                break;
        }
    }



    public void addNewPerson(View view){
        Intent i = new Intent(getActivity(), AddPerson.class);
        startActivity(i);

    }

    public void editPerson(View view){
        Intent i = new Intent(getActivity(), EditPerson.class);
        startActivity(i);

    }

    public void detailsPerson(View view){
        Intent i = new Intent(getActivity(), DetailPerson.class);
        startActivity(i);

    }
}
