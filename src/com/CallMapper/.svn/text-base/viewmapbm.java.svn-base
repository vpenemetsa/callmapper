package com.PUMa;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class viewmapbm extends MapActivity{

	HelloItemizedOverlay coords1;
	HelloItemizedOverlay coords2;
	HelloItemizedOverlay coords3;
	HelloItemizedOverlay coords4;
	Drawable drawable1;
	Drawable drawable2;
	Drawable drawable3;
	Drawable drawable4;
	List<Overlay> mapOverlays;
	OverlayItem overlay;
	static String table_name = "bookmarks";
	int listlength = databaseControl.getNumberOfRows(table_name);
	
	public void onCreate(Bundle savedInstanceState)
	 {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		MapView mapView = (MapView) findViewById(R.id.viewmap);
		mapView.setBuiltInZoomControls(true);
		
		
		List<Overlay> mapOverlays = mapView.getOverlays();
		if(!mapOverlays.isEmpty()) 
	    { 
			mapOverlays.clear(); 
			mapView.invalidate();
	    }
		//
		drawable1 = this.getResources().getDrawable(R.drawable.blue);
		drawable2 = this.getResources().getDrawable(R.drawable.green);
		drawable3 = this.getResources().getDrawable(R.drawable.red);
		drawable4 = this.getResources().getDrawable(R.drawable.yellow);
		
		coords1 = new HelloItemizedOverlay(drawable1, this);
		HelloItemizedOverlay itemizedoverlay1 = new HelloItemizedOverlay(drawable1,this);
		
		coords2 = new HelloItemizedOverlay(drawable2, this);
		HelloItemizedOverlay itemizedoverlay2 = new HelloItemizedOverlay(drawable2,this);
		
		coords3 = new HelloItemizedOverlay(drawable3, this);
		HelloItemizedOverlay itemizedoverlay3 = new HelloItemizedOverlay(drawable3,this);
		
		coords4 = new HelloItemizedOverlay(drawable4, this);
		HelloItemizedOverlay itemizedoverlay4 = new HelloItemizedOverlay(drawable4,this);
		
			
			int[] selects = bookmarklist.selects;
			
		
		for(int i=1; i<selects.length; i++)
		{
			if(selects[i]!=-1)
			{//
				
				String phnum = databaseControl.GetBMphonenumber(selects.length-i);
				double latitude1 = (databaseControl.GetBMlat(selects.length-i))*1000000;
				double longitude1 = (databaseControl.GetBMlong(selects.length-i))*1000000;
				
				String groupid = databaseControl.GetGroupID(selects.length-i);
				Log.d("MAPOVERLAY>>>>>>>>>", "<<<<<<<<<<<<<" + groupid + ">>>>>>>>>>>>>>");
				
				int latitude = (int)latitude1;
				int longitude = (int)longitude1;
				//String temp1 = cllvals2[1];
				//String temp2 = cllvals2[2];
				//int latitude = Integer.parseInt(temp1);
				//int longitude = Integer.parseInt(temp2);
				//int size = a.size();
				//String phno = (String) a.get(1);
				//String latitude1 = (String) a.get(3);
				//int latitude = Integer.parseInt(latitude1);
				//String longitude1 = (String) a.get(4);
				//int longitude = Integer.parseInt(longitude1);
			 	 
				if(groupid.equals("family"))
				{
					
					GeoPoint point = new GeoPoint(latitude, longitude);
					OverlayItem overlayitem = new OverlayItem(point, "Family Number: "+phnum, null);
					itemizedoverlay1.addOverlay(overlayitem);	
					Log.d("MAPOVERLAY>>>>>>>>>", "FAMILY");
				}
				
				else if(groupid.equals("friends"))
				{
					GeoPoint point = new GeoPoint(latitude, longitude);
					OverlayItem overlayitem = new OverlayItem(point, "Friend Number: "+phnum, null);
					itemizedoverlay2.addOverlay(overlayitem);
					Log.d("MAPOVERLAY>>>>>>>>>", "FRIENDS");
				}
				
				else if(groupid.equals("Work"))
				{
					GeoPoint point = new GeoPoint(latitude, longitude);
					OverlayItem overlayitem = new OverlayItem(point, "Work Number: "+phnum, null);
					itemizedoverlay3.addOverlay(overlayitem);
					Log.d("MAPOVERLAY>>>>>>>>>", "WORK");
				}
				
				else if(groupid.equals("Others"))
				{
					GeoPoint point = new GeoPoint(latitude, longitude);
					OverlayItem overlayitem = new OverlayItem(point, "Other Number: "+phnum, null);
					itemizedoverlay4.addOverlay(overlayitem);
					Log.d("MAPOVERLAY>>>>>>>>>", "OTHERS");
				}
				else
				{
					GeoPoint point = new GeoPoint(latitude, longitude);
					OverlayItem overlayitem = new OverlayItem(point, "Phone Number: "+phnum, null);
					itemizedoverlay1.addOverlay(overlayitem);
					Log.d("MAPOVERLAY>>>>>>>>>", "ELSEEEE");
				}
				
				Log.i(groupid, "The group value");
				
			}
		 
		}
		 
		mapOverlays.add(itemizedoverlay1);
		mapOverlays.add(itemizedoverlay2);
		mapOverlays.add(itemizedoverlay3);
		mapOverlays.add(itemizedoverlay4);
	 }

	@Override
	 protected boolean isRouteDisplayed()
	 {
	 return false;
	 }

}
