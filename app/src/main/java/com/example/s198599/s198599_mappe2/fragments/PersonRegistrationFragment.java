package com.example.s198599.s198599_mappe2.fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.s198599.s198599_mappe2.R;
import com.example.s198599.s198599_mappe2.models.Person;
import com.example.s198599.s198599_mappe2.registration_activities.AddPerson;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonRegistrationFragment extends Fragment{



    private EditText firstNameEdit;
    private EditText lastNameEdit;
    private EditText phoneNumberEdit;

    public PersonRegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        firstNameEdit = (EditText)getView().findViewById(R.id.edit_first_name);
        lastNameEdit = (EditText)getView().findViewById(R.id.edit_last_name);
        phoneNumberEdit = (EditText)getView().findViewById(R.id.edit_phone_number);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person_registration, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    public Person getPersonDetails(){
        Person p = new Person();

        p.setFirstName(firstNameEdit.toString());
        p.setLastName(lastNameEdit.toString());
        p.setPhoneNr(phoneNumberEdit.toString());

        return p;
    }


    public void setPersonInView(Person p, boolean isEditable){
        if(p != null){
            firstNameEdit.setText(p.getFirstName());
            lastNameEdit.setText(p.getLastName());
            phoneNumberEdit.setText(p.getPhoneNr());
        }
    }

}
