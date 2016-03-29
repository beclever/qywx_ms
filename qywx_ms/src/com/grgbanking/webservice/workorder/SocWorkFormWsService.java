package com.grgbanking.webservice.workorder;

import java.util.List;

import javax.jws.WebService;

import com.grgbanking.core.entity.equipment.WsDeviceLocationBean;
import com.grgbanking.core.entity.equipment.WsEquipmentHistoryProblemBean;
import com.grgbanking.core.entity.workorder.WSPhoneImageBean;
import com.grgbanking.core.entity.workorder.WsResultCityBean;
import com.grgbanking.core.entity.workorder.WsResultCustomerBean;
import com.grgbanking.core.entity.workorder.WsResultFinishWorkFormBean;
import com.grgbanking.core.entity.workorder.WsResultProblemPartBean;
import com.grgbanking.core.entity.workorder.WsWorkFormFeeBean;
import com.grgbanking.core.entity.workorder.WsWorkTaskApplyBean;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultDeviceInfoWorkFormBean;
import com.grgbanking.core.entity.ws.WsResultFillWorkFormBean;
import com.grgbanking.core.entity.ws.WsResultPendingWorkFormBean;
import com.grgbanking.core.entity.ws.WsResultTogetherPersonsBean;
import com.grgbanking.core.entity.ws.WsResultTotalPendingWorkBean;
import com.grgbanking.core.entity.ws.WsResultUploadWorkFormTimeBean;
import com.grgbanking.core.entity.ws.WsResultWorkFormModuleReplaceOldBean;

@WebService
public interface SocWorkFormWsService {
	
	//5.2.11.	工单管理-获取待处理工单列表接口
	public WsResultPendingWorkFormBean getPendingWorkForm(String loginCode,WsPage wsPage);
	
	//5.2.11.  工单管理-获取待处理工单列表接口
    public WsResultPendingWorkFormBean getPending(String loginCode,WsPage wsPage,String clientType);
	
	//5.2.12.	工单管理-点击开始录单接口
	public WsResultFillWorkFormBean beginFillInWorkForm(String loginCode,String equipmentId,String workformId);

    //5.2.12.   工单管理-点击开始录单接口(企业一期微信调用）
    public WsResultFillWorkFormBean beginFillInWorkFormForCSS(String loginCode,String equipmentId,String workformId);
	
	//5.2.13.	工单管理-待录入工单设备序列号查询接口
	public WsResultDeviceInfoWorkFormBean queryDeviceInfo(String serialNumber,String workformId);
	
	
	/**
	 * 5.2.14.	录入工单的出发/到达现场/开始工作/结束工作时间及GPS信息
	 * @param workformId 工单ID
	 * @param GPSInfo GPS信息
	 * @param timeType时间类型 1：出发时间，2：到达现场时间，3：开始工作时间，4：结束工作时间，5：接收任务时间，
	 * @param delayTime由于sa是采用离线保存的方式，故需要在当前时间基础上减去延迟时间，才是真正上传的时间
	 * @return
	 */
	public WsResultUploadWorkFormTimeBean doUploadWorkFormTimeGPS(String loginCode, String workformId,WsDeviceLocationBean GPSInfo,
			String timeType, Long delayTime);
	
	//5.2.16.	查询待处理工单数接口
	public WsResultTotalPendingWorkBean queryTotalOfPendingWorkForm(String loginCode);
	
	//5.2.18.	同行人员下拉列表接口
	public WsResultTogetherPersonsBean dropdownTogetherPersons(String workformId,String loginCode);
	
	//5.2.23.	工单模块替换查询接口(旧模块)
	public WsResultWorkFormModuleReplaceOldBean queryWorkFormModuleReplaceOld(String loginCode,String serialNumber);
	
	/**
	 * 5.2.19.	录入工单提交接口
	 * @param workformId
	 * @param serialNumber
	 * @param satisfiedId 客户评价满意度
	 * @param equipmentId
	 * @param loginCode
	 * @param togetherPersonId 同行人ID
	 * @param togetherPersonName 同行人名称
	 * @param atmManager 管理员
	 * @param reportTel 报修电话
	 * @param deviceBequeath 设备历史遗留问题
	 * @param workformFee 工单费用
	 * @param applyTaskInfo工单任务信息
	 * @param submitType 提交方式（手机、微信、其它、客户端定义）
	 * @param wsPhoneImageBeans工单提交附件信息（需保存）
	 * @param delImages删除工单原来已经有的附件格式：11,233,44
	 * @return
	 */
	public WsResultFinishWorkFormBean doFinishWorkForm(String workformId, String serialNumber, String satisfiedId, String equipmentId,
			String loginCode, String togetherPersonId, String togetherPersonName, String atmManager, String reportTel,
			List<WsEquipmentHistoryProblemBean> deviceBequeath, List<WsWorkFormFeeBean> workformFee, List<WsWorkTaskApplyBean> applyTaskInfo,
			String submitType, List<WSPhoneImageBean> wsPhoneImageBeans, String delImages);

