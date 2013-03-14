package com.CallMapper.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.CallMapper.Constants;
import com.CallMapper.entities.Contact;

/**
 * This class contains helper methods for reading and writing into the db
 * 
 * @author vpenemetsa
 *
 */
public class DatabaseControl {
	static Context mContext;
		
	public DatabaseControl(Context context) {
		mContext = context;
	}

	// Adding row to Call Log List
	public static void addRowCLL(String rowId, String phoneNumber, String userName, 
			double latitude, double longitude) {
		
		CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(mContext);
		SQLiteDatabase pum = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(CustomSQLiteOpenHelper.TABLE_ROW_ID, rowId);
		values.put(CustomSQLiteOpenHelper.TABLE_PHONE_NUMBER, phoneNumber);
		values.put(CustomSQLiteOpenHelper.TABLE_USERNAME, userName);
		values.put(CustomSQLiteOpenHelper.TABLE_LATITUDE, latitude);
		values.put(CustomSQLiteOpenHelper.TABLE_LONGITUDE, longitude);
		values.put(CustomSQLiteOpenHelper.TABLE_TYPE, Constants.CALL);
		
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
	    Date now = new Date();
	    String date = sdfDate.format(now);
		
	    values.put(CustomSQLiteOpenHelper.TABLE_TIMESTAMP, date);
		
		try {
			pum.insert(CustomSQLiteOpenHelper.TABLE_NAME, null, values);
		} catch (Exception e) {
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}
		
		pum.close();
	}
	
	public static void addRowTLL(String rowId, String phoneNumber, String userName, 
			double latitude, double longitude, String message) {
		
		CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(mContext);
		SQLiteDatabase pum = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(CustomSQLiteOpenHelper.TABLE_ROW_ID, rowId);
		values.put(CustomSQLiteOpenHelper.TABLE_PHONE_NUMBER, phoneNumber);
		values.put(CustomSQLiteOpenHelper.TABLE_USERNAME, userName);
		values.put(CustomSQLiteOpenHelper.TABLE_LATITUDE, latitude);
		values.put(CustomSQLiteOpenHelper.TABLE_LONGITUDE, longitude);
		values.put(CustomSQLiteOpenHelper.TABLE_TYPE, Constants.TEXT);
		values.put(CustomSQLiteOpenHelper.TABLE_MESSAGE, message);
		
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
	    Date now = new Date();
	    String date = sdfDate.format(now);
		
	    values.put(CustomSQLiteOpenHelper.TABLE_TIMESTAMP, date);
		
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

//	// Retrieving data from CLL
//	public static ArrayList<Object> getRowCLL(long rowID) {
//		ArrayList<Object> rowArray = new ArrayList<Object>();
//		Cursor cursor;
//
//		CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(mContext);
//		SQLiteDatabase pum = helper.getWritableDatabase();
//		
//		try {
//			cursor = pum.query(CustomSQLiteOpenHelper.TABLE_NAME, new String[] { CustomSQLiteOpenHelper.TABLE_ROW_ID,
//					CustomSQLiteOpenHelper.TABLE_PHONE_NUMBER, CustomSQLiteOpenHelper.TABLE_USERNAME, 
//					CustomSQLiteOpenHelper.TABLE_LATITUDE, CustomSQLiteOpenHelper.TABLE_LONGITUDE }, 
//					CustomSQLiteOpenHelper.TABLE_ROW_ID + "=" + rowID, null,
//					null, null, null, null);
//
//			cursor.moveToFirst();
//
//			if (!cursor.isAfterLast()) {
//				 {
//					rowArray.add(cursor.getLong(0));
//					rowArray.add(cursor.getString(1));
//					rowArray.add(cursor.getString(2));
//					rowArray.add(cursor.getInt(3));
//					rowArray.add(cursor.getInt(4));
//				} //while (cursor.moveToNext());
//			}
//			cursor.close();
//		}
//
//		catch (SQLException e) {
//			Log.e("DB ERROR", e.toString());
//			e.printStackTrace();
//		}
//
//		pum.close();
//		
//		return rowArray;
//	}
	
	public ArrayList<Contact> getContacts(String type) 
	{
		CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(mContext);
		SQLiteDatabase pum = helper.getReadableDatabase();
		
		Cursor cur = null;
		ArrayList<Contact> phone_array = new ArrayList<Contact>();	
		if (type.equals(Constants.CALL)) {
			String sql_query  = "select " + CustomSQLiteOpenHelper.TABLE_PHONE_NUMBER + "," + 
			CustomSQLiteOpenHelper.TABLE_USERNAME + "," + CustomSQLiteOpenHelper.TABLE_LATITUDE
					+ "," + CustomSQLiteOpenHelper.TABLE_LONGITUDE + "," + CustomSQLiteOpenHelper.TABLE_TIMESTAMP 
					+ " from " + CustomSQLiteOpenHelper.TABLE_NAME
					+ " where " + CustomSQLiteOpenHelper.TABLE_TYPE + "='" + Constants.CALL + "'";
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
			    			contact.setType(Constants.CALL);
			    			contact.setTimeStamp(cur.getString(4));
		    				phone_array.add(contact);
			   		 	} while(cur.moveToPrevious());
			    	}
			    }
		    		
