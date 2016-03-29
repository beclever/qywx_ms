package com.grgbanking.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.grgbanking.common.utils.SysContent;

/**
 * 往SysContent工具类set Request
 * 
 * @author Administrator
 * 
 */
public class GetContentFilter implements Filter {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void destroy() {
		logger.info("GetContentFilter 过滤器销毁");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain arg2) throws IOException, ServletException {
		SysContent.setRequest((HttpServletRequest) request);
		// logger.debug("注入request成功!");
		arg2.doFilter(request, response);// 移交给下一个filter
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		logger.info("GetContentFilter 过滤器初始化");
	}

}
