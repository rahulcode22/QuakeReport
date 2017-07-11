package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends ActionBarActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquakes
        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();

        ListView listView =(ListView) findViewById(R.id.list);
        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this,earthquakes);

        listView.setAdapter(earthquakeAdapter);

    }
}