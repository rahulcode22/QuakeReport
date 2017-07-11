package com.example.android.quakereport;
//Magnitude ,Location ,date

/**
 * Created by Rahul on ०९-०७-२०१७.
 */
public class Earthquake {


    //Earthquake Location
    private String mlocation;
    //EQ date
    private long mTimeInMilliseconds;
    private double mmagnitude;
    public Earthquake(double magnitude,String location, long timeInMilliseconds)
    {
        mmagnitude=magnitude;
        mlocation=location;
        mTimeInMilliseconds=timeInMilliseconds;
    }
    //Get earthquake magnitude
    public double getMagnitude(){
        return mmagnitude;
    }
    //Get EQ location
    public String getlocation(){ return mlocation;}
    //get date
    public long getTimeInMilliseconds(){return mTimeInMilliseconds;}

    
}
