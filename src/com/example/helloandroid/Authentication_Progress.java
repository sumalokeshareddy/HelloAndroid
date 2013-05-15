package com.example.helloandroid;

import android.app.Activity;

public class Authentication_Progress extends Activity {
//        private FQOAuth2 androidOAuth;
//
//        private String LOG_TAG;
//
//        /** Called when the activity is first created. */
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//
//                super.onCreate(savedInstanceState);
//
//                LOG_TAG = getString(R.string.androidOauthLogTag);
//
//                androidOAuth = new FQOAuth2(this);
//
//                // If there is no intent data being sent in via foursquare callback
//                Intent i = this.getIntent();
//                if (i.getData() == null) {
//
//                        // This is really important. If you were able to register your
//                        // real callback Uri with Twitter, and not some fake Uri
//                        // like I registered when I wrote this example, you need to send
//                        // null as the callback Uri in this function call. Then
//                        // Twitter will correctly process your callback redirection
//                        String authUrl = androidOAuth.getAuthenticationURL();
//                        Log.d(LOG_TAG, "Starting web intent at authUrl of: " + authUrl);
//                        this.startActivity(new Intent(Intent.ACTION_VIEW, Uri
//                                        .parse(authUrl)));
//
//                }
//        }
//
//        @Override
//        protected void onResume() {
//                super.onResume();
//
//                Uri uri = getIntent().getData();
//
//                // intent started with same scheme as called
//                if (uri != null
//                                && androidOAuth.getCallbackUri().getScheme()
//                                                .equals(uri.getScheme())) {
//
//                        // How we go to our next activity
//
//                        Intent i = new Intent();
//                        String packageName = getString(R.string.resultingActivityPackageName);
//                        String className = getString(R.string.resultingActivityClassName);
//                        i.setClassName(packageName, className);
//
//                        String authorizedCode = uri.getQueryParameter("code");
//                        Log.d(LOG_TAG, "Auth code: " + authorizedCode);
//                        Log.d(LOG_TAG,
//                                        "Auth URL : "
//                                                        + androidOAuth.getAccessTokenUrl(authorizedCode));
//                        HttpGet request = new HttpGet(
//                                        androidOAuth.getAccessTokenUrl(authorizedCode));
//
//                        DefaultHttpClient client = new DefaultHttpClient();
//
//                        try {
//
//                                HttpResponse resp = client.execute(request);
//                                int responseCode = resp.getStatusLine().getStatusCode();
//                                Log.d(LOG_TAG, "Reponse code: " + responseCode);
//
//                                if (responseCode >= 200 && responseCode < 300) {
//
//                                        String response = responseToString(resp);
//                                        JSONObject jsonObj = new JSONObject(response);
//                                        androidOAuth
//                                                        .saveAccessToken(PreferenceManager
//                                                                        .getDefaultSharedPreferences(this), jsonObj
//                                                                        .getString("access_token"));
//
//                                } else {
//                                        Log.e(LOG_TAG, "Foursquare error: "
//                                                        + responseToString(resp));
//                                }
//                        } catch (ClientProtocolException e) {
//                                // TODO Auto-generated catch block
//                                e.printStackTrace();
//                        } catch (IOException e) {
//                                // TODO Auto-generated catch block
//                                e.printStackTrace();
//                        } catch (JSONException e) {
//                                // TODO Auto-generated catch block
//                                e.printStackTrace();
//                        }
//
//                        startActivity(i); // we either authenticated and have the extras
//                                                                // or not, but we're going back
//                        finish();
//
//                }
//        }
//
//        private String responseToString(HttpResponse resp)
//                        throws IllegalStateException, IOException {
//                BufferedReader in = new BufferedReader(new InputStreamReader(resp
//                                .getEntity().getContent()));
//
//                StringBuffer sb = new StringBuffer();
//
//                String line = "";
//
//                while ((line = in.readLine()) != null) {
//                        sb.append(line);
//                }
//                in.close();
//
//                return sb.toString();
//        }
}
