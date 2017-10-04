package com.example.amitdasgupta.newsappusingretrofit.smaller_models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.amitdasgupta.newsappusingretrofit.R;

/**
 * Created by AMIT DAS GUPTA on 27-09-2017.
 */

public class CustomSpinner extends BaseAdapter{
    Context context;
    String[] spinnerNames;
    LayoutInflater inflter;

    public CustomSpinner(Context applicationContext, String[] names) {
        this.context = applicationContext;
        this.spinnerNames = names;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return spinnerNames.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner, null);
        TextView names = (TextView) view.findViewById(R.id.names);
        names.setText(spinnerNames[i]);
        return view;
    }
}
