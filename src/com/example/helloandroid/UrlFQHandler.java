package com.example.helloandroid;

import android.util.Log;

public class UrlFQHandler {
	
	private static final String TAG = "TITAN_URL_FQ_HANDLER";
	public final String OAUTH_TOKEN = "M3FLDTKWXUJNA0WU4NJ0IIKUCMOSJBQS0ZHWY24R1HQOFNXO";
	
	String fqVenueURLOne = "https://api.foursquare.com/v2/venues/search?";
    String fqVenueURLTwo = "llAcc=10000.0&alt=0&atlAcc=10000.0&limit=10&oauth_token=";
    String fqCheckin = "checkins/add?";
    String fqRecentCheckin = "checkins/recent?"; 
    
    UrlFQHandler () {
    }
    
    //https://api.foursquare.com/v2/venues/search?ll=37.39960375,%20-122.01881088&llAcc=10000.0&alt=0&atlAcc=10000.0&limit=10&oauth_token=
	
	String getFQUrlVenueGeocode(double latitude, double longitude){
		String finalURL = new String("");
		finalURL = fqVenueURLOne + "ll="+ latitude + "," + longitude + "&" + fqVenueURLTwo + OAUTH_TOKEN;
		Log.d(TAG, "getFQUrlVenueGeocode():" + finalURL);
		return finalURL;
	}
	
//	String getFQUrlCheckin(String venueID, String broadcast){
//		String finalURL = new String(fqBaseURL);
//		finalURL = finalURL + fqCheckin + "venueId="+ venueID + "&" + "broadcast=" + broadcast + "&" +"oauth_token=" +OAUTH_TOKEN;
//		Log.d(TAG, "getFQUrlCheckin():" + finalURL);
//		return finalURL;
//	}
//	
//	String getFQUrlRecentCheckin(){
//		String finalURL = new String(fqBaseURL);
//		finalURL = finalURL + fqRecentCheckin + "oauth_token=" +OAUTH_TOKEN;
//		Log.d(TAG, "getFQUrlRecentCheckin():" + finalURL);
//		return finalURL;
//	}
}
