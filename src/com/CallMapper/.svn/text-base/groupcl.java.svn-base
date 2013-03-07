package com.PUMa;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

	public class groupcl extends Activity implements OnClickListener{ 
		Button FamilyButton, FriendsButton, WorkButton, OthersButton;
		String group_ID;
		public void onCreate(Bundle savedInstanceState) 
        {
        	
            super.onCreate(savedInstanceState);
            setContentView(R.layout.groupscl);
            
            FamilyButton = (Button)findViewById(R.id.FamilyButton);
            FriendsButton = (Button)findViewById(R.id.FriendsButton);
            WorkButton = (Button)findViewById(R.id.WorkButton);
            OthersButton = (Button)findViewById(R.id.OthersButton);
            
            FamilyButton.setOnClickListener(this);
            FriendsButton.setOnClickListener(this);
            WorkButton.setOnClickListener(this);
            OthersButton.setOnClickListener(this);
        }
		
		public void onClick(View gp)
		{
		int[] selects1 = callloglist.selects;
		switch(gp.getId()){
		
		case R.id.FamilyButton:
			
			group_ID = "family";
			for(int i=1; i<selects1.length; i++)
			{
				if(selects1[i]!=-1)
				{
					databaseControl.SaveBookMarks(selects1.length-i, group_ID);
				}
				
			}
			Intent i1=new Intent(this,PUMa.class);
        	startActivity(i1);
			break;
			
		case R.id.FriendsButton:
			group_ID = "friends";
			for(int i=1; i<selects1.length; i++)
			{
				if(selects1[i]!=-1)
				{
					databaseControl.SaveBookMarks(selects1.length-i, group_ID);
				}
			}
			Intent i2=new Intent(this,PUMa.class);
        	startActivity(i2);
			break;
		case R.id.WorkButton:
			group_ID = "Work";
			for(int i=1; i<selects1.length; i++)
			{
				if(selects1[i]!=-1)
				{
					databaseControl.SaveBookMarks(selects1.length-i, group_ID);
				}
				
			}
			Intent i3=new Intent(this,PUMa.class);
        	startActivity(i3);
			break;
		case R.id.OthersButton:
			group_ID = "Others";
			for(int i=1; i<selects1.length; i++)
			{
				if(selects1[i]!=-1)
				{
					databaseControl.SaveBookMarks(selects1.length-i, group_ID);
				}
				
			}
			Intent i4=new Intent(this,PUMa.class);
        	startActivity(i4);	
			break;
		}
		}
	}
