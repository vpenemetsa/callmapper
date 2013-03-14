package com.CallMapper.adapter;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.CallMapper.entities.Contact;

/**
 * This adapter set the view for each element in the listview 
 * 
 * @author vpenemetsa
 *
 */
public class ContactAdapter extends ArrayAdapter<Contact> {
	
	Context mContext;
	LayoutInflater mLayoutInflater;
	
	public ContactAdapter(Context context, int textViewResourceId, LayoutInflater layoutInflater) {
		super(context, textViewResourceId);
		mContext = context;
		mLayoutInflater = layoutInflater;
	}
	
	public void setData(List<Contact> data) {
		clear();
		if (data != null) {
			addAll(data);
		}
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final Contact item = getItem(position);
		
		if (item != null) {
			convertView = mLayoutInflater.inflate(android.R.layout.simple_list_item_checked, parent, false);
			
			final CheckedTextView number = (CheckedTextView) convertView.findViewById(android.R.id.text1);
			
			String[] projection = new String[] {
			        ContactsContract.PhoneLookup.DISPLAY_NAME};
			
			Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(item.getPhoneNumber()));
			Cursor cursor = mContext.getContentResolver().query(contactUri, projection, null, null, null);
			
			if (cursor.moveToFirst()) {
				String name = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
				number.setText(name + " (" + item.getPhoneNumber() + ")");
			} else {
				number.setText(item.getPhoneNumber());
			}
		}
		
		return convertView;
	}
}