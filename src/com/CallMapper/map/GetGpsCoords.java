package com.CallMapper.map;

import java.util.List;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

/**
 * Service to continously keep track of location changes
 * 
 * @author vpenemetsa
 *
 */
@Deprecated
public class GetGpsCoords extends Service implements LocationListener{
	
    LocationManager locMan;
	public static double latitude = 65.667634;
	public static double longitude = 140.894312;
	public void onCreate()
	{
		
		locMan = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		
		final Criteria criteria = new Criteria();
        
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        
        final String bestProvider = locMan.getBestProvider(criteria, true);
        
        if (bestProvider != null && bestProvider.length() > 0)
        {
               Location location = locMan.getLastKnownLocation(bestProvider);
               latitude = location.getLatitude();
               longitude = location.getLongitude();
        }
        else
        {
                final List<String> providers = locMan.getProviders(true);
                
                for (final String provider : providers)
                {
                	Location location = locMan.getLastKnownLocation(provider);
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
        }
        
        //TODO: Bind service and add helper methods for location retrival

}
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		latitude = location.getLatitude();
		longitude = location.getLongitude();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
}