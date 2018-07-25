package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter (Activity context, ArrayList<Earthquake> earthquakes){

        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magTextView = (TextView) convertView.findViewById(R.id.mag_text_view);
        magTextView.setText(currentEarthquake.getMag());

        TextView locationTextView = (TextView) convertView.findViewById(R.id.location_text_view);
        locationTextView.setText(currentEarthquake.getLocation());

        TextView dateTextView = (TextView) convertView.findViewById(R.id.date_text_view);
        dateTextView.setText(currentEarthquake.getDate());

        return convertView;
    }
}
