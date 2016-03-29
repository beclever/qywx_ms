package com.grgbanking.core.service.workorder;

import java.util.List;

import com.grgbanking.core.entity.workorder.WsWorkTaskBean;
import com.grgbanking.core.entity.ws.WsBorrowInfoBean;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultBean;
import com.grgbanking.core.entity.ws.WsResultPendingWorkFormBean;
import com.grgbanking.core.entity.ws.WsWorkFormBean;

public interface WorkFormEngineService {

  //5.2.11.   工单管理-获取待处理工单列表接口
    public WsResultPendingWorkFormBean getPendingWorkForm(String loginCode,WsPage wsPage) throws Exception;
        
    
    public WsResultBean createWorkform(WsWorkFormBean wsWorkFormBean) throws Exception ;
    /**
     * 创建工单
     * @param wsWorkFormBean
     * @param clientType 方式(SA/MP/QYWX)
     * @return
     */
    public WsResultBean createWorkformInfo(WsWorkFormBean wsWorkFormBean,String clientType) throws Exception;
    
    public WsResultBean cancelWorkform(String poNumber) throws Exception ;
    
    /**
     * 取消工单
     * @param poNumber 工单号
     * @param checkUserCode 操作人员
     * @param clientType 方式(SA/MP)
     * @return
     */
    public WsResultBean cancelWorkformInfo(String poNumber,String checkUserCode,String clientType) throws Exception;
    
    
    
    /**
     * 工单审核
     * @param poNumber 工单号
     * @param buttonType  (pass 通过,back 退回)
     * @param checkUserCode 审核人员
     * @param checkRemark 审核备注
     * @return
     */
    //public WsResultBean checkWorkform(String poNumber,String arriveTime,String workStartDate,String workFinishDate,String buttonType,String checkUserCode,String checkRemark);
    
    //添加任务
    public WsResultBean addWorkTask(String poNumber,List<WsWorkTaskBean> wsWorkTaskBean,String createUserCode) throws Exception ;
    /**
     * 添加任务
     * @param poNumber
     * @param wsWorkTaskBean
     * @param createUserCode操作人员
     * @param clientType 方式(SA/MP)
     * @return
     */
    public WsResultBean addWorkTaskInfo(String poNumber,List<WsWorkTaskBean> wsWorkTaskBean,String createUserCode,String clientType) throws Exception;
    
    //移除任务
    public WsResultBean deleteWorkTask(String poNumber,List<WsWorkTaskBean> wsWorkTaskBean,String createUserCode) throws Exception;
    
    /**
     * 移除任务
     * @param poNumber
     * @param wsWorkTaskBean
     * @param createUserCode 操作人员
     * @param clientType 方式(SA/MP)
     * @return
     */
    public WsResultBean deleteWorkTaskInfo(String poNumber,List<WsWorkTaskBean> wsWorkTaskBean,String createUserCode,String clientType) throws Exception;
    
    /**
     * 移除任务，和上面的全部是没有任务列表这个参数
     * @param poNumber 工单编号
     * @param createUserCode 操作人员
     * @param clientType 方式(SA/MP)
     * @return
     */
    public WsResultBean deleteWorkTaskInfoByPoNumber(String poNumber,String createUserCode,String clientType) throws Exception;
    
    /**
     * 更新工单备件信息
     * @param workFormId
     * @param sptContent
     * @param taskContent
     * @return
     */
    public WsResultBean updateWorkFormSptInfo(String workFormId,String sptContent,String taskContent) throws Exception;
    
    
    /**
     * 修改预约时间
     * @param checkUserCode
     * @param poNumber
     * @param appointmentDate
     * @return
     */
    public WsResultBean doUpdateAppointmentDate(String checkLongName,String workFormId,String appointmentDate) throws Exception;
    
    /**
     * 修改预约时间
     * @param checkLongName
     * @param workFormId
     * @param appointmentDate
     * @param clientType 方式(SA/MP)
     * @return
     */
    public WsResultBean doUpdateAppointmentDateInfo(String checkLongName,String workFormId,String appointmentDate,String clientType) throws Exception;
    
    
    /**
     * 工单审核(主任、库管员审核)
     * @param poNumber 工单号
     * @param buttonType  (pass 通过,back 退回)
     * @param checkUserCode 审核人员
     * @param checkRemark 审核备注
     * @return
     */
    //public WsResultBean checkWorkformStep(String poNumber,String arriveTime,String workStartDate,String workFinishDate,String buttonType,String checkUserCode,String checkRemark);
    
