package com.example.helloandroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserInfoPage extends Activity{
	final static String LOGTAG = "TITAN_CHECKIN_PAGE";
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userinfo_page);
		String fqToken = new Values(this).getSavedAccessToken();

		/*
		 *  Get the user info
		 */
		String userURL = "https://api.foursquare.com/v2/users/self?oauth_token=" + fqToken;
		Log.d(LOGTAG, "The request url is " + userURL);
		HttpGet request = new HttpGet(userURL);
		DefaultHttpClient client = new DefaultHttpClient();

		try {
			HttpResponse resp = client.execute(request);
			int responseCode = resp.getStatusLine().getStatusCode();
			Log.d(LOGTAG, "Reponse code: " + responseCode);
			if (responseCode >= 200 && responseCode < 300) {
				String response = responseToString(resp);
				JSONObject jsonObj = new JSONObject(response);
				String name = jsonObj.getJSONObject("response").getJSONObject("user").getString("firstName") + " " 
				+ jsonObj.getJSONObject("response").getJSONObject("user").getString("lastName");

				ImageView photo = (ImageView) findViewById(R.id.userPhoto);
				String photoUrl = jsonObj.getJSONObject("response").
				getJSONObject("user").getString("photo");
				
				TextView tvLastSeen = (TextView) findViewById(R.id.userLastSeen);

				JSONArray menuitemArray = jsonObj.getJSONObject("response").getJSONObject("user").getJSONObject("checkins").getJSONArray("items");
				String venueName = menuitemArray.getJSONObject(0).getJSONObject("venue").getString("name");
				TextView tvName = (TextView) findViewById(R.id.userName);
				tvName.setText(name);
				tvLastSeen.setText(venueName);
				photo.setImageBitmap(BitmapUtils.loadBitmap(photoUrl));
			}
			
			Button btnFQLogout = (Button) findViewById(R.id.btnLogoutFQ);
			btnFQLogout.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					new FQOAuth2(v.getContext()).removeSavedAccessToken();
					finish();
					}
			});
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private String responseToString(HttpResponse resp)
	throws IllegalStateException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(resp
				.getEntity().getContent()));
		StringBuffer sb = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		in.close();
		return sb.toString();
	}
}
