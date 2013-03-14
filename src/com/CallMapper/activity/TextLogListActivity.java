package com.CallMapper.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
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
import com.CallMapper.loaders.DataLoader;

/**
 * List activity to display text message list
 * 
 * @author vpenemetsa
 *
 */
public class TextLogListActivity extends Activity implements LoaderCallbacks<ArrayList<Contact>> {
	Button mButtonViewMap, mButtonGroup;

	ListView list;
	static String table_name = "callloglist";
	static boolean[] selections;
	ArrayList<Integer> selects = new ArrayList<Integer>();
	static int checkedCount = 0;
	
	private static final int LOADER_LIST = 1;
	
	ContactAdapter contactAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		
		list = (ListView) findViewById(R.id.content_list);
		
		mButtonViewMap = (Button) findViewById(R.id.view_map);
		mButtonGroup = (Button) findViewById(R.id.group);
		
		setButtonInteractions();
		
		contactAdapter = new ContactAdapter(getApplicationContext(), 0, getLayoutInflater());
		list.setAdapter(contactAdapter);
		
		getLoaderManager().initLoader(LOADER_LIST, createLoaderBundle(), this);
	}
	
	protected Bundle createLoaderBundle() {
		Bundle b = new Bundle();
		b.putSerializable(Constants.LOADER_ACTION, Constants.LOADER_TEXT_ACTION);
		return b;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		getLoaderManager().restartLoader(LOADER_LIST, createLoaderBundle(), this);
	}
	
	@Override
	public Loader<ArrayList<Contact>> onCreateLoader(int id, Bundle args) {
		return new DataLoader(getApplicationContext(),new DatabaseControl(getApplicationContext()), args);
	}

	@Override
	public void onLoadFinished(Loader<ArrayList<Contact>> loader, ArrayList<Contact> data) {
		contactAdapter.setData(data);
	}

	@Override
	public void onLoaderReset(Loader<ArrayList<Contact>> loader) {
		contactAdapter.setData(null);
	}
	
	private void setButtonInteractions() {
		
		mButtonViewMap.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ArrayList<String> phNumbers = new ArrayList<String>();
				 
				for (int position : selects) {
					Contact contact = (Contact) list.getItemAtPosition(position);
					phNumbers.add(contact.getPhoneNumber());
				}
				 
				Intent i = new Intent(getApplicationContext(), LogMapActivity.class);
				i.putStringArrayListExtra(Constants.EXTRA_PHONE_NUMBERS, phNumbers);
				startActivity(i);
			}
		});
		mButtonGroup.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ArrayList<String> phoneNumbers = new ArrayList<String>();
				 
				 for (int position : selects) {
					 Contact contact = (Contact) list.getItemAtPosition(position);
					 phoneNumbers.add(contact.getPhoneNumber());
				 }
				 
				 Intent i=new Intent(getApplicationContext(),GroupActivity.class);
				 i.putExtra(Constants.EXTRA_GROUP_FLAG, Constants.EXTRA_SAVE_GROUPS);
				 i.putStringArrayListExtra(Constants.EXTRA_PHONE_NUMBERS, phoneNumbers);
				 startActivity(i);
			}
		});
		
		list.setOnItemClickListener(new OnItemClickListener() {
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
}
