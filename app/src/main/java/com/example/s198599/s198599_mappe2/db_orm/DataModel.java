package com.example.s198599.s198599_mappe2.db_orm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.s198599.s198599_mappe2.models.Person;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import za.co.neilson.sqlite.orm.DatabaseDriverInterface;
import za.co.neilson.sqlite.orm.DatabaseInfo;
import za.co.neilson.sqlite.orm.DatabaseModel;
import za.co.neilson.sqlite.orm.ObjectModel;
import za.co.neilson.sqlite.orm.android.AndroidObjectModel;
import za.co.neilson.sqlite.orm.android.AndroidSqliteDatabaseDriverInterface;

/**
 * Created by espen on 10/17/15.
 */
public class DataModel extends DatabaseModel<Cursor, ContentValues> {


    public DataModel(Context context) throws SQLException, ClassNotFoundException, NoSuchFieldException {
        super(context);
        Log.d("Birthday", "DataModel - Konstruktør");
    }

    @Override
    public ObjectModel<DatabaseInfo, Cursor, ContentValues> onCreateDatabaseInfoModel()
            throws ClassNotFoundException, NoSuchFieldException {
        return new AndroidObjectModel<DatabaseInfo>(this) {
        };
    }

    @Override
    protected DatabaseDriverInterface<Cursor, ContentValues> onInitializeDatabaseDriverInterface(Object... objects) {
        return new AndroidSqliteDatabaseDriverInterface((Context)objects[0], this);
    }

    @Override
    protected void onRegisterObjectModels(HashMap<Type, ObjectModel<?, Cursor, ContentValues>> hashMap)
            throws ClassNotFoundException, NoSuchFieldException {

        hashMap.put(Person.class, new AndroidObjectModel<Person>(this){

        });


    }

    @Override
    public String getDatabaseName() {
        return "database.db";
    }

    @Override
    public int getDatabaseVersion() {
        return 19;
    }

    @Override
    protected void onCreate() throws SQLException {
        super.onCreate();
        Log.d("Birthday", "DataModel - onCreate");
    }

    @Override
    protected void onUpgrade(int previousVersion) throws SQLException {
        super.onUpgrade(previousVersion);
    }

    @Override
    protected void onInsertDefaultValues() {
        Person p1 = new Person();
        p1.setFirstName("Espen");
        p1.setLastName("Zaal");
        p1.setPhoneNr("98653942");
        Calendar birthdate = new GregorianCalendar(1979, 5-1, 26);
        p1.setBirthDate(birthdate);

        Person p2 = new Person();
        p2.setFirstName("Truls");
        p2.setLastName("Pettersen");
        p2.setPhoneNr("90223344");
        birthdate = new GregorianCalendar(1990, 8-1, 1);
        p2.setBirthDate(birthdate);

        try{
            getObjectModel(Person.class).insert(p1);
            getObjectModel(Person.class).insert(p2);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
