package com.PUMa;

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


public class getgpscoords extends Service implements LocationListener{
	//private static final String TAG = "getgpscoords";
	private static final int gpsMinTime = 200;
    private static final int gpsMinDistance = 0;
   
    LocationManager locMan;
	public static double latitude = 65.667634;
	public static double longitude = 140.894312;
	public void onCreate()
	{
		//
		locMan = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		
		final Criteria criteria = new Criteria();
        
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);//
        criteria.setCostAllowed(true);
        //criteria.setPowerRequirement(Criteria.POWER_LOW);
        
        final String bestProvider = locMan.getBestProvider(criteria, true);
        
        if (bestProvider != null && bestProvider.length() > 0)
        {
                locMan.requestLocationUpdates(bestProvider, gpsMinTime,gpsMinDistance, this);
        }
        else
        {
                final List<String> providers = locMan.getProviders(true);
                
                for (final String provider : providers)
                {
                        locMan.requestLocationUpdates(provider, gpsMinTime, gpsMinDistance, this);
                        
                }
        }



		//locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, gpsListener);  
		
		/*gpsListener = new LocationListener(){
		      Location curLocation;
		      boolean locationChanged = false; 
		      public void onLocationChanged(Location location)
		      {
		         if(curLocation == null)
		         {
		            curLocation = location;
		            locationChanged = true;
		         }
		         
		         if(curLocation.getLatitude() == location.getLatitude() && curLocation.getLongitude() == location.getLongitude())
		            locationChanged = false;
		         else
		         {
		            locationChanged = true;
		             curLocation = location;
		         }
		         latitude = curLocation.getLatitude();	/////The current latitude value.
		         longitude = curLocation.getLongitude();	/////The current longitude value.
		         //We need to put both these values in the database for retrieving them to plot on the maps.
	
		       }
		      public void onProviderDisabled(String provider){}
		      public void onProviderEnabled(String provider){}
		      public void onStatusChanged(String provider, int status, Bundle extras){}
		};*/

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