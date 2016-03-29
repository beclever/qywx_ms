package com.grgbanking.common.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.grgbanking.core.entity.equipment.WsEquipmentHistoryProblemBean;
import com.grgbanking.core.entity.workorder.WsModuleWorkTaskBean;
import com.grgbanking.core.entity.workorder.WsOptionsBean;
import com.grgbanking.core.entity.workorder.WsPendingWorkTaskJsonListBean;
import com.grgbanking.core.entity.workorder.WsPendingWorkformDetailBean;
import com.grgbanking.core.entity.workorder.WsSjTypeDetailsBean;
import com.grgbanking.core.entity.workorder.WsSubmitWorkTaskBean;
import com.grgbanking.core.entity.workorder.WsWorkFormFeeBean;
import com.grgbanking.core.entity.workorder.WsWorkTaskRepairPlaceBean;
import com.grgbanking.core.entity.workorder.WsWorkTaskReplaceBean;

public class WorkOrderTools {
	public void processWorkDetaiInfo(HttpServletRequest request, WsPendingWorkformDetailBean workbean) {
		List<WsPendingWorkTaskJsonListBean> taskJsonList = workbean.getTaskJsonList();

		for (WsPendingWorkTaskJsonListBean taskbean : taskJsonList) {
			// 升级
			if (taskbean.getTaskType().equals("SJ")) {
				String taskId = taskbean.getTaskId();
				String SJfinish = request.getParameter("SJfinish" + taskId);
				String SJtime = request.getParameter("SJtime" + taskId);
				String SJworkRemark = request.getParameter("SJworkRemark" + taskId);

				WsSubmitWorkTaskBean submitbean = getInstallSubmit(SJfinish, SJtime, SJworkRemark);
				taskbean.setInstallAndRemoveDeviceModel(submitbean);
				workTaskBean(taskbean, SJfinish, SJtime, SJworkRemark);
				// 模块
				List<WsWorkTaskReplaceBean> bylist = new ArrayList<WsWorkTaskReplaceBean>();
				String[] oldSerialName = request.getParameterValues(taskId + "oldSerialName");
				String[] oldSerialNumber = request.getParameterValues(taskId + "oldSerialNumber");
				String[] oldMaterialCode = request.getParameterValues(taskId + "oldMaterialCode");
				String[] oldHardwareVersion = request.getParameterValues(taskId + "oldHardwareVersion");
				String[] oldSoftwareVersion = request.getParameterValues(taskId + "oldSoftwareVersion");
				String[] equipmentConfigId = request.getParameterValues(taskId + "equipmentConfigId");

				String[] newSerialName = request.getParameterValues(taskId + "newSerialName");
				String[] newMaterialCode = request.getParameterValues(taskId + "newMaterialCode");
				String[] newSerialNumber = request.getParameterValues(taskId + "newSerialNumber");
				String[] newHardwareVersion = request.getParameterValues(taskId + "newHardwareVersion");
				String[] newSoftwareVersion = request.getParameterValues(taskId + "newSoftwareVersion");
				workspareInfo(taskbean, bylist, oldSerialName, oldSerialNumber, oldMaterialCode, oldHardwareVersion, oldSoftwareVersion,
						equipmentConfigId, newSerialName, newMaterialCode, newSerialNumber, newHardwareVersion, newSoftwareVersion);
				// 零件
				List<WsWorkTaskReplaceBean> elementlist = new ArrayList<WsWorkTaskReplaceBean>();// 零件
				String[] newSerialNames = request.getParameterValues(taskId + "upgradeElementDivnewSerialName");
				String[] oldSerialNames = request.getParameterValues(taskId + "upgradeElementDivoldSerialName");
				String[] nums = request.getParameterValues(taskId + "upgradeElementDivnums");
				String[] newMaterialCodes = request.getParameterValues(taskId + "upgradeElementDivnewMaterialCode");
				String[] oldMaterialCodes = request.getParameterValues(taskId + "upgradeElementDivoldMaterialCode");
				String[] newHardwareVersions = request.getParameterValues(taskId + "upgradeElementDivnewHardwareVersion");
				String[] newSoftwareVersions = request.getParameterValues(taskId + "upgradeElementDivnewSoftwareVersion");
				String[] useMethod = request.getParameterValues(taskId + "upgradeElementDivuseMethod");
				workElements(taskbean, elementlist, newSerialNames, oldSerialNames, nums, newMaterialCodes, oldMaterialCodes,
						newHardwareVersions, newSoftwareVersions, useMethod);

			}

			// 保养
			if (taskbean.getTaskType().equals("BY")) {
				String bYfinish = request.getParameter("BYfinish");
				String bYtime = request.getParameter("BYtime");
				String bYworkRemark = request.getParameter("BYworkRemark");
				workTaskBean(taskbean, bYfinish, bYtime, bYworkRemark);

				WsSubmitWorkTaskBean submitbean = getInstallSubmit(bYfinish, bYtime, bYworkRemark);
				taskbean.setInstallAndRemoveDeviceModel(submitbean);

				List<WsWorkTaskReplaceBean> bylist = new ArrayList<WsWorkTaskReplaceBean>();// 模块
				List<WsWorkTaskReplaceBean> elementlist = new ArrayList<WsWorkTaskReplaceBean>();// 零件
				// 模块
				String[] oldSerialName = request.getParameterValues("goodmaintain1oldSerialName");
				String[] oldSerialNumber = request.getParameterValues("goodmaintain1oldSerialNumber");
				String[] oldMaterialCode = request.getParameterValues("goodmaintain1oldMaterialCode");
				String[] oldHardwareVersion = request.getParameterValues("goodmaintain1oldHardwareVersion");
				String[] oldSoftwareVersion = request.getParameterValues("goodmaintain1oldSoftwareVersion");
				String[] equipmentConfigId = request.getParameterValues("goodmaintain1equipmentConfigId");
				String[] newSerialName = request.getParameterValues("goodmaintain1newSerialName");
				String[] newMaterialCode = request.getParameterValues("goodmaintain1newMaterialCode");
				String[] newSerialNumber = request.getParameterValues("goodmaintain1newSerialNumber");
				String[] newHardwareVersion = request.getParameterValues("goodmaintain1newHardwareVersion");
				String[] newSoftwareVersion = request.getParameterValues("goodmaintain1newSoftwareVersion");
				workspareInfo(taskbean, bylist, oldSerialName, oldSerialNumber, oldMaterialCode, oldHardwareVersion, oldSoftwareVersion,
						equipmentConfigId, newSerialName, newMaterialCode, newSerialNumber, newHardwareVersion, newSoftwareVersion);

				// 零件
				String[] newSerialNames = request.getParameterValues("goodmaintain2newSerialName");
				String[] oldSerialNames = request.getParameterValues("goodmaintain2oldSerialName");
				String[] nums = request.getParameterValues("goodmaintain2nums");
				String[] newMaterialCodes = request.getParameterValues("goodmaintain2newMaterialCode");
				String[] oldMaterialCodes = request.getParameterValues("goodmaintain2oldMaterialCode");
				String[] newHardwareVersions = request.getParameterValues("goodmaintain2newHardwareVersion");
				String[] newSoftwareVersions = request.getParameterValues("goodmaintain2newSoftwareVersion");
				String[] useMethod = request.getParameterValues("goodmaintain2useMethod");
				workElements(taskbean, elementlist, newSerialNames, oldSerialNames, nums, newMaterialCodes, oldMaterialCodes,
						newHardwareVersions, newSoftwareVersions, useMethod);

			}
			// 维修
			if (taskbean.getTaskType().equals("WX")) {
				String wxfinish = request.getParameter("WXfinish");
				String wxtime = request.getParameter("WXtime");
				String wxworkRemark = request.getParameter("WXworkRemark");
				WsSubmitWorkTaskBean submitbean = getInstallSubmit(wxfinish, wxtime, wxworkRemark);
				taskbean.setInstallAndRemoveDeviceModel(submitbean);
				// 模块
				List<WsWorkTaskReplaceBean> bylist = new ArrayList<WsWorkTaskReplaceBean>();
				String[] oldSerialName = request.getParameterValues("maintain2oldSerialName");
				String[] oldSerialNumber = request.getParameterValues("maintain2oldSerialNumber");
				String[] oldMaterialCode = request.getParameterValues("maintain2oldMaterialCode");
				String[] oldHardwareVersion = request.getParameterValues("maintain2oldHardwareVersion");
				String[] oldSoftwareVersion = request.getParameterValues("maintain2oldSoftwareVersion");
				String[] equipmentConfigId = request.getParameterValues("maintain2equipmentConfigId");
				String[] newSerialName = request.getParameterValues("maintain2newSerialName");
				String[] newMaterialCode = request.getParameterValues("maintain2newMaterialCode");
				String[] newSerialNumber = request.getParameterValues("maintain2newSerialNumber");
				String[] newHardwareVersion = request.getParameterValues("maintain2newHardwareVersion");
				String[] newSoftwareVersion = request.getParameterValues("maintain2newSoftwareVersion");

				workspareInfo(taskbean, bylist, oldSerialName, oldSerialNumber, oldMaterialCode, oldHardwareVersion, oldSoftwareVersion,
						equipmentConfigId, newSerialName, newMaterialCode, newSerialNumber, newHardwareVersion, newSoftwareVersion);
				workTaskBean(taskbean, wxfinish, wxtime, wxworkRemark);

				// 故障
				List<WsWorkTaskRepairPlaceBean> faultlist = new ArrayList<WsWorkTaskRepairPlaceBean>();
				String[] problemPartId = request.getParameterValues("maintain1problemPartId");
				String[] problemPartCode = request.getParameterValues("maintain1problemPartCode");
				String[] problemCodeId = request.getParameterValues("maintain1problemCodeId");
				String[] troubleCode = request.getParameterValues("maintain1troubleCode");
				String[] problemReasonId = request.getParameterValues("maintain1problemReasonId");
				String[] troubleReasonCode = request.getParameterValues("maintain1troubleReasonCode");
				String[] problemMethodId = request.getParameterValues("maintain1problemMethodId");
				String[] processCode = request.getParameterValues("maintain1processCode");
				loadFaultInfo(taskbean, faultlist, problemPartId, problemPartCode, problemCodeId, troubleCode, problemReasonId,
						troubleReasonCode, problemMethodId, processCode);

				// 零件
				List<WsWorkTaskReplaceBean> elementlist = new ArrayList<WsWorkTaskReplaceBean>();// 零件
				String[] newSerialNames = request.getParameterValues("maintain3newSerialName");
				String[] oldSerialNames = request.getParameterValues("maintain3oldSerialName");
				String[] nums = request.getParameterValues("maintain3nums");
				String[] newMaterialCodes = request.getParameterValues("maintain3newMaterialCode");
				String[] oldMaterialCodes = request.getParameterValues("maintain3oldMaterialCode");
				String[] newHardwareVersions = request.getParameterValues("maintain3newHardwareVersion");
				String[] newSoftwareVersions = request.getParameterValues("maintain3newSoftwareVersion");
				String[] useMethod = request.getParameterValues("maintain3useMethod");
				workElements(taskbean, elementlist, newSerialNames, oldSerialNames, nums, newMaterialCodes, oldMaterialCodes,
						newHardwareVersions, newSoftwareVersions, useMethod);
			}
			// 账务处理
			if (taskbean.getTaskType().equals("ZW")) {

				String ZWfinish = request.getParameter("ZWfinish");
				String ZWtime = request.getParameter("ZWtime");
				String ZWworkRemark = request.getParameter("ZWworkRemark");
				WsSubmitWorkTaskBean submitbean = getInstallSubmit(ZWfinish, ZWtime, ZWworkRemark);
				taskbean.setInstallAndRemoveDeviceModel(submitbean);

				List<WsWorkTaskReplaceBean> bylist = new ArrayList<WsWorkTaskReplaceBean>();
				String[] oldSerialName = request.getParameterValues("account1oldSerialName");
				String[] oldSerialNumber = request.getParameterValues("account1oldSerialNumber");
				String[] oldMaterialCode = request.getParameterValues("account1oldMaterialCode");
				String[] oldHardwareVersion = request.getParameterValues("account1oldHardwareVersion");
				String[] oldSoftwareVersion = request.getParameterValues("account1oldSoftwareVersion");
				String[] equipmentConfigId = request.getParameterValues("account1equipmentConfigId");
				String[] newSerialName = request.getParameterValues("account1newSerialName");
				String[] newMaterialCode = request.getParameterValues("account1newMaterialCode");
				String[] newSerialNumber = request.getParameterValues("account1newSerialNumber");
				String[] newHardwareVersion = request.getParameterValues("account1newHardwareVersion");
				String[] newSoftwareVersion = request.getParameterValues("account1newSoftwareVersion");
				workspareInfo(taskbean, bylist, oldSerialName, oldSerialNumber, oldMaterialCode, oldHardwareVersion, oldSoftwareVersion,
						equipmentConfigId, newSerialName, newMaterialCode, newSerialNumber, newHardwareVersion, newSoftwareVersion);

				workTaskBean(taskbean, ZWfinish, ZWtime, ZWworkRemark);
				// 零件
				List<WsWorkTaskReplaceBean> elementlist = new ArrayList<WsWorkTaskReplaceBean>();// 零件
				String[] newSerialNames = request.getParameterValues("account2newSerialName");
				String[] oldSerialNames = request.getParameterValues("account2oldSerialName");
				String[] nums = request.getParameterValues("account2nums");
				String[] newMaterialCodes = request.getParameterValues("account2newMaterialCode");
				String[] oldMaterialCodes = request.getParameterValues("account2oldMaterialCode");
				String[] newHardwareVersions = request.getParameterValues("account2newHardwareVersion");
				String[] newSoftwareVersions = request.getParameterValues("account2newSoftwareVersion");
				String[] useMethod = request.getParameterValues("account2useMethod");
				workElements(taskbean, elementlist, newSerialNames, oldSerialNames, nums, newMaterialCodes, oldMaterialCodes,
						newHardwareVersions, newSoftwareVersions, useMethod);

			}
			// 其他
			if (taskbean.getTaskType().equals("QT")) {

				String QTfinish = request.getParameter("QTfinish");
				String QTtime = request.getParameter("QTtime");
				String QTworkRemark = request.getParameter("QTworkRemark");
				WsSubmitWorkTaskBean submitbean = getInstallSubmit(QTfinish, QTtime, QTworkRemark);
				taskbean.setInstallAndRemoveDeviceModel(submitbean);

				List<WsWorkTaskReplaceBean> bylist = new ArrayList<WsWorkTaskReplaceBean>();
				String[] oldSerialName = request.getParameterValues("other1oldSerialName");
				String[] oldSerialNumber = request.getParameterValues("other1oldSerialNumber");
				String[] oldMaterialCode = request.getParameterValues("other1oldMaterialCode");
				String[] oldHardwareVersion = request.getParameterValues("other1oldHardwareVersion");
				String[] oldSoftwareVersion = request.getParameterValues("other1oldSoftwareVersion");
				String[] equipmentConfigId = request.getParameterValues("other1equipmentConfigId");
				String[] newSerialName = request.getParameterValues("other1newSerialName");
				String[] newMaterialCode = request.getParameterValues("other1newMaterialCode");
				String[] newSerialNumber = request.getParameterValues("other1newSerialNumber");
				String[] newHardwareVersion = request.getParameterValues("other1newHardwareVersion");
				String[] newSoftwareVersion = request.getParameterValues("other1newSoftwareVersion");

				workspareInfo(taskbean, bylist, oldSerialName, oldSerialNumber, oldMaterialCode, oldHardwareVersion, oldSoftwareVersion,
						equipmentConfigId, newSerialName, newMaterialCode, newSerialNumber, newHardwareVersion, newSoftwareVersion);
				workTaskBean(taskbean, QTfinish, QTtime, QTworkRemark);

				// 零件
				List<WsWorkTaskReplaceBean> elementlist = new ArrayList<WsWorkTaskReplaceBean>();// 零件
				String[] newSerialNames = request.getParameterValues("other2newSerialName");
				String[] oldSerialNames = request.getParameterValues("other2oldSerialName");
				String[] nums = request.getParameterValues("other2nums");
				String[] newMaterialCodes = request.getParameterValues("other2newMaterialCode");
				String[] oldMaterialCodes = request.getParameterValues("other2oldMaterialCode");
				String[] newHardwareVersions = request.getParameterValues("other2newHardwareVersion");
				String[] newSoftwareVersions = request.getParameterValues("other2newSoftwareVersion");
				String[] useMethod = request.getParameterValues("other2useMethod");
				workElements(taskbean, elementlist, newSerialNames, oldSerialNames, nums, newMaterialCodes, oldMaterialCodes,
						newHardwareVersions, newSoftwareVersions, useMethod);

			}
			// 培训
			if (taskbean.getTaskType().equals("PX")) {

				String PXfinish = request.getParameter("PXfinish");
				String PXtime = request.getParameter("PXtime");
				String PXworkRemark = request.getParameter("PXworkRemark");
				WsSubmitWorkTaskBean submitbean = getInstallSubmit(PXfinish, PXtime, PXworkRemark);
				taskbean.setInstallAndRemoveDeviceModel(submitbean);
				List<WsWorkTaskReplaceBean> bylist = new ArrayList<WsWorkTaskReplaceBean>();
				String[] oldSerialName = request.getParameterValues("client1oldSerialName");
				String[] oldSerialNumber = request.getParameterValues("client1oldSerialNumber");
				String[] oldMaterialCode = request.getParameterValues("client1oldMaterialCode");
				String[] oldHardwareVersion = request.getParameterValues("client1oldHardwareVersion");
				String[] oldSoftwareVersion = request.getParameterValues("client1oldSoftwareVersion");
				String[] equipmentConfigId = request.getParameterValues("client1equipmentConfigId");
				String[] newSerialName = request.getParameterValues("client1newSerialName");
				String[] newMaterialCode = request.getParameterValues("client1newMaterialCode");
				String[] newSerialNumber = request.getParameterValues("client1newSerialNumber");
				String[] newHardwareVersion = request.getParameterValues("client1newHardwareVersion");
				String[] newSoftwareVersion = request.getParameterValues("client1newSoftwareVersion");
				workspareInfo(taskbean, bylist, oldSerialName, oldSerialNumber, oldMaterialCode, oldHardwareVersion, oldSoftwareVersion,
						equipmentConfigId, newSerialName, newMaterialCode, newSerialNumber, newHardwareVersion, newSoftwareVersion);
				workTaskBean(taskbean, PXfinish, PXtime, PXworkRemark);

				// 零件
				List<WsWorkTaskReplaceBean> elementlist = new ArrayList<WsWorkTaskReplaceBean>();
				String[] newSerialNames = request.getParameterValues("client2newSerialName");
				String[] oldSerialNames = request.getParameterValues("client2oldSerialName");
				String[] nums = request.getParameterValues("client2nums");
				String[] newMaterialCodes = request.getParameterValues("client2newMaterialCode");
				String[] oldMaterialCodes = request.getParameterValues("client2oldMaterialCode");
				String[] newHardwareVersions = request.getParameterValues("client2newHardwareVersion");
				String[] newSoftwareVersions = request.getParameterValues("client2newSoftwareVersion");
				String[] useMethod = request.getParameterValues("client2useMethod");
				workElements(taskbean, elementlist, newSerialNames, oldSerialNames, nums, newMaterialCodes, oldMaterialCodes,
						newHardwareVersions, newSoftwareVersions, useMethod);

			}

			// 巡检
			if (taskbean.getTaskType().equals("XJ")) {
			    String taskId = taskbean.getTaskId();
				String XJfinish = request.getParameter("XJfinish" + taskId); // 完成情况
				String XJtime = request.getParameter("XJ" + taskId + "time"); // 耗时
				String XJworkRemark = request.getParameter("XJworkRemark" + taskId); // 工作描述
				workTaskBean(taskbean, XJfinish, XJtime, XJworkRemark);
				/*
				 * WsSubmitWorkTaskBean
				 * submitbean=getInstallSubmit(XJfinish,XJtime,XJworkRemark);
				 * taskbean.setInstallAndRemoveDeviceModel(submitbean);
				 */

				String firstType = request.getParameter("firstType" + taskId);
				String secondType;
				String explain;
				if (firstType.equals("客户要求巡检")) {
					secondType = request.getParameter("secondType" + taskId);
					explain = request.getParameter("explain" + taskId);
				} else {
					secondType = "";
					explain = "";
				}

				WsSubmitWorkTaskBean worksubmitbean = new WsSubmitWorkTaskBean();
				List<WsSjTypeDetailsBean> sjTypeDetailslist = new ArrayList<WsSjTypeDetailsBean>();// 巡检内容

				String softcheck = request.getParameter("softcheck" + taskId);
				String hardcheck = request.getParameter("hardcheck" + taskId);
				String enviromentcheck = request.getParameter("enviromentcheck" + taskId);
				String othercheck = request.getParameter("othercheck" + taskId);
				if (StringUtils.isNotBlank(softcheck)) {
					WsSjTypeDetailsBean softbean = new WsSjTypeDetailsBean();
					softbean.setSjTypeid("软件检查");
					softbean.setSjContext(softcheck);
					sjTypeDetailslist.add(softbean);
				}
				if (StringUtils.isNotBlank(hardcheck)) {
					WsSjTypeDetailsBean hardbean = new WsSjTypeDetailsBean();
					hardbean.setSjTypeid("硬件检查");
					hardbean.setSjContext(hardcheck);
					sjTypeDetailslist.add(hardbean);
				}
				if (StringUtils.isNotBlank(enviromentcheck)) {
					WsSjTypeDetailsBean enviromentbean = new WsSjTypeDetailsBean();
					enviromentbean.setSjTypeid("环境检查");
					enviromentbean.setSjContext(enviromentcheck);
					sjTypeDetailslist.add(enviromentbean);
				}
				if (StringUtils.isNotBlank(othercheck)) {
					WsSjTypeDetailsBean otherbean = new WsSjTypeDetailsBean();
					otherbean.setSjTypeid("其他");
					otherbean.setSjContext(othercheck);
					sjTypeDetailslist.add(otherbean);
				}
				worksubmitbean.setSjTypeDetails(sjTypeDetailslist);
				worksubmitbean.setFirstType(firstType);
				worksubmitbean.setSecondType(secondType);
				worksubmitbean.setExplain(explain);
				worksubmitbean.setWorkIsFinish(XJfinish);
				worksubmitbean.setCostTime(XJtime);
				worksubmitbean.setWorkRemark(XJworkRemark);
				taskbean.setInstallAndRemoveDeviceModel(worksubmitbean);

				// 模块
				List<WsWorkTaskReplaceBean> bylist = new ArrayList<WsWorkTaskReplaceBean>();
				String[] oldSerialName = request.getParameterValues("inspection1" + taskId + "oldSerialName");
				String[] oldSerialNumber = request.getParameterValues("inspection1" + taskId + "oldSerialNumber");
				String[] oldMaterialCode = request.getParameterValues("inspection1" + taskId + "oldMaterialCode");
				String[] oldHardwareVersion = request.getParameterValues("inspection1" + taskId + "oldHardwareVersion");
				String[] oldSoftwareVersion = request.getParameterValues("inspection1" + taskId + "oldSoftwareVersion");
				String[] equipmentConfigId = request.getParameterValues("inspection1" + taskId + "equipmentConfigId");
				String[] newSerialName = request.getParameterValues("inspection1" + taskId + "newSerialName");
				String[] newMaterialCode = request.getParameterValues("inspection1" + taskId + "newMaterialCode");
				String[] newSerialNumber = request.getParameterValues("inspection1" + taskId + "newSerialNumber");
				String[] newHardwareVersion = request.getParameterValues("inspection1" + taskId + "newHardwareVersion");
				String[] newSoftwareVersion = request.getParameterValues("inspection1" + taskId + "newSoftwareVersion");
				workspareInfo(taskbean, bylist, oldSerialName, oldSerialNumber, oldMaterialCode, oldHardwareVersion, oldSoftwareVersion,
						equipmentConfigId, newSerialName, newMaterialCode, newSerialNumber, newHardwareVersion, newSoftwareVersion);

				// 零件
				List<WsWorkTaskReplaceBean> elementlist = new ArrayList<WsWorkTaskReplaceBean>();
				String[] newSerialNames = request.getParameterValues("inspection2" + taskId + "newSerialName");
				String[] oldSerialNames = request.getParameterValues("inspection2" + taskId + "oldSerialName");
				String[] nums = request.getParameterValues("inspection2" + taskId + "nums");
				String[] newMaterialCodes = request.getParameterValues("inspection2" + taskId + "newMaterialCode");
				String[] oldMaterialCodes = request.getParameterValues("inspection2" + taskId + "oldMaterialCode");
				String[] newHardwareVersions = request.getParameterValues("inspection2" + taskId + "newHardwareVersion");
				String[] newSoftwareVersions = request.getParameterValues("inspection2" + taskId + "newSoftwareVersion");
				String[] useMethod = request.getParameterValues("inspection2" + taskId + "useMethod");
				workElements(taskbean, elementlist, newSerialNames, oldSerialNames, nums, newMaterialCodes, oldMaterialCodes,
						newHardwareVersions, newSoftwareVersions, useMethod);
			}
			// 移机辅助
			if (taskbean.getTaskType().equals("YJFZ")) {

				String yJFZfinish = request.getParameter("YJFZfinish");
				String yJFZtime = request.getParameter("YJFZtime");
				String yJFZworkRemark = request.getParameter("YJFZworkRemark");

				// 客户
				String yJFZcustom = request.getParameter("YJFZcustom");
				String[] yjfzarrs = yJFZcustom.split(",");
				String yJFZcustomerNameId = yjfzarrs[0];
				String yJFZcustomerName = yjfzarrs[1];
				String yJFZbranchName = request.getParameter("YJFZbranchName").trim();
				// 安装类型
				String yJFZinstallTypes = request.getParameter("YJFZinstallType");
				String[] installtypes = yJFZinstallTypes.split(",");
				String yJFZinstallTypeId = installtypes[0];
				String yJFZinstallType = installtypes[1];

				// 安装方式
				String yJFZinstallModels = request.getParameter("YJFZinstallModels");
				String[] installModels = yJFZinstallModels.split(",");
				String yJFZinstallModelId = installModels[0];
				String yJFZinstallModel = installModels[1];

				// 省份
				String provinces = request.getParameter("YJFZprovinces");
				String yJFZprovinceId = "";
                String yJFZprovince = "";
                if (provinces != null && !"".equals(provinces)) {
                    String[] proviceinfo = provinces.split(",");
                    if (proviceinfo.length > 1) {
                        yJFZprovinceId = proviceinfo[0];
                        yJFZprovince = proviceinfo[1];
                    }
                }

				// 城市
				String citys = request.getParameter("YJFZcitys");
				String cityId = "";
                String city = "";
                if (citys != null && !"".equals(citys)) {
                    String[] yjfzcity = citys.split(",");
                    if (yjfzcity.length > 1) {
                        cityId = yjfzcity[0];
                        city = yjfzcity[1];
                    }
                }
				
				// 装机属性
				String yJFZinstallPropertys = request.getParameter("YJFZinstallPropertys");
				String[] yJFZinstallPropertyinfo = yJFZinstallPropertys.split(",");
				String yJFZinstallPropertyId = yJFZinstallPropertyinfo[0];
				String yJFZinstallProperty = yJFZinstallPropertyinfo[1];

				// ATM管理员
				//String yJFZATMManager = request.getParameter("YJFZATMManager").trim();
				//String yJFZATMManagerTel = request.getParameter("YJFZATMManagerTel").trim();
				//String yJFZbranchPrincipalTel = request.getParameter("YJFZbranchPrincipalTel").trim();// 网点负责人电话
				// 操作系统
				String YJFZoperationSystems = request.getParameter("YJFZoperationSystems");
				String[] yJFZoperationSystemsInfo = YJFZoperationSystems.split(",");
				String YJFZoperationSystemId = yJFZoperationSystemsInfo[0];
				String YJFZoperationSystem = yJFZoperationSystemsInfo[1];

				String yJFZosVersion = request.getParameter("YJFZosVersion").trim();
				// ATMC版本
				String yJFZATMCNs = request.getParameter("YJFZATMCNs");
				String[] yJFZATMCNInfo = yJFZATMCNs.split(",");
				String yJFZATMCId = yJFZATMCNInfo[0];
				String yJFZATMCName = yJFZATMCNInfo[1];

				String yJFZATMCVersion = request.getParameter("YJFZATMCVersion").trim();
				String yJFZATMCSpVersion = request.getParameter("YJFZATMCSpVersion").trim();

				// 加密方式
				String yJFZencryptModels = request.getParameter("YJFZencryptModels");
				String[] yJFZencryptModelinfo = yJFZencryptModels.split(",");
				String yJFZencryptModelId = yJFZencryptModelinfo[0];
				String yJFZencryptModel = yJFZencryptModelinfo[1];

				String yJFZATMNumber = request.getParameter("YJFZATMNumber").trim();
				String yJFZbankNumber = request.getParameter("YJFZbankNumber").trim();
				String yJFZbankTerminalNumber = request.getParameter("YJFZbankTerminalNumber").trim();
				String yJFZnetProtocol = request.getParameter("YJFZnetProtocol").trim();
				String yJFZLocalIP = request.getParameter("YJFZLocalIP").trim();
				String yJFZpip = request.getParameter("YJFZpip").trim();
				String yJFZgateway = request.getParameter("YJFZgateway");
				String yJFZsubnetMask = request.getParameter("YJFZsubnetMask");

				// 日均交易金额
				String yJFZdailyAverageAmounts = request.getParameter("YJFZdailyAverageAmounts");
				String[] yJFZdailyAverageAmountinfo = yJFZdailyAverageAmounts.split(",");
				String yJFZdailyAverageAmountId = yJFZdailyAverageAmountinfo[0];
				String yJFZdailyAverageAmount = yJFZdailyAverageAmountinfo[1];
				// 日均交易笔数
				String yJFZdailyAverageNums = request.getParameter("YJFZdailyAverageNums");
				String[] yJFZdailyAverageNuminfo = yJFZdailyAverageNums.split(",");
				String yJFZdailyAverageNumId = yJFZdailyAverageNuminfo[0];
				String yJFZdailyAverageNum = yJFZdailyAverageNuminfo[1];
				// 灰尘
				String yJFZdusts = request.getParameter("YJFZdusts");
				String[] yJFZdustinfo = yJFZdusts.split(",");
				String yJFZdustId = yJFZdustinfo[0];
				String yJFZdust = yJFZdustinfo[1];
				// 温度
				String yJFZtemperatures = request.getParameter("YJFZtemperatures");
				String[] yJFZtemperaturesinfo = yJFZtemperatures.split(",");
				String yJFZtemperatureId = yJFZtemperaturesinfo[0];
				String yJFZtemperature = yJFZtemperaturesinfo[1];
				// 湿度
				String yJFZhumiditys = request.getParameter("YJFZhumiditys");
				String[] yJFZhumiditysinfo = yJFZhumiditys.split(",");
				String yJFZhumidityId = yJFZhumiditysinfo[0];
				String yJFZhumidity = yJFZhumiditysinfo[1];
				// 供电
				String yJFZpowerSupplys = request.getParameter("YJFZpowerSupplys");
				String[] yJFZpowerSupplyinfos = yJFZpowerSupplys.split(",");
				String yJFZpowerSupplyId = yJFZpowerSupplyinfos[0];
				String yJFZpowerSupply = yJFZpowerSupplyinfos[1];
				// 防雨
				String yJFZwaterproofs = request.getParameter("YJFZwaterproofs");
				String[] yJFZwaterproofinfo = yJFZwaterproofs.split(",");
				String yJFZwaterproofId = yJFZwaterproofinfo[0];
				String yJFZwaterproof = yJFZwaterproofinfo[1];
				// 日晒
				String yJFZsolarizations = request.getParameter("YJFZsolarizations");
				String[] yJFZsolarizationinfo = yJFZsolarizations.split(",");
				String yJFZsolarizationId = yJFZsolarizationinfo[0];
				String yJFZsolarization = yJFZsolarizationinfo[1];
				// 安装地址
				String yJFZinstallAddress = request.getParameter("YJFZinstallAddress").trim();
				String yJFZreferenceCharge = request.getParameter("YJFZreferenceCharge").trim();
				String yJFZconsultWay = request.getParameter("YJFZconsultWay").trim();
				workTaskBean(taskbean, yJFZfinish, yJFZtime, yJFZworkRemark);

				setOptions(taskbean, yJFZinstallTypeId, yJFZinstallType, yJFZinstallModelId, yJFZinstallModel, yJFZinstallPropertyId,
						yJFZinstallProperty, YJFZoperationSystemId, YJFZoperationSystem, yJFZATMCId, yJFZATMCName, yJFZencryptModelId,
						yJFZencryptModel, yJFZdailyAverageAmountId, yJFZdailyAverageAmount, yJFZdailyAverageNumId, yJFZdailyAverageNum,
						yJFZdustId, yJFZdust, yJFZhumidityId, yJFZhumidity, yJFZtemperatureId, yJFZtemperature, yJFZpowerSupplyId,
						yJFZpowerSupply, yJFZwaterproofId, yJFZwaterproof, yJFZsolarizationId, yJFZsolarization);

				WsSubmitWorkTaskBean submitBean = InstallTypeInfo(yJFZcustomerNameId, yJFZcustomerName, yJFZbranchName, yJFZinstallTypeId,
						yJFZinstallType, yJFZinstallModelId, yJFZinstallModel, yJFZprovinceId, yJFZprovince, cityId, city,
						yJFZinstallPropertyId, yJFZinstallProperty, //yJFZATMManager, yJFZATMManagerTel, yJFZbranchPrincipalTel,
						YJFZoperationSystemId, YJFZoperationSystem, yJFZosVersion, yJFZATMCId, yJFZATMCName, yJFZATMCVersion,
						yJFZATMCSpVersion, yJFZencryptModelId, yJFZencryptModel, yJFZATMNumber, yJFZbankNumber, yJFZbankTerminalNumber,
						yJFZnetProtocol, yJFZLocalIP, yJFZpip, yJFZgateway, yJFZsubnetMask, yJFZdailyAverageAmountId,
						yJFZdailyAverageAmount, yJFZdailyAverageNumId, yJFZdailyAverageNum, yJFZdustId, yJFZdust, yJFZtemperatureId,
						yJFZtemperature, yJFZhumidityId, yJFZhumidity, yJFZpowerSupplyId, yJFZpowerSupply, yJFZwaterproofId,
						yJFZwaterproof, yJFZsolarizationId, yJFZsolarization, yJFZinstallAddress, yJFZreferenceCharge, yJFZconsultWay, "");
				submitBean.setWorkIsFinish(yJFZfinish);
				submitBean.setCostTime(yJFZtime);
				submitBean.setWorkRemark(yJFZworkRemark);
				taskbean.setInstallAndRemoveDeviceModel(submitBean);

			}

			// 移机开通
			if (taskbean.getTaskType().equals("YJKT")) {
				String workIsFinish = request.getParameter("YJKTfinish");
				String costTime = request.getParameter("YJKTtime");
				String workRemark = request.getParameter("YJKTworkRemark");
				workTaskBean(taskbean, workIsFinish, costTime, workRemark);

				// 客户
				String customs = request.getParameter("YJKTcustom");
				String[] YJKTarrs = customs.split(",");
				String customerNameId = YJKTarrs[0];
				String customerName = YJKTarrs[1];
				String branchName = request.getParameter("YJKTbranchName");
				// 安装类型
				String installTypess = request.getParameter("YJKTinstallType");
				String[] installtypes = installTypess.split(",");
				String installTypeId = installtypes[0];
				String installType = installtypes[1];

				// 安装方式
				String installModelss = request.getParameter("YJKTinstallModels");
				String[] installModels = installModelss.split(",");
				String installModelId = installModels[0];
				String installModel = installModels[1];

				// 省份
				String provinces = request.getParameter("YJKTprovinces");
				String provinceId = "";
                String province = "";
                if (provinces != null && !"".equals(provinces)) {
                    String[] proviceinfo = provinces.split(",");
                    if (proviceinfo.length > 1) {
                        provinceId = proviceinfo[0];
                        province = proviceinfo[1];
                    }
                }

				// 城市
				String citys = request.getParameter("YJKTcitys");
				String cityId = "";
                String city = "";
                if (citys != null && !"".equals(citys)) {
                    String[] yjfzcity = citys.split(",");
                    if (yjfzcity.length > 1) {
                        cityId = yjfzcity[0];
                        city = yjfzcity[1];
                    }
                }
				// 装机属性
				String installPropertyss = request.getParameter("YJKTinstallPropertys");
				String[] installPropertyinfo = installPropertyss.split(",");
				String installPropertyId = installPropertyinfo[0];
				String installProperty = installPropertyinfo[1];

				// ATM管理员
				//String ATMManager = request.getParameter("YJKTATMManager");
				//String ATMManagerTel = request.getParameter("YJKTATMManagerTel");
				//String branchPrincipalTel = request.getParameter("YJKTbranchPrincipalTel");// 网点负责人电话
				// 操作系统
				String operationSystemss = request.getParameter("YJKToperationSystems");
				String[] operationSystemsInfo = operationSystemss.split(",");
				String operationSystemId = operationSystemsInfo[0];
				String operationSystem = operationSystemsInfo[1];

				String osVersion = request.getParameter("YJKTosVersion");
				// ATMC版本
				String ATMCNss = request.getParameter("YJKTATMCNs");
				String[] ATMCNInfo = ATMCNss.split(",");
				String ATMCId = ATMCNInfo[0];
				String ATMCName = ATMCNInfo[1];

				String ATMCVersion = request.getParameter("YJKTATMCVersion");
				String ATMCSpVersion = request.getParameter("YJKTATMCSpVersion");

				// 加密方式
				String encryptModelss = request.getParameter("YJKTencryptModels");
				String[] encryptModelinfo = encryptModelss.split(",");
				String encryptModelId = encryptModelinfo[0];
				String encryptModel = encryptModelinfo[1];

				String ATMNumber = request.getParameter("YJKTATMNumber");
				String bankNumber = request.getParameter("YJKTbankNumber");
				String bankTerminalNumber = request.getParameter("YJKTbankTerminalNumber");
				String netProtocol = request.getParameter("YJKTnetProtocol");
				String LocalIP = request.getParameter("YJKTLocalIP");
				String pip = request.getParameter("YJKTpip");
				String gateway = request.getParameter("YJKTgateway");
				String subnetMask = request.getParameter("YJKTsubnetMask");

				// 日均交易金额
				String dailyAverageAmountss = request.getParameter("YJKTdailyAverageAmounts");
				String[] dailyAverageAmountinfo = dailyAverageAmountss.split(",");
				String dailyAverageAmountId = dailyAverageAmountinfo[0];
				String dailyAverageAmount = dailyAverageAmountinfo[1];
				// 日均交易笔数
				String dailyAverageNumss = request.getParameter("YJKTdailyAverageNums");
				String[] dailyAverageNuminfo = dailyAverageNumss.split(",");
				String dailyAverageNumId = dailyAverageNuminfo[0];
				String dailyAverageNum = dailyAverageNuminfo[1];
				// 灰尘
				String dustss = request.getParameter("YJKTdusts");
				String[] dustinfo = dustss.split(",");
				String dustId = dustinfo[0];
				String dust = dustinfo[1];
				// 温度
				String temperaturess = request.getParameter("YJKTtemperatures");
				String[] temperaturesinfo = temperaturess.split(",");
				String temperatureId = temperaturesinfo[0];
				String temperature = temperaturesinfo[1];
				// 湿度
				String humiditys = request.getParameter("YJKThumiditys");
				String[] humiditysinfo = humiditys.split(",");
				String humidityId = humiditysinfo[0];
				String humidity = humiditysinfo[1];
				// 供电
				String powerSupplyss = request.getParameter("YJKTpowerSupplys");
				String[] yJFZpowerSupplyinfos = powerSupplyss.split(",");
				String powerSupplyId = yJFZpowerSupplyinfos[0];
				String powerSupply = yJFZpowerSupplyinfos[1];
				// 防雨
				String waterproofss = request.getParameter("YJKTwaterproofs");
				String[] waterproofinfo = waterproofss.split(",");
				String waterproofId = waterproofinfo[0];
				String waterproof = waterproofinfo[1];
				// 日晒
				String solarizationss = request.getParameter("YJKTsolarizations");
				String[] solarizationinfo = solarizationss.split(",");
				String solarizationId = solarizationinfo[0];
				String solarization = solarizationinfo[1];
				// 安装地址
				String installAddress = request.getParameter("YJKTinstallAddress");
				String referenceCharge = request.getParameter("YJKTreferenceCharge");
				String consultWay = request.getParameter("YJKTconsultWay");

				setOptions(taskbean, installTypeId, installType, installModelId, installModel, installPropertyId, installProperty,
						operationSystemId, operationSystem, ATMCId, ATMCName, encryptModelId, encryptModel, dailyAverageAmountId,
						dailyAverageAmount, dailyAverageNumId, dailyAverageNum, dustId, dust, humidityId, humidity, temperatureId,
						temperature, powerSupplyId, powerSupply, waterproofId, waterproof, solarizationId, solarization);
				WsSubmitWorkTaskBean submitBean = InstallTypeInfo(customerNameId, customerName, branchName, installTypeId, installType,
						installModelId, installModel, provinceId, province, cityId, city, installPropertyId, installProperty, //ATMManager,
						//ATMManagerTel, branchPrincipalTel, 
						operationSystemId, operationSystem, osVersion, ATMCId, ATMCName, ATMCVersion,
						ATMCSpVersion, encryptModelId, encryptModel, ATMNumber, bankNumber, bankTerminalNumber, netProtocol, LocalIP, pip,
						gateway, subnetMask, dailyAverageAmountId, dailyAverageAmount, dailyAverageNumId, dailyAverageNum, dustId, dust,
						temperatureId, temperature, humidityId, humidity, powerSupplyId, powerSupply, waterproofId, waterproof,
						solarizationId, solarization, installAddress, referenceCharge, consultWay, "");

				submitBean.setWorkIsFinish(workIsFinish);
				submitBean.setCostTime(costTime);
				submitBean.setWorkRemark(workRemark);

				taskbean.setInstallAndRemoveDeviceModel(submitBean);
			}
			// 安装辅助
			if (taskbean.getTaskType().equals("AZFZ")) {

				String workIsFinish = request.getParameter("AZFZfinish");
				String costTime = request.getParameter("AZFZtime");
				String workRemark = request.getParameter("AZFZworkRemark");
				workTaskBean(taskbean, workIsFinish, costTime, workRemark);

				// 客户
				String customs = request.getParameter("AZFZcustom");
				String[] YJKTarrs = customs.split(",");
				String customerNameId = YJKTarrs[0];
				String customerName = YJKTarrs[1];
				String branchName = request.getParameter("AZFZbranchName");
				// 安装类型
				String installTypess = request.getParameter("AZFZinstallType");
				String[] installtypes = installTypess.split(",");
				String installTypeId = installtypes[0];
				String installType = installtypes[1];

				// 安装方式
				String installModelss = request.getParameter("AZFZinstallModels");
				String[] installModels = installModelss.split(",");
				String installModelId = installModels[0];
				String installModel = installModels[1];

				// 省份
				String provinces = request.getParameter("AZFZprovinces");
				String provinceId = "";
				String province = "";
				if (provinces != null && !"".equals(provinces)) {
					String[] proviceinfo = provinces.split(",");
					if (proviceinfo.length > 1) {
						provinceId = proviceinfo[0];
						province = proviceinfo[1];
					}
				}
				// 城市
				String citys = request.getParameter("AZFZcitys");
				String cityId = "";
				String city = "";
				if (citys != null && !"".equals(provinces)) {
					String[] yjfzcity = citys.split(",");
					if (yjfzcity.length > 1) {
						cityId = yjfzcity[0];
						city = yjfzcity[1];
					}
				}
				// 装机属性
				String installPropertyss = request.getParameter("AZFZinstallPropertys");
				String[] installPropertyinfo = installPropertyss.split(",");
				String installPropertyId = installPropertyinfo[0];
				String installProperty = installPropertyinfo[1];

				// ATM管理员
				//String ATMManager = request.getParameter("AZFZATMManager");
				//String ATMManagerTel = request.getParameter("AZFZATMManagerTel");
				//String branchPrincipalTel = request.getParameter("AZFZbranchPrincipalTel");// 网点负责人电话
				// 操作系统
				String operationSystemss = request.getParameter("AZFZoperationSystems");
				String[] operationSystemsInfo = operationSystemss.split(",");
				String operationSystemId = operationSystemsInfo[0];
				String operationSystem = operationSystemsInfo[1];

				String osVersion = request.getParameter("AZFZosVersion");
				// ATMC版本
				String ATMCNss = request.getParameter("AZFZATMCNs");
				String[] ATMCNInfo = ATMCNss.split(",");
				String ATMCId = ATMCNInfo[0];
				String ATMCName = ATMCNInfo[1];

				String ATMCVersion = request.getParameter("AZFZATMCVersion");
				String ATMCSpVersion = request.getParameter("AZFZATMCSpVersion");

				// 加密方式
				String encryptModelss = request.getParameter("AZFZencryptModels");
				String[] encryptModelinfo = encryptModelss.split(",");
				String encryptModelId = encryptModelinfo[0];
				String encryptModel = encryptModelinfo[1];

				String ATMNumber = request.getParameter("AZFZATMNumber");
				String bankNumber = request.getParameter("AZFZbankNumber");
				String bankTerminalNumber = request.getParameter("AZFZbankTerminalNumber");
				String netProtocol = request.getParameter("AZFZnetProtocol");
				String LocalIP = request.getParameter("AZFZLocalIP");
				String pip = request.getParameter("AZFZpip");
				String gateway = request.getParameter("AZFZgateway");
				String subnetMask = request.getParameter("AZFZsubnetMask");

				// 日均交易金额
				String dailyAverageAmountss = request.getParameter("AZFZdailyAverageAmounts");
				String[] dailyAverageAmountinfo = dailyAverageAmountss.split(",");
				String dailyAverageAmountId = dailyAverageAmountinfo[0];
				String dailyAverageAmount = dailyAverageAmountinfo[1];
				// 日均交易笔数
				String dailyAverageNumss = request.getParameter("AZFZdailyAverageNums");
				String[] dailyAverageNuminfo = dailyAverageNumss.split(",");
				String dailyAverageNumId = dailyAverageNuminfo[0];
				String dailyAverageNum = dailyAverageNuminfo[1];
				// 灰尘
				String dustss = request.getParameter("AZFZdusts");
				String[] dustinfo = dustss.split(",");
				String dustId = dustinfo[0];
				String dust = dustinfo[1];
				// 温度
				String temperaturess = request.getParameter("AZFZtemperatures");
				String[] temperaturesinfo = temperaturess.split(",");
				String temperatureId = temperaturesinfo[0];
				String temperature = temperaturesinfo[1];
				// 湿度
				String humiditys = request.getParameter("AZFZhumiditys");
				String[] humiditysinfo = humiditys.split(",");
				String humidityId = humiditysinfo[0];
				String humidity = humiditysinfo[1];
				// 供电
				String powerSupplyss = request.getParameter("AZFZpowerSupplys");
				String[] yJFZpowerSupplyinfos = powerSupplyss.split(",");
				String powerSupplyId = yJFZpowerSupplyinfos[0];
				String powerSupply = yJFZpowerSupplyinfos[1];
				// 防雨
				String waterproofss = request.getParameter("AZFZwaterproofs");
				String[] waterproofinfo = waterproofss.split(",");
				String waterproofId = waterproofinfo[0];
				String waterproof = waterproofinfo[1];
				// 日晒
				String solarizationss = request.getParameter("AZFZsolarizations");
				String[] solarizationinfo = solarizationss.split(",");
				String solarizationId = solarizationinfo[0];
				String solarization = solarizationinfo[1];
				// 安装地址
				String installAddress = request.getParameter("AZFZinstallAddress");
				String referenceCharge = request.getParameter("AZFZreferenceCharge");
				String consultWay = request.getParameter("AZFZconsultWay");

				setOptions(taskbean, installTypeId, installType, installModelId, installModel, installPropertyId, installProperty,
						operationSystemId, operationSystem, ATMCId, ATMCName, encryptModelId, encryptModel, dailyAverageAmountId,
						dailyAverageAmount, dailyAverageNumId, dailyAverageNum, dustId, dust, humidityId, humidity, temperatureId,
						temperature, powerSupplyId, powerSupply, waterproofId, waterproof, solarizationId, solarization);

				WsSubmitWorkTaskBean submitBean = InstallTypeInfo(customerNameId, customerName, branchName, installTypeId, installType,
						installModelId, installModel, provinceId, province, cityId, city, installPropertyId, installProperty, //ATMManager,
						//ATMManagerTel, branchPrincipalTel,
						operationSystemId, operationSystem, osVersion, ATMCId, ATMCName, ATMCVersion,
						ATMCSpVersion, encryptModelId, encryptModel, ATMNumber, bankNumber, bankTerminalNumber, netProtocol, LocalIP, pip,
						gateway, subnetMask, dailyAverageAmountId, dailyAverageAmount, dailyAverageNumId, dailyAverageNum, dustId, dust,
						temperatureId, temperature, humidityId, humidity, powerSupplyId, powerSupply, waterproofId, waterproof,
						solarizationId, solarization, installAddress, referenceCharge, consultWay, "");

				submitBean.setWorkIsFinish(workIsFinish);
				submitBean.setCostTime(costTime);
				submitBean.setWorkRemark(workRemark);

				taskbean.setInstallAndRemoveDeviceModel(submitBean);
			}
			// 安装开通
			if (taskbean.getTaskType().equals("AZKT")) {
				String workIsFinish = request.getParameter("AZKTfinish");
				String costTime = request.getParameter("AZKTtime");
				String workRemark = request.getParameter("AZKTworkRemark");
				workTaskBean(taskbean, workIsFinish, costTime, workRemark);

				// 客户
				String customs = request.getParameter("AZKTcustom");
				String[] YJKTarrs = customs.split(",");
				String customerNameId = YJKTarrs[0];
				String customerName = YJKTarrs[1];
				String branchName = request.getParameter("AZKTbranchName");
				// 安装类型
				String installTypess = request.getParameter("AZKTinstallType");
				String[] installtypes = installTypess.split(",");
				String installTypeId = installtypes[0];
				String installType = installtypes[1];

				// 安装方式
				String installModelss = request.getParameter("AZKTinstallModels");
				String[] installModels = installModelss.split(",");
				String installModelId = installModels[0];
				String installModel = installModels[1];

				// 省份
				String provinces = request.getParameter("AZKTprovinces");
				String provinceId = "";
				String province = "";
				if (provinces != null && !"".equals(provinces)) {
					String[] proviceinfo = provinces.split(",");
					if (proviceinfo.length > 1) {
						provinceId = proviceinfo[0];
						province = proviceinfo[1];
					}
				}
				// 城市
				String citys = request.getParameter("AZKTcitys");
				String cityId = "";
				String city = "";
				if (citys != null && !"".equals(provinces)) {
					String[] yjfzcity = citys.split(",");
					if (yjfzcity.length > 1) {
						cityId = yjfzcity[0];
						city = yjfzcity[1];
					}
				}
				// 装机属性
				String installPropertyss = request.getParameter("AZKTinstallPropertys");
				String[] installPropertyinfo = installPropertyss.split(",");
				String installPropertyId = installPropertyinfo[0];
				String installProperty = installPropertyinfo[1];

				// ATM管理员
				//String ATMManager = request.getParameter("AZKTATMManager");
				//String ATMManagerTel = request.getParameter("AZKTATMManagerTel");
				//String branchPrincipalTel = request.getParameter("AZKTbranchPrincipalTel");// 网点负责人电话
				// 操作系统
				String operationSystemss = request.getParameter("AZKToperationSystems");
				String[] operationSystemsInfo = operationSystemss.split(",");
				String operationSystemId = operationSystemsInfo[0];
				String operationSystem = operationSystemsInfo[1];

				String osVersion = request.getParameter("AZKTosVersion");
				// ATMC版本
				String ATMCNss = request.getParameter("AZKTATMCNs");
				String[] ATMCNInfo = ATMCNss.split(",");
				String ATMCId = ATMCNInfo[0];
				String ATMCName = ATMCNInfo[1];

				String ATMCVersion = request.getParameter("AZKTATMCVersion");
				String ATMCSpVersion = request.getParameter("AZKTATMCSpVersion");

				// 加密方式
				String encryptModelss = request.getParameter("AZKTencryptModels");
				String[] encryptModelinfo = encryptModelss.split(",");
				String encryptModelId = encryptModelinfo[0];
				String encryptModel = encryptModelinfo[1];

				String ATMNumber = request.getParameter("AZKTATMNumber");
				String bankNumber = request.getParameter("AZKTbankNumber");
				String bankTerminalNumber = request.getParameter("AZKTbankTerminalNumber");
				String netProtocol = request.getParameter("AZKTnetProtocol");
				String LocalIP = request.getParameter("AZKTLocalIP");
				String pip = request.getParameter("AZKTpip");
				String gateway = request.getParameter("AZKTgateway");
				String subnetMask = request.getParameter("AZKTsubnetMask");

				// 日均交易金额
				String dailyAverageAmountss = request.getParameter("AZKTdailyAverageAmounts");
				String[] dailyAverageAmountinfo = dailyAverageAmountss.split(",");
				String dailyAverageAmountId = dailyAverageAmountinfo[0];
				String dailyAverageAmount = dailyAverageAmountinfo[1];
				// 日均交易笔数
				String dailyAverageNumss = request.getParameter("AZKTdailyAverageNums");
				String[] dailyAverageNuminfo = dailyAverageNumss.split(",");
				String dailyAverageNumId = dailyAverageNuminfo[0];
				String dailyAverageNum = dailyAverageNuminfo[1];
				// 灰尘
				String dustss = request.getParameter("AZKTdusts");
				String[] dustinfo = dustss.split(",");
				String dustId = dustinfo[0];
				String dust = dustinfo[1];
				// 温度
				String temperaturess = request.getParameter("AZKTtemperatures");
				String[] temperaturesinfo = temperaturess.split(",");
				String temperatureId = temperaturesinfo[0];
				String temperature = temperaturesinfo[1];
				// 湿度
				String humiditys = request.getParameter("AZKThumiditys");
				String[] humiditysinfo = humiditys.split(",");
				String humidityId = humiditysinfo[0];
				String humidity = humiditysinfo[1];
				// 供电
				String powerSupplyss = request.getParameter("AZKTpowerSupplys");
				String[] yJFZpowerSupplyinfos = powerSupplyss.split(",");
				String powerSupplyId = yJFZpowerSupplyinfos[0];
				String powerSupply = yJFZpowerSupplyinfos[1];
				// 防雨
				String waterproofss = request.getParameter("AZKTwaterproofs");
				String[] waterproofinfo = waterproofss.split(",");
				String waterproofId = waterproofinfo[0];
				String waterproof = waterproofinfo[1];
				// 日晒
				String solarizationss = request.getParameter("AZKTsolarizations");
				String[] solarizationinfo = solarizationss.split(",");
				String solarizationId = solarizationinfo[0];
				String solarization = solarizationinfo[1];
				// 安装地址
				String installAddress = request.getParameter("AZKTinstallAddress");
				String referenceCharge = request.getParameter("AZKTreferenceCharge");
				String consultWay = request.getParameter("AZKTconsultWay");
				// 设备责任人 20150603新增
				String aZKTequipmentChargeName = request.getParameter("AZKTequipmentChargeName");

				// 选项值
				setOptions(taskbean, installTypeId, installType, installModelId, installModel, installPropertyId, installProperty,
						operationSystemId, operationSystem, ATMCId, ATMCName, encryptModelId, encryptModel, dailyAverageAmountId,
						dailyAverageAmount, dailyAverageNumId, dailyAverageNum, dustId, dust, humidityId, humidity, temperatureId,
						temperature, powerSupplyId, powerSupply, waterproofId, waterproof, solarizationId, solarization);
				// 提交值
				WsSubmitWorkTaskBean submitBean = InstallTypeInfo(customerNameId, customerName, branchName, installTypeId, installType,
						installModelId, installModel, provinceId, province, cityId, city, installPropertyId, installProperty, //ATMManager,
						//ATMManagerTel, branchPrincipalTel, 
						operationSystemId, operationSystem, osVersion, ATMCId, ATMCName, ATMCVersion,
						ATMCSpVersion, encryptModelId, encryptModel, ATMNumber, bankNumber, bankTerminalNumber, netProtocol, LocalIP, pip,
						gateway, subnetMask, dailyAverageAmountId, dailyAverageAmount, dailyAverageNumId, dailyAverageNum, dustId, dust,
						temperatureId, temperature, humidityId, humidity, powerSupplyId, powerSupply, waterproofId, waterproof,
						solarizationId, solarization, installAddress, referenceCharge, consultWay, aZKTequipmentChargeName);
				submitBean.setWorkIsFinish(workIsFinish);
				submitBean.setCostTime(costTime);
				submitBean.setWorkRemark(workRemark);
				taskbean.setInstallAndRemoveDeviceModel(submitBean);
			}
		}

		// 费用的处理
		String[] feeId = request.getParameterValues("feeId");
		String[] feeName = request.getParameterValues("feeName");
		String[] togetherPersonId = request.getParameterValues("togetherPersonId");
		String[] togetherPersonName = request.getParameterValues("togetherPersonName");
		String[] total = request.getParameterValues("total");
		String[] remark = request.getParameterValues("feeremark");

		// 遗留问题
		String[] historyProblemId = request.getParameterValues("historyProblemId");
		String[] problemRemark = request.getParameterValues("problemRemark");
		String[] levelId = request.getParameterValues("levelId");
		String[] dealContent = request.getParameterValues("dealContent");
		String[] problemContent = request.getParameterValues("problemContent");
		String[] status = request.getParameterValues("status");

		workbean.setEquipmentHistoryProblemResultJson(getProblemList(historyProblemId, problemRemark, levelId, dealContent, problemContent,
				status));
		workbean.setWorkFormFeeJson(getWorkFreeList(feeId, feeName, togetherPersonId, togetherPersonName, total, remark));
		// 更新纸质工单
		workbean.setIniPaperId(request.getParameter("paperId").toString());
		workbean.setIniPaperCode(request.getParameter("paperCode").toString());
		workbean.setIntPaperStatus(request.getParameter("paperStatus").toString());
	}

