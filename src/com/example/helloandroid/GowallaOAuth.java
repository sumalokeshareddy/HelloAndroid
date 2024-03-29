package com.example.helloandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

public class GowallaOAuth {
	private String consumerKey;
    private String consumerSecret;
    private String accessTokenUrl;
    private String authenticationUrl;
    private boolean touchDisplay;

    // Callback field
    private Uri callbackUri;

    // Fields used for obtaining Android preferences
    private String accessTokenPreferenceName;

    // Log_CAT tag to be set
    private String TAG = "TITAN_FQOauth2";
    private Context context;

    public GowallaOAuth(Context context) {
            this.context = context;
            setGowallaOAuthPreferences();
    }

    private void setGowallaOAuthPreferences() {

            Resources res = context.getResources();

            this.consumerKey = res.getString(R.string.client_id);
            this.consumerSecret = res.getString(R.string.client_secret);
            this.accessTokenUrl = res.getString(R.string.accessTokenUrl);
            this.authenticationUrl = res.getString(R.string.authenticationUrl);
            this.callbackUri = Uri.parse(res.getString(R.string.oauthCallbackUri));
            this.touchDisplay = Boolean.parseBoolean(res.getString(R.string.touch));
            this.accessTokenPreferenceName = res
                            .getString(R.string.accessTokenPreferenceName);
    }

    public Uri getCallbackUri() {
            return callbackUri;
    }

    public String getAuthenticationURL() {

            String touchParam = "";

            if (touchDisplay) {
                    touchParam = "&display=touch";
            }

            return authenticationUrl + "?" + "client_id=" + consumerKey
                            + "&response_type=code" + "&redirect_uri=" + callbackUri
                            + touchParam;
    }

    public String getAccessTokenUrl(String code) {

            String touchParam = "";

            if (touchDisplay) {
                    touchParam = "&display=touch";
            }

            return accessTokenUrl + "?client_id=" + consumerKey + "&client_secret="
                            + consumerSecret + "&grant_type=authorization_code"
                            + "&redirect_uri=" + callbackUri + "&code=" + code + touchParam;
    }

    public String getAccessTokenPreferenceName() {
            return accessTokenPreferenceName;
    }

    public void saveAccessToken(String accessToken) {
            SharedPreferences preferences = PreferenceManager
                            .getDefaultSharedPreferences(context);
            saveAccessToken(preferences, accessToken);
    }

    public void saveAccessToken(SharedPreferences preferences,
                    String accessToken) {
            // null means to clear the old values
            SharedPreferences.Editor editor = preferences.edit();

            editor.putString(getAccessTokenPreferenceName(), accessToken);
            Log.d(TAG, "Saving OAuth Token: " + accessToken);

            editor.commit();
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
                            .getDefaultSharedPreferences(context);
            return getSavedAccessToken(preferences);
    }

    public void removeSavedAccessToken() {

            Log.d(TAG, "Clearing OAuth Token");

            SharedPreferences preferences = PreferenceManager
                            .getDefaultSharedPreferences(context);

            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(accessTokenPreferenceName);
            editor.commit();
    }

    public String authenticateUrl(String requestUrl) {

            String savedAccessToken = getSavedAccessToken();

            if (savedAccessToken == null) {

                    Log.w(TAG, "Attempting to authenticate a URL without access token.  Using consumer info instead");
                    return requestUrl + "&client_id=" + consumerKey + "&client_secret="
                                    + consumerSecret;
            } else {
                    return requestUrl + "&oauth_token=" + savedAccessToken;
            }
    }
}
