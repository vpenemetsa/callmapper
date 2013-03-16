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
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Listens for incoming messages and saves them to the db
 * 
 * @author vpenemetsa
 *
 */
public class IncomingSmsListener extends BroadcastReceiver {

	Context mContext;
	LocationManager mLocationManager;
	LocationListener mListener;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
        mContext = context;
        
        if(intent.getAction().equalsIgnoreCase(Constants.ACTION_SMS_RECEIVED)) {
        	SmsMessage[] msgs = null;
            String phoneNumber = null;
            String message = null;
            if (bundle != null){
            	try{
                    Object[] pdus = (Object[]) bundle.get(Constants.EXTRA_SMS_PDUS);
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        phoneNumber = msgs[i].getOriginatingAddress();
                        message = msgs[i].getMessageBody();
                    }
                } catch(Exception e){
                    Log.d("Exception caught",e.getMessage());
                }
            }
			
			final String phNumber = phoneNumber;
			final String mesg = message;
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
    				saveToDatabase(phNumber, mesg, location.getLatitude(), location.getLongitude());
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
	private void saveToDatabase(String phonenumber, String message, double latitude, double longitude) {
		DatabaseControl dbControl = new DatabaseControl(mContext);
        dbControl.addRowTLL(null, phonenumber, null, latitude, longitude, message);
        mLocationManager.removeUpdates(mListener);
	}
}
