package com.CallMapper.loaders;

import java.util.ArrayList;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

import com.CallMapper.Constants;
import com.CallMapper.database.DatabaseControl;
import com.CallMapper.entities.Contact;

public class DataLoader extends AsyncTaskLoader<ArrayList<Contact>>{

	Context mContext;
	String action;
	ArrayList<Contact> mData;
	DatabaseControl dbControl;
	Bundle mBundle;
	
	public DataLoader(Context context, DatabaseControl dc, Bundle bundle) {
		super(context);
		action = bundle.getString(Constants.LOADER_ACTION);
		mBundle = bundle;
		dbControl = dc;
	}
	
	@Override
	public ArrayList<Contact> loadInBackground() {
		ArrayList<Contact> result = new ArrayList<Contact>();
		if (action.equals(Constants.EXTRA_CALL_LOG)) {
			result = dbControl.getContacts(Constants.CALL);
		} else if (action.equals(Constants.LOADER_TEXT_ACTION)) {
			result = dbControl.getContacts(Constants.TEXT);
		} else if (action.equals(Constants.LOADER_MAP_ACTION)) {
			result = dbControl.getContactsFromPhoneNumbers(mBundle.getStringArrayList(Constants.EXTRA_PHONE_NUMBERS));
		} else {
			result = dbControl.getContactsByGroup(action);
		}
		
		return result;
	}
	
	@Override
	public void deliverResult(ArrayList<Contact> data) {
		mData = data;
		
		if (isStarted()) {
			super.deliverResult(data);
		}
	}
	
	@Override
	protected void onStartLoading() {
		if (mData != null) {
			deliverResult(mData);
		}

		if (takeContentChanged() || mData == null) {
			forceLoad();
		}
	}
	
	@Override
	protected void onStopLoading() {
		cancelLoad();
	}
	
	@Override
	public void onCanceled(ArrayList<Contact> data) {
		super.onCanceled(data);
	}
	
	@Override
	protected void onReset() {
		super.onReset();
	}
}
