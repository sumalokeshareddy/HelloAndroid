package com.example.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AccountLogin extends Activity {
	
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_button);
		ImageButton fq = (ImageButton) findViewById(R.id.fqButton);
		fq.setOnClickListener(new LoginOnClickListener(1));
		
		ImageButton fb = (ImageButton) findViewById(R.id.fbButton);
		fb.setOnClickListener(new LoginOnClickListener(2));
		
		ImageButton gowallaButton = (ImageButton) findViewById(R.id.gowallaButton);
		gowallaButton.setOnClickListener(new LoginOnClickListener(3));
		
		ImageButton yButton = (ImageButton) findViewById(R.id.yButton);
		yButton.setOnClickListener(new LoginOnClickListener(4));
		
		Log.d("TitanAccountLogin", "onCreate() done .... ");
	} 
	
	public void onResume() {
		super.onResume();
		Log.d("TitanAccountLogin", "Came here somehow");
		FQOAuth2 fq = new FQOAuth2(this);
		
		String access_token = fq.getSavedAccessToken(PreferenceManager.
				getDefaultSharedPreferences(getApplicationContext()));
		Log.d("TitanAccountLogin", "Access Token is " + access_token);
		ImageButton fq1 = (ImageButton) findViewById(R.id.fqButton);
		Button btnFQUserInfo = (Button) findViewById(R.id.btnFQInfo);
		if (access_token != null) {
			
			fq1.setVisibility(View.INVISIBLE);
			btnFQUserInfo.setVisibility(View.VISIBLE);
			btnFQUserInfo.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent mIntent = new Intent(v.getContext(), UserInfoPage.class);
					v.getContext().startActivity(mIntent);
				}
				
			});
		} else {
			fq1.setVisibility(View.VISIBLE);
			btnFQUserInfo.setVisibility(View.INVISIBLE);
		}
		
	}
}

class LoginOnClickListener implements OnClickListener {
	private int loginType;
	LoginOnClickListener(int type) {
		loginType = type;
	}
	public void onClick(View v) {
		Intent myIntent = new Intent(v.getContext(), MyAccount.class);
        myIntent.putExtra("type", loginType); 
        v.getContext().startActivity(myIntent);
	}
}


