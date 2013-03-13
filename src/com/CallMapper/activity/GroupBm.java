package com.CallMapper.activity;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.CallMapper.CallMapper;
import com.CallMapper.Constants;
import com.CallMapper.R;
import com.CallMapper.database.DatabaseControl;

	public class GroupBm extends Activity implements OnClickListener{ 
		Button FamilyButton, FriendsButton, WorkButton, OthersButton;
		ArrayList<String> contacts = new ArrayList<String>();
		boolean isSavingGroups;
		
		//int listlen = databaseControl.getNumberOfRows("bookmarks");
		public void onCreate(Bundle savedInstanceState) 
        {
			super.onCreate(savedInstanceState);
            setContentView(R.layout.groupsbm);
            
            Bundle intent = getIntent().getExtras();
            
			if (intent.getString(Constants.EXTRA_GROUP_FLAG).equals(Constants.EXTRA_SAVE_GROUPS)) {
				isSavingGroups = true;
				contacts = intent.getStringArrayList(Constants.EXTRA_PHONE_NUMBERS);
			} else {
				isSavingGroups = false;
			}
			
            FamilyButton = (Button)findViewById(R.id.family);
            FriendsButton = (Button)findViewById(R.id.friends);
            WorkButton = (Button)findViewById(R.id.work);
            OthersButton = (Button)findViewById(R.id.others);
            
            FamilyButton.setOnClickListener(this);
            FriendsButton.setOnClickListener(this);
            WorkButton.setOnClickListener(this);
            OthersButton.setOnClickListener(this);
        }
		
		public void onClick(View gp)
		{
			switch(gp.getId()){
			
			case R.id.family:
				if (isSavingGroups) {
					DatabaseControl.createGroups(contacts, Constants.GROUP_FAMILY);
					
					Intent i1=new Intent(this,CallMapper.class);
					i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        	startActivity(i1);
					break;
				} else {
					Intent i1=new Intent(this,CallLogList.class);
					i1.putExtra(Constants.EXTRA_CALL_LOG_FLAG, Constants.EXTRA_CALL_LOG_BY_GROUP);
					i1.putExtra(Constants.EXTRA_GROUP_NAME, Constants.GROUP_FAMILY);
		        	startActivity(i1);
					break;
				}
				
			case R.id.friends:
				if (isSavingGroups) {
					DatabaseControl.createGroups(contacts, Constants.GROUP_FRIENDS);
					
					Intent i2=new Intent(this,CallMapper.class);
					i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        	startActivity(i2);	
					break;
				} else {
					Intent i1=new Intent(this,CallLogList.class);
					i1.putExtra(Constants.EXTRA_CALL_LOG_FLAG, Constants.EXTRA_CALL_LOG_BY_GROUP);
					i1.putExtra(Constants.EXTRA_GROUP_NAME, Constants.GROUP_FRIENDS);
		        	startActivity(i1);
					break;
				}
				
			case R.id.work:
				if (isSavingGroups) {
					DatabaseControl.createGroups(contacts, Constants.GROUP_WORK);
					
					Intent i3=new Intent(this,CallMapper.class);
					i3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        	startActivity(i3);
					break;
				} else {
					Intent i1=new Intent(this,CallLogList.class);
					i1.putExtra(Constants.EXTRA_CALL_LOG_FLAG, Constants.EXTRA_CALL_LOG_BY_GROUP);
					i1.putExtra(Constants.EXTRA_GROUP_NAME, Constants.GROUP_WORK);
		        	startActivity(i1);
					break;
				}
				
			case R.id.others:
				if (isSavingGroups) {
					DatabaseControl.createGroups(contacts, Constants.GROUP_OTHERS);
					
					Intent i4=new Intent(this,CallMapper.class);
					i4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        	startActivity(i4);
					break;
				} else {
					Intent i1=new Intent(this,CallLogList.class);
					i1.putExtra(Constants.EXTRA_CALL_LOG_FLAG, Constants.EXTRA_CALL_LOG_BY_GROUP);
					i1.putExtra(Constants.EXTRA_GROUP_NAME, Constants.GROUP_OTHERS);
		        	startActivity(i1);
					break;
				}
			}
		}
	}