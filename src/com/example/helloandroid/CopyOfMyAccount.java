package com.example.helloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.view.*;
import android.view.View.OnClickListener;

public class CopyOfMyAccount extends Activity{
	/** Called when the activity is first created. */
	
	String Username;
	String Password;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaccount);
        
//        EditText et1 = (EditText) findViewById(R.id.editText1);
//        EditText et2 = (EditText) findViewById(R.id.editText2);
        ImageButton b1 = (ImageButton) findViewById(R.id.imageButton1);
        
//        Username = new String();
//        Password = new String();
//        
//        et1.addTextChangedListener(new TextWatcher() {
//
//			public void afterTextChanged(Editable arg0) {
//				// TODO Auto-generated method stub
//			}
//
//			public void beforeTextChanged(CharSequence s, int start, int count,
//					int after) {
//				// TODO Auto-generated method stub
//			}
//
//			public void onTextChanged(CharSequence s, int start, int before,
//					int count) {
//				Username = s.toString();
//			}	
//        });
//        
//        et2.addTextChangedListener(new TextWatcher() {
//
//			public void afterTextChanged(Editable s) {
//				// TODO Auto-generated method stub
//			}
//
//			public void beforeTextChanged(CharSequence s, int start, int count,
//					int after) {
//				// TODO Auto-generated method stub
//			}
//
//			public void onTextChanged(CharSequence s, int start, int before,
//					int count) {
//				Password = s.toString();
//			}
//    });
        
        b1.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
                Intent i = new Intent(CopyOfMyAccount.this, Authentication_Progress.class);
                CopyOfMyAccount.this.startActivity(i);
			}
        	
        });
    }
}