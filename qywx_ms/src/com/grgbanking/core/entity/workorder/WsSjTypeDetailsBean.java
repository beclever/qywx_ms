package com.grgbanking.core.entity.workorder;;

//巡检内容
public class WsSjTypeDetailsBean  {

	private String sjTypeid;// String(Y) 巡检内容类型ID
	private String sjContext;// String(Y) 说明

	public String getSjTypeid() {
		return sjTypeid;
	}

	public void setSjTypeid(String sjTypeid) {
		this.sjTypeid = sjTypeid;
	}

	public String getSjContext() {
		return sjContext;
	}

	public void setSjContext(String sjContext) {
		this.sjContext = sjContext;
	}
	
}