	// 除了安装辅助，移机开通之外
	private WsSubmitWorkTaskBean getInstallSubmit(String workIsFinish, String costTime, String workRemark) {
		WsSubmitWorkTaskBean submitbean = new WsSubmitWorkTaskBean();
		submitbean.setWorkIsFinish(workIsFinish);
		submitbean.setCostTime(costTime);
		submitbean.setWorkRemark(workRemark);
		return submitbean;
	}

	/**
	 * 其他，保养
	 * 
	 * @param taskbean
	 * @param finishinfo
	 * @param times
	 * @param workremark
	 */
	private void workTaskBean(WsPendingWorkTaskJsonListBean taskbean, String finishinfo, String times, String workremark) {
		taskbean.setWorkIsFinish(finishinfo);
		taskbean.setCostTime(times);
		taskbean.setWorkRemark(workremark);
	}

	// 主要模块
	private void workspareInfo(WsPendingWorkTaskJsonListBean taskbean, List<WsWorkTaskReplaceBean> bylist, String[] oldSerialName,
			String[] oldSerialNumber, String[] oldMaterialCode, String[] oldHardwareVersion, String[] oldSoftwareVersion,
			String[] equipmentConfigId, String[] newSerialName, String[] newMaterialCode, String[] newSerialNumber,
			String[] newHardwareVersion, String[] newSoftwareVersion) {
		if (oldSerialName != null) {
			for (int i = 0; i < oldSerialName.length; i++) {
				WsWorkTaskReplaceBean taskreplacebean = new WsWorkTaskReplaceBean();
				taskreplacebean.setOldSerialName(oldSerialName[i]);
				taskreplacebean.setOldSerialNumber(oldSerialNumber[i]);
				taskreplacebean.setOldMaterialCode(oldMaterialCode[i]);
				taskreplacebean.setOldHardwareVersion(oldHardwareVersion[i]);
				taskreplacebean.setOldSoftwareVersion(oldSoftwareVersion[i]);
				taskreplacebean.setEquipmentConfigId(equipmentConfigId[i]);
				taskreplacebean.setNewSerialName(newSerialName[i]);
				taskreplacebean.setNewMaterialCode(newMaterialCode[i]);
				taskreplacebean.setNewSerialNumber(newSerialNumber[i]);
				taskreplacebean.setNewHardwareVersion(newHardwareVersion[i]);
				taskreplacebean.setNewSoftwareVersion(newSoftwareVersion[i]);

				bylist.add(taskreplacebean);
			}
		}

		taskbean.setImportModel(bylist);
	}

