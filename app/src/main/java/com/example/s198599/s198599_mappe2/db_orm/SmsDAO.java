package com.example.s198599.s198599_mappe2.db_orm;

import android.content.Context;

import com.example.s198599.s198599_mappe2.models.Sms;

/**
 * Created by espen on 10/18/15.
 */
public class SmsDAO {


    public static Sms getSms(Context context, int id){

        DataModel db = null;
        Sms sms = null;
        try{

            db = Dao.getDataModel(context);
            sms = db.getObjectModel(Sms.class).getFirst("messageId", id);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.disconnect();
                db = null;
            }
        }

        return sms;
    }


    public static void updateSms(Context context, Sms sms){

        DataModel db = null;

        try{

            db = Dao.getDataModel(context);
            db.getObjectModel(Sms.class).update(sms);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.disconnect();
                db = null;
            }
        }
    }


    public static void deleteSms(Context context, Sms sms){

        DataModel db = null;

        try{

            db = Dao.getDataModel(context);
            db.getObjectModel(Sms.class).delete(sms);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.disconnect();
                db = null;
            }
        }
    }


    public static void addSms(Context context, Sms sms){

        DataModel db = null;

        try{

            db = Dao.getDataModel(context);
            db.getObjectModel(Sms.class).insert(sms);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.disconnect();
                db = null;
            }
        }
    }
}
