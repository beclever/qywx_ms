package com.grgbanking.common.utils;

public class PathKit {
    public static String getWebPath(){
        return PropUtils.get("domain.url")+SysContent.getRequest().getContextPath();
    }
}