	// 零件模块
	private void workElements(WsPendingWorkTaskJsonListBean taskbean, List<WsWorkTaskReplaceBean> elementlist, String[] newSerialNames,
			String[] oldSerialNames, String[] nums, String[] newMaterialCode, String[] oldMaterialCode, String[] newHardwareVersions,
			String[] newSoftwareVersions, String[] useMethod) {
		if (newSerialNames != null) {
			for (int i = 0; i < newSerialNames.length; i++) {
				WsWorkTaskReplaceBean elementbean = new WsWorkTaskReplaceBean();
				elementbean.setNewSerialName(newSerialNames[i]);
				if (newMaterialCode != null) {
					elementbean.setNewMaterialCode(newMaterialCode[i]);
				}
				elementbean.setOldSerialName(oldSerialNames[i]);

				if (oldMaterialCode != null) {
					elementbean.setOldMaterialCode(oldMaterialCode[i]);
				}
				elementbean.setQuantity(nums[i]);
				if (newHardwareVersions != null) {
					elementbean.setNewHardwareVersion(newHardwareVersions[i]);
				}

				if (newHardwareVersions != null) {
					elementbean.setNewSoftwareVersion(newSoftwareVersions[i]);
				}
				elementlist.add(elementbean);
				elementbean.setUseMethod(useMethod[i]);
			}
		}
		taskbean.setSubModel(elementlist);
	}

