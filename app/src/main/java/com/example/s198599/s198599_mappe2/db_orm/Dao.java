package com.example.s198599.s198599_mappe2.db_orm;

import android.content.Context;

import java.sql.SQLException;

/**
 * Created by espen on 10/17/15.
 */
public class Dao {

    private static DataModel dataModel;

    public static DataModel getDataModel(Context context)
            throws ClassNotFoundException, NoSuchFieldException, SQLException {
        if(dataModel == null)
            dataModel = new DataModel(context);
        return dataModel;
    }

    public static void setDataModel(DataModel model){
        dataModel = model;
    }
}
