package com.grgbanking.core.service.workorder;

import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.css.bean.work.WorkTaskBean;
import com.grgbanking.webservice.bean.WorkFormMonitorBean;
import com.grgbanking.webservice.bean.WsResultBean;

/**
 * 
 * 版权所有：2016-GRGBANKING
 * 项目名称：css_wwb   
 *
 * 类描述：现场服务接口服务层定义
 * 类名称：com.grgbanking.webservice.service.SocWorkTaskService     
 * 创建人：WSH
 * 创建时间：2016年1月25日 上午9:15:31   
 * 修改人：
 * 修改时间：2016年1月25日 上午9:15:31   
 * 修改备注：   
 * @version   V1.0
 */
public interface WorkTaskService {

    /**
     * 查询现场服务列表
     * @Title: queryWorkTask
     * @param loginName
     * @param wsPage
     * @param clientType
     * @return
     */
    public WsResultBean queryWorkTask(String loginName, WsPage wsPage, WorkTaskBean queryBean, String clientType);
    /**
     * 现场任务-设备的信息
     * @param loginName
     * @param serialNumber
     * @param clientType
     * @return
     */
    public WsResultBean getEquipmentInfo(String loginName, String serialNumber, String clientType);
    
    
    /**
     * 现场任务-未完成列表
     * @param loginName
     * @param serialNumber
     * @param clientType
     * @return
     */
    public WsResultBean getToDoWorkTask(String loginName, String serialNumber, String clientType);
    
    /**
     * 现场任务-历史遗留问题
     * @param loginName
     * @param serialNumber
     * @param clientType
     * @return
     */
    public WsResultBean getEquipmentHistoryProblemList(String loginName, String serialNumber, String clientType);

    /**
     * 现场任务-工程师可选列表
     * @param loginName
     * @param clientType
     * @return
     */
    public WsResultBean getDepartmentUserList(String loginName,String clientType);
    /**
     * 现场任务-创建工单
     * 
     * @param workTaskBean
     * @param needCreateWorkform
     * @param taskIds
     * @param clientType
     * @return
     */
    public WsResultBean doSaveWorktask(String loginName,WorkTaskBean workTaskBean, String needCreateWorkform, String[] taskIds, String clientType);
   
    /**
     * 现场任务-修改任务
     * 
     * @param loginName
     * @param workTaskBean
     * @param needCreateWorkform
     * @param taskIds
     * @param clientType
     * @return
     */
    public WsResultBean doUpdateWorkTask(String loginName, WorkTaskBean workTaskBean, String needCreateWorkform, String[] taskIds, String clientType) throws Exception;

    /**
     * 现场任务-分配任务
     * 
     * @param needCreateWorkform
     *            是否需要创建工单
     * @param engineerId
     *            工程师ID
     * @param taskIds
     *            任务ID
     * @param appointmentTime
     *            预约上门时间
     */
    public WsResultBean doSaveDispatch(String loginName, WorkTaskBean workTaskBean, String needCreateWorkform, String engineerId, String[] taskIds, String appointmentTime,
            String clientType) throws Exception;
    
    
    /**
     * 现场任务-删除任务
     * @param loginName
     * @param taskId
     * @param clientType
     * @return
     */
    public WsResultBean doDeletWorkTask(String loginName, Integer taskId, String clientType);
    /**
     * 已派工单
     * @Title: getWorkformMonitorList
     * @param wsPage
     * @return
     */
    public WsResultBean getWorkformMonitorList(String loginName, WsPage wsPage, WorkFormMonitorBean queryBean);
}
