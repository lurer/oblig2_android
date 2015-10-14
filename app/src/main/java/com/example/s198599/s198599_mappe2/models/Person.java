package com.example.s198599.s198599_mappe2.models;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by espen on 10/5/15.
 */
public class Person {

    private String firstName;
    private String lastName;
    private String phoneNr;
    private Date birthDate;

    public Person(String firstName, String lastName, String phoneNr, Date birthDate) {
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
