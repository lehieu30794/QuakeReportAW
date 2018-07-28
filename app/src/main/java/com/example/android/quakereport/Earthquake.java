package com.example.android.quakereport;

public class Earthquake {

    double mMag;
    String mLocation;
    /** Time of the earthquake */
    private long mTimeInMilliseconds;

    public Earthquake(double mag, String location, long timeInMilliseconds) {
        mMag = mag;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
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
}
