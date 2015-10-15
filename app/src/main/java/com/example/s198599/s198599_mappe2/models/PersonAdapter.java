package com.example.s198599.s198599_mappe2.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.s198599.s198599_mappe2.ApplicationState;
import com.example.s198599.s198599_mappe2.R;
import com.example.s198599.s198599_mappe2.lib.StaticLib;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by espen on 10/13/15.
 */
public class PersonAdapter extends ArrayAdapter<Person> {

    private Context mContext;
    private int mResource;
    private int mTextViewResourceId;
    private List<Person> mList;
    private CheckboxChangedCallback checkboxCallback;
    private EditPersonCallback editPersonCallback;


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


    public interface CheckboxChangedCallback {
        void onCheckboxClicked(int position, boolean isChecked);
    }

    public interface EditPersonCallback{
        void onEditPersonClicked(int position);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.person_adapter_item, null);

        }else{
            view = convertView;
        }

        final ItemElements element = new ItemElements();
        element.firstName = (TextView)view.findViewById(R.id.person_item_firstname);
        element.lastName = (TextView)view.findViewById(R.id.person_item_lastname);
        element.phone = (TextView)view.findViewById(R.id.person_item_phone);
        element.date = (TextView)view.findViewById(R.id.person_item_date);
        element.checkBox = (CheckBox)view.findViewById(R.id.person_item_checkbox);
        element.details = (ImageView)view.findViewById(R.id.person_item_details);

        element.firstName.setText(mList.get(position).getFirstName());
        element.lastName.setText(mList.get(position).getLastName());
        element.phone.setText(mList.get(position).getPhoneNr());


        element.date.setText(StaticLib.getDateAsString(mList.get(position).getBirthDate()));


        //lytter på om checkbox er endret/trykket og gir beskjed via checkboxCallback om hendelsen
        element.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(element.checkBox.isChecked()){
                    checkboxCallback.onCheckboxClicked(position, true);
                }else{
                    checkboxCallback.onCheckboxClicked(position, false);
                }
            }
        });


        element.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPersonCallback.onEditPersonClicked(position);
            }
        });

        return view;
    }



    /**
     * Set-metode for å sette checkboxCallback til PersonListFragment.
     * Hver gang
     * @param callback
     */
    public void setCheckboxChangedCallback(CheckboxChangedCallback callback){
        this.checkboxCallback = callback;
    }


    /**
     * Set-metode for å sette callback for editering av person
     * @param callback
     */
    public void setEditPersonCallback(EditPersonCallback callback){
        editPersonCallback = callback;
    }


    private class ItemElements{
        TextView firstName;
        TextView lastName;
        TextView phone;
        TextView date;
        CheckBox checkBox;
        ImageView details;
    }
}
