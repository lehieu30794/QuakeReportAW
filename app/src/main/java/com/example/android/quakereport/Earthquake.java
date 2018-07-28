package com.example.android.quakereport;

public class Earthquake {

    String mMag;
    String mLocation;
    /** Time of the earthquake */
    private long mTimeInMilliseconds;

    public Earthquake(String mag, String location, long timeInMilliseconds) {
        mMag = mag;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public String getMag() {
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
}
