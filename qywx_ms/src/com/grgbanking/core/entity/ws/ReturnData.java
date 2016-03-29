package com.grgbanking.core.entity.ws;
/**
 * Copyright (c) 2011 GRG Banking Equipment Co.,Ltd. 
 * @createDate 2012-12-6
 * @author luowei 
 * @description 
 * 返回类
 */
public class ReturnData {
	
	private boolean flag;//返回标志
	
	private String msg;//返回消息
	
	public ReturnData(){
		this.flag = true;
		this.msg = "";
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