	// 故障模块
	private void loadFaultInfo(WsPendingWorkTaskJsonListBean taskbean, List<WsWorkTaskRepairPlaceBean> faultlist, String[] problemPartId,
			String[] problemPartCode, String[] problemCodeId, String[] troubleCode, String[] problemReasonId, String[] troubleReasonCode,
			String[] problemMethodId, String[] processCode) {
		if (problemPartId != null) {
			for (int i = 0; i < problemPartId.length; i++) {
				WsWorkTaskRepairPlaceBean faultbean = new WsWorkTaskRepairPlaceBean();
				faultbean.setProblemPartId(problemPartId[i]);
				faultbean.setProblemPartCode(problemPartCode[i]);
				faultbean.setProblemCodeId(problemCodeId[i]);
				faultbean.setTroubleCode(troubleCode[i]);
				faultbean.setProblemReasonId(problemReasonId[i]);
				faultbean.setTroubleReasonCode(troubleReasonCode[i]);
				faultbean.setProblemMethodId(problemMethodId[i]);
				faultbean.setProcessCode(processCode[i]);
				faultlist.add(faultbean);
			}
		}
		taskbean.setDeviceHitch(faultlist);
	}

	/**
	 * 移机辅助，安装辅助，安装开通，移机开通
	 * @param yJFZcustomerNameId
	 * @param yJFZcustomerName
	 * @param yJFZbranchName
	 * @param yJFZinstallTypeId
	 * @param yJFZinstallType
	 * @param yJFZinstallModelId
	 * @param yJFZinstallModel
	 * @param yJFZprovinceId
	 * @param yJFZprovince
	 * @param cityId
	 * @param city
	 * @param yJFZinstallPropertyId
	 * @param yJFZinstallProperty
	 * @param yJFZATMManager
	 * @param yJFZATMManagerTel
	 * @param yJFZbranchPrincipalTel
	 * @param YJFZoperationSystemId
	 * @param YJFZoperationSystem
	 * @param yJFZosVersion
	 * @param yJFZATMCId
	 * @param yJFZATMCName
	 * @param yJFZATMCVersion
	 * @param yJFZATMCSpVersion
	 * @param yJFZencryptModelId
	 * @param yJFZencryptModel
	 * @param yJFZATMNumber
	 * @param yJFZbankNumber
	 * @param yJFZbankTerminalNumber
	 * @param yJFZnetProtocol
	 * @param yJFZLocalIP
	 * @param yJFZpip
	 * @param yJFZgateway
	 * @param yJFZsubnetMask
	 * @param yJFZdailyAverageAmountId
	 * @param yJFZdailyAverageAmount
	 * @param yJFZdailyAverageNumId
	 * @param yJFZdailyAverageNum
	 * @param yJFZdustId
	 * @param yJFZdust
	 * @param yJFZtemperatureId
	 * @param yJFZtemperature
	 * @param yJFZhumidityId
	 * @param yJFZhumidity
	 * @param yJFZpowerSupplyId
	 * @param yJFZpowerSupply
	 * @param yJFZwaterproofId
	 * @param yJFZwaterproof
	 * @param yJFZsolarizationId
	 * @param yJFZsolarization
	 * @param yJFZinstallAddress
	 * @param yJFZreferenceCharge
	 * @param yJFZconsultWay
	 * @param aZKTequipmentChargeName 安装开通专用字段-设备责任人
	 * @return
	 */
	private WsSubmitWorkTaskBean InstallTypeInfo(String yJFZcustomerNameId, String yJFZcustomerName, String yJFZbranchName,
			String yJFZinstallTypeId, String yJFZinstallType, String yJFZinstallModelId, String yJFZinstallModel, String yJFZprovinceId,
			String yJFZprovince, String cityId, String city, String yJFZinstallPropertyId, String yJFZinstallProperty,
			String YJFZoperationSystemId,
			String YJFZoperationSystem, String yJFZosVersion, String yJFZATMCId, String yJFZATMCName, String yJFZATMCVersion,
			String yJFZATMCSpVersion, String yJFZencryptModelId, String yJFZencryptModel, String yJFZATMNumber, String yJFZbankNumber,
			String yJFZbankTerminalNumber, String yJFZnetProtocol, String yJFZLocalIP, String yJFZpip, String yJFZgateway,
			String yJFZsubnetMask, String yJFZdailyAverageAmountId, String yJFZdailyAverageAmount, String yJFZdailyAverageNumId,
			String yJFZdailyAverageNum, String yJFZdustId, String yJFZdust, String yJFZtemperatureId, String yJFZtemperature,
			String yJFZhumidityId, String yJFZhumidity, String yJFZpowerSupplyId, String yJFZpowerSupply, String yJFZwaterproofId,
			String yJFZwaterproof, String yJFZsolarizationId, String yJFZsolarization, String yJFZinstallAddress,
			String yJFZreferenceCharge, String yJFZconsultWay, String aZKTequipmentChargeName) {
		WsSubmitWorkTaskBean submitBean = new WsSubmitWorkTaskBean();
		submitBean.setCustomerNameId(yJFZcustomerNameId);
		submitBean.setCustomerName(yJFZcustomerName);
		submitBean.setInstallTypeId(yJFZinstallTypeId);
		submitBean.setInstallType(yJFZinstallType);
		submitBean.setInstallModelId(yJFZinstallModelId);
		submitBean.setInstallModel(yJFZinstallModel);
		submitBean.setBranchName(yJFZbranchName);
		submitBean.setInstallPropertyId(yJFZinstallPropertyId);
		submitBean.setInstallProperty(yJFZinstallProperty);
		/*submitBean.setATMManager(yJFZATMManager);
		submitBean.setATMManagerTel(yJFZATMManagerTel);
		submitBean.setBranchPrincipalTel(yJFZbranchPrincipalTel);*/
		submitBean.setOperationSystemId(YJFZoperationSystemId);
		submitBean.setOperationSystem(YJFZoperationSystem);
		submitBean.setOsVersion(yJFZosVersion);
		submitBean.setATMCId(yJFZATMCId);
		submitBean.setATMCName(yJFZATMCName);
		submitBean.setATMCVersion(yJFZATMCVersion);
		submitBean.setATMCSpVersion(yJFZATMCSpVersion);
		submitBean.setEncryptModelId(yJFZencryptModelId);
		submitBean.setEncryptModel(yJFZencryptModel);
		submitBean.setATMNumber(yJFZATMNumber);
		submitBean.setBankNumber(yJFZbankNumber);
		submitBean.setBankTerminalNumber(yJFZbankTerminalNumber);
		submitBean.setNetProtocol(yJFZnetProtocol);
		submitBean.setLocalIP(yJFZLocalIP);
		submitBean.setPip(yJFZpip);
		submitBean.setGateway(yJFZgateway);
		submitBean.setSubnetMask(yJFZsubnetMask);
		submitBean.setDailyAverageAmountId(yJFZdailyAverageAmountId);
		submitBean.setDailyAverageAmount(yJFZdailyAverageAmount);
		submitBean.setDailyAverageNumId(yJFZdailyAverageNumId);
		submitBean.setDailyAverageAmount(yJFZdailyAverageNum);
		submitBean.setDust(yJFZdust);
		submitBean.setDustId(yJFZdustId);
		submitBean.setTemperatureId(yJFZtemperatureId);
		submitBean.setTemperature(yJFZtemperature);
		submitBean.setHumidityId(yJFZhumidityId);
		submitBean.setHumidity(yJFZhumidity);
		submitBean.setPowerSupplyId(yJFZpowerSupplyId);
		submitBean.setPowerSupply(yJFZpowerSupply);
		submitBean.setWaterproofId(yJFZwaterproofId);
		submitBean.setWaterproof(yJFZwaterproof);
		submitBean.setSolarizationId(yJFZsolarizationId);
		submitBean.setSolarization(yJFZsolarization);
		submitBean.setInstallAddress(yJFZinstallAddress);
		submitBean.setConsultWay(yJFZconsultWay);
		submitBean.setReferenceCharge(yJFZreferenceCharge);
		submitBean.setProvinceId(yJFZprovinceId);
		submitBean.setProvince(yJFZprovince);
		submitBean.setCityId(cityId);
		submitBean.setCity(city);
		submitBean.setEquipmentChargeName(aZKTequipmentChargeName);
		return submitBean;
	}

