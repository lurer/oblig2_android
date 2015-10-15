package com.example.s198599.s198599_mappe2.models;

/**
 * Created by espen on 10/5/15.
 */
public class Sms {

    private int messageId;
    private String message;

    public Sms(String message) {
        this.message = message;
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
