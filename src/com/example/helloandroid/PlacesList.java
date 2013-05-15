package com.example.helloandroid;

import java.io.Serializable;
import java.util.*;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

public class PlacesList extends ListActivity implements ViewBinder, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final private String LOGTAG = "TITAN_PLACES_LIST";
	List<Map<String, TitanPlaces>> placesData;

	@Override  
	public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);

		final ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		placesData = new ArrayList<Map<String, TitanPlaces>>();

		
		// Reusable map
		HashMap<String, TitanPlaces> hashPlaces;
	
		GeoScout geo = new GeoScout(this);
		geo.getLocation();
		geo.getNewLocation();

		HttpHandler httpHand = new HttpHandler();

		/*
		 *  Below code for YELP
		 */
		
		try {
			while(geo.location == null)
				Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UrlYelpHandler yelpURL = new UrlYelpHandler();
		String URL = yelpURL.genYelpUrlBusinessGeocode(geo.getLat(), geo.getLong());
		String result = httpHand.getResponeTypeGET(URL);
		YelpParser yPar = new YelpParser();
		boolean res = yPar.parseYelpHeader(result);

		TitanPlaces tPlaces = new TitanPlaces();
		tPlaces = yPar.parseYelpBusiness(result);
		while (tPlaces != null) {
			hashPlaces = new HashMap<String, TitanPlaces>();
			hashPlaces.put( "name", tPlaces);
			placesData.add(hashPlaces);
			tPlaces = yPar.parseYelpBusiness(result);
		}
		

		/*
		 * Below code for FQ
		 */

//		HttpHandler httpHand1 = new HttpHandler();
//		UrlFQHandler urlFq = new UrlFQHandler();
//		String fqURL = urlFq.getFQUrlVenueGeocode(geo.getLat(), geo.getLong());
//		String fqResult = httpHand1.getResponeTypeGET(fqURL);
//
//		FQParser fqPar = new FQParser();
//		boolean bRes = fqPar.parseFqHeader(fqResult);
//		TitanPlaces tPlacesFQ = new TitanPlaces();
//		tPlacesFQ = fqPar.parseFqNeighbourhood(fqResult);
//
//		while (tPlacesFQ != null) {
//			hashPlaces = new HashMap<String, TitanPlaces>();
//			hashPlaces.put("name", tPlacesFQ);
//			placesData.add(hashPlaces);
//
//			tPlacesFQ = fqPar.parseFqNeighbourhood(fqResult);
//		}
		
		SimpleAdapter adapter = new SimpleAdapter( this, placesData, 
				android.R.layout.simple_list_item_2, 
				new String[] {"name" },
				new int[]{ android.R.id.text1} );
		adapter.setViewBinder(this);
		setListAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				String name, address, photo_url;
				name = placesData.get((int) id).get("name").getName();
				address = placesData.get((int) id).get("name").getAddress();
				photo_url = placesData.get((int) id).get("name").getPhotourl();
				
				Intent myIntent = new Intent(getApplicationContext(), com.example.helloandroid.PlacesView.class);
				myIntent.putExtra("name", name);
				myIntent.putExtra("address", address);
				myIntent.putExtra("photourl", photo_url);
				Log.d(LOGTAG, "Name is: " +  name + " Address is: " + address + " Photo URL: " + photo_url);
				startActivity(myIntent);
			}
		});
		
	}

	@Override
	public boolean setViewValue(View view, Object data,
			String textRepresentation) {
		TitanPlaces tPlaces = (TitanPlaces) data;		
        TextView menuItemView = (TextView)view;
        menuItemView.setText(tPlaces.getName());
        return true;
	}
}
