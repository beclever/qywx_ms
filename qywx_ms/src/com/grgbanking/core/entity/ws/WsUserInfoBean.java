package com.grgbanking.core.entity.ws;

public class WsUserInfoBean {

	private String storeId;//	仓库ID				
	private String loginName	;//	登陆名				
	private String userCode	;//	用户编码				
	private String userName	;//	员工号		
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
