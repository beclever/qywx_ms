package com.grgbanking.core.service.workorder;

import java.util.List;

import com.grgbanking.common.exception.WsInterfaceException;
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

/**
 * 工单处理信息 date:2013.10.23 modify2015.06.01
 * 
 * @author yt
 * 
 */

public interface WorkOrderService {

    // public static final String
    // URL="http://10.2.15.7:8080/soc/ws/socWorkFormWsService?wsdl";

    // 5.2.11. 工单管理-获取待处理工单列表接口
    public WsResultPendingWorkFormBean getPendingWorkForm(String loginCode, WsPage wsPage) throws Exception;
    
    //工单管理-获取待处理工单列表接口(非集中式）
    public WsResultPendingWorkFormBean getPending(String loginCode,WsPage wsPage) throws Exception;

    /**
     * 5.2.11. 工单管理-获取待处理工单列表接口
     * 
     * @param loginCode
     *            登录code
     * @param wsPage
     *            分页类
     * @param serialNumber
     *            序列号
     * @param branchName
     *            网点名称
     * @param workFormStatus
     *            工单状态
     * @param taskStatus
     *            任务状态
     * @param engineerUserCode
     *            工程师 userCode
     * @return
     */
    public WsResultPendingWorkFormBean getWorkForm(String loginCode, WsPage wsPage, String serialNumber,
            String branchName, String workFormStatus, String taskStatus, String engineerUserCode, String createUserCode);

    // 5.2.12. 工单管理-点击开始录单接口
    public WsResultFillWorkFormBean beginFillInWorkForm(String loginCode, String equipmentId, String workformId, String poNumber) throws Exception;

    // 单管理-待录入工单设备序列号查询接口
    public WsResultDeviceInfoWorkFormBean queryDeviceInfo(String serialNumber, String workformId) throws Exception;

    /**
     * 5.2.14. 录入工单的出发/到达现场/开始工作/结束工作时间及GPS信息
     * 
     * @param workformId
     *            工单ID
     * @param GPSInfo
     *            GPS信息
     * @param timeType时间类型
     *            1：出发时间，2：到达现场时间，3：开始工作时间，4：结束工作时间，5：接收任务时间，
     * @return
     * @throws Exception 
     */
    public WsResultUploadWorkFormTimeBean doUploadWorkFormTimeGPS(String loginCode, String workformId, WsDeviceLocationBean GPSInfo,
            String timeType, Long time) throws Exception;

    /**
     * 5.2.16. 查询待处理工单数接口
     * 
     * @param loginCode
     * @return
     */

    public WsResultTotalPendingWorkBean queryTotalOfPendingWorkForm(String loginCode);

    /**
     * 5.2.18. 同行人员下拉列表接口
     * 
     * @param workformId
     * @param loginCode
     * @return
     * @throws Exception 
     */
    public WsResultTogetherPersonsBean dropdownTogetherPersons(String workformId, String loginCode) throws Exception;

    // 5.2.23. 工单模块替换查询接口(旧模块)
    public WsResultWorkFormModuleReplaceOldBean queryWorkFormModuleReplaceOld(String loginCode, String serialNumber) throws Exception;

    // 5.2.28. 任务-客户选择项接口
    public WsResultCustomerBean selectCustomerList(String loginName) throws WsInterfaceException;

    // 5.2.29. 任务-城市选择项接口
    public WsResultCityBean selectCityList(String provinceId) throws Exception;

    /**
     * 5.2.17. 录入工单维修任务故障部位模块请求选项接口
     * 
     * @param keyword
     *            查询关键字
     * @param problemRelation
     * @param parentLayer
     *            父节点ID
     * @param type
     *            1:故障部位，2：故障描述，3：故障原因，4：处理方法
     * @return
     * @throws Exception 
     */
    public WsResultProblemPartBean dropdownProblemPartOption(String keyword, String problemRelation,
            Integer parentLayer, String type) throws Exception;

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
	 * @param submitType 提交类型（区分微信和MP提交工单）
	 * @param applyTaskInfo工单任务信息
	 * @param wsPhoneImageBeans工单提交附件信息（需保存）
	 * @param delImages删除工单原来已经有的附件格式：11,233,44
	 * @return
     * @throws Exception 
	 */
	public WsResultFinishWorkFormBean doFinishWorkForm(String workformId,
			String serialNumber, String satisfiedId, String equipmentId,
			String loginCode, String togetherPersonId,
			String togetherPersonName, String atmManager, String reportTel,
			List<WsEquipmentHistoryProblemBean> deviceBequeath,
			List<WsWorkFormFeeBean> workformFee,
			List<WsWorkTaskApplyBean> applyTaskInfo, String submitType, List<WSPhoneImageBean> wsPhoneImageBeans, String delImages) throws Exception;

    /**
     * @throws Exception 
     * 工单移除接口
     * 
     * @Title: doRemoveTaskWorkForm
     * @Description: TODO
     * @param @param loginName 登录名
     * @param @param workFormId 工单ID
     * @param @param workTaskId 任务ID
     * @param @param remark 备注
     * @param @return
     * @return WsResultFinishWorkFormBean
     * @throws
     */
    public WsResultFinishWorkFormBean doRemoveTaskWorkForm(String loginName, String workFormId, String workTaskId,
            String remark) throws Exception;

    /**
     * 工单更改设备序列号
     * 
     * @param loginCode
     * @param workformId
     * @param newEquipmentId
     * @return
     * @throws Exception 
     */
    public WsResultFillWorkFormBean doEditSerialNumberWorkForm(String loginName, String workformId,
            String newEquipmentId) throws Exception;

    /**
     * 获取待审核工单列表接口
     * 
     * @param loginName
     *            登入名
     * @param status
     *            (主任审核/库管员审核 ，多个用英文逗号分开)
     * @param wsPage
     * @param clientType
     *            方式(SA/MP)
     * @return
     */
    public WsResultPendingWorkFormBean getCheckPendingWorkForm(String loginName, String status, WsPage wsPage,
            String clientType);

    /**
     * 查询待审核工单数接口
     * 
     * @param loginName
     * @param status
     *            (主任审核/库管员审核 ，多个用英文逗号分开)
     * @param clientType
     * @return
     */
    public WsResultTotalPendingWorkBean queryTotalOfCheckPendingWorkForm(String loginName, String status,
            String clientType);

    /*
     * 获取工单详情方法(手机端定义，同时获取联系人）
     */
    public WsResultFillWorkFormBean getWorkFormDetail(String loginCode, String equipmentId, String workformId, String poNumber, String detailType);
    /*
     *返回工单联系人   
     */
    public String getWorkTaskContact(String workformId) throws Exception;
    
    //工单详情接口
    public WsResultFillWorkFormBean getWorkFormBean(String loginCode,String workformId);
}
