package com.grgbanking.core.dao;

import com.grgbanking.core.entity.user.InterfaceLog;
/**
 * 
 * 版权所有：2015-GRGBANKING
 * 项目名称：AtmMendService   
 *
 * 类描述：
 * 类名称：com.grgbanking.user.dao.InterfaceLogDao     
 * 创建人：TXH 
 * 创建时间：2015-5-26 上午8:58:31   
 * 修改人：
 * 修改时间：2015-5-26 上午8:58:31   
 * 修改备注：   
 * @version   V1.0
 */
public interface InterfaceLogDao {
    /**
     * 
     * @Title: saveLog
     * @Description: TODO(保存接口请求日志详细)
     * @param iLog
     * @return
     */
    public void saveLog(InterfaceLog iLog);
}
