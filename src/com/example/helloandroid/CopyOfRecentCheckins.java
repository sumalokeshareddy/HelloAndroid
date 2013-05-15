package com.example.helloandroid;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class CopyOfRecentCheckins extends Activity{
	final private String TAG = "TITAN_RECENT_CHECKINS";
	private TextView tv;
	String display_result = null;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.recent_checkins);
	    this.tv = ( TextView ) findViewById( R.id.textView1234 ); 
	    final String fqURL= "https://api.foursquare.com/v2/venues/search?ll=40.7,-74&oauth_token=M3FLDTKWXUJNA0WU4NJ0IIKUCMOSJBQS0ZHWY24R1HQOFNXO";
	    new RequestTask().execute(fqURL);
	    final String yelpURL= "http://api.yelp.com/business_review_search?term=yelp&tl_lat=37.9&tl_long=-122.5&br_lat=37.788022&br_long=-122.399797&limit=3&ywsid=1NXGXESsWw1agNBbVJScHA";
	    new RequestTask().execute(yelpURL);
	      
	}
	
	public void setTextViewField(String result) {
		display_result = display_result + "Result1\n";
		tv.setText(display_result);
		Log.d(TAG, display_result);
	}
	
	class RequestTask extends AsyncTask<String, String, String>{
	    
	    protected void onPostExecute(String result) {
	        super.onPostExecute(result);
	        CopyOfRecentCheckins.this.setTextViewField(result);
	        Log.d(TAG, "Result is:" + result );
	    }

		@Override
		protected String doInBackground(String... URL) {
			HttpHandler httpHand = new HttpHandler();
	        String responseString = httpHand.getResponeTypeGET(URL[0]);
	        Log.d(TAG, "doInBackground called");
			return responseString;
		}
	}
	
	
}