	//5.2.28.	任务-客户选择项接口
	/**
	 * loginName是一个服务站的工程师，那就只返回这个服务站管理的设备客户，如果loginName是片区经理就返回整个片区的所有设备对应的客户
	 * 返回：根据 登录名-查所管辖的部门-部门所对应的设备-设备关联的客户。
	 */
	public WsResultCustomerBean selectCustomerList(String loginName);
	//5.2.29.	任务-城市选择项接口
	public WsResultCityBean selectCityList(String provinceId);
	/**
	 * 5.2.17.	录入工单维修任务故障部位模块请求选项接口
	 * @param keyword 查询关键字
	 * @param problemRelation
	 * @param parentLayer 父节点ID
	 * @param type 1:故障部位，2：故障描述，3：故障原因，4：处理方法
	 * @return
	 */
	public WsResultProblemPartBean dropdownProblemPartOption(String keyword,String problemRelation,Integer parentLayer,String type);
	
	
	/**
	 * 移除任务
	 * @param loginName
	 * @param workFormId
	 * @param workTaskId
	 * @param remark
	 * @return
	 */
	public WsResultFinishWorkFormBean doRemoveTaskWorkForm(String loginName,String workFormId,String workTaskId,String remark);
	
	
	/**
	 * 工单更改设备序列号
	 * @param loginCode
	 * @param workformId
	 * @param newEquipmentId
	 * @return
	 */
	public WsResultFillWorkFormBean doEditSerialNumberWorkForm(String loginName,String workformId,String newEquipmentId);
	
	
	/**
	 * 获取待审核工单列表接口
	 * @param loginName 登入名
	 * @param status(主任审核/库管员审核 ，多个用英文逗号分开)
	 * @param wsPage 
	 * @param clientType 方式(SA/MP)
	 * @return
	 */
	public WsResultPendingWorkFormBean getCheckPendingWorkForm(String loginName,String status,WsPage wsPage,String clientType);
	
	/**
	 * 查询待审核工单数接口
	 * @param loginName
	 * @param status (主任审核/库管员审核 ，多个用英文逗号分开)
	 * @param clientType
	 * @return
	 */
	public WsResultTotalPendingWorkBean queryTotalOfCheckPendingWorkForm(String loginName,String status,String clientType);

	
	
	  /**
     * 5.2.11.	工单管理-ATM维保界面新增工单查询功能，根据查询条件查询出工单的基本信息以及进度情况
     * @param loginCode 登录code
     * @param wsPage 分页类
     * @param serialNumber 序列号 
     * @param branchName 网点名称 
     * @param workFormStatus 工单状态
     * @param taskStatus 任务状态
     * @param engineerUserCode 工程师 userCode
     * @param createUserCode 创建人工号
     * @return
     */
    public WsResultPendingWorkFormBean getWorkForm(String loginCode, WsPage wsPage, String serialNumber, String branchName,
			String workFormStatus, String taskStatus, String engineerUserCode,String createUserCode);

    
    public String getTaskType();
    
    /**
     * 查询自己派过的工单
     * @param loginCode 登录code
     * @param wsPage 分页类
     * @param serialNumber 序列号 
     * @param branchName 网点名称 
     * @param workFormStatus 工单状态
     * @param taskStatus 任务状态
     * @param engineerUserCode 工程师 userCode
     * @return
     */
    public WsResultPendingWorkFormBean getWorkFormByCreatUser(String loginCode, WsPage wsPage, String serialNumber, String branchName,
			String workFormStatus, String taskStatus, String engineerUserCode);
    
    //工单详情接口
	public WsResultFillWorkFormBean getWorkFormBean(String loginCode,String workformId);
	/*
     *返回工单联系人   
     */
    public String getWorkTaskContact(String workformId);
}
