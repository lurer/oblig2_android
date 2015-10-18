package com.example.s198599.s198599_mappe2.db_orm;

import android.content.Context;
import android.util.Log;

import com.example.s198599.s198599_mappe2.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by espen on 10/18/15.
 */
public class PersonDAO {

    public static List<Person> getListFromDatabase(Context context){

        DataModel db = null;
        List<Person> list = null;
        try {

            db = Dao.getDataModel(context);
            list = db.getObjectModel(Person.class).getAll();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.disconnect();
                db = null;
            }
        }

        return list;
    }


    public static void updatePerson(Context context, Person p){


        DataModel db = null;
        try {

            db = Dao.getDataModel(context);
            db.getObjectModel(Person.class).update(p);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.disconnect();
                db = null;
            }
        }
    }


    public static void addPerson(Context context, Person p){

        DataModel db = null;
        try {

            db = Dao.getDataModel(context);
            db.getObjectModel(Person.class).insert(p);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.disconnect();
                db = null;
            }
        }
    }


    public static void deletePersons(Context context, List<Integer> personIdsSelected){
        DataModel db = null;
        try {

            db = Dao.getDataModel(context);
            Log.d("Birthday", "Skal i gang med sletting..");
            List<Person> toBeDeleted = new ArrayList<>();
            for(int i = 0; i < personIdsSelected.size(); i++){

                int index = personIdsSelected.get(i);
                Person p = db.getObjectModel(Person.class).getFirst("personId=?",  index);
                toBeDeleted.add(p);
                Log.d("Birthday", "Sletter: " + p.toString());

            }
            db.getObjectModel(Person.class).deleteAll(toBeDeleted);

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
