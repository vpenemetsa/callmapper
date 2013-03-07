package com.CallMapper;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.CallMapper.entities.Contact;

public class DatabaseControl {
	static Context mContext;
	
	public DatabaseControl(Context context) {
		mContext = context;
	}

	// Adding row to Call Log List
	public static void addRowCLL(String row0, String row1, String row2, double latitude, double longitude) {
		
		CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(mContext);
		SQLiteDatabase pum = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(CustomSQLiteOpenHelper.TABLE_ROW_ID, row0);
		values.put(CustomSQLiteOpenHelper.TABLE_PHONE_NUMBER, row1);
		values.put(CustomSQLiteOpenHelper.TABLE_USERNAME, row2);
		values.put(CustomSQLiteOpenHelper.TABLE_LATITUDE, latitude);
		values.put(CustomSQLiteOpenHelper.TABLE_LONGITUDE, longitude);

		try {
			pum.insert(CustomSQLiteOpenHelper.TABLE_NAME, null, values);
		} catch (Exception e) {
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}
		
		pum.close();
	}
	
	public static void createGroups(ArrayList<String> contacts, String groupFamily) {
		CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(mContext);
		SQLiteDatabase pum = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(CustomSQLiteOpenHelper.TABLE_GROUP, groupFamily);
		
		String selection = CustomSQLiteOpenHelper.TABLE_PHONE_NUMBER;
		
		for (String contact : contacts) {
			String[] c = new String[1];
			c[0] = contact;
			pum.update(CustomSQLiteOpenHelper.TABLE_NAME, values, selection + " = ?", c);
		}
		
		pum.close();
	}
	
	// Deleting a row in CLL
	public void deleteRowCLL(long rowID) {
		
		CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(mContext);
		SQLiteDatabase pum = helper.getWritableDatabase();
		
		try {
			pum.delete(CustomSQLiteOpenHelper.TABLE_NAME, CustomSQLiteOpenHelper.TABLE_ROW_ID + "=" + rowID, null);
		} catch (Exception e) {
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}
		
		pum.close();
	}

	// Retrieving data from CLL
	public static ArrayList<Object> getRowCLL(long rowID) {
		ArrayList<Object> rowArray = new ArrayList<Object>();
		Cursor cursor;

		CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(mContext);
		SQLiteDatabase pum = helper.getWritableDatabase();
		
		try {
			cursor = pum.query(CustomSQLiteOpenHelper.TABLE_NAME, new String[] { CustomSQLiteOpenHelper.TABLE_ROW_ID,
					CustomSQLiteOpenHelper.TABLE_PHONE_NUMBER, CustomSQLiteOpenHelper.TABLE_USERNAME, 
					CustomSQLiteOpenHelper.TABLE_LATITUDE, CustomSQLiteOpenHelper.TABLE_LONGITUDE }, 
					CustomSQLiteOpenHelper.TABLE_ROW_ID + "=" + rowID, null,
					null, null, null, null);

			cursor.moveToFirst();

			if (!cursor.isAfterLast()) {
				 {
					rowArray.add(cursor.getLong(0));
					rowArray.add(cursor.getString(1));
					rowArray.add(cursor.getString(2));
					rowArray.add(cursor.getInt(3));
					rowArray.add(cursor.getInt(4));
				} //while (cursor.moveToNext());
			}
			cursor.close();
		}

		catch (SQLException e) {
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}

		pum.close();
		
		return rowArray;
	}
	
