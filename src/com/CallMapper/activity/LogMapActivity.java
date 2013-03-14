package com.CallMapper.activity;

import java.util.ArrayList;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.CallMapper.Constants;
import com.CallMapper.R;
import com.CallMapper.database.DatabaseControl;
import com.CallMapper.entities.Contact;
import com.CallMapper.loaders.DataLoader;
import com.CallMapper.map.MapItemizedOverlay;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

/**
 * This activity handles the map view
 * 
 * @author vpenemetsa
 *
 */
public class LogMapActivity extends MapActivity implements LoaderCallbacks<ArrayList<Contact>> {

	ArrayList<String> phoneNumbers = new ArrayList<String>();
	MapView mMapView;
	MapItemizedOverlay mItemizedOverlay;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        mMapView = (MapView) findViewById(R.id.mapview);
        
        phoneNumbers = getIntent().getStringArrayListExtra(Constants.EXTRA_PHONE_NUMBERS);
        
        getLoaderManager().initLoader(1, createLoaderBundle(), this);
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	protected Bundle createLoaderBundle() {
		Bundle b = new Bundle();
		b.putSerializable(Constants.LOADER_ACTION, Constants.LOADER_MAP_ACTION);
		b.putStringArrayList(Constants.EXTRA_PHONE_NUMBERS, phoneNumbers);
		return b;
	}

	@Override
	public Loader<ArrayList<Contact>> onCreateLoader(int id, Bundle args) {
		return new DataLoader(getApplicationContext(), new DatabaseControl(getApplicationContext()), args);
	}

	@Override
	public void onLoadFinished(Loader<ArrayList<Contact>> loader, ArrayList<Contact> data) {
		ArrayList<GeoPoint> points = new ArrayList<GeoPoint>();
		for (Contact contact : data) {
        	double latitude = Double.parseDouble(contact.getLatitude());
        	double longitude = Double.parseDouble(contact.getLongitude());
        	
			GeoPoint point = new GeoPoint((int) (latitude * 1E6), (int) (longitude * 1E6));
			points.add(point);
			MapController controller = mMapView.getController();

			//animate to the desired point
	        controller.animateTo(point);

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
	        
	        OverlayItem overlayItem = new OverlayItem(point, "", "");

	        mItemizedOverlay = new MapItemizedOverlay(drawable,this);
	        mItemizedOverlay.addOverlay(overlayItem);

	        mMapView.getOverlays().add(mItemizedOverlay);
	        
	        mMapView.invalidate();
		}
	}

	@Override
	public void onLoaderReset(Loader<ArrayList<Contact>> loader) {
	}
}
