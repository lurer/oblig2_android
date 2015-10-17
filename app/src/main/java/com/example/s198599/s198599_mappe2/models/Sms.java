package com.example.s198599.s198599_mappe2.models;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by espen on 10/5/15.
 */
public class Sms {

    private int messageId;
    private String message;
    private Calendar dateTime;


    public Sms(String message, Calendar dateTime) {
        this.message = message;
        this.dateTime = dateTime;
    }

    public Sms(int messageId, String message, Calendar dateTime) {
        this.messageId = messageId;
        this.message = message;
        this.dateTime = dateTime;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

    public Sms(){}

    public int getMessageId() {
        return messageId;
    }


    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
