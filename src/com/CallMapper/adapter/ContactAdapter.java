package com.CallMapper.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.CallMapper.entities.Contact;

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
			number.setText(item.getPhoneNumber());
			
		}
		
		return convertView;
	}
}
