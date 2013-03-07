package com.CallMapper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CallMapper extends Activity {
    /** Called when the activity is first created. */
        Button mButtonCallLogs,mButtonGroups, StartButton;
    	/** Called when the activity is first created. */
           
        @Override
        public void onCreate(Bundle savedInstanceState) 
        {
        	super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            
            CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(getApplicationContext());
            helper.getReadableDatabase();
            
			Intent i3 = new Intent(this,GetGpsCoords.class);
        	startService(i3);
					
            mButtonCallLogs = (Button)findViewById(R.id.call_logs);
            mButtonGroups = (Button)findViewById(R.id.groups);
            
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
            
        }
        
        //private void starts() {
			// TODO Auto-generated method stub
        //}

		@Override
        public void onDestroy()
        {
        	super.onDestroy();
        }
		
}