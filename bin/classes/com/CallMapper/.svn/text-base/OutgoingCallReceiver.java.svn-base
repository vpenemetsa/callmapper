package com.PUMa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class OutgoingCallReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras();
               
                if(null == bundle)
                        return;
                String phonenumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
                Log.i("OutgoingCallReceiver",phonenumber);
                Log.i("OutgoingCallReceiver",bundle.toString());
               
                /*String info = "Detect Calls sample application\nOutgoing number: " + phonenumber;
                Toast.makeText(context, info, Toast.LENGTH_LONG).show();*/
                double latitude = (getgpscoords.latitude);
                //int latitude = (int)latitude1;
                double longitude = (getgpscoords.longitude);
                //int longitude = (int)longitude1;
                databaseControl.addRowCLL(null, phonenumber, null, latitude, longitude);
        }
}


