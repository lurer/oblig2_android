package com.example.s198599.s198599_mappe2.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.s198599.s198599_mappe2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by espen on 10/13/15.
 */
public class PersonAdapter extends ArrayAdapter<Person> {

    private Context mContext;
    private int mResource;
    private int mTextViewResourceId;
    private List<Person> mList;




    public PersonAdapter(Context context, int resource, List<Person> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mList = objects;
    }

    public PersonAdapter(Context context, int resource, int textViewResourceId, List<Person> objects) {
        super(context, resource, textViewResourceId, objects);
        mContext = context;
        mResource = resource;
        mTextViewResourceId = textViewResourceId;
        mList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.person_adapter_item, null);

        }else{
            view = convertView;
        }

        TextView firstName = (TextView)view.findViewById(R.id.person_item_firstname);
        TextView lastName = (TextView)view.findViewById(R.id.person_item_lastname);

        firstName.setText(mList.get(position).getFirstName());
        lastName.setText(mList.get(position).getLastName());
        return view;
    }
}
