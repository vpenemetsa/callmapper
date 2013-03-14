package com.CallMapper;

/**
 * Constants used throughout the application
 * 
 * @author vpenemetsa
 *
 */
public class Constants {

	public static final String EXTRA_PHONE_NUMBERS = "EXTRA_PHONE_NUMBERS";
	
	public static final String EXTRA_GROUP_NAME = "EXTRA_GROUP_NAME";
	public static final String EXTRA_GROUP_FLAG = "EXTRA_GROUP_FLAG";
	
	public static final String EXTRA_CALL_LOG_FLAG = "EXTRA_CALL_LOG_FLAG";
	public static final String EXTRA_SAVE_GROUPS = "EXTRA_SAVE_GROUPS";
	public static final String EXTRA_VIEW_BY_GROUPS = "EXTRA_VIEW_BY_GROUPS";
	
	public static final String EXTRA_CALL_LOG = "EXTRA_CALL_LOG";
	public static final String EXTRA_CALL_LOG_BY_GROUP = "EXTRA_CALL_LOG_BY_GROUP";
	public static final String EXTRA_TEXT_LOG_FLAG = "EXTRA_TEXT_LOG_FLAG";
	public static final String EXTRA_TEXT_LOG = "EXTRA_TEXT_LOG";
	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	
	public static final String EXTRA_SMS_PDUS = "pdus";
	public static final String EXTRA_SMS_URI = "content://sms";
	public static final String EXTRA_SMS_TYPE = "type";
	public static final String EXTRA_SMS_TYPE_OUTGOING = "2";
	public static final String EXTRA_SMS_ID = "_id";
	public static final String EXTRA_SMS_BODY = "body";
	public static final String EXTRA_SMS_ADDRESS = "address";
	
	public static final long EXTRA_MIN_TIME = 60000; //1 Minute
	public static final float EXTRA_MIN_DISTANCE = 1000; //1 Km
	
	public static final String GROUP_FAMILY = "family";
	public static final String GROUP_FRIENDS = "friends";
	public static final String GROUP_WORK = "work";
	public static final String GROUP_OTHERS = "others";
	
	public static final String CALL = "call";
	public static final String TEXT = "text";
	
	public static final String ACTION_SMS_SENT = "action_sms_sent";
	public static final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
	public static final String ACTION_OUTGOING_CALL = "android.intent.action.NEW_OUTGOING_CALL";
	
}
