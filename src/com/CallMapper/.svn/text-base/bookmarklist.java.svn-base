package com.PUMa;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class bookmarklist extends ListActivity implements OnClickListener{

	
	Button ViewMapButtonBM,GroupsButtonBM;
	ListView list;
	static String table_name_2 = "bookmarks";
	static int listlen = databaseControl.getNumberOfRows(table_name_2);
	static boolean[] selections = new boolean[listlen+1];
	static int[] selects = new int[listlen+1];

	String[] LIST_temp = databaseControl.GetPhoneNumbers(table_name_2);
	String[] LIST = LIST_temp;
	@Override//
	public void onCreate(Bundle savedInstanceState) 
	    {
			//
	        super.onCreate(savedInstanceState);
	        //setContentView(R.layout.bml);
	                       
	        
            list = getListView();
            View bml = getLayoutInflater().inflate(R.layout.bml,list,false);
            getListView().addHeaderView(bml, null, true);
            
            ViewMapButtonBM = (Button)bml.findViewById(R.id.ViewMapButtonBM);
	        GroupsButtonBM = (Button)bml.findViewById(R.id.GroupsButtonBM);
	        //GetDirectionsButtonBM = (Button)findViewById(R.id.GetDirectionsButtonBM);
	        
	        ViewMapButtonBM.setOnClickListener(this);
            GroupsButtonBM.setOnClickListener(this);
            //GetDirectionsButtonBM.setOnClickListener(this);
                        
            getListView().setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, LIST));
            getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            getListView().setTextFilterEnabled(true);
            
   
            getListView().setOnItemClickListener(new OnItemClickListener()
            {
            			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
            					long arg3) {
            				SparseBooleanArray a;
            				a = getListView().getCheckedItemPositions();
            	//			
            				
            				for(int i=0; i<=listlen;i++)
            				{
            					selections[i] = a.get(i);
            				}
	
            				
            				for(int i=0; i<=listlen;i++)
            				{
            					if(a.get(i))
            					{
            						selects[i] = i;
            						Log.d("selections", Integer.toString(selects[i]));
            						
            					}
            					else
            					{
            						selects[i] = -1;
            						
            					}
            				}
            			}
             });
         			
            
         }
	//Getting data from database
	
		
	
	public void onClick(View ss) {
		switch(ss.getId())
		{
		case R.id.ViewMapButtonBM:
			Intent i6=new Intent(this,viewmapbm.class);
	    	startActivity(i6);
	    	break;
	    	
		case R.id.GroupsButtonBM:
			Intent i7=new Intent(this,groupbm.class);
	    	startActivity(i7);
	    	break;
	    	
		
		}
		// TODO Auto-generated method stub
		
	}
	        

	

}
