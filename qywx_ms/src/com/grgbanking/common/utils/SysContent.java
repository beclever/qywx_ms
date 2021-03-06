package com.grgbanking.common.utils;

import javax.servlet.http.HttpServletRequest;

public class SysContent {
	
	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
	
	
	public static void setRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}
	
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest)requestLocal.get();
	}
	
}
