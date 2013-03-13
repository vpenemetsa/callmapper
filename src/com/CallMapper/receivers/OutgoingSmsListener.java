package com.CallMapper.receivers;

import com.CallMapper.Constants;
import com.CallMapper.database.DatabaseControl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class OutgoingSmsListener extends BroadcastReceiver {

	Context mContext;
	LocationManager mLocationManager;
	LocationListener mListener;
	String mProvider;
	String mPhoneNumber;
	String mMessage;
	
	static boolean saved = false;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		mContext = context;
        if(intent.getAction().equalsIgnoreCase(Constants.ACTION_SMS_SENT)) {
        	mPhoneNumber = intent.getStringExtra(Constants.EXTRA_PHONE_NUMBERS);
			mMessage = intent.getStringExtra(Constants.EXTRA_MESSAGE);
			
			mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setCostAllowed(true);
            criteria.setPowerRequirement(Criteria.POWER_LOW);
            
            mProvider = mLocationManager.getBestProvider(criteria, true);
            
            mListener = new LocationListener() {
    			@Override
    			public void onStatusChanged(String provider, int status, Bundle extras) {
    				
    			}
    			
    			@Override
    			public void onProviderEnabled(String provider) {
    				
    			}
    			
    			@Override
    			public void onProviderDisabled(String provider) {
    				
    			}
    			
    			@Override
    			public void onLocationChanged(Location location) {
    				saveToDatabase(mPhoneNumber, mMessage, location.getLatitude(), location.getLongitude());
    			}
    		};
            
    		mLocationManager.requestLocationUpdates(mProvider,
                    60000, // 1min
                    500,   // 0.5km
                    mListener);
        }
	}
	
	@SuppressWarnings("static-access")
	private void saveToDatabase(String phonenumber, String message, double latitude, double longitude) {
		if (!saved) {
			saved = true;
			mLocationManager.removeUpdates(mListener);
			DatabaseControl dbControl = new DatabaseControl(mContext);
	        dbControl.addRowTLL(null, phonenumber, null, latitude, longitude, message);
		}
	}
}
