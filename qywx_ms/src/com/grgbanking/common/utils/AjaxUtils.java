package com.grgbanking.common.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.grgbanking.core.entity.ws.WsInterfaceBean;

/**
 * AJAX帮助类，在action中,使用此工具返回内容。 未完成 Product:Grgbanking Service Of Customer
 * System. Version:2.0 Copyright 2010 by Grgbanking All Rights Reserved. Author:
 * zhangzhi Date: 2010-5-29 下午08:14:38
 */
public class AjaxUtils {

	private static Log log = LogFactory.getLog(AjaxUtils.class);

	/**
	 * 将字符串内容写到一个页面
	 * 
	 * @param content
	 */
	public static void renderText(final String content,
			HttpServletResponse response) {
		try {
			log.info("response: " + content);
			if (StringUtils.isEmpty(content)) {
				log.debug("AjaxRender.java>>method:renderText>>content is null!");
				return;
			}
			initHeader(response);
			response.getWriter().write(content);
			response.getWriter().close();
		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * 初始化页面header
	 * 
	 * @param response
	 */
	private static void initHeader(HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");// 设置中文问题
	}

	/**
	 * 生成分页JSON数据
	 * 
	 * @param objectList
	 */
	public static void renderPagination(List<JSONObject> objectList,
			Integer recordCount, HttpServletResponse response) {
		if (objectList == null || objectList.size() == 0) {
			log.info("目前对象集合为空！");
			objectList = new ArrayList<JSONObject>();
		}
		JSONObject jsonRoot = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (JSONObject jsonObj : objectList) {
			jsonArray.add(jsonObj);
		}
		jsonRoot.put("rows", jsonArray);
		jsonRoot.put("total", recordCount);
		renderText(jsonRoot.toString(), response);
	}

	/**
	 * 生成JSON数组
	 * 
	 * @param objectList
	 */
	public static void renderList(List<JSONObject> objectList, String root,
			HttpServletResponse response) {
		if (objectList == null || objectList.size() == 0) {
			log.info("目前对象集合为空！");
			objectList = new ArrayList<JSONObject>();
		}
		JSONObject jsonRoot = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (JSONObject jsonObj : objectList) {
			jsonArray.add(jsonObj);
		}
		jsonRoot.put(root, jsonArray);
		renderText(jsonRoot.toString(), response);
	}

	/**
	 * 向页面返回一个错误JSON信息。
	 * 
	 * @param msg
	 */
	public static void renderFailure(String displayMsg, String msg,
			HttpServletResponse response) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String scr = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath();
		StringBuffer sb = new StringBuffer();
		sb.append("<div style=text-align:center;padding-top:10em;><img src=");
		sb.append(scr);
		sb.append("/images/sa/expression01.png /> </br>获取").append(displayMsg);
		sb.append("数据出错!</br></br>请联系管理系统开发部&nbsp;020-83092329&nbsp;&nbsp;&nbsp;&nbsp;");
		sb.append("<a href='tel:020-83092329'><img style='top:23em;position:absolute;' id='phImg' src=");
        sb.append(scr);
        sb.append("/images/phone.png /></a></div></br>");
		AjaxUtils.renderText(sb.toString(), response);
	}

	/**
	 * 向页面返回一个错误JSON信息。
	 * 
	 * @param msg
	 */
	public static void renderFailure(WsInterfaceBean wib, String method,
			String msg, HttpServletResponse response) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String scr = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath();
		StringBuffer sb = new StringBuffer();
		sb.append("<div style=text-align:center;padding-top:10em;><img src=");
		sb.append(scr);
		sb.append("/images/sa/expression01.png /> </br>获取");
		sb.append(wib.getiSystem());
		sb.append("数据出错!</br></br>请联系<strong>");
		sb.append(wib.getiSystem()).append("负责人：");
		sb.append(wib.getContacts()).append("</strong>&nbsp;");
		sb.append(wib.getPhone());
		sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
		sb.append("<a href='tel:"
                + wib.getPhone()
                + "'><img style='top:23em;position:absolute;' id='phImg' src=");
        sb.append(scr);
        sb.append("/images/phone.png /></a>");
		sb.append("</br></br>为了负责人快速解决您的问题，</br></br>请将以下信息截图提供给负责人。</br></br><strong>【");
		sb.append(wib.getiName()).append("(").append(wib.getSign());
		sb.append(")</br></br>&nbsp;&nbsp;方法名(").append(method);
		sb.append(")】</strong></div></br>");
	}

	/**
	 * 向页面返回一个成功JSON信息。
	 * 
	 * @param msg
	 */
	public static void renderSuccess(String msg, HttpServletResponse response) {
		AjaxUtils.renderText(
				"{\"status\":\"success\",\"msg\":\"" + msg + "\"}", response);
	}

}