    /**
     * 工单审核(主任、库管员审核)
     * @param poNumber
     * @param arriveTime
     * @param workStartDate
     * @param workFinishDate
     * @param buttonType
     * @param checkUserCode
     * @param checkRemark
     * @param targetStoreId
     * @return 目标仓库
     */
    public WsResultBean checkWorkformStepInStore(String poNumber,String arriveTime,String workStartDate,String workFinishDate,String buttonType,String checkUserCode,String checkRemark,String targetStoreId) throws Exception;
    
    /**
     * 工单审核(主任、库管员审核)
     * @param poNumber 工单号
     * @param arriveTime 到达现场时间
     * @param workStartDate 工作开始时间
     * @param workFinishDate 工作完成时间
     * @param buttonType (pass 通过,back 退回)
     * @param checkUserCode 审核人员
     * @param checkRemark 审核备注
     * @param targetStoreId 目标仓库
     * @param clientType 方式(SA/MP)
     * @return
     */
    public WsResultBean checkWorkformStepInStoreInfo(String poNumber,String arriveTime,String workStartDate,String workFinishDate,String buttonType,String checkUserCode,String checkRemark,String targetStoreId,String clientType) throws Exception;
    
    /**
     * 工单审核(主任、库管员审核)
     * @Title: checkWorkformStepInStoreInfoForCSS
     * @param poNumber
     * @param reportTime
     * @param appointmentDate
     * @param arriveTime
     * @param workStartDate
     * @param workFinishDate
     * @param buttonType
     * @param checkUserCode
     * @param checkRemark
     * @param targetStoreId
     * @param clientType
     * @return
     */
    public WsResultBean checkWorkformStepInStoreInfoForCSS(String poNumber,String reportTime, String appointmentDate, String arriveTime,String workStartDate,String workFinishDate,String buttonType,String checkUserCode,String checkRemark,String targetStoreId,String clientType) throws Exception;
    /**
     * 工单退回
     * @param poNumber 工单号
     * @param checkUserCode 退回人员
     * @param checkRemark 退回备注
     * @return
     */
    public WsResultBean backWorkform(String poNumber,String checkUserCode,String checkRemark);
    /**
     * 工单退回
     * @param poNumber工单号
     * @param checkUserCode 退回人员
     * @param checkRemark 退回备注
     * @param clientType 方式(SA/MP)
     * @return
     */
    public WsResultBean backWorkformInfo(String poNumber,String checkUserCode,String checkRemark,String clientType) throws Exception;
    
    /**
     * 添加工单处理进程记录
     * @param poNumber
     * @param writeUserCode
     * @param writeMessage
     * @return
     */
    public WsResultBean doWriteWorkFormProcess(String poNumber,String writeUserCode,String writeMessage) throws Exception;
    
    /**
     * 添加工单处理进程记录
     * @param poNumber
     * @param writeUserCode 操作人员
     * @param writeMessage 添加的信息
     * @param clientType 方式(SA/MP)
     * @return
     */
    public WsResultBean doWriteWorkFormProcessInfo(String poNumber,String writeUserCode,String writeMessage,String clientType) throws Exception;

    /**
     * 增加同行人员
     * @param FollowEngineerCode 员工编号
     * @return
     */
    
    public WsResultBean addFollowEngineer(String poNumber,List<String> followEngineerCodeList) throws Exception;
    
    /**
     * 检查设备是否可以派单
     * @param serialNumber 序列号
     * @param type 派单类型
     * @return
     */
    
    public WsResultBean checkWorkFormInfo(String serialNumber,String types) throws Exception;

    /**
     * 修改调度建议
     * @param workformId 工单id
     * @param advice 调度建议
     * @return
     */
    
    public WsResultBean updateWorkformAdvice(Integer workformId,String advice);
    
    //AOC工单部分信息特殊修改接口
    public String changeWorkform(String receiveStr);
    
    //AOC工单删除接口
    public String deleteWorkform(String workformId);
    
    //AOC任务移除接口
    public String deleteTask(String taskId) throws Exception;
    
    //非AOC任务移除接口（弃用）
    //public String doDeleteTask(String taskId) throws Exception;
    //新
    public WsResultBean doDeleteTask(String poNumber,String taskId, String createUserCode, String clientType) throws Exception;
    
    /**
    *
    * @param createUserCode
    * @param moduleLevels
     * @param workformId 
     * @param clientType 
    * @return
    * 获取用户借用(不含待归还)备件清单
    */
   public List<WsBorrowInfoBean> findBorrowInfoList(String createUserCode,String[] moduleLevels,Integer workformId,String clientType) throws Exception;
}
