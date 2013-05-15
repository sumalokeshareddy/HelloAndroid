package com.example.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MyAccount extends Activity {
	private FQOAuth2 androidOAuth;
	WebView mWebView;
	private String LOG_TAG;

	private WebView webView;  
	private TextView title;    
	static final int FB_BLUE = 0xFF6D84B4;  
	static final float[] DIMENSIONS_LANDSCAPE = {460, 260};  
	static final float[] DIMENSIONS_PORTRAIT = {280, 420};  
	static final FrameLayout.LayoutParams FILL =   
		new FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,   
				ViewGroup.LayoutParams.FILL_PARENT);  
	static final int MARGIN = 4;  
	static final int PADDING = 2;  
	static final String DISPLAY_STRING = "touch";
	private String authUrl;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accounts);
		LOG_TAG = getString(R.string.androidOauthLogTag);
		title = (TextView) findViewById(R.id.title);  
		title.setBackgroundColor(FB_BLUE);  

		Integer type = getIntent().getIntExtra("type", 0);
		if (type == 1) {
			androidOAuth = new FQOAuth2(this);
			authUrl = androidOAuth.getAuthenticationURL();
		} else if (type == 2) {
		} else if (type == 3) {
		} else if (type == 4) {
		} else {
			Log.e(LOG_TAG, "What the hell ~!!!!!!!!!!!!!!!!!!!!!!!! Check the type in MyAccount");
			return;
		}

		Log.d(LOG_TAG, "Starting web intent of at authUrl of: " + authUrl);
		webView = (WebView) findViewById(R.id.webview);  
		webView.getSettings().setJavaScriptEnabled(true);  
		webView.setVerticalScrollBarEnabled(false);  
		webView.setHorizontalScrollBarEnabled(false);  
		webView.setWebViewClient(new FqWebViewClient());  
		webView.bringToFront();
		webView.clearHistory();
		webView.clearCache(true);
		webView.loadUrl(authUrl);
	}

	private class FqWebViewClient extends WebViewClient {  
		@Override  
		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			Log.d(LOG_TAG, "shouldOverrideUrlLoading() URL is: " + url);
			String callbackURI = view.getContext().getResources().getString(R.string.oauthCallbackUri);
			if (url.startsWith(callbackURI)) {
				Log.d(LOG_TAG, "CallbackURI is: " + callbackURI);
				return true;
			}
			view.loadUrl(url);
			return true;  
		}

		public void onPageFinished(WebView view, String url) {
			Log.d(LOG_TAG, "onPageFinished: " + url);
			finish();
			Uri uri = Uri.parse(url);
			Log.d(LOG_TAG, "Uri is: " + uri.toString());
			if (androidOAuth.getCallbackUri().getScheme()
					.equals(uri.getScheme())) {
				String authorizedCode = uri.getQueryParameter("code");
				Log.d(LOG_TAG, "Auth code: " + authorizedCode);
				Log.d(LOG_TAG,
						"Auth URL : "
						+ androidOAuth.getAccessTokenUrl(authorizedCode));
				HttpGet request = new HttpGet(
						androidOAuth.getAccessTokenUrl(authorizedCode));

				DefaultHttpClient client = new DefaultHttpClient();

				try {
					HttpResponse resp = client.execute(request);
					int responseCode = resp.getStatusLine().getStatusCode();
					Log.d(LOG_TAG, "Reponse code: " + responseCode);
					if (responseCode >= 200 && responseCode < 300) {
						String response = responseToString(resp);
						JSONObject jsonObj = new JSONObject(response);
						androidOAuth
						.saveAccessToken(PreferenceManager
								.getDefaultSharedPreferences(view.getContext()), jsonObj
								.getString("access_token"));
					} else {
						Log.e(LOG_TAG, "Foursquare error: "
								+ responseToString(resp));
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
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
