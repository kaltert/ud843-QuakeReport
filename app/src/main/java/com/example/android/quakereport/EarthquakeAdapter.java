package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class offers views to represent earthquake objects.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    // Magnitude levels
    private final int MAG_ONE = 0;
    private final int MAG_TWO = 1;
    private final int MAG_THREE = 2;
    private final int MAG_FOUR = 3;
    private final int MAG_FIVE = 4;
    private final int MAG_SIX = 5;
    private final int MAG_SEVEN = 6;
    private final int MAG_EIGHT = 7;
    private final int MAG_NINE = 8;
    private final int MAG_TEN_OR_MORE = 9;


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
        magTextView.setText(formatMag(aEarthquake.getMag()));


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(aEarthquake.getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);



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
        timeTextView.setText(formatTime(aEarthquake.getTime()));

        return listItemView;
    }

    /**
     * Helper medhotd to get reference place of earthquake
     */
    public String getRplace(String place) {
        // delimiter index
        int dIndex = place.indexOf("of");
        return (dIndex > 0 ? place.substring(dIndex + 2) : place);
    }

    /**
     * Helper medhod to get 'distance from' reference place
     */

    private String getRefDistanc(String place) {
        // delimiter index
        int dIndex = place.indexOf("of");
        return (dIndex > 0 ? place.substring(0, dIndex + 2) : "Near the");
    }

    /**
     * Helper medhod to get date of earthquake
     */
    private String getDate(long time) {
        return new SimpleDateFormat("yyyy MMM, d").format(new Date(time));
    }

    /**
     * Helper medhod to format time of earthquake
     */
    private String formatTime(long time) {
        return new SimpleDateFormat("hh:mm aaa").format(new Date(time));
    }

    /**
     * Helper method to format magnitude of eartquake.
     * It shows only one digit after dot.
     */
    private String formatMag(double magnitude) {
        return new DecimalFormat("0.0").format(magnitude);
    }

    /**
     * Helper method to get the assciated color for magnitude.
     */
    private int getMagnitudeColor(double magnitude) {
        // magnitude is double so get the floor
        // for the switch to work
        int roundLevel = (int) Math.floor(magnitude);
        switch (roundLevel) {
            case MAG_ONE:
                return ContextCompat.getColor(getContext(), R.color.magnitude1);
            case MAG_TWO:
                return ContextCompat.getColor(getContext(), R.color.magnitude2);
            case MAG_THREE:
                return ContextCompat.getColor(getContext(), R.color.magnitude3);
            case MAG_FOUR:
                return ContextCompat.getColor(getContext(), R.color.magnitude4);
            case MAG_FIVE:
                return ContextCompat.getColor(getContext(), R.color.magnitude5);
            case MAG_SIX:
                return ContextCompat.getColor(getContext(), R.color.magnitude6);
            case MAG_SEVEN:
                return ContextCompat.getColor(getContext(), R.color.magnitude7);
            case MAG_EIGHT:
                return ContextCompat.getColor(getContext(), R.color.magnitude8);
            case MAG_NINE:
                return ContextCompat.getColor(getContext(), R.color.magnitude9);
            default:
                return ContextCompat.getColor(getContext(), R.color.magnitude10plus);
        }
    }
}
