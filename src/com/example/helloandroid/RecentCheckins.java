package com.example.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RecentCheckins extends Activity{
	final private String TAG = "TITAN_RECENT_CHECKINS";
	private TextView tv;
	String display_result = null;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.recent_checkins);
	}
	
	
}
