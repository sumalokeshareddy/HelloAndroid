package com.example.helloandroid;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpHandler {
	
	HttpClient	httpClient;
	ResponseHandler<String> respHandler;
	HttpGet		httpGet;
	HttpPost	httpPost;
	
	HttpHandler (){
		httpClient = new DefaultHttpClient();
		respHandler = new BasicResponseHandler();
	}
	
	/*
	 * Check if this method returns NULL before 
	 * proceeding with result	
	 */
	String getResponeTypeGET(String URL) {
		httpGet = new HttpGet(URL);
		String result = null;
		try {
			result = httpClient.execute(httpGet, respHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	String getResponeTypePOST(String URL) {
		httpPost = new HttpPost(URL);
		String result = null;
		try {
			result = httpClient.execute(httpPost, respHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
