package com.example.android.quakereport;

/**
 * {@link Earthquake} represents a location in Portland.
 */
public class Earthquake {
    private double mEarthquakeMagnitude;
    private String mEarthquakeLocation;
    private Long mTimeInMilliseconds;
    private String url;


    /**
     * Create an object that contains a title, hours, address, phone number, and image.
     *
     * @param earthquakeMagnitude is the magnitude of the earthquake.
     * @param earthquakeLocation  is the title of the earthquake.
     * @param timeInMilliseconds  is the time in milliseconds (from the Epoch) when the earthquake happened.
     */
    public Earthquake(double earthquakeMagnitude, String earthquakeLocation, Long timeInMilliseconds, String url) {
        this.mEarthquakeMagnitude = earthquakeMagnitude;
        this.mEarthquakeLocation = earthquakeLocation;
        this.mTimeInMilliseconds = timeInMilliseconds;
        this.url = url;
    }

    public String getmEarthquakeLocation() {
        return mEarthquakeLocation;
    }

    public double getmEarthquakeMagnitude() {
        return mEarthquakeMagnitude;
    }

    public Long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getUrl() {
        return url;
    }
}
