package com.example.s198599.s198599_mappe2.fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.s198599.s198599_mappe2.R;
import com.example.s198599.s198599_mappe2.lib.StaticLib;
import com.example.s198599.s198599_mappe2.models.Person;

import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonRegistrationFragment extends Fragment{



    private EditText firstNameEdit;
    private EditText lastNameEdit;
    private EditText phoneNumberEdit;
    private Calendar displayedBirthDate;
    private Calendar editedBirthDate;
    private Button dateButton;


    public PersonRegistrationFragment() {
        // Required empty public constructor

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_person_registration, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstNameEdit = (EditText)getView().findViewById(R.id.edit_first_name);
        lastNameEdit = (EditText)getView().findViewById(R.id.edit_last_name);
        phoneNumberEdit = (EditText)getView().findViewById(R.id.edit_phone_number);
        dateButton = (Button)getView().findViewById(R.id.open_date_picker);

        final MyDateDialog myDateDialog = new MyDateDialog();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editedBirthDate = displayedBirthDate;
                Log.d("Birthday", "Clicked Birth date button");
                myDateDialog.show(getFragmentManager(), "datePicker");

                editedBirthDate = myDateDialog.getDate();
                if (!editedBirthDate.equals(displayedBirthDate)) {
                    displayedBirthDate = editedBirthDate;
                }
                if(editedBirthDate != null){
                    Log.d("Birthday", "Displayed date: " + StaticLib.getDateAsString(displayedBirthDate));
                    Log.d("Birthday", "Edited date set: " + StaticLib.getDateAsString(editedBirthDate));
                }
            }
        });


    }


    public Person getPersonDetails(){
        Person p = new Person();

        p.setFirstName(firstNameEdit.toString());
        p.setLastName(lastNameEdit.toString());
        p.setPhoneNr(phoneNumberEdit.toString());
        p.setBirthDate(displayedBirthDate);
        return p;
    }


    public void setPersonInView(Person p){
        Log.d("Birthday", "PersonFragment " + p.getFirstName() + " " + p.getLastName());
        if(p != null){
            firstNameEdit.setText(p.getFirstName());
            lastNameEdit.setText(p.getLastName());
            phoneNumberEdit.setText(p.getPhoneNr());
            displayedBirthDate = p.getBirthDate();
        }
    }



}
