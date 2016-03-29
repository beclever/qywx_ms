package com.grgbanking.css.load;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 统一管理系统启动时加载项
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-5-29 下午12:53:26
 */
public class ApplicationContextListener implements ServletContextListener{
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent event) {
		//初始化配置文件工具类
		/*PropertiesUtils.init(event.getServletContext());
		if("Online".equalsIgnoreCase(PropertiesUtils.getValue("EVN"))){
			//6.加载短信平台
			//SMSLoad.init(event.getServletContext());
			//处理AVS传送的信息
			AVSLoad.init(event.getServletContext());
		}*/
		//1.加载数据字典表至application
		DataDictionaryLoad.init(event.getServletContext());
		//2.加载权限访问资源
		//ResourceLoad.init(event.getServletContext());
		//3.加载省份，城市 
		//ProvinceLoad.init(event.getServletContext());
		//4.加载市场产品信息反馈
		//MarketProjectLoad.init(event.getServletContext());
		//5.加载维修工单故障信息
		//WorkformRepairPlaceLoad.init(event.getServletContext());
		//处理AOC调度任务
		//AOCLoad.init(event.getServletContext());
		//区镇
		//DowntownLoad.init(event.getServletContext());
		//课程
		//ExamsLoad.init(event.getServletContext());
		//
		//EquipmentModelKeyLoad.init(event.getServletContext());
		//spring context instance
		//ApplicationContextHandle.init(event.getServletContext());
		
		//SparepartStockWS sparepartStockWS = (SparepartStockWS) WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()).getBean("sparepartStockWS");
		//sparepartStockWS.init();
		
	}
	
	
}
