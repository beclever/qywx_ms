package com.grgbanking.core.service.user.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grgbanking.core.dao.InterfaceLogDao;
import com.grgbanking.core.entity.user.InterfaceLog;
import com.grgbanking.core.service.user.InterfaceLogService;
/**
 * 
 * 版权所有：2015-GRGBANKING
 * 项目名称：AtmMendService   
 *
 * 类描述：
 * 类名称：com.grgbanking.user.service.impl.InterfaceLogServiceImpl     
 * 创建人：TXH 
 * 创建时间：2015-5-26 上午9:08:29   
 * 修改人：
 * 修改时间：2015-5-26 上午9:08:29   
 * 修改备注：   
 * @version   V1.0
 */
@Service(value="interfaceLogService")
public class InterfaceLogServiceImpl implements InterfaceLogService {
    @Resource(name="interfaceLogDao")
    private InterfaceLogDao interfaceLogDao;
    
    /**
     * 
     * @see com.grgbanking.user.service.InterfaceLogService#saveLog(com.grgbanking.user.bean.InterfaceLog)
     */
    @Override
    public boolean saveLog(InterfaceLog iLog) {
        // TODO Auto-generated method stub
        iLog.setCreateTime(new Date());
        this.interfaceLogDao.saveLog(iLog);
        return true;
    }

}
