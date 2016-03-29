package com.grgbanking.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

public class SpringContextUtil extends ApplicationObjectSupport {
	Logger logger = LoggerFactory.getLogger(getClass());
	private static ApplicationContext applicationContext = null;

	@Override
	protected void initApplicationContext(ApplicationContext context)
			throws BeansException {
		// TODO Auto-generated method stub
		super.initApplicationContext(context);
		if (SpringContextUtil.applicationContext == null) {
			SpringContextUtil.applicationContext = context;
			logger.info("ApplicationContext配置成功");
		}
	}

	public static ApplicationContext getAppContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		return getAppContext().getBean(name);
	}
}
