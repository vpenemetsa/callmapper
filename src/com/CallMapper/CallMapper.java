package com.CallMapper;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.CallMapper.activity.CallLogList;
import com.CallMapper.activity.GroupBm;
import com.CallMapper.activity.TextLogList;
import com.CallMapper.database.CustomSQLiteOpenHelper;

public class CallMapper extends Activity {
    /** Called when the activity is first created. */
        Button mButtonCallLogs,mButtonGroups, StartButton, mButtonTextLogs;
    	/** Called when the activity is first created. */
        CustomSQLiteOpenHelper helper;
        
        @Override
        public void onCreate(Bundle savedInstanceState) 
        {
        	super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            
            ContentResolver contentRes = getApplicationContext().getContentResolver();
            contentRes.registerContentObserver(Uri.parse("content://sms"),true, new SmsSendObserver(new Handler()));
            
            helper = new CustomSQLiteOpenHelper(getApplicationContext());
            helper.getReadableDatabase();
            		
            mButtonCallLogs = (Button)findViewById(R.id.call_logs);
            mButtonGroups = (Button)findViewById(R.id.groups);
            mButtonTextLogs = (Button)findViewById(R.id.text_logs);
            
            mButtonCallLogs.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i=new Intent(getApplicationContext(),CallLogList.class);
					i.putExtra(Constants.EXTRA_CALL_LOG_FLAG, Constants.EXTRA_CALL_LOG);
		        	startActivity(i);
				}
			});
            mButtonGroups.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i2=new Intent(getApplicationContext(),GroupBm.class);
					i2.putExtra(Constants.EXTRA_GROUP_FLAG, Constants.EXTRA_VIEW_BY_GROUPS);
		        	startActivity(i2);
				}
			});
            mButtonTextLogs.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(), TextLogList.class);
					i.putExtra(Constants.EXTRA_TEXT_LOG_FLAG, Constants.EXTRA_TEXT_LOG);
					startActivity(i);
				}
			});
        }
        
        //private void starts() {
			// TODO Auto-generated method stub
        //}

		@Override
        public void onDestroy()
        {
        	super.onDestroy();
        	helper.close();
        }
		
		protected class SmsSendObserver extends ContentObserver {
			
			LocationManager mLocationManager;
			LocationListener mListener;
			String id = null;
			
			public SmsSendObserver(Handler handler) {
				super(handler);
			}
			
			@Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                Uri uriSMSURI = Uri.parse("content://sms");
                
                Cursor cur = getApplicationContext().getContentResolver().query(uriSMSURI, null, null, null, null);
                cur.moveToNext();
                
                String type = cur.getString(cur.getColumnIndex("type"));
                if (type.equals("2")) {
                	if (id == null) {
                    	id = cur.getString(cur.getColumnIndex("_id"));
                    	final String message = cur.getString(cur.getColumnIndex("body"));
                        final String phoneNumber = cur.getString(cur.getColumnIndex("address"));
                        cur.close();
                        
                        Intent i = new Intent();
                        i.setAction(Constants.ACTION_SMS_SENT);
                        i.putExtra(Constants.EXTRA_MESSAGE, message);
                        i.putExtra(Constants.EXTRA_PHONE_NUMBERS, phoneNumber);
                        sendBroadcast(i);
                    } else if (!cur.getString(cur.getColumnIndex("_id")).equals(id)) {
                    	id = cur.getString(cur.getColumnIndex("_id"));
                    	final String message = cur.getString(cur.getColumnIndex("body"));
                        final String phoneNumber = cur.getString(cur.getColumnIndex("address"));
                        cur.close();
                        
                        Intent i = new Intent();
                        i.setAction(Constants.ACTION_SMS_SENT);
                        i.putExtra(Constants.EXTRA_MESSAGE, message);
                        i.putExtra(Constants.EXTRA_PHONE_NUMBERS, phoneNumber);
                        sendBroadcast(i);
                    }
                }
                
            }
			
		}
}