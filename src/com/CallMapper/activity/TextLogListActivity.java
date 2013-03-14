package com.CallMapper.activity;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.CallMapper.Constants;
import com.CallMapper.R;
import com.CallMapper.adapter.ContactAdapter;
import com.CallMapper.database.DatabaseControl;
import com.CallMapper.entities.Contact;

/**
 * List activity to display text message list
 * 
 * @author vpenemetsa
 *
 */
public class TextLogListActivity extends ListActivity implements OnClickListener {
	Button mButtonViewMap, mButtonGroup;

	ListView list;
	static String table_name = "callloglist";
	static boolean[] selections;
	ArrayList<Integer> selects = new ArrayList<Integer>();
	static int checkedCount = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		list = getListView();
		View cll = getLayoutInflater().inflate(R.layout.list_header, list, false);
		getListView().addHeaderView(cll, null, true);
		
		DatabaseControl dbControl = new DatabaseControl(getApplicationContext());
		
		mButtonViewMap = (Button) cll.findViewById(R.id.view_map);
		mButtonGroup = (Button) cll.findViewById(R.id.group);
		
		ArrayList<Contact> items = new ArrayList<Contact>();
		
		Intent bundle = getIntent();
		if (bundle.getStringExtra(Constants.EXTRA_TEXT_LOG_FLAG).equals(Constants.EXTRA_TEXT_LOG)) {
			mButtonViewMap.setOnClickListener(this);
			mButtonGroup.setOnClickListener(this);
			items = dbControl.getContacts(Constants.TEXT);
		} else {
			mButtonGroup.setVisibility(View.GONE);
			mButtonViewMap.setOnClickListener(this);
			items = dbControl.getContactsByGroup(bundle.getStringExtra(Constants.EXTRA_GROUP_NAME));
		}
		
		ContactAdapter ca = new ContactAdapter(getApplicationContext(), 0, getLayoutInflater());
		ca.addAll(items);
		
		getListView().setAdapter(ca);
		
		getListView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				CheckedTextView ctv = (CheckedTextView) view;
				if (ctv.isChecked()) {
					ctv.setChecked(false);
					for (int i=0; i<selects.size(); i++) {
						if (selects.get(i) == position) {
							selects.remove(i);
						}
					}
					checkedCount--;
				} else {
					ctv.setChecked(true);
					selects.add(position);
					checkedCount++;
				}
				
				if (checkedCount > 0) {
					mButtonViewMap.setEnabled(true);
					mButtonGroup.setEnabled(true);
				} else {
					mButtonViewMap.setEnabled(false);
					mButtonGroup.setEnabled(false);
				}
			}
		});
		
	}

	public void onClick(View ss) {
		switch (ss.getId()) {
			case R.id.view_map:
				ArrayList<String> phNumbers = new ArrayList<String>();
				 
				 for (int position : selects) {
					 Contact contact = (Contact) getListView().getItemAtPosition(position);
					 phNumbers.add(contact.getPhoneNumber());
				 }
				 
				Intent i = new Intent(getApplicationContext(), LogMapActivity.class);
				i.putStringArrayListExtra(Constants.EXTRA_PHONE_NUMBERS, phNumbers);
				startActivity(i);
				break;
				
			 case R.id.group:
				 ArrayList<String> phoneNumbers = new ArrayList<String>();
				 
				 for (int position : selects) {
					 Contact contact = (Contact) getListView().getItemAtPosition(position);
					 phoneNumbers.add(contact.getPhoneNumber());
				 }
				 
				 Intent i1=new Intent(this,GroupActivity.class);
				 i1.putExtra(Constants.EXTRA_GROUP_FLAG, Constants.EXTRA_SAVE_GROUPS);
				 i1.putStringArrayListExtra(Constants.EXTRA_PHONE_NUMBERS, phoneNumbers);
				 startActivity(i1); 
				 break;
		 
		
		}
	}
}
