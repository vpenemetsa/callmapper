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

public class viewmap extends MapActivity{

	HelloItemizedOverlay coords;
	Drawable drawable;
	List<Overlay> mapOverlays;
	OverlayItem overlay;
	static String table_name = "callloglist";
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
		drawable = this.getResources().getDrawable(R.drawable.lightblue);
		coords = new HelloItemizedOverlay(drawable, this);
		HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable,this);
		 
			//boolean[] selects1 = callloglist.selections;
			
			int[] selects = callloglist.selects;
		
		int j=0;
		for(int i=1;i<selects.length; i++)
		{
			if(selects[i]!=-1)
			{//
				String phnum = databaseControl.GetCLLphonenumber(selects.length-i);
				double latitude1 = (databaseControl.GetCLLlat(selects.length-i))*1000000;
				double longitude1 = (databaseControl.GetCLLlong(selects.length-i))*1000000;
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
			 	 
				GeoPoint point = new GeoPoint(latitude, longitude);
				OverlayItem overlayitem = new OverlayItem(point, "Phone Number: "+phnum, null);
				itemizedoverlay.addOverlay(overlayitem);
				
				
				j++;				
			}
		 
		}
		 
		mapOverlays.add(itemizedoverlay);
	 }

	@Override
	 protected boolean isRouteDisplayed()
	 {
	 return false;
	 }

}
