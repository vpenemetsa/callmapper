package com.PUMa;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class telephonyservices extends Service implements LocationListener {

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
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
///*
//	//databaseControl pum;
//	//databaseControl pum = new databaseControl(this);
//	public static double latitude;
//	public static double longitude;
//	Double latitude1;
//	Double longitude1;
//	int test = 0;
//
//	private static final String TAG = "telephonyservices";
//
//	public static String phnos;
//	public static String phname;
//	public int j = 0;
//	PhoneStateListener listener;
//	TelephonyManager telephonyManager;
//	LocationListener gpsListener;
//
//	@Override
//	public IBinder onBind(Intent arg0) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void onCreate() 
//	{
//		
//		
//		telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//		telephonyManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
//		
//		listener = new PhoneStateListener() 
//		{
//
//			@Override
//			public void onCallStateChanged(int state, String IncomingNumber) 
//			{
//				phnos = IncomingNumber;
//				switch (state) 
//				{
//				case TelephonyManager.CALL_STATE_IDLE:
//					if(test==0)
//						break;
//					else
//					{
//						if(test==1)
//						{
//							phnos = getOutgoing();
//							addRow(phnos);
//						}
//						else if(test==2)
//						{
//							phnos = getOutgoing();
//							addRow(phnos);
//						}
//						test=0;
//					}
//					break;
//
//				case TelephonyManager.CALL_STATE_OFFHOOK:
//					// The incoming or outgoing phone number is stored in
//					// variable "IncomingNumber" - We can put this in the
//					// database through direct call here
//					// Call to get and save gps coordinates
//					
//					
//					//Log.d(TAG, "onClick: starting srvice");
//					test = 2;
//					//addRow();
//										
//					
//					 // ///The
//					// current
//					// latitude
//					// value.
//					 // ///The
//					// current
//					// longitude
//					// value.
//
//					// locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//					// 100, 1, gpsListener);
//
//					/*
//					 * gpsListener = new LocationListener() {
//					 * 
//					 * public void onLocationChanged(Location location) {
//					 * //Location location = null; //boolean locationChanged =
//					 * false;
//					 * 
//					 * if (curLocation == null) { curLocation = location;
//					 * locationChanged = true; }
//					 * 
//					 * if (curLocation.getLatitude() == location .getLatitude()
//					 * && curLocation.getLongitude() == location
//					 * .getLongitude()) locationChanged = false; else {
//					 * locationChanged = true; curLocation = location; }
//					 * latitude = location.getLatitude(); // ///The // current
//					 * // latitude // value. longitude =
//					 * location.getLongitude(); // ///The // current //
//					 * longitude // value. // We need to put both these values
//					 * in the database // for retrieving them to plot on the
//					 * maps. //addRow(phnos, null, latitude, longitude);
//					 * 
//					 * }
//					 * 
//					 * public void onProviderDisabled(String provider) { }
//					 * 
//					 * public void onProviderEnabled(String provider) { }
//					 * 
//					 * public void onStatusChanged(String provider, int status,
//					 * Bundle extras) { } };
//					 */
//					/*
//					 * Thread t = new Thread() { public void run() { Intent i7 =
//					 * new Intent(telephonyservices.this, getgpscoords.class);
//					 * startService(i7);
//					 * 
//					 * latitude1 = getgpscoords.latitude; longitude1 =
//					 * getgpscoords.longitude; latitude2 = latitude1.toString();
//					 * longitude2 = longitude1.toString(); // stopService(i7);
//					 * addRow(); } //};t.start();
//					 */
//					// Intent i7=new Intent(this,getgpscoords.class);
//					// startActivity(i7);
//
//					break;
//
//				case TelephonyManager.CALL_STATE_RINGING:
//					test = 1;
//					break;
//				}
//				
//			}
//			
//		};
//		
//		/*if(test==1)
//		{
//			addRow(phnos);
//			test = 0;
//		}*/
//		//databaseControl.addRowCLL(null, phnos, null, latitude, longitude);
//		
//
//	}
//
//	public String getOutgoing() {
//		// TODO Auto-generated method stub
//		//Cursor c = getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI, null, null, null, android.provider.CallLog.Calls.DATE+" DESC");
//		//startManagingCursor(c);
//		//int pnum = c.getColumnIndex(android.provider.CallLog.Calls.NUMBER);
//		//long pname = c.getColumnIndex(android.provider.CallLog.Calls.DATE);
//		//String phnos1 = Integer.toString(pnum);
//		//System.out.println(phnos1);
//		return null;
//	}
//
//	public void getIncoming() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	/*
//	 * public void onStart() { telephonyManager = (TelephonyManager)
//	 * getSystemService(Context.TELEPHONY_SERVICE);
//	 * telephonyManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
//	 * 
//	 * 
//	 * listener = new PhoneStateListener() {
//	 * 
//	 * @Override
//	 * 
//	 * public void onCallStateChanged(int state, String IncomingNumber) {
//	 * switch(state) { case TelephonyManager.CALL_STATE_IDLE: break;
//	 * 
//	 * case TelephonyManager.CALL_STATE_OFFHOOK: //The incoming or outgoing
//	 * phone number is stored in variable "IncomingNumber" - We can put this in
//	 * the database through direct call here //Call to get and save gps
//	 * coordinates
//	 * 
//	 * LocationManager locMan; LocationListener gpsListener = null; locMan =
//	 * (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//	 * locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1,
//	 * gpsListener);
//	 * 
//	 * gpsListener = new LocationListener(){ Location curLocation; boolean
//	 * locationChanged = false; public void onLocationChanged(Location location)
//	 * { if(curLocation == null) { curLocation = location; locationChanged =
//	 * true; }
//	 * 
//	 * if(curLocation.getLatitude() == location.getLatitude() &&
//	 * curLocation.getLongitude() == location.getLongitude()) locationChanged =
//	 * false; else { locationChanged = true; curLocation = location; } latitude
//	 * = curLocation.getLatitude(); /////The current latitude value. longitude =
//	 * curLocation.getLongitude(); /////The current longitude value. //We need
//	 * to put both these values in the database for retrieving them to plot on
//	 * the maps. }
//	 * 
//	 * 
//	 * ////////// phnos = IncomingNumber; Intent i7=new Intent(null,
//	 * getgpscoords.class); startService(i7);
//	 * 
//	 * //Intent i7=new Intent(this,getgpscoords.class); //startActivity(i7);
//	 * latitude1 = getgpscoords.latitude; longitude1 = getgpscoords.longitude;
//	 * latitude2 = latitude1.toString(); longitude2 = longitude1.toString();
//	 * addRow();
//	 * 
//	 * stopService(i7); break;
//	 * 
//	 * case TelephonyManager.CALL_STATE_RINGING: break; } } private void
//	 * addRow() { // TODO Auto-generated method stub try { pum.addRowCLL (
//	 * phnos, null, latitude2, longitude2 ); } catch(Exception e) {
//	 * Log.e("Add Error", e.toString()); e.printStackTrace(); } }
//	 * 
//	 * }; }
//	 */
//	@Override
//	public void onLocationChanged(Location arg0) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void onProviderDisabled(String provider) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void onProviderEnabled(String provider) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void onStatusChanged(String provider, int status, Bundle extras) {
//		// TODO Auto-generated method stub
//
//	}
//
//	
//	  public void addRow(String phnos) 
//	  { // TODO Auto-generated method stub 
//		  LocationManager locMan;
//			// LocationListener gpsListener = null;
//			locMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//			Criteria criteria = new Criteria();
//			criteria.setAccuracy(Criteria.ACCURACY_FINE);
//			criteria.setAltitudeRequired(false);
//			criteria.setBearingRequired(false);
//			criteria.setPowerRequirement(Criteria.POWER_LOW);
//			criteria.setCostAllowed(true);
//
//			String provider = locMan.getBestProvider(criteria, true);
//
//			if (provider != null) 
//			{
//				Location location = locMan.getLastKnownLocation(provider);
//				latitude = location.getLatitude();
//				longitude = location.getLongitude();
//			}
//			
//			 // databaseControl.addRowCLL(null, phnos, null, latitude, longitude); 
//	  }
//	 
//*/
}
