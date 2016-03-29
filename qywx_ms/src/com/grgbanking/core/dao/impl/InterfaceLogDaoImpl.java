package com.grgbanking.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.grgbanking.core.dao.InterfaceLogDao;
import com.grgbanking.core.entity.user.InterfaceLog;
import com.grgbanking.core.service.BaseService;
/**
 * 
 * 版权所有：2015-GRGBANKING
 * 项目名称：AtmMendService   
 *
 * 类描述：
 * 类名称：com.grgbanking.user.dao.impl.InterfaceLogDaoImpl     
 * 创建人：TXH 
 * 创建时间：2015-5-26 上午9:00:10   
 * 修改人：
 * 修改时间：2015-5-26 上午9:00:10   
 * 修改备注：   
 * @version   V1.0
 */
@Repository(value="interfaceLogDao")
public class InterfaceLogDaoImpl extends BaseService<InterfaceLog, Long> implements InterfaceLogDao {
    
    /**
     * 
     * @see com.grgbanking.user.dao.InterfaceLogDao#saveLog(com.grgbanking.user.bean.InterfaceLog)
     */
    @Override
    public void saveLog(InterfaceLog iLog) {
        supperHibernateDao.saveOrUpdate(iLog);
    }

}
