package com.example.helloandroid;

import java.io.Serializable;

import android.net.Uri;

public class TitanPlaces implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8600468414871087934L;
	final private String LOGTAG = "TITAN_PLACES";
	String name;
	String url;
	String address;
	String phone;
	String city;
	String zip;
	String state;
	String country;
	String yelp_id;
	String photo_url;
	double latitude;
	double longitude;
	double distance;
	
	TitanPlaces () {
		name = new String();
		url = new String();
		address = new String();
		phone = new String();
		city = new String();
		zip = new String();
		state = new String();
		country = new String();
		yelp_id = new String();
		photo_url = new String();
		latitude = 0;
		longitude = 0;
		distance = 0;
	}
	
	// get functions
	
	public String getName() {
		return this.name;
	}
	
	public String getURL() {
		return this.url;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getPhone() {
		return this.phone;
	}

	public String getCity() {
		return this.city;
	}
	
	public String getZip() {
		return this.zip;
	}
	
	public String getState() {
		return this.state;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public String getYelpid() {
		return this.yelp_id;
	}
	
	public String getPhotourl() {
		return this.photo_url;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
	public double getDistance() {
		return this.distance;
	}
	
	// set functions

	public void setName(String string) {
		this.name = string;
	}
	
	public void setUrl(String string) {
		this.url = string;
	}
	
	public void setAddress(String string) {
		this.address = string;
	}
	
	public void setCity(String string) {
		this.city = string;
	}
	
	public void setPhone(String string) {
		this.phone = string;
	}

	public void setState(String string) {
		this.state = string;		
	}
	
	public void setZip(String string) {
		this.zip = string;		
	}

	public void setCountry(String string) {
		this.country = string;
	}
	
	public void setYelpid(String string) {
		this.yelp_id = string;
	}
	
	public void setPhotourl(String string) {
		this.photo_url = string;
	}
	
	public void setLatitude(double num) {
		this.latitude = num;
	}

	public void setLongitude(double num) {
		this.longitude = num;
	}
	
	public void setDistance(double num) {
		this.distance = num;
	}
}