	// 选项值
	private void setOptions(WsPendingWorkTaskJsonListBean taskbean, String installTypeId, String installType, String installModelId,
			String installModel, String installPropertyId, String installProperty, String operationSystemId, String operationSystem,
			String ATMCId, String ATMCName, String encryptModelId, String encryptModel, String dailyAverageAmountId,
			String dailyAverageAmount, String dailyAverageNumId, String dailyAverageNum, String dustId, String dust, String humidityId,
			String humidity, String temperatureId, String temperature, String powerSupplyId, String powerSupply, String waterproofId,
			String waterproof, String solarizationId, String solarization) {
		WsModuleWorkTaskBean taskoptions = taskbean.getTaskAttributeOption();

		WsOptionsBean intallTypes = taskoptions.getIntallType();
		intallTypes.setDefaultId(installTypeId);
		intallTypes.setDefaultName(installType);
		taskoptions.setIntallType(intallTypes);

		WsOptionsBean installModels = taskoptions.getInstallModel();
		installModels.setDefaultId(installModelId);
		installModels.setDefaultName(installModel);
		taskoptions.setInstallModel(installModels);

		WsOptionsBean installPropertys = taskoptions.getInstallProperty();
		installPropertys.setDefaultId(installPropertyId);
		installPropertys.setDefaultName(installProperty);
		taskoptions.setInstallProperty(installPropertys);

		WsOptionsBean operationSystems = taskoptions.getOperationSystem();
		operationSystems.setDefaultId(operationSystemId);
		operationSystems.setDefaultName(operationSystem);
		taskoptions.setOperationSystem(operationSystems);

		WsOptionsBean ATMCNames = taskoptions.getATMCName();
		ATMCNames.setDefaultId(ATMCId);
		ATMCNames.setDefaultName(ATMCName);
		taskoptions.setATMCName(ATMCNames);

		WsOptionsBean encryptModes = taskoptions.getEncryptMode();
		encryptModes.setDefaultId(encryptModelId);
		encryptModes.setDefaultName(encryptModel);
		taskoptions.setEncryptMode(encryptModes);

		// WsOptionsBean dayAerageSums = taskoptions.getEncryptMode();
		// up is the previous code,the blow is modefy by zt20150326
		WsOptionsBean dayAerageSums = taskoptions.getDayAerageSum();
		dayAerageSums.setDefaultId(dailyAverageAmountId);
		dayAerageSums.setDefaultName(dailyAverageAmount);
		taskoptions.setDayAerageSum(dayAerageSums);

		WsOptionsBean dayAerageTimes = taskoptions.getDayAerageTime();
		dayAerageTimes.setDefaultId(dailyAverageNumId);
		dayAerageTimes.setDefaultName(dailyAverageNum);
		taskoptions.setDayAerageTime(dayAerageTimes);

		WsOptionsBean environmentDust = taskoptions.getEnvironmentDust();
		environmentDust.setDefaultId(dustId);
		environmentDust.setDefaultName(dust);
		taskoptions.setEnvironmentDust(environmentDust);

		WsOptionsBean environmentHumidity = taskoptions.getEnvironmentHumidity();
		environmentHumidity.setDefaultId(humidityId);
		environmentHumidity.setDefaultName(humidity);
		taskoptions.setEnvironmentHumidity(environmentHumidity);

		WsOptionsBean environmentTemperature = taskoptions.getEnvironmentTemperature();
		environmentTemperature.setDefaultId(temperatureId);
		environmentTemperature.setDefaultName(temperature);
		taskoptions.setEnvironmentTemperature(environmentTemperature);

		WsOptionsBean environmentPowerSupply = taskoptions.getEnvironmentPowerSupply();
		environmentPowerSupply.setDefaultId(powerSupplyId);
		environmentPowerSupply.setDefaultName(powerSupply);
		taskoptions.setEnvironmentPowerSupply(environmentPowerSupply);

		WsOptionsBean environmentRainDefence = taskoptions.getEnvironmentRainDefence();
		environmentRainDefence.setDefaultId(waterproofId);
		environmentRainDefence.setDefaultName(waterproof);
		taskoptions.setEnvironmentRainDefence(environmentRainDefence);

		WsOptionsBean environmentSunshine = taskoptions.getEnvironmentSunshine();
		environmentSunshine.setDefaultId(solarizationId);
		environmentSunshine.setDefaultName(solarization);
		taskoptions.setEnvironmentSunshine(environmentSunshine);

		taskbean.setTaskAttributeOption(taskoptions);

	}

