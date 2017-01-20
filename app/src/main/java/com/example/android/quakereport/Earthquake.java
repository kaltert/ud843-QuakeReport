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
    private long mTime;
    /**
     * details url.
     */
    private String mDetails;

    /**
     * Create a new Earthquake object.
     *
     * @param mag   is the earthquake magnitude.
     * @param place is the place where earthquake happened.
     * @param time  is the time of earthquake.
     */
    public Earthquake(double mag, String place, long time) {
        mMag = mag;
        mPlace = place;
        mTime = time;
    }

    /**
     * Create a new Earthquake object.
     *
     * @param mag        is the earthquake magnitude.
     * @param place      is the place where earthquake happened.
     * @param time       is the time of earthquake.
     * @param detailsUrl is the datails web-page of earthquake
     */
    public Earthquake(double mag, String place, long time, String detailsUrl) {
        mMag = mag;
        mPlace = place;
        mTime = time;
        mDetails = detailsUrl;
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
    public long getTime() {
        return mTime;
    }

    /**
     * get detail url of earthquake.
     */
    public String getDetails() {
        return (mDetails != null) ? mDetails : "NOURL";
    }
}
