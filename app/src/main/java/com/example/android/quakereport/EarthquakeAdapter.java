package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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

        // Find the place textView
        TextView placeTextView = (TextView) listItemView.findViewById(R.id.text_view_place);

        // Set text to place.
        placeTextView.setText(aEarthquake.getPlace());

        // Find the time textView
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.text_view_time);

        // Set text to place.
        timeTextView.setText(aEarthquake.getTime());

        return listItemView;
    }
}
