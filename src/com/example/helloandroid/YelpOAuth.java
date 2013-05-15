package com.example.helloandroid;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;

public class YelpOAuth {
	private String consumerKey;
    private String consumerSecret;
    private String token;
    private String tokenSecret;
    private String ywsid;

    // Log_CAT tag to be set
    private String LOGTAG = "TITAN_YELPOAUTH";
    private Context context;
    
    public YelpOAuth(Context context) {
        this.context = context;
        Resources res = context.getResources();

        this.consumerKey = res.getString(R.string.yelpConsumerKey);
        this.consumerSecret = res.getString(R.string.yelpConsumerSecret);
        this.token = res.getString(R.string.yelpToken);
        this.tokenSecret = res.getString(R.string.yelpTokenSecret);
        this.ywsid = res.getString(R.string.yelpYWSID);
    }
    
    public String getAuthenticationURL() {
		return null;
    	
    }

}
