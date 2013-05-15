package com.example.helloandroid;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

public class HelloAndroid extends TabActivity {
	final private String LOG_TAG = "TITAN_HELLO_ANDROID";
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, PlacesList.class);
        //startActivity(intent);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("places").setIndicator("Places",
        		res.getDrawable(R.layout.target))
        		.setContent(intent);
        tabHost.addTab(spec);
        Log.d(LOG_TAG, "Tab Places Class");

        // Do the same for the other tabs
        intent = new Intent().setClass(this, RecentCheckins.class);
        spec = tabHost.newTabSpec("checkins").setIndicator("Checkins",
        		res.getDrawable(R.layout.marker))
        		.setContent(intent);
        tabHost.addTab(spec);
        Log.d(LOG_TAG, "Tab Checkins Class");

        intent = new Intent().setClass(this, Offers.class);
        spec = tabHost.newTabSpec("tags").setIndicator("Offers",
        		res.getDrawable(R.layout.tags))
        		.setContent(intent);
        tabHost.addTab(spec);
        Log.d(LOG_TAG, "Tab Offers Class");
        
        intent = new Intent().setClass(this, AccountLogin.class);
        spec = tabHost.newTabSpec("user").setIndicator("My Account",
        		res.getDrawable(R.layout.user))
        		.setContent(intent);
        tabHost.addTab(spec);
        Log.d(LOG_TAG, "Tab My Account Class");

        intent = new Intent().setClass(this, Settings.class);
        spec = tabHost.newTabSpec("sliders").setIndicator("Settings",
        		res.getDrawable(R.layout.sliders))
        		.setContent(intent);
        tabHost.addTab(spec);
        Log.d(LOG_TAG, "Tab Settings  Class");
        
        tabHost.setCurrentTab(2);
    }
}