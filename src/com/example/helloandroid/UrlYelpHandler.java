package com.example.helloandroid;

import android.util.Log;

public class UrlYelpHandler {
	private static final String TAG = "TITAN_URL_YELP_HANDLER";
	public final String CONSUMER_KEY = "5TSgtaNG08EQ9G1lioKrCg"; 
    public final String CONSUMER_SECRET = "zwOR3kfP34DrZ3NyMcil2HcfIlA"; 
    public final String TOKEN = "ovmnqVOXcSDZtpT6n4edmSVSpKZbPLd1"; 
    public final String TOKEN_SECRET = "TWlTP98D8D0-Clwsl0OYQqbZeFc"; 
    public final String YWSID = "1NXGXESsWw1agNBbVJScHA"; 
    public final String ENCODING_SCHEME = "UTF-8"; 
	
    String yelpBaseURL = "http://api.yelp.com/";
    String yelpNeighbour = "neighborhood_search";
    String yelpBusiness = "business_review_search";
    
    UrlYelpHandler () {
    }
	
	String getYelpUrlNeighbourhoodGeocode(double latitude, double longitude){
		String finalURL = new String(yelpBaseURL);
		finalURL = finalURL + yelpNeighbour + "?" + "lat="+ latitude + "&" + "long=" + longitude + "&" + "ywsid=" +YWSID;
		Log.d(TAG, "getYelpUrlNeighbourhoodGeocode():" + finalURL);
		return finalURL;
	}
	
	/*
	 *  Change the input arguments to Location
	 */
	String genYelpUrlBusinessGeocode(double lat, double lon) {
		String finalURL; 
		finalURL = "http://api.yelp.com/business_review_search?lat=" + lat + "&long=" + lon + "&radius=0.1000&limit=10&ywsid=" + YWSID;
		return finalURL;
	}
	
	/*String getYelpUrlNeighbourhoodLocation(String address){
		String finalURL = new String(yelpBaseURL);
		// Not correct usage .. corrent when possible
		finalURL = finalURL + yelpNeighbour + "?" + "location="+ latitude + "&" + "long=" + longitude + "&" + "ywsid=" +YWSID;
		Log.d(LOG_CAT, "getYelpUrlNeighbourhoodGeocode():" + finalURL);
		return finalURL;
	}*/
}