		    	cur.close();
			} catch (SQLException e){
				Log.e("DB ERROR", e.toString());
				e.printStackTrace();
			}			
		} else {
			String sql_query  = "select " + CustomSQLiteOpenHelper.TABLE_PHONE_NUMBER + "," + 
					CustomSQLiteOpenHelper.TABLE_USERNAME + "," + CustomSQLiteOpenHelper.TABLE_LATITUDE
							+ "," + CustomSQLiteOpenHelper.TABLE_LONGITUDE + "," + CustomSQLiteOpenHelper.TABLE_MESSAGE
							+ "," + CustomSQLiteOpenHelper.TABLE_TIMESTAMP
							+ " from " + CustomSQLiteOpenHelper.TABLE_NAME 
							+ " where " + CustomSQLiteOpenHelper.TABLE_TYPE + "='" + Constants.TEXT + "'";
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
			    			contact.setMessage(cur.getString(4));
			    			contact.setType(Constants.TEXT);
			    			contact.setTimeStamp(cur.getString(5));
		    				phone_array.add(contact);
			   		 	} while(cur.moveToPrevious());
			    	}
			    }
		    		
		    	cur.close();
			} catch (SQLException e){
				Log.e("DB ERROR", e.toString());
				e.printStackTrace();
			}					
		}
		
		cur.close();
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
				CustomSQLiteOpenHelper.TABLE_LONGITUDE,
				CustomSQLiteOpenHelper.TABLE_MESSAGE,
				CustomSQLiteOpenHelper.TABLE_TYPE,
				CustomSQLiteOpenHelper.TABLE_TIMESTAMP
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
		    			contact.setMessage(cur.getString(4));
		    			contact.setType(cur.getString(5));
		    			contact.setTimeStamp(cur.getString(6));
	    				phone_array.add(contact);
		   		 	} while(cur.moveToPrevious());
		    	}
		    }
	    		
	    	cur.close();
		} catch (SQLException e){
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}
		
		cur.close();
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
				CustomSQLiteOpenHelper.TABLE_GROUP,
				CustomSQLiteOpenHelper.TABLE_TYPE,
				CustomSQLiteOpenHelper.TABLE_MESSAGE,
				CustomSQLiteOpenHelper.TABLE_TIMESTAMP
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
			    			contact.setType(cur.getString(5));
			    			contact.setMessage(cur.getString(6));
			    			contact.setTimeStamp(cur.getString(7));
		    				contacts.add(contact);
			   		 	} while(cur.moveToPrevious());
			    	}
			    }
		    		
		    	cur.close();
			} catch (SQLException e){
				cur.close();
				Log.e("DB ERROR", e.toString());
				e.printStackTrace();
			}
		}
		
		pum.close();
		return contacts;
	}
}
