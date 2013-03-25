package com.CallMapper.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
	ArrayList<Contact> checked = new ArrayList<Contact>();
	
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
	
	public void setChecked(Contact item) {
		if (checked.contains(item)) {
			checked.remove(item);
		} else {
			checked.add(item);
		}
	}
	
	public ArrayList<Contact> getCheckedItems() {
		return checked;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final ViewHolder holder;
		final Contact item = getItem(position);
		
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(android.R.layout.simple_list_item_checked, parent, false);
			holder.ctvData = (CheckedTextView) convertView.findViewById(android.R.id.text1);
			convertView.setTag(holder);
		}  else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		if (checked.contains(item)) {
			holder.ctvData.setChecked(true);
		} else {
			holder.ctvData.setChecked(false);
		}
		
		if (item != null) {
			holder.ctvData.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					CheckedTextView ctv = (CheckedTextView) v;
					if (checked.contains(item)) {
						checked.remove(item);
						ctv.setChecked(false);
					} else {
						checked.add(item);
						ctv.setChecked(true);
					}
				}
			});
			
			String[] projection = new String[] {
			        ContactsContract.PhoneLookup.DISPLAY_NAME};
			
			Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(item.getPhoneNumber()));
			Cursor cursor = mContext.getContentResolver().query(contactUri, projection, null, null, null);
			
			if (cursor.moveToFirst()) {
				String name = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
				holder.ctvData.setText(name + " (" + item.getPhoneNumber() + ")");
			} else {
				holder.ctvData.setText(item.getPhoneNumber());
			}
		}
		
		return convertView;
	}
	
	class ViewHolder {
		CheckedTextView ctvData;
	}
}