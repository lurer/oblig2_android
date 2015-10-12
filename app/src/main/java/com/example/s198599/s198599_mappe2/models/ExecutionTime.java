package com.example.s198599.s198599_mappe2.models;

import java.util.Calendar;

/**
 * Created by espen on 10/5/15.
 */
public class ExecutionTime {

    private Calendar executeNow;

    public ExecutionTime(Calendar executeNow) {
        this.executeNow = executeNow;
    }

    public ExecutionTime(){}

    public Calendar getExecuteNow() {
        return executeNow;
    }

    public void setExecuteNow(Calendar executeNow) {
        this.executeNow = executeNow;
    }
}
