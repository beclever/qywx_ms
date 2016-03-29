package com.grgbanking.css.exception;

/**
 * 业务逻辑层异常
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhang zhi
 * Date: 2010-4-21 下午03:40:51
 */
public class ServiceException extends RuntimeException{
	
	public ServiceException(String message){
		super(message);
	}
	
	public ServiceException(String message,Exception e){
		super(message,e);
	}
	
	public ServiceException(Exception e){
		super(e);
	}
}
