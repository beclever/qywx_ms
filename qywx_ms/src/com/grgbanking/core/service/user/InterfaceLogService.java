package com.grgbanking.core.service.user;

import com.grgbanking.core.entity.user.InterfaceLog;
/**
 * 
 * 版权所有：2015-GRGBANKING
 * 项目名称：AtmMendService   
 *
 * 类描述：
 * 类名称：com.grgbanking.user.service.InterfaceLogService     
 * 创建人：TXH 
 * 创建时间：2015-5-26 上午9:07:02   
 * 修改人：
 * 修改时间：2015-5-26 上午9:07:02   
 * 修改备注：   
 * @version   V1.0
 */
public interface InterfaceLogService {
    /**
     * 
     * @Title: saveLog
     * @Description: TODO(保存请求接口日志业务接口)
     * @param iLog
     * @return 
     */
    public boolean saveLog(InterfaceLog iLog);
}
