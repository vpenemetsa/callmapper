package com.CallMapper.receivers;

import com.CallMapper.database.DatabaseControl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class OutgoingCallReceiver extends BroadcastReceiver {
	
	Context mContext;
	LocationManager mLocationManager;
	LocationListener mListener;
	
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        mContext = context;
        
        if(null == bundle)
            return;
        
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
        	final String phonenumber = bundle.getString(Intent.EXTRA_PHONE_NUMBER);
            
            mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setCostAllowed(true);
            criteria.setPowerRequirement(Criteria.POWER_LOW);
            
            String provider = mLocationManager.getBestProvider(criteria, true);
            
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
    				saveToDatabase(phonenumber, location.getLatitude(), location.getLongitude());
    			}
    		};
            
    		mLocationManager.requestLocationUpdates(provider,
                    60000, // 1min
                    1000,   // 0.5km
                    mListener);
        }
    }
    
    @SuppressWarnings("static-access")
	private void saveToDatabase(String phonenumber, double latitude, double longitude) {
    	mLocationManager.removeUpdates(mListener);
    	DatabaseControl dbControl = new DatabaseControl(mContext);
        dbControl.addRowCLL(null, phonenumber, null, latitude, longitude);
	}
}