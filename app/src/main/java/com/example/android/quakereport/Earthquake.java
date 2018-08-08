package com.example.android.quakereport;

public class Earthquake {

    double mMag;
    String mLocation;
    /** Time of the earthquake */
    private long mTimeInMilliseconds;
    private String mUrl;


    public Earthquake(double mag, String location, long timeInMilliseconds, String url) {
        mMag = mag;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl=url;
    }

    public double getMag() {
        return mMag;
    }

    public String getLocation() {
        return mLocation;
    }

    /**
     * Returns the time of the earthquake.
     */
    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    /**
     * Returns the website URL to find more information about the earthquake.
     */
    public String getUrl() {
        return mUrl;
    }
}
