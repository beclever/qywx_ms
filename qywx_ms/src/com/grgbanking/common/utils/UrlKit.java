package com.grgbanking.common.utils;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class UrlKit {
    /**
     * 获取url完整链接，包括参数
     * @Title: getRequestURL
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param request
     * @return
     */
    public static String getRequestURL(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String url = "";
        url = request.getServletPath();

        if (StringUtils.isNotEmpty(request.getQueryString())) {
            url = url + "?" + request.getQueryString();
        }

        return url;
    }
}
