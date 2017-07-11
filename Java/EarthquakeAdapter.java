/**
 * Created by Rahul on ०९-०७-२०१७.
 */
package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.awt.font.TextAttribute;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.DecimalFormat;
import android.graphics.drawable.GradientDrawable;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    String primaryLocation;
    String locationOffset;
    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);

    }
    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude)
    {
        int magnitudeColorResourceId;
        int magnitudeFloor =(int) Math.floor(magnitude);
        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;


        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        }
        //get the object located at this position in the list
        Earthquake currEarthquake = getItem(position);
        //find the TextView int the list_item.xml with id mag_data
        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag_data);
        //Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currEarthquake.getMagnitude());
        //Get the magnitude from getMagnitude method
        //set this text to textView
        magTextView.setText(formattedMagnitude);

        //find the textView in the list_item.xml with id location_data
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location_data);
        locationTextView.setText(currEarthquake.getlocation());
        //create a new date object from the time in milliseconds
        Date dateObject = new Date(currEarthquake.getTimeInMilliseconds());
        //find the textView in the list_item.xml with id date_data
        TextView dateView = (TextView) listItemView.findViewById(R.id.date_data);
        //Format the date string
        String formattedDate = formatDate(dateObject);
        dateView.setText(formattedDate);

        //Find the textView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);

        //Get the original String from the Earthquake object and store that in a variable
        String originalLocation = currEarthquake.getlocation();
        //Splitting String
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();
        //Get the appropriate background color based pn magnitude
        int magnitudeColor = getMagnitudeColor(currEarthquake.getMagnitude());
        //Set the color on the magnitude color
        magnitudeCircle.setColor(magnitudeColor);
        return listItemView;
    }
}
