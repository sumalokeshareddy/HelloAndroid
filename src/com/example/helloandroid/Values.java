package com.example.helloandroid;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Values extends Activity{

	Context mContext;
	Values(Context mCtx) {
		mContext = mCtx;
	}

	public String getAccessTokenPreferenceName() {
		return mContext.getResources().getString(R.string.accessTokenPreferenceName);
	}

	public String getSavedAccessToken(SharedPreferences preferences) {

		String savedAccessToken = preferences.getString(
				getAccessTokenPreferenceName(), null);

		if (savedAccessToken == null) {
			return null;
		}

		return savedAccessToken;
	}

	public String getSavedAccessToken() {
		SharedPreferences preferences = PreferenceManager
		.getDefaultSharedPreferences(mContext);
		return getSavedAccessToken(preferences);
	}
}
