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

public class callloglist extends ListActivity implements OnClickListener {

	Button ViewMapButtonCLL, SaveLocationButtonCLL;
	ListView list;
	static String table_name = "callloglist";
	static int listlen = databaseControl.getNumberOfRows(table_name);
	static boolean[] selections = new boolean[listlen+1];
	static int[] selects = new int[listlen+1];

	String[] LIST_temp = databaseControl.GetPhoneNumbers(table_name);
	String[] LIST = LIST_temp;
	//
	@Override
	public void onCreate(Bundle savedInstanceState) {
////
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.bml);
		Log.d("testinglog", "Will this come up");
		list = getListView();
		View cll = getLayoutInflater().inflate(R.layout.cll, list, false);
		getListView().addHeaderView(cll, null, true);

		ViewMapButtonCLL = (Button) cll.findViewById(R.id.ViewMapButtonCLL);
		SaveLocationButtonCLL = (Button) cll.findViewById(R.id.SaveLocationButtonCLL);
		// GetDirectionsButtonBM =
		// (Button)findViewById(R.id.GetDirectionsButtonBM);

		ViewMapButtonCLL.setOnClickListener(this);
		SaveLocationButtonCLL.setOnClickListener(this);
		// GetDirectionsButtonBM.setOnClickListener(this);

		getListView().setAdapter(
				new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_checked, LIST));
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		getListView().setTextFilterEnabled(true);

		getListView().setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView arg0, View arg1, int arg2,
					long arg3) {
//				
				SparseBooleanArray a;
				a = getListView().getCheckedItemPositions();
					
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

		public void onClick(View ss) {
		switch (ss.getId()) {
		case R.id.ViewMapButtonCLL:
			Intent i4 = new Intent(this, viewmap.class);
			startActivity(i4);
			break;
		
		 case R.id.SaveLocationButtonCLL: 
		 Intent i5=new Intent(this,groupcl.class); 
		 startActivity(i5); break;
		 

		}
		// TODO Auto-generated method stub

	}

}
