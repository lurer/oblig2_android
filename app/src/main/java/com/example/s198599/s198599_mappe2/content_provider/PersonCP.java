package com.example.s198599.s198599_mappe2.content_provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;

import com.example.s198599.s198599_mappe2.db_orm.PersonDAO;
import com.example.s198599.s198599_mappe2.models.Person;

import java.util.List;

/**
 * Created by espen on 10/20/15.
 */
public class PersonCP extends ContentProvider {

    public final static String PROVIDER = "com.example.s198599.s198599_mappe2.content_provider";

    public final static int PERSON = 1;
    public final static int MPERSON =2;

    public static final Uri CONTENT_URI = Uri.parse("content://"+PROVIDER + "/person");
    public static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER, "person", MPERSON);
        uriMatcher.addURI(PROVIDER, "person/#", PERSON);
    }


    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Log.d("Birthday", "CP - Prøver å hente liste mer personer");

        List<Person> list = PersonDAO.getListFromDatabase(getContext());
        String[] personColumns = new String[]{"personId","firstName", "lastName", "phoneNr", "birthDate"};
        MatrixCursor c = new MatrixCursor(personColumns);

        for(Person p : list){
            c.addRow(new Object[]{p.getPersonId(), p.getFirstName(), p.getLastName(), p.getPhoneNr(), p.getBirthDate().getTime().getTime()});
        }

        return c;
    }



    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
