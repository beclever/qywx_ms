package com.grgbanking.core.entity.common;

public class AttachmentBean {
	
	private String imgId ; 
	private String path ;
	private String longitude;
	private String latitude;
	
	public AttachmentBean() {
		super();
	}

	public AttachmentBean(String imgId, String path) {
		super();
		this.imgId = imgId;
		this.path = path;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
	
	
	
}
