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

import com.CallMapper.activity.CallLogListActivity;
import com.CallMapper.activity.GroupActivity;
import com.CallMapper.activity.TextLogListActivity;
import com.CallMapper.database.CustomSQLiteOpenHelper;

/**
 * Base activity. Also contains content observer for outgoing messages.
 * 
 * @author vpenemetsa
 *
 */
public class CallMapper extends Activity {

	Button mButtonCallLogs,mButtonGroups, StartButton, mButtonTextLogs;
	CustomSQLiteOpenHelper helper;
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ContentResolver contentRes = getApplicationContext().getContentResolver();
        contentRes.registerContentObserver(Uri.parse(Constants.EXTRA_SMS_URI),true, new SmsSendObserver(new Handler()));
        
        helper = new CustomSQLiteOpenHelper(getApplicationContext());
        helper.getReadableDatabase();
        		
        mButtonCallLogs = (Button)findViewById(R.id.call_logs);
        mButtonGroups = (Button)findViewById(R.id.groups);
        mButtonTextLogs = (Button)findViewById(R.id.text_logs);
        
        mButtonCallLogs.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(),CallLogListActivity.class);
				i.putExtra(Constants.EXTRA_CALL_LOG_FLAG, Constants.EXTRA_CALL_LOG);
	        	startActivity(i);
			}
		});
        mButtonGroups.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i2=new Intent(getApplicationContext(),GroupActivity.class);
				i2.putExtra(Constants.EXTRA_GROUP_FLAG, Constants.EXTRA_VIEW_BY_GROUPS);
	        	startActivity(i2);
			}
		});
        mButtonTextLogs.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), TextLogListActivity.class);
				i.putExtra(Constants.EXTRA_TEXT_LOG_FLAG, Constants.EXTRA_TEXT_LOG);
				startActivity(i);
			}
		});
    }
    
	@Override
    public void onDestroy() {
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
            Uri uriSMSURI = Uri.parse(Constants.EXTRA_SMS_URI);
            
            Cursor cur = getApplicationContext().getContentResolver().query(uriSMSURI, null, null, null, null);
            cur.moveToNext();
            
            String type = cur.getString(cur.getColumnIndex(Constants.EXTRA_SMS_TYPE));
            if (type.equals(Constants.EXTRA_SMS_TYPE_OUTGOING)) {
            	if (id == null) {
                	id = cur.getString(cur.getColumnIndex(Constants.EXTRA_SMS_ID));
                	final String message = cur.getString(cur.getColumnIndex(Constants.EXTRA_SMS_BODY));
                    final String phoneNumber = cur.getString(cur.getColumnIndex(Constants.EXTRA_SMS_ADDRESS));
                    cur.close();
                    
                    Intent i = new Intent();
                    i.setAction(Constants.ACTION_SMS_SENT);
                    i.putExtra(Constants.EXTRA_MESSAGE, message);
                    i.putExtra(Constants.EXTRA_PHONE_NUMBERS, phoneNumber);
                    sendBroadcast(i);
                } else if (!cur.getString(cur.getColumnIndex(Constants.EXTRA_SMS_ID)).equals(id)) {
                	id = cur.getString(cur.getColumnIndex(Constants.EXTRA_SMS_ID));
                	final String message = cur.getString(cur.getColumnIndex(Constants.EXTRA_SMS_BODY));
                    final String phoneNumber = cur.getString(cur.getColumnIndex(Constants.EXTRA_SMS_ADDRESS));
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