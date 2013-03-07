package com.CallMapper.entities;

public class Contact {
	private String phoneNumber;
	
	private String name;
	
	private String latitude;
	
	private String longitude;
	
	private String group;
	
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
}
