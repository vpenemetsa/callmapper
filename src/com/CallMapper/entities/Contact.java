package com.CallMapper.entities;

/**
 * Data model for Contact entity
 * 
 * @author vpenemetsa
 *
 */
public class Contact {
	private String phoneNumber;
	
	private String name;
	
	private String latitude;
	
	private String longitude;
	
	private String group;
	
	private String message;
	
	private String type;
	
	private String timestamp;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String number) {
		this.phoneNumber = number;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String lat) {
		this.latitude = lat;
	}
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTimeStamp() {
		return timestamp;
	}
	public void setTimeStamp(String ts) {
		this.timestamp = ts;
	}
}
