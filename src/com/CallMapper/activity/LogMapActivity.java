package com.CallMapper.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.CallMapper.Constants;
import com.CallMapper.R;
import com.CallMapper.database.DatabaseControl;
import com.CallMapper.entities.Contact;
import com.CallMapper.map.CallLogItemizedOverlay;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class LogMapActivity extends MapActivity {

	ArrayList<Contact> contacts = new ArrayList<Contact>();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        MapView mapView = (MapView) findViewById(R.id.mapview);
        CallLogItemizedOverlay itemizedOverlay;
        
        Intent bundle = getIntent();
        ArrayList<String> phoneNumbers = bundle.getStringArrayListExtra(Constants.EXTRA_PHONE_NUMBERS);
        
        contacts = DatabaseControl.getContactsFromPhoneNumbers(phoneNumbers);
        ArrayList<GeoPoint> points = new ArrayList<GeoPoint>();
        
        for (Contact contact : contacts) {
        	double latitude = Double.parseDouble(contact.getLatitude());
        	double longitude = Double.parseDouble(contact.getLongitude());
        	
			GeoPoint point = new GeoPoint((int) (latitude * 1E6), (int) (longitude * 1E6));
			points.add(point);
			MapController controller = mapView.getController();

			//animate to the desired point
	        controller.animateTo(point);

	        //set the map zoom to 13
	        // zoom 1 is top world view
	        controller.setZoom(4);
	        
	        Drawable drawable;
	        
	        if (contact.getGroup() == null) {
	        	drawable = this.getResources().getDrawable(R.drawable.black);
	        } else if (contact.getGroup().equals(Constants.GROUP_FAMILY)) {
	        	drawable = this.getResources().getDrawable(R.drawable.blue);
	        } else if (contact.getGroup().equals(Constants.GROUP_FRIENDS)) {
	        	drawable = this.getResources().getDrawable(R.drawable.green);
	        } else if (contact.getGroup().equals(Constants.GROUP_WORK)){
	        	drawable = this.getResources().getDrawable(R.drawable.orange);
	        } else {
	        	drawable = this.getResources().getDrawable(R.drawable.pink);
	        }
	        

	        // create and add an OverlayItem to the MyItemizedOverlay list
	        OverlayItem overlayItem = new OverlayItem(point, "", "");

	        itemizedOverlay = new CallLogItemizedOverlay(drawable,this);
	        itemizedOverlay.addOverlay(overlayItem);

	        // add the overlays to the map
	        mapView.getOverlays().add(itemizedOverlay);
	        
	        //invalidate the map in order to show changes
	        mapView.invalidate();
		}
        
        
        
    }
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
