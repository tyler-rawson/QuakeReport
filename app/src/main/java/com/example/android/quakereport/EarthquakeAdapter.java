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

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }
        Earthquake currentEarthquake = getItem(position); // Find the earthquake at the given position in the list of earthquakes
        String originalLocation = currentEarthquake.getLocation();
        String primaryLocation;
        String locationOffset;
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }
        TextView primaryLocationView = listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);
        TextView locationOffsetView = listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);
        TextView magnitudeView = listItemView.findViewById(R.id.magnitude); // Find the TextView with view ID magnitude
        TextView dateView = listItemView.findViewById(R.id.date); // Find the TextView with view ID date
        TextView timeView = listItemView.findViewById(R.id.time); // Find the TextView with view ID time
        magnitudeView.setText(formatMagnitude(currentEarthquake.getMagnitude())); // Display the magnitude of the current earthquake in that TextView
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds()); // Create a new Date object from the time in milliseconds of the earthquake
        String formattedDate = formatDate(dateObject); // Format the date string (i.e. "Mar 3, 1984")
        String formattedTime = formatTime(dateObject); // Format the time string (i.e. "4:30PM")
        dateView.setText(formattedDate); // Display the date of the current earthquake in that TextView
        timeView.setText(formattedTime); // Display the time of the current earthquake in that TextView
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        return listItemView; // Return the list item view that is now showing the appropriate data
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 10:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
        return df.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.US);
        return tf.format(dateObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
}
