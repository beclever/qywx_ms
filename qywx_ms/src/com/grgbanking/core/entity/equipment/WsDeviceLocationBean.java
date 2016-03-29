package com.grgbanking.core.entity.equipment;




/**
 * 
 * GPS信息
 *
 */
public class WsDeviceLocationBean {
	
private String longitude = "-1";	//经度	String (Y)
private String latitude = "-1";//纬度	String (Y)
private String location = "";	//具体位置	String (N)

public WsDeviceLocationBean(){}



public WsDeviceLocationBean(String longitude, String latitude, String location) {
	this.longitude = longitude;
	this.latitude = latitude;
	this.location = location;
}



public String getLongitude() {
	return longitude;
}
public void setLongitude(String longitude) {
	this.longitude = longitude;
}
public String getLatitude() {
	return latitude;
}
public void setLatitude(String latitude) {
	this.latitude = latitude;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
	

}
