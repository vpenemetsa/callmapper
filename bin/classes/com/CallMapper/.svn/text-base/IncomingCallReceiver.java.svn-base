package com.PUMa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class IncomingCallReceiver extends BroadcastReceiver{
	
	
	
        @Override
        public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras();
               
                if(null == bundle)
                        return;
               
               
                Log.i("IncomingCallReceiver",bundle.toString());
                String state = bundle.getString(TelephonyManager.EXTRA_STATE);
                Log.i("IncomingCallReceiver","State: "+ state);
               
                if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING))
                {
                        String phonenumber = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                        double latitude = (getgpscoords.latitude);
                        //int latitude = (int)latitude1;
                        double longitude = (getgpscoords.longitude);
                        //int longitude = (int)longitude1;
                        
                        databaseControl.addRowCLL(null, phonenumber, null, latitude, longitude);
                        Log.i("IncomingCallReceiver","Incomng Number: " + phonenumber);
                        //String info = "Detect Calls sample application\nIncoming number: " + phonenumber;
                        //Toast.makeText(context, info, Toast.LENGTH_LONG).show();
                }
        }
       


}