	public ArrayList<Contact> getContacts() 
	{
		CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(mContext);
		SQLiteDatabase pum = helper.getReadableDatabase();
		
		Cursor cur = null;
		ArrayList<Contact> phone_array = new ArrayList<Contact>();	
		String sql_query  = "select " + CustomSQLiteOpenHelper.TABLE_PHONE_NUMBER + "," + 
		CustomSQLiteOpenHelper.TABLE_USERNAME + "," + CustomSQLiteOpenHelper.TABLE_LATITUDE
				+ "," + CustomSQLiteOpenHelper.TABLE_LONGITUDE + " from " + CustomSQLiteOpenHelper.TABLE_NAME;
		try {
			cur = pum.rawQuery(sql_query, null);
		    if (cur.getCount() > 0) {
		    	cur.moveToLast();
			    if(!cur.isBeforeFirst()){
		    		do {
		    			Contact contact = new Contact();
		    			contact.setPhoneNumber(cur.getString(0));
		    			contact.setName(cur.getString(1));
		    			contact.setLatitude(cur.getString(2));
		    			contact.setLongitude(cur.getString(3));
	    				phone_array.add(contact);
		   		 	} while(cur.moveToPrevious());
		    	}
		    }
	    		
	    	cur.close();
		} catch (SQLException e){
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}
		
		pum.close();
		
		return phone_array;
	}
	
	public ArrayList<Contact> getContactsByGroup(String group) {
		CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(mContext);
		SQLiteDatabase pum = helper.getReadableDatabase();
		
		String projection[] = {
				CustomSQLiteOpenHelper.TABLE_PHONE_NUMBER,
				CustomSQLiteOpenHelper.TABLE_USERNAME,
				CustomSQLiteOpenHelper.TABLE_LATITUDE,
				CustomSQLiteOpenHelper.TABLE_LONGITUDE
		};
		
		String selection = CustomSQLiteOpenHelper.TABLE_GROUP;
		String[] selectionArgs = {group};
		
		Cursor cur = pum.query(CustomSQLiteOpenHelper.TABLE_NAME, projection, selection + " = ?", selectionArgs, null, null, null);
		
		ArrayList<Contact> phone_array = new ArrayList<Contact>();	
		
		try {
			if (cur.getCount() > 0) {
		    	cur.moveToLast();
			    if(!cur.isBeforeFirst()){
		    		do {
		    			Contact contact = new Contact();
		    			contact.setPhoneNumber(cur.getString(0));
		    			contact.setName(cur.getString(1));
		    			contact.setLatitude(cur.getString(2));
		    			contact.setLongitude(cur.getString(3));
	    				phone_array.add(contact);
		   		 	} while(cur.moveToPrevious());
		    	}
		    }
	    		
	    	cur.close();
		} catch (SQLException e){
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}
		
		pum.close();
		
		return phone_array;
	}
	
	public static ArrayList<Contact> getContactsFromPhoneNumbers(ArrayList<String> phoneNumbers) {
		CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(mContext);
		SQLiteDatabase pum = helper.getReadableDatabase();
		
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		String projection[] = {
				CustomSQLiteOpenHelper.TABLE_PHONE_NUMBER,
				CustomSQLiteOpenHelper.TABLE_USERNAME,
				CustomSQLiteOpenHelper.TABLE_LATITUDE,
				CustomSQLiteOpenHelper.TABLE_LONGITUDE,
				CustomSQLiteOpenHelper.TABLE_GROUP
		};
		
		String selection = CustomSQLiteOpenHelper.TABLE_PHONE_NUMBER;
		
		for (String phoneNumber : phoneNumbers) {
			String[] selectionArgs = {phoneNumber};
			Cursor cur = pum.query(CustomSQLiteOpenHelper.TABLE_NAME, projection, selection + " = ?", selectionArgs, null, null, null);
			try {
				if (cur.getCount() > 0) {
			    	cur.moveToLast();
				    if(!cur.isBeforeFirst()){
			    		do {
			    			Contact contact = new Contact();
			    			contact.setPhoneNumber(cur.getString(0));
			    			contact.setName(cur.getString(1));
			    			contact.setLatitude(cur.getString(2));
			    			contact.setLongitude(cur.getString(3));
			    			contact.setGroup(cur.getString(4));
		    				contacts.add(contact);
			   		 	} while(cur.moveToPrevious());
			    	}
			    }
		    		
		    	cur.close();
			} catch (SQLException e){
				Log.e("DB ERROR", e.toString());
				e.printStackTrace();
			}
		}
		
		return contacts;
	}
}
