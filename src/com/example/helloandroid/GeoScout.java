package com.example.helloandroid;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class GeoScout implements LocationListener{
	private static final String TAG = "GEOSCOUT";
	double LAT;
	double LONG;
	Location location;
	private LocationManager locationManager;
	private Context mContext;
	String bestProvider;

	GeoScout(Context mCtx) {
		mContext = mCtx;
		locationManager = (LocationManager) mContext
		.getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		bestProvider = locationManager.getBestProvider(criteria, false);
	}

	public void getLocation() {
		location = locationManager
		.getLastKnownLocation(bestProvider);
	}

	public void getNewLocation() {
		//locListener = new GeoScout(this.mContext);
		locationManager.requestLocationUpdates(bestProvider, 0,
				0, (LocationListener) this);
	}
	
	
	public String getLocationString() {
		String loc;
		if (location == null) {
			loc = "Lat n Long";
		} else {
			loc = "Lat: " + location.getLatitude() + 
			"\nLong: " + location.getLongitude();
		}
		return loc;
	}

	public void onLocationChanged(Location loc) {
		location = loc;		
		Log.d(TAG, "The location has been updated!");
	}

	public void onProviderDisabled(String provider) {
		Log.d(TAG, "The provider " + provider + " is disabled");
	}

	public void onProviderEnabled(String provider) {
		Log.d(TAG, "The provider " + provider + " is enabled");
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.d(TAG, "The status of the provider " + provider
				+ " has changed");
	}

	public double getLat() {
		double lat;
		if (location != null)
			lat = this.location.getLatitude();
		else
			lat = 0;
		
		return lat;
	}

	public double getLong() {
		double lon;
		if (location != null)
			lon = this.location.getLongitude();
		else
			lon = 0;
		
		return lon;
	}
}
