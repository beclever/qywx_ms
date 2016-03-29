package com.grgbanking.common.utils;

import com.grgbanking.common.consts.Consts;
import com.grgbanking.core.entity.user.WxUser;

/**
 * 
 * 版权所有：2015-GRGBANKING
 * 项目名称：Qywx_ms   
 *
 * 类描述：当前用户帮助类
 * 类名称：com.grgbanking.common.utils.LoginUser     
 * 创建人：TXH 
 * 创建时间：2015-7-3 下午2:53:38   
 * 修改人：
 * 修改时间：2015-7-3 下午2:53:38   
 * 修改备注：   
 * @version   V1.0
 */
public class LoginUser {
    /**
     * 
     * @Title: getSessionUser
     * @Description: TODO(获取当前会话中的用户)
     * @return
     */
    public static WxUser getSessionUser(){
        return (WxUser) SysContent.getRequest().getSession().getAttribute(Consts.SESSION_USER);
    }
}
