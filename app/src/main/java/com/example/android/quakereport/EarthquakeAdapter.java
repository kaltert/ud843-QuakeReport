package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class offers views to represent earthquake objects.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /**
     * Create a new EarthquakeAdapter object.
     *
     * @param context    is the current context (i.e. Activity) that the adapter is being created in.
     * @param earthquake is the list of Earthquakes to be displayed.
     */
    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquake) {
        super(context, 0, earthquake);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_list, parent, false);
        }

        // Get current earthquake
        Earthquake aEarthquake = getItem(position);


        // Find the magnitude textView
        TextView magTextView = (TextView) listItemView.findViewById(R.id.text_view_mag);

        // Set text to magnitude.
        magTextView.setText(String.valueOf(
                aEarthquake.getMag()
        ));

        // Find the reference place textView
        TextView refPlaceTextView = (TextView) listItemView.findViewById(R.id.text_view_ref_place);

        // Set text to place.
        refPlaceTextView.setText(getRplace(aEarthquake.getPlace()));

        // Find the  refDistance  textView
        TextView refDistancTextView = (TextView) listItemView.findViewById(R.id.text_view_ref_distanc);

        // Set text to place.
        refDistancTextView.setText(getRefDistanc(aEarthquake.getPlace()));


        // Find the date textView
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.text_view_date);

        // Set text to place.
        dateTextView.setText(getDate(aEarthquake.getTime()));

        // Find the time textView
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.text_view_time);

        // Set text to place.
        timeTextView.setText(getTime(aEarthquake.getTime()));

        return listItemView;
    }

    /**
     * Helper medhotd to get reference place of earthquake
     */
    public String getRplace(String place) {
        // delimiter index
        int dIndex = place.indexOf("of");
        return place.substring(dIndex + 2);
    }

    /**
     * Helper medhod to get 'distance from' reference place
     */

    public String getRefDistanc(String place) {
        // delimiter index
        int dIndex = place.indexOf("of");
        return place.substring(0, dIndex + 2);
    }

    /**
     * Helper medhod to get date of earthquake
     */
    public String getDate(long time) {
        return new SimpleDateFormat("yyyy MMM, d").format(new Date(time));
    }

    /**
     * Helper medhod to get time of earthquake
     */
    public String getTime(long time) {
        return new SimpleDateFormat("hh:mm aaa").format(new Date(time));
    }
}
