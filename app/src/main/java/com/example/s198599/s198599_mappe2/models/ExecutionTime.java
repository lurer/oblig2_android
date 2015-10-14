package com.example.s198599.s198599_mappe2.models;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by espen on 10/5/15.
 */
public class ExecutionTime {

    private Date executeNow;

    public ExecutionTime(Date executeNow) {
        this.executeNow = executeNow;
    }

    public ExecutionTime(){}

    public Date getExecuteNow() {
        return executeNow;
    }

    public void setExecuteNow(Date executeNow) {
        this.executeNow = executeNow;
    }
}
