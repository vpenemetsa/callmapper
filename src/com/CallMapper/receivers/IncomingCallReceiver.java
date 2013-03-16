package com.CallMapper.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.CallMapper.Constants;
import com.CallMapper.database.DatabaseControl;

/**
 * Listens for incoming calls and saves them to the db
 * 
 * @author vpenemetsa
 *
 */
public class IncomingCallReceiver extends BroadcastReceiver {
	
	Context mContext;
	LocationManager mLocationManager;
	LocationListener mListener;
	
	@Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        mContext = context;
        
        if(null == bundle) {
        	return;
        }
        
        String state = bundle.getString(TelephonyManager.EXTRA_STATE);
        
        if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)) {
        	
            final String phonenumber = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            
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
            
    		if (provider != null) {
    			mLocationManager.requestLocationUpdates(provider,
                        Constants.EXTRA_MIN_TIME,
                        Constants.EXTRA_MIN_DISTANCE,
                        mListener);
    		}
        }
    }
	
	@SuppressWarnings("static-access")
	private void saveToDatabase(String phonenumber, double latitude, double longitude) {
		DatabaseControl dbControl = new DatabaseControl(mContext);
        dbControl.addRowCLL(null, phonenumber, null, latitude, longitude);
        mLocationManager.removeUpdates(mListener);
	}
}