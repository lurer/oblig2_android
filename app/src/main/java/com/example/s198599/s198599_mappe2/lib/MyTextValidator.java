package com.example.s198599.s198599_mappe2.lib;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * Created by espen on 10/20/15.
 */
public abstract class MyTextValidator implements TextWatcher {
    private final TextView textView;

    public MyTextValidator(TextView textView) {
        this.textView = textView;
    }

    public abstract void validate(TextView textView, String text);

    @Override
    final public void afterTextChanged(Editable s) {
        String text = textView.getText().toString();
        validate(textView, text);
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */ }

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care */ }
}