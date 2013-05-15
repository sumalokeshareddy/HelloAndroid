package com.example.helloandroid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class FqHeader {
	String text;
	double code;
	String version;
	
	FqHeader() {
		text = null;
		version = null;
	}
}

public class FQParser { 

	private static final String LOG_TAG = "TITAN_FQ_PARSER";
	FqHeader fqHeader;
	int iter;
	boolean done_parsing;
	
	FQParser () {
		fqHeader = new FqHeader(); 
		iter = 0;
		done_parsing = false;
	}
	
	/*
	 * First Parse FourSquare Response header before proceeding to the actual response
	 * if TRUE, its OK to parse
	 */
	boolean parseFqHeader(String jResponse) {
		JSONObject jObject;
		boolean res = false;
		try {
			jObject = new JSONObject(jResponse);
			JSONObject menuObject = jObject.getJSONObject("meta");
		
			fqHeader.code = menuObject.getLong("code");
			
			if (fqHeader.code == 200) {
				res = true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return res;
	}
	

	public void resetIter() {
		this.iter = 0;
		this.done_parsing = false;
	}
	
	TitanPlaces parseFqNeighbourhood(String jResponse) {
		TitanPlaces tPlaces = new TitanPlaces();
		try {
			JSONObject iObject = new JSONObject(jResponse);
			JSONArray itemArray = iObject.getJSONObject("response").getJSONArray("groups").getJSONObject(0).getJSONArray("items");
			int len= itemArray.length();
			
			if ( len != this.iter) {
				JSONObject locationObject = itemArray.getJSONObject(this.iter).getJSONObject("location");
				tPlaces.setName(itemArray.getJSONObject(this.iter).getString("name").toString());
				//tPlaces.setCity(locationObject.getString("city").toString());
				//tPlaces.setState(locationObject.getString("state").toString());
				this.iter++;
			}else {
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
			JSONArray itemArray = iObject.getJSONObject("response").getJSONArray("groups").getJSONObject(1).getJSONArray("items");
				len = itemArray.length();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return len;
	}

}
