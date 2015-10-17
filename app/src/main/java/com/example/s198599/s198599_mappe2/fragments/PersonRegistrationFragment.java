package com.example.s198599.s198599_mappe2.fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.s198599.s198599_mappe2.R;
import com.example.s198599.s198599_mappe2.models.Person;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonRegistrationFragment extends Fragment{


    private int personID;
    private EditText firstNameEdit;
    private EditText lastNameEdit;
    private EditText phoneNumberEdit;
    private Calendar displayedBirthDate;

    private DatePicker datePicker;

    public PersonRegistrationFragment() {}


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
        //dateButton = (Button)getView().findViewById(R.id.open_date_picker);
        datePicker = (DatePicker)getView().findViewById(R.id.date_picker);


    }


    public Person getUpdatedPerson(){
        Person p = new Person();

        p.setPersonId(personID);
        p.setFirstName(firstNameEdit.getText().toString());
        p.setLastName(lastNameEdit.getText().toString());
        p.setPhoneNr(phoneNumberEdit.getText().toString());

        if(displayedBirthDate == null)
            displayedBirthDate = new GregorianCalendar();
        displayedBirthDate.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        p.setBirthDate(displayedBirthDate);

        return p;
    }


    public void setPersonInView(Person p){
        Log.d("Birthday", "PersonFragment " + p.getFirstName() + " " + p.getLastName());
        if(p != null){
            personID = p.getPersonId();
            firstNameEdit.setText(p.getFirstName());
            lastNameEdit.setText(p.getLastName());
            phoneNumberEdit.setText(p.getPhoneNr());
            displayedBirthDate = p.getBirthDate();

            int year = displayedBirthDate.get(Calendar.YEAR);
            int month = displayedBirthDate.get(Calendar.MONTH);
            int day = displayedBirthDate.get(Calendar.DAY_OF_MONTH);
            datePicker.updateDate(year, month, day);

        }
    }



}
