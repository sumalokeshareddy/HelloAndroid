package com.example.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class PlacesView extends Activity {

	final static String LOGTAG = "TITAN_PLACES_VIEW";
	CheckBox cFQ, cYelp, cFB, cGoogle;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.places_view);
		
		String photo_url = getIntent().getStringExtra("photourl");
		TextView placeName = ( TextView ) findViewById( R.id.placeName );
		placeName.setText(getIntent().getStringExtra("name"));
		TextView placeAddress = ( TextView ) findViewById( R.id.placeAddress );
		placeAddress.setText(getIntent().getStringExtra("address"));
		ImageView photo = (ImageView) findViewById(R.id.placeIcon);
		photo.setImageBitmap(BitmapUtils.loadBitmap(photo_url));
		
		/*
		 *  CheckBox Types
		 */
		cFQ = (CheckBox) findViewById(R.id.fqcb);
		cYelp = (CheckBox) findViewById(R.id.yelpcb);
		cFB = (CheckBox) findViewById(R.id.fbcb);
		cGoogle = (CheckBox) findViewById(R.id.googlecb);
		
		cFQ.setOnCheckedChangeListener(new CheckBoxCheckedChangeListener());
		cYelp.setOnCheckedChangeListener(new CheckBoxCheckedChangeListener());
		cFB.setOnCheckedChangeListener(new CheckBoxCheckedChangeListener());
		cGoogle.setOnCheckedChangeListener(new CheckBoxCheckedChangeListener());
		
		Button btnCheckin = (Button) findViewById(R.id.btnCheckin);
		btnCheckin.setOnClickListener(new CheckinOnClickListener());
	}

	class CheckinOnClickListener implements OnClickListener {
		private int loginType;
		
		public void onClick(View v) {
			int type = 0;
			if (cFQ.isChecked()) {
				Log.d(LOGTAG, "Foursquare is checked");
				type = type | 1;
			} else if (cYelp.isChecked()) {
				Log.d(LOGTAG, "Foursquare is checked");
				type = type | 2;
			} else if (cFB.isChecked()) {
				Log.d(LOGTAG, "Facebook is checked");
				type = type | 3;
			} else if (cGoogle.isChecked()) {
				Log.d(LOGTAG, "Google is checked");
				type = type | 4;
			}
			loginType = type;
			Intent myIntent = new Intent(v.getContext(), CheckinPage.class);
	        myIntent.putExtra("type", loginType);
	        v.getContext().startActivity(myIntent);
		}
	}

	
	class CheckBoxCheckedChangeListener implements OnCheckedChangeListener {
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (isChecked) {
	        	Log.d(LOGTAG, "Button got checked");
	        	Log.d(LOGTAG, "The id of the button is: " + buttonView.getId());
	        	Drawable d = null;
	        	int checkboxtype = buttonView.getId();
	        	if (checkboxtype == R.id.fqcb) {
	        		d = getResources().getDrawable(R.drawable.fq_checkbox_pressed);
	        	} else if (checkboxtype == R.id.yelpcb) {
	        		d = getResources().getDrawable(R.drawable.yelp_checkbox_pressed);
	        	} else if (checkboxtype == R.id.fbcb) {
	        		d = getResources().getDrawable(R.drawable.fb_checkbox_pressed);
	        	} else if (checkboxtype == R.id.googlecb) {
	        		d = getResources().getDrawable(R.drawable.google_checkbox_pressed);
	        	}
	        	
	        	if (d != null) {
	        		buttonView.setButtonDrawable(d);
	        	}
	        } else {
	        	Log.d(LOGTAG, "Button got un checked");
	        	Log.d(LOGTAG, "The id of the button is: " + buttonView.getId());
	        	Drawable d = null;
	        	int checkboxtype = buttonView.getId();
	        	if (checkboxtype == R.id.fqcb) {
	        		d = getResources().getDrawable(R.drawable.fq_checkbox_notpressed);
	        	} else if (checkboxtype == R.id.yelpcb) {
	        		d = getResources().getDrawable(R.drawable.yelp_checkbox_notpressed);
	        	} else if (checkboxtype == R.id.fbcb) {
	        		d = getResources().getDrawable(R.drawable.fb_checkbox_notpressed);
	        	} else if (checkboxtype == R.id.googlecb) {
	        		d = getResources().getDrawable(R.drawable.google_checkbox_notpressed);
	        	}
	        	
	        	if (d != null) {
	        		buttonView.setButtonDrawable(d);
	        	}
	        }
		}
	}
}