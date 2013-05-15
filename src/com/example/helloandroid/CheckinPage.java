package com.example.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CheckinPage extends Activity{
	final static String LOGTAG = "TITAN_CHECKIN_PAGE";
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkin_page);
		
		TextView tv = (TextView) findViewById(R.id.textView1);
		int type = getIntent().getIntExtra("type", 0);
		if (type == 1) {
			/*
			 * FourSquare Type
			 */
			//String venueId = getIntent().getStringExtra("venueId");
			//new FQOAuth2(this).getSavedAccessToken();
			tv.setText("Came with Fourquare");
		} else if (type == 2) {
			/*
			 * Yelp Type
			 */
			tv.setText("Came with Yelp");
		} else if (type == 3) {
			tv.setText("Came with Facebook");
		} else if (type == 4) {
			tv.setText("Came with Google");
		} else {
			tv.setText("Sorry!! Work in progress");
		}
	}
}
