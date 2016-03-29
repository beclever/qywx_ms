package com.grgbanking.core.service.user;

import com.grgbanking.core.entity.user.WxUser;

/**
 * 
 * 版权所有：2015-GRGBANKING
 * 项目名称：qywx_ms   
 *
 * 类描述：
 * 类名称：com.grgbanking.core.service.user.WxUserService     
 * 创建人：TXH 
 * 创建时间：2015-8-4 上午11:17:17   
 * 修改人：
 * 修改时间：2015-8-4 上午11:17:17   
 * 修改备注：   
 * @version   V1.0
 */
public interface WxUserService {
    public WxUser getUserInfo(String loginName);
}
