package com.grgbanking.core.service.workorder.impl;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.grgbanking.common.consts.Consts;
import com.grgbanking.common.consts.WsConsts;
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
import com.grgbanking.core.service.WebService;
import com.grgbanking.core.service.workorder.WorkOrderService;
import com.grgbanking.webservice.workorder.SocWorkFormWsService;
@Service(value="workOrderService")
public class WorkOrderServiceImpl extends WebService<SocWorkFormWsService> implements WorkOrderService {

    public WorkOrderServiceImpl() {
        super(SocWorkFormWsService.class, WsConsts.SocWorkformWsServiceUrl);
        // TODO Auto-generated constructor stub
    }

    @Override
    public WsResultPendingWorkFormBean getPendingWorkForm(String loginCode, WsPage wsPage) throws Exception {
        // TODO Auto-generated method stub
        try {
            return this.service.getPendingWorkForm(loginCode, wsPage);//todo:,Consts.CREATE_Type
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public WsResultPendingWorkFormBean getWorkForm(String loginCode, WsPage wsPage, String serialNumber,
            String branchName, String workFormStatus, String taskStatus, String engineerUserCode, String createUserCode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultFillWorkFormBean beginFillInWorkForm(String loginCode, String equipmentId, String workformId, String poNumber) throws Exception {
        // TODO Auto-generated method stub
        try {
            return this.service.beginFillInWorkForm(loginCode, equipmentId, workformId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }
    
    /*
     * 获取工单详情方法(手机端定义，同时获取联系人）
     */
    public WsResultFillWorkFormBean getWorkFormDetail(String loginCode, String equipmentId, String workformId, String poNumber, String detailType)
    {        
        //保修人与接待人
        String resultStr = null;
        
        WsResultFillWorkFormBean wsResultFillWorkFormBean = null;
        try {
            if(detailType == "1"){
                wsResultFillWorkFormBean = this.service.beginFillInWorkForm(loginCode, equipmentId, workformId); 
            }else{
                wsResultFillWorkFormBean = this.service.beginFillInWorkFormForCSS(loginCode, equipmentId, workformId);
            }
            
            if(wsResultFillWorkFormBean != null && wsResultFillWorkFormBean.getWsPendingWorkformDetailBean() != null){
                resultStr = this.service.getWorkTaskContact(workformId);
                JSONArray array = JSON.parseArray(resultStr);
                JSONObject o= null;
                if(array != null){
                    for (int i = 0; i < array.size(); i++) {
                        if(array.get(i) != null){
                            if((o = JSON.parseObject(array.get(i).toString())) != null){
                                if(o.get("taskContactType") != null && o.get("taskContactType").equals("1")){
                                    wsResultFillWorkFormBean.getWsPendingWorkformDetailBean().setRepairsManName(o.getString("contactName"));
                                    wsResultFillWorkFormBean.getWsPendingWorkformDetailBean().setRepairsMoblie(o.getString("mobliePhone"));
                                    wsResultFillWorkFormBean.getWsPendingWorkformDetailBean().setRepairsTelephone(o.getString("telephone"));
                                }else if(o.get("taskContactType") != null && o.get("taskContactType").equals("2")){
                                    wsResultFillWorkFormBean.getWsPendingWorkformDetailBean().setReceiveManName(o.getString("contactName"));
                                    wsResultFillWorkFormBean.getWsPendingWorkformDetailBean().setReceiveMoblie(o.getString("mobliePhone"));
                                    wsResultFillWorkFormBean.getWsPendingWorkformDetailBean().setReceiveTelephone(o.getString("telephone"));
                                }
                            }
                        }
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return wsResultFillWorkFormBean;
    }

    @Override
    public WsResultDeviceInfoWorkFormBean queryDeviceInfo(String serialNumber, String workformId) throws Exception {
        // TODO Auto-generated method stub
        try {
            return this.service.queryDeviceInfo(serialNumber, workformId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public WsResultUploadWorkFormTimeBean doUploadWorkFormTimeGPS(String loginCode,String workformId, WsDeviceLocationBean GPSInfo,
            String timeType, Long time) throws Exception {
        // TODO Auto-generated method stub
        try {
            return this.service.doUploadWorkFormTimeGPS(loginCode, workformId, GPSInfo, timeType, time);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public WsResultTotalPendingWorkBean queryTotalOfPendingWorkForm(String loginCode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultTogetherPersonsBean dropdownTogetherPersons(String workformId, String loginCode) throws Exception {
        // TODO Auto-generated method stub
        try {
            return this.service.dropdownTogetherPersons(workformId, loginCode);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw e;
        }
    }

    @Override
    public WsResultWorkFormModuleReplaceOldBean queryWorkFormModuleReplaceOld(String loginCode, String serialNumber) throws Exception {
        // TODO Auto-generated method stub
        try {
            return this.service.queryWorkFormModuleReplaceOld(loginCode, serialNumber);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public WsResultCustomerBean selectCustomerList(String loginName) throws WsInterfaceException {
        try {
            WsResultCustomerBean resultbean = service.selectCustomerList(loginName);
            return resultbean;
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SocWorkformWsServiceUrl, "selectCustomerList");
        }
    }

    @Override
    public WsResultCityBean selectCityList(String provinceId) throws Exception {
        // TODO Auto-generated method stub
        try {
            return this.service.selectCityList(provinceId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public WsResultProblemPartBean dropdownProblemPartOption(String keyword, String problemRelation,
            Integer parentLayer, String type) throws Exception {
        // TODO Auto-generated method stub
        try {
            return this.service.dropdownProblemPartOption(keyword, problemRelation, parentLayer, type);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    @Override
	public WsResultFinishWorkFormBean doFinishWorkForm(String workformId,
			String serialNumber, String satisfiedId, String equipmentId,
			String loginCode, String togetherPersonId,
			String togetherPersonName, String atmManager, String reportTel,
			List<WsEquipmentHistoryProblemBean> deviceBequeath,
			List<WsWorkFormFeeBean> workformFee,
			List<WsWorkTaskApplyBean> applyTaskInfo, String submitType,
			List<WSPhoneImageBean> wsPhoneImageBeans, String delImages) throws Exception {
		// TODO Auto-generated method stub
		try {
            return this.service.doFinishWorkForm(workformId, serialNumber, satisfiedId, equipmentId, loginCode, togetherPersonId, togetherPersonName, atmManager, reportTel, deviceBequeath, workformFee, applyTaskInfo, submitType,wsPhoneImageBeans,delImages);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
	}

    @Override
    public WsResultFinishWorkFormBean doRemoveTaskWorkForm(String loginName, String workFormId, String workTaskId,
            String remark) throws Exception {
        // TODO Auto-generated method stub
        try {
            return this.service.doRemoveTaskWorkForm(loginName, workFormId, workTaskId, remark);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public WsResultFillWorkFormBean doEditSerialNumberWorkForm(String loginName, String workformId,
            String newEquipmentId) throws Exception {
        // TODO Auto-generated method stub
        try {
            return this.service.doEditSerialNumberWorkForm(loginName, workformId, newEquipmentId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public WsResultPendingWorkFormBean getCheckPendingWorkForm(String loginName, String status, WsPage wsPage,
            String clientType) {
        WsResultPendingWorkFormBean result = this.service.getCheckPendingWorkForm(loginName, status, wsPage, clientType);
        return result;
    }

    @Override
    public WsResultTotalPendingWorkBean queryTotalOfCheckPendingWorkForm(String loginName, String status,
            String clientType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WsResultPendingWorkFormBean getPending(String loginCode, WsPage wsPage) throws Exception {
        try {
            return service.getPending(loginCode, wsPage, Consts.CREATE_TYPE);
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SocWorkformWsServiceUrl, "getPending");
        }
    }
    /*
     *返回工单联系人   
     */
    public String getWorkTaskContact(String workformId) throws Exception{
        try {
            return service.getWorkTaskContact(workformId);
        } catch (Exception e) {
            throw new WsInterfaceException(e, WsConsts.SocWorkformWsServiceUrl, "getPending");
        }
    }

    @Override
    public WsResultFillWorkFormBean getWorkFormBean(String loginCode, String workformId) {
        return service.getWorkFormBean(loginCode, workformId);
    }

}
