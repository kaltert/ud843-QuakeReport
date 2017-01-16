package com.example.android.quakereport;

/**
 * This class represents an earthquake.
 * It's character is determent by:
 * magnitude,
 * place,
 * time.
 */

public class Earthquake {
    /**
     * magnitutde of erthquake
     */
    private double mMag;
    /**
     * place where earthquake happened
     */
    private String mPlace;
    /**
     * time of earthquake
     */
    private String mTime;

    /**
     * Create a new Earthquake object.
     *
     * @param mag   is the earthquake magnitude.
     * @param place is the place where earthquake happened.
     * @param time  is the time of earthquake.
     */
    public Earthquake(double mag, String place, String time) {
        mMag = mag;
        mPlace = place;
        mTime = time;
    }

    /**
     * get magnitude of earthquake
     */
    public double getMag() {
        return mMag;
    }

    /**
     * get place of earthquake
     */
    public String getPlace() {
        return mPlace;
    }

    /**
     * get time of earthquake
     */
    public String getTime() {
        return mTime;
    }
}
