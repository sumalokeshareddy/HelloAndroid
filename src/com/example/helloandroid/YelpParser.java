package com.example.helloandroid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

class YelpHeader {
	String text;
	double code;
	String version;
	
	YelpHeader() {
		text = null;
		version = null;
	}
}

public class YelpParser { 

	private static final String LOGTAG = "TITAN_YELP_PARSER";
	YelpHeader yHeader;
	int iter;
	boolean done_parsing;
	
	YelpParser () {
		yHeader = new YelpHeader();
		iter = 0;
	}
	
	/*
	 * First Parse Yelp Response header before proceeding to the actual response
	 * if TRUE, its OK to parse
	 */
	boolean parseYelpHeader(String jResponse) {
		JSONObject jObject;
		boolean res = false;
		try {
			jObject = new JSONObject(jResponse);
			JSONObject menuObject = jObject.getJSONObject("message");
		
			yHeader.text = menuObject.getString("text");
			yHeader.code = menuObject.getLong("code");
			yHeader.version = menuObject.getString("version");
			
			if (yHeader.code == 0) {
				res = true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	TitanPlaces parseYelpNeighbourhood(String jResponse) {
		TitanPlaces tPlaces = new TitanPlaces();
		try {
			JSONObject iObject = new JSONObject(jResponse);
			JSONArray menuitemArray = iObject.getJSONArray("neighborhoods");
			//tPlaces = new TitanPlaces[1];
//			for (int i = 0; i < menuitemArray.length(); i++) {
				Log.d("Array", "We are here");
				tPlaces.setName(menuitemArray.getJSONObject(0)
						.getString("name").toString());
				tPlaces.setUrl(menuitemArray.getJSONObject(0)
						.getString("url").toString());
				tPlaces.setCity(menuitemArray.getJSONObject(0)
						.getString("city").toString());
				tPlaces.setCountry(menuitemArray.getJSONObject(0)
						.getString("country").toString());
				tPlaces.setState(menuitemArray.getJSONObject(0)
						.getString("state").toString());
//			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return tPlaces;
	}
	
	public void resetIter() {
		this.iter = 0;
	}
	
	
	TitanPlaces parseYelpBusiness(String response) {
		TitanPlaces tPlaces = new TitanPlaces();
		try {
			JSONObject iObject = new JSONObject(response);
			JSONArray menuitemArray = iObject.getJSONArray("businesses");
			int len = menuitemArray.length();
			if (menuitemArray.length() != this.iter) {
				tPlaces.setName(menuitemArray
						.getJSONObject(this.iter)
						.getString("name").toString());
				tPlaces.setUrl(menuitemArray
						.getJSONObject(this.iter)
						.getString("url").toString());
				tPlaces.setAddress(
						menuitemArray.getJSONObject(this.iter)
						.getString("address1").toString() + " " +
						menuitemArray.getJSONObject(this.iter)
						.getString("address2").toString() + " " +
						menuitemArray.getJSONObject(this.iter)
						.getString("address3").toString());
				tPlaces.setPhone(menuitemArray
						.getJSONObject(this.iter)
						.getString("phone").toString());
				tPlaces.setCity(menuitemArray
						.getJSONObject(this.iter)
						.getString("city").toString());
				tPlaces.setZip(menuitemArray
						.getJSONObject(this.iter)
						.getString("zip").toString());
				tPlaces.setCountry(menuitemArray
						.getJSONObject(this.iter)
						.getString("country").toString());
				tPlaces.setState(menuitemArray
						.getJSONObject(this.iter)
						.getString("state").toString());
				tPlaces.setYelpid(menuitemArray
						.getJSONObject(this.iter)
						.getString("id").toString());
				tPlaces.setPhotourl(menuitemArray
						.getJSONObject(this.iter)
						.getString("photo_url").toString());
				tPlaces.setLatitude(menuitemArray
						.getJSONObject(this.iter)
						.getDouble("latitude"));
				tPlaces.setLongitude(menuitemArray
						.getJSONObject(this.iter)
						.getDouble("longitude"));
				tPlaces.setDistance(menuitemArray
						.getJSONObject(this.iter)
						.getDouble("distance"));
				this.iter++;
			} else {
				tPlaces = null;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return tPlaces;
	}


	public int getNumberBusiness(String response) {
		int len = 0;
		try {
			JSONObject iObject = new JSONObject(response);
			JSONArray menuitemArray = iObject.getJSONArray("businesses");
			len = menuitemArray.length();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return len;
	}
}