	/**
	 * 费用（工单任务）
	 * 
	 * @param feeId
	 * @param feeName
	 * @param togetherPersonId
	 * @param togetherPersonName
	 * @param total
	 * @param remark
	 * @return
	 */
	private List<WsWorkFormFeeBean> getWorkFreeList(String[] feeId, String[] feeName, String[] togetherPersonId,
			String[] togetherPersonName, String[] total, String[] remark) {
		List<WsWorkFormFeeBean> lists = new ArrayList<WsWorkFormFeeBean>();

		if (feeId != null) {
			for (int i = 0; i < feeId.length; i++) {
				WsWorkFormFeeBean workfeeBean = new WsWorkFormFeeBean();
				workfeeBean.setFeeId(feeId[i]);
				workfeeBean.setFeeName(feeName[i]);
				workfeeBean.setTogetherPersonId(togetherPersonId[i]);
				workfeeBean.setTogetherPersonName(togetherPersonName[i]);
				workfeeBean.setTotal(total[i]);
				if (remark != null && StringUtils.isNotBlank(remark[i].trim())) {
					workfeeBean.setRemark(remark[i] == null ? "" : remark[i]);
				}
				lists.add(workfeeBean);
			}

		}
		return lists;
	}

	/**
	 * 遗留问题（工单任务）
	 * 
	 * @param historyProblemId
	 * @param problemRemark
	 * @param levelId
	 * @param problemContent
	 * @param status
	 * @return
	 */
	private List<WsEquipmentHistoryProblemBean> getProblemList(String[] historyProblemId, String[] problemRemark, String[] levelId,
			String[] dealContent, String[] problemContent, String[] status) {
		List<WsEquipmentHistoryProblemBean> problemlist = new ArrayList<WsEquipmentHistoryProblemBean>();
		/*
		 * if (levelId != null) { for (int i = 0; i < levelId.length; i++) {
		 * WsEquipmentHistoryProblemBean problemBean = new
		 * WsEquipmentHistoryProblemBean(); problemBean.setLevelId(levelId[i]);
		 * problemBean.setProblemContent(problemContent[i]);
		 * problemBean.setProblemRemark(problemRemark[i]);
		 * problemBean.setStatus(status[i]); problemlist.add(problemBean); } }
		 */
		if (historyProblemId != null) { // 更新操作
			for (int i = 0; i < historyProblemId.length; i++) {
				WsEquipmentHistoryProblemBean problemBean = new WsEquipmentHistoryProblemBean();
				problemBean.setHistoryProblemId(historyProblemId[i]);
				problemBean.setLevelId(levelId[i]);
				problemBean.setProblemContent(problemContent[i]);
				problemBean.setProblemRemark(problemRemark[i]);
				problemBean.setStatus(status[i]);
				if (dealContent != null && dealContent.length > i) {
					problemBean.setDealContent(dealContent[i]);
				}
				problemlist.add(problemBean);
			}
		}

		if (levelId != null) {
			int startLen = 0;
			if (historyProblemId != null)
				startLen = historyProblemId.length;
			// 保存操作
			for (int i = startLen; i < levelId.length; i++) {
				WsEquipmentHistoryProblemBean problemBean = new WsEquipmentHistoryProblemBean();
				problemBean.setLevelId(levelId[i]);
				problemBean.setProblemContent(problemContent[i]);
				problemBean.setProblemRemark(problemRemark[i]);
				problemBean.setStatus(status[i]);
				if (dealContent != null && dealContent.length > i) {
					problemBean.setDealContent(dealContent[i]);
				}
				problemlist.add(problemBean);
			}
		}
		return problemlist;

	}
}
