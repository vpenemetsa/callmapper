package com.PUMa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PUMa extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
        Button CallLogListButton,BookmarksButton, StartButton;
    	/** Called when the activity is first created. */
           
        @Override
        public void onCreate(Bundle savedInstanceState) 
        {
        	//
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            
            //starts();
            databaseControl pum = new databaseControl(this);
            
					// TODO Auto-generated method stub
					Intent i3 = new Intent(this,getgpscoords.class);
	            	startService(i3);
					
            CallLogListButton = (Button)findViewById(R.id.CallLogListButton);
            BookmarksButton = (Button)findViewById(R.id.BookmarksButton);
            
            CallLogListButton.setOnClickListener(this);
            BookmarksButton.setOnClickListener(this);
            
        }
        
        //private void starts() {
			// TODO Auto-generated method stub
        //}

		@Override
        public void onDestroy()
        {
        	super.onDestroy();
        	{
        		Intent i1 = new Intent(this, telephonyservices.class);
        		stopService(i1);
        	}
        }
            
        public void onClick(View sc)
         {
        	
        	switch(sc.getId()){
        	case R.id.CallLogListButton:
        	Intent i=new Intent(this,callloglist.class);
        	startActivity(i);
        	break;
        		
        	case R.id.BookmarksButton:
        	Intent i2=new Intent(this,bookmarklist.class);
        	startActivity(i2);
        	break;
        	
        	}
        
         }
        
}