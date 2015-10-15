package com.example.s198599.s198599_mappe2.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by espen on 10/5/15.
 */
public class Person implements Serializable{

    private String firstName;
    private String lastName;
    private String phoneNr;
    private Calendar birthDate;

    public Person(String firstName, String lastName, String phoneNr, Calendar birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNr = phoneNr;
        this.birthDate = birthDate;
    }

    public Person(){}


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }
}
