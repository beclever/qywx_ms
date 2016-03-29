package com.grgbanking.css.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.grgbanking.css.bean.CssRole;
import com.grgbanking.css.bean.CssUserBean;
import com.grgbanking.css.util.FrameworkConstants;



/**
 * 获取当前登录用户信息
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-5-29 下午12:54:54
 */
public  class  UserContext {
	/**
	 * 获取当前登录用户对象
	 * @return
	 */
	@Autowired
	private static HttpServletRequest request;
	
	public static CssUserBean getUser(){
		try {
			if(request!=null){
				return (CssUserBean) request.getSession().getAttribute(FrameworkConstants.SESSION_KEY_CURRENT_USER);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	/**
	 * 自接从REQUEST中获取当前登录用户信息
	 * @param request
	 * @return
	 */
	public static CssUserBean getUser(HttpServletRequest request){
		return (CssUserBean) request.getSession().getAttribute(FrameworkConstants.SESSION_KEY_CURRENT_USER);
	}

	/**
	 * 判断用户是否具有指定角色列表的其中之一.
	 * @param roleString 以逗号隔开的角色字符串
	 * @param user
	 * @return
	 */
	public static boolean hasAuthority(String roleString, CssUserBean user) {
		if(user==null||StringUtils.isEmpty(user.getRoleString())){
			return false;
		}
		String[] menuRoleArray=roleString.split(",");
		String[] userRoleArray=user.getRoleString().split(",");
		for (String userRole : userRoleArray) {
			if(ArrayUtils.contains(menuRoleArray, userRole)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断用户是否具有指定角色列表的其中之一.
	 * @param roleString 以逗号隔开的角色字符串
	 * @param user
	 * @return
	 */
	public static boolean hasAuthority(String roleString) {
		return hasAuthority(roleString,getUser());
	}

	/**
	 * 判断roleList中是否存在role
	 * @param role
	 * @param roleList
	 * @return
	 */
	public static boolean hasAuthority(String role,List<CssRole> roleList) {
		if(roleList==null||roleList.size()<1){
			return false;
		}
		for (CssRole role2 : roleList) {
			if(role2.getCode().equals(role)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 将用户SESSION从APPLICATION中添加或移除，调用统一方法，避免死锁。。
	 */
	public static synchronized void operateUserSesstion(HttpSession session,String operateType,Object ... sessionArray){
		List<HttpSession> sessionList = (List<HttpSession>) session.getServletContext().getAttribute(FrameworkConstants.APPLICATION_KEY_ONLINE_USER_SESSION);
		if(sessionList==null){
			sessionList=new ArrayList<HttpSession>();
			session.getServletContext().setAttribute(FrameworkConstants.APPLICATION_KEY_ONLINE_USER_SESSION,sessionList);
		}
		if("add".equals(operateType)){
			for (Object httpSession : sessionArray) {
				sessionList.add((HttpSession)httpSession);
			}
		}else if("remove".equals(operateType)){
			for (Object httpSession : sessionArray) {
				sessionList.remove(httpSession);
			}
		}
		
	}

	/**
	 * 跨系统登录：例如管理平台每一个访问地址都会传一个token
	 * @param token
	 * @param request 
	 * @return
	 */
	/*public static synchronized boolean crossSystemLogin(String token, HttpServletRequest request,String cssrole,String cssdepId) {
		UserService userService = (UserService) WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext()).getBean("userService");
		UserBean user=userService.crossSystemLogin(token,cssrole,cssdepId);
		if(user==null){
			return false;
		}
		user.setLoginIp(request.getRemoteAddr());
		
		//存放在APPLICATION中已登录的SESSIOIN集合，用于统计在线人员
		List<HttpSession> sessionList = (List<HttpSession>) request.getSession().getServletContext().getAttribute(FrameworkConstants.APPLICATION_KEY_ONLINE_USER_SESSION);
		if(sessionList==null){
			sessionList=new ArrayList<HttpSession>();
		}
		try{
			List<HttpSession> removeList=new ArrayList<HttpSession>();
			for (HttpSession httpSession : sessionList) {
				if(httpSession!=null){
					try {
						UserBean onlineUser=(UserBean) httpSession.getAttribute(FrameworkConstants.SESSION_KEY_CURRENT_USER);
						//如果已经有人使用此帐号登录,则将其剔除
						if(onlineUser!=null&&onlineUser.getLoginName().equals(user.getLoginName())){
							httpSession.invalidate();
							break;
						}
					} catch (Exception e) {
						removeList.add(httpSession);
					}
				}
			}
			//将用户SESSION存入application
			request.getSession().setAttribute(FrameworkConstants.SESSION_KEY_CURRENT_USER, user);
			request.getSession().setAttribute(FrameworkConstants.SESSION_KEY_CURRENT_USER_STYLE,user.getDefaultStyle());
			//将当前用户SESSION加入在线人员列表中
			UserContext.operateUserSesstion(request.getSession(), "add", request.getSession());
			UserContext.operateUserSesstion(request.getSession(), "remove", removeList.toArray());
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}*/
}
