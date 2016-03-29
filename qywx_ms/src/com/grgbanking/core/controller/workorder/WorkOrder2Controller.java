package com.grgbanking.core.controller.workorder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.grgbanking.common.consts.Consts;
import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.common.utils.DateUtil;
import com.grgbanking.common.utils.LoginUser;
import com.grgbanking.common.utils.NumberUtils;
import com.grgbanking.common.utils.PropUtils;
import com.grgbanking.common.utils.Wechat;
import com.grgbanking.common.utils.WechatAPIUtil;
import com.grgbanking.common.utils.WorkFormFileUtil;
import com.grgbanking.common.utils.WorkOrderTools;
import com.grgbanking.core.controller.BaseController;
import com.grgbanking.core.entity.ResultJson;
import com.grgbanking.core.entity.common.AttachmentBean;
import com.grgbanking.core.entity.equipment.CssPhoneImageBean;
import com.grgbanking.core.entity.equipment.WsEquipmentImageBean;
import com.grgbanking.core.entity.user.Order;
import com.grgbanking.core.entity.user.WxUser;
import com.grgbanking.core.entity.workorder.WsPendingWorkTaskJsonListBean;
import com.grgbanking.core.entity.workorder.WsPendingWorkformDetailBean;
import com.grgbanking.core.entity.workorder.WsResultCustomerBean;
import com.grgbanking.core.entity.workorder.WsResultFinishWorkFormBean;
import com.grgbanking.core.entity.workorder.WsSjTypeDetailsBean;
import com.grgbanking.core.entity.workorder.WsSubmitWorkTaskBean;
import com.grgbanking.core.entity.workorder.WsWorkTaskApplyBean;
import com.grgbanking.core.entity.workorder.WsWorkTaskModelBean;
import com.grgbanking.core.entity.workorder.WsWorkformTogetherPersonBean;
import com.grgbanking.core.entity.ws.ReturnData;
import com.grgbanking.core.entity.ws.WsResultFillWorkFormBean;
import com.grgbanking.core.entity.ws.WsResultTogetherPersonsBean;
import com.grgbanking.core.service.equipment.WeiXinImagesService;
import com.grgbanking.core.service.user.OrderService;
import com.grgbanking.core.service.workorder.IPaperService;
import com.grgbanking.core.service.workorder.WebSoftwareMediaWsService;
import com.grgbanking.core.service.workorder.WorkOrderService;
import com.grgbanking.css.bean.CssUser;
import com.grgbanking.css.dao.CssUserDAO;

@Controller
public class WorkOrder2Controller extends BaseController {

    @Resource(name = "workOrderService")
    private WorkOrderService workOrderService;

    // 纸质工单
    @Resource(name = "webPaperWorkOrderService")
    private IPaperService webPaperWorkOrderService;

    // 本地工单服务接口
    @Resource(name = "orderService")
    private OrderService orderService;

    @Autowired
    private WebSoftwareMediaWsService softwareMediaWSService;
    
    @Resource(name = "weiXinImagesService")
    private WeiXinImagesService imagesService;
    
    @Resource(name = "userDAO")
    private CssUserDAO<CssUser,Integer> userDAO;

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/cp/ouath/workOrder/dealTest")
    public ModelAndView dealTest(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/workorder/taskOrderTest");
        // String poNumber = request.getParameter("poNumber");
        String poNumber = "SHX0011507160002";
        Order order = orderService.findOrderBypoNumber(poNumber);
        modelAndView.addObject("order", order);
        return modelAndView;
    }

    /**
     * 点击录单按钮
     * 
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/cp/workOrder/saveorder")
    public String saveorder(HttpServletRequest request) {
        ResultJson<String> result = null;
        WxUser wxUser = LoginUser.getSessionUser();
        String loginName = wxUser.getLoginName();
        String workformId = request.getParameter("workformId"); // 工单ID(不显示，后期查询，提交数据要用)
        String equipmentId = request.getParameter("equipmentId"); // 设备ID
        String poNumber = request.getParameter("poNumber");// 工单编号
        // 设备型号
        String equipmentModel = request.getParameter("equipmentModel");
        String branchName = request.getParameter("branchName");
        String installAddress = request.getParameter("installAddress");
        String atmManager = request.getParameter("atmManager");
        String atmManagerTel = request.getParameter("atmManagerTel");
        String serialNumber = request.getParameter("serialNumber");
        
        // 满意度
        //String defaultId = request.getParameter("defaultId");
        //String defaultName = request.getParameter("defaultName");

        // 工单明细
        String jsonResult = null;

        // 接口返回信息
        WsResultFillWorkFormBean serviceBean = null;
        try {
            logger.info("调用接口获取工单详细数据:【{}】,【{}】,【{}】,【{}】",wxUser.getLoginName(), equipmentId,workformId,poNumber);
            startTime = new Date();
            serviceBean = workOrderService.getWorkFormDetail(loginName, equipmentId, workformId, poNumber, "1");
            logger.warn("{}调用工单详情接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("录单(保存)页面 报错:" + ex.toString());
        }

        try {
            Order orderBean = orderService.findOrderBypoNumber(poNumber);
            WsPendingWorkformDetailBean bean = serviceBean.getWsPendingWorkformDetailBean();
            
            if (orderBean != null) {// 如果存在，先读取，再更新
                logger.info("数据库存在该记录，先读取后更新,poNumber:【{}】",poNumber);
                WsPendingWorkformDetailBean txtBean = (WsPendingWorkformDetailBean) JSON.parseObject(
                        orderBean.getContent(), WsPendingWorkformDetailBean.class);
                if (StringUtils.isEmpty(txtBean.getFinishWorkTime())
                        && StringUtils.isEmpty(txtBean.getFinishWorkTime())) {
                    if (serviceBean != null) {
                        if (serviceBean.getWsPendingWorkformDetailBean() != null)
                            txtBean.setFinishWorkTime(serviceBean.getWsPendingWorkformDetailBean().getFinishWorkTime());
                    }
                }
                // 更新工单状态改变时间
                txtBean.setArriveTime(bean.getArriveTime());
                txtBean.setLeaveTime(bean.getLeaveTime());
                txtBean.setStartWorkTime(bean.getStartWorkTime());
                txtBean.setFinishWorkTime(bean.getFinishWorkTime());

                // 更新纸质工单
                txtBean.setIniPaperId(bean.getIniPaperId());
                txtBean.setIniPaperCode(bean.getIniPaperCode());
                txtBean.setIntPaperStatus(bean.getIntPaperStatus());
                txtBean.setAbandonReason(bean.getAbandonReason());
                
                //更新报修人与接待人
                txtBean.setRepairsId(bean.getRepairsId());
                txtBean.setRepairsManName(bean.getRepairsManName());
                txtBean.setRepairsMoblie(bean.getRepairsMoblie());
                txtBean.setRepairsTelephone(bean.getRepairsTelephone());
                txtBean.setReceiveId(bean.getReceiveId());
                txtBean.setReceiveManName(bean.getReceiveManName());
                txtBean.setReceiveMoblie(bean.getReceiveMoblie());
                txtBean.setReceiveTelephone(bean.getReceiveTelephone());
                
                //更新满意度
                //txtBean.getDegreeSatisfaction().setDefaultId(defaultId);
                //txtBean.getDegreeSatisfaction().setDefaultName(defaultName);
                
                //更新退回原因
                txtBean.setWorkformBackContent(bean.getWorkformBackContent());

                Map<String, WsPendingWorkTaskJsonListBean> currentTaskBeans = bean.getTaskJsonMap();
                Map<String, WsPendingWorkTaskJsonListBean> historyTaskBeans = txtBean.getTaskJsonMap();
                List<WsPendingWorkTaskJsonListBean> executeTaskBeans = new ArrayList<WsPendingWorkTaskJsonListBean>();
                for (String taskId : currentTaskBeans.keySet()) {
                    if (historyTaskBeans.containsKey(taskId))
                        executeTaskBeans.add(historyTaskBeans.get(taskId));
                    else
                        executeTaskBeans.add(currentTaskBeans.get(taskId));
                }
                txtBean.setTaskJsonList(executeTaskBeans);
                txtBean.setEquipmentModel(bean.getEquipmentModel());
                txtBean.setBranchName(branchName);
                txtBean.setInstallAddress(installAddress);
                txtBean.setAtmManagerTel(atmManagerTel);
                txtBean.setAtmManager(atmManager);
                txtBean.setSerialNumber(serialNumber);
                txtBean.setEquipmentId(equipmentId);// 设备id

                jsonResult = JSON.toJSONString(txtBean);

                orderBean.setLoginName(loginName);
                orderBean.setContent(jsonResult);
                orderService.updateOrder(orderBean);

            } else {// 如果不存在，保存
                logger.info("数据库不存在该记录，插入数据,poNumber:【{}】",poNumber);
                bean.setEquipmentModel(equipmentModel);
                bean.setBranchName(branchName);
                bean.setInstallAddress(installAddress);
                bean.setAtmManagerTel(atmManagerTel);
                bean.setAtmManager(atmManager);
                bean.setSerialNumber(serialNumber);
                jsonResult = JSON.toJSONString(bean);
                Order order = new Order();
                order.setLoginName(loginName);
                order.setCreateDate(new Date());
                order.setUpDate(new Date());
                order.setPoNumber(poNumber);
                order.setContent(jsonResult);
                
                orderService.saveOrder(order);
            }
            result = new ResultJson<String>(1, "进入录单成功", poNumber);
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultJson<String>(0, "进入录单失败", e.toString());
        }
        return JSON.toJSONString(result);
    }

    /**
     * 进入录单页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/cp/workOrder/dealWorkOrder")
    public ModelAndView dealWorkOrder(HttpServletRequest request) {

        String poNumber = request.getParameter("poNumber");
        String type = request.getParameter("type"); // 类型，区分 是 返回 按钮 还是保存按钮
        WxUser wxUser = LoginUser.getSessionUser();
        String loginName = wxUser.getLoginName();
        String detailType = request.getParameter("detailType");
        String showInfo = request.getParameter("showInfoargs");
        String typeInfo = request.getParameter("typeInfo");
        String workTaskId = request.getParameter("workTaskId");
        String workformId = request.getParameter("workformId");
        String equipmentId = request.getParameter("equipmentId");
        String paperIdInfou = request.getParameter("paperIdInfou") == null ? "" : request.getParameter("paperIdInfou");
        String paperCodeInfou = request.getParameter("paperCodeInfou") == null ? "" : request
                .getParameter("paperCodeInfou");
        // ====================end===========================
        try {
            if (showInfo != null && !"".equals(showInfo))
                showInfo = URLDecoder.decode(showInfo, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("转码出错了", e);
        }
        ModelAndView modelAndView = null;
        if ("2".equals(detailType)) {// 主任审核
            modelAndView = new ModelAndView("/workorderdircheck/taskOrderListCheck");
        } else if ("4".equals(detailType)) {// 仓管员审核
            modelAndView = new ModelAndView("/workorderlibcheck/taskOrderListLibCheck");
        } else {
            modelAndView = new ModelAndView("/workorder/taskOrderList");
        }
        try {
            Order order = null;
            WsPendingWorkformDetailBean beans = new WsPendingWorkformDetailBean();
            if (!detailType.equals("2") && !detailType.equals("4")) {
                order = orderService.findOrderBypoNumber(poNumber);
                if(order != null){
                    beans = (WsPendingWorkformDetailBean) JSON.parseObject(order.getContent(), WsPendingWorkformDetailBean.class);
                }
            }else{//通过接口获取主任审核、库管员审核的工单详细信息
                WsResultFillWorkFormBean wsBean = workOrderService.getWorkFormDetail(wxUser.getLoginName(), equipmentId, workformId, poNumber, detailType);
                if(wsBean != null){
                    beans = wsBean.getWsPendingWorkformDetailBean();
                }
            }

            // ==============add by zt========================
            if (paperIdInfou != null && !paperIdInfou.equals("")) {
                beans.setIniPaperCode(paperCodeInfou);
                beans.setIniPaperId(paperIdInfou);
            }
            // ===============================================
            // 工单备件审核页面的工单基本信息页
            Map<String, String> baseInfo = new HashMap<String, String>();
            baseInfo.put("customerName", beans.getCustomerName());
            baseInfo.put("poNumber", beans.getPoNumber());
            baseInfo.put("engineerName", beans.getEngineerName());
            baseInfo.put("iniPaperCode", beans.getIniPaperCode());
            baseInfo.put("serialNumber", beans.getSerialNumber());
            baseInfo.put("equipmentModel", beans.getEquipmentModel());
            baseInfo.put("branchName", beans.getBranchName());
            baseInfo.put("installAddress", beans.getInstallAddress());

            // ==============add by zt========================
            if (paperIdInfou != null && !paperIdInfou.equals("")) {
                baseInfo.put("iniPaperCode", paperCodeInfou);
            }

            // ================工单附件信息======================
            List<AttachmentBean> imgs = new LinkedList<AttachmentBean>();
            try {
                WsEquipmentImageBean images = imagesService.getImagesByWorkformId(beans.getWorkformId());
                AttachmentBean attachmentBean = null;
                if(images != null && images.getReturnResult().equals("true") && images.getPhoneImageBeanList() != null && images.getPhoneImageBeanList().size() > 0){
                    for(int i = 0; i< images.getPhoneImageBeanList().size(); i++){
                        attachmentBean = new AttachmentBean();
                        attachmentBean.setImgId(images.getPhoneImageBeanList().get(i).getId().toString());
                        attachmentBean.setPath(WsConsts.AttachmentRootUrl+WsConsts.AttachmentRootPath +images.getPhoneImageBeanList().get(i).getImagesUrl());
                        imgs.add(attachmentBean);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 除了工单任务（升级外）
            List<WsPendingWorkTaskJsonListBean> tasklist = new ArrayList<WsPendingWorkTaskJsonListBean>();

            List<WsPendingWorkTaskJsonListBean> gradelist = new ArrayList<WsPendingWorkTaskJsonListBean>();
            // 仓管员排序下，维修、保养、其他 优先前面
            WsPendingWorkTaskJsonListBean beanWx = null, beanBy = null, beanQt = null;
            for (WsPendingWorkTaskJsonListBean bean : beans.getTaskJsonList()) {
                if (bean.getTaskType().contains("SJ")) {
                    gradelist.add(bean);
                } else {
                    if ("4".equals(detailType)) {// 仓管员排序下，维修、保养、其他 优先前面
                        if (bean.getTaskType().contains("WX")) {
                            beanWx = bean;
                        } else if (bean.getTaskType().contains("BY")) {
                            beanBy = bean;
                        } else if (bean.getTaskType().contains("QT")) {
                            beanQt = bean;
                        } else {
                            tasklist.add(bean);
                        }
                    } else {
                        tasklist.add(bean);
                    }
                }
            }
            if ("4".equals(detailType)) {// 仓管员排序下，维修、保养、其他 优先前面
                int x = 0;
                if (beanWx != null) {
                    tasklist.add(x++, beanWx);
                }
                if (beanBy != null) {
                    tasklist.add(x++, beanBy);
                }
                if (beanQt != null) {
                    tasklist.add(x++, beanQt);
                }
            }
            for (WsPendingWorkTaskJsonListBean bean : beans.getTaskJsonList()) {
                if (bean.getTaskType().contains("XJ")) {
                    WsSubmitWorkTaskBean submittaskbean = bean.getInstallAndRemoveDeviceModel();
                    if (submittaskbean != null) {
                        List<WsSjTypeDetailsBean> sjTypeDetails = submittaskbean.getSjTypeDetails();
                        if (sjTypeDetails != null) {
                            for (WsSjTypeDetailsBean xjbean : sjTypeDetails) {
                                if (xjbean.getSjTypeid().equals("软件检查")) {
                                    modelAndView.addObject("softcheck", xjbean.getSjContext());
                                }
                                if (xjbean.getSjTypeid().equals("硬件检查")) {
                                    modelAndView.addObject("hardcheck", xjbean.getSjContext());
                                }
                                if (xjbean.getSjTypeid().equals("环境检查")) {
                                    modelAndView.addObject("enviromentcheck", xjbean.getSjContext());
                                }
                                if (xjbean.getSjTypeid().equals("其他")) {
                                    modelAndView.addObject("othercheck", xjbean.getSjContext());
                                }
                            }
                        }
                    }
                }
            }

            // ==========================end=====================================
            List<String> provinces = new ArrayList<String>(Arrays.asList("黑龙江", "吉林", "辽宁", "内蒙古", "甘肃", "天津", "河北",
                    "山东", "山西", "北京", "河南", "陕西", "江苏", "上海", "安徽", "浙江", "江西", "福建", "四川", "湖北", "重庆", "贵州", "云南",
                    "广西", "广东", "海南", "青海", "新疆", "西藏", "湖南", "香港", "澳门", "台湾"));
            request.setAttribute("provinces", provinces);
            request.setAttribute("workbean", beans);
            request.setAttribute("poNumber", poNumber);
            request.setAttribute("serialNumber", beans.getSerialNumber());
            request.setAttribute("tasklist", tasklist);
            request.setAttribute("gradelist", gradelist);// 升级模块
            request.setAttribute("type", type); // 类型，区分 是 返回 按钮 还是保存按钮
            request.setAttribute("baseInfo", baseInfo); // 基本信息
            WsResultCustomerBean resultbean = workOrderService.selectCustomerList(loginName);
            modelAndView.addObject("customerList", resultbean == null ? null : resultbean.getCustomerList());
            modelAndView.addObject("userName", wxUser.getName());

            String imgListStr = JSONArray.toJSONString(imgs).toString();

            modelAndView.addObject("imgs", imgListStr);

            modelAndView.addObject("showInfo", showInfo);
            modelAndView.addObject("typeInfo", typeInfo);
            modelAndView.addObject("workTaskId", workTaskId);
            if(order != null){
                modelAndView.addObject("qrCode", JSONObject.parseObject(order.getQrCode()));
            }
            
            //故障部位
            String isExistFault = request.getParameter("isExistFault");
            if(isExistFault != "" && isExistFault != null)
            {
                try {
                    String divname = request.getParameter("divname");
                    String problemPartId = request.getParameter("problemPartId");
                    String problemPartCode = "";
                    String problemCodeId = request.getParameter("problemCodeId");
                    String troubleCode = "";
                    String problemReasonId = request.getParameter("problemReasonId");
                    String troubleReasonCode = "";
                    String problemMethodId = request.getParameter("problemMethodId");
                    String processCode = "";
                    if(request.getParameter("problemPartCode") != null){
                        problemPartCode = java.net.URLDecoder.decode(request.getParameter("problemPartCode"),"UTF-8");
                    }
                    if(request.getParameter("troubleCode") != null){
                        troubleCode = java.net.URLDecoder.decode(request.getParameter("troubleCode"),"UTF-8");
                    }
                    if(request.getParameter("troubleReasonCode") != null){
                        troubleReasonCode = java.net.URLDecoder.decode(request.getParameter("troubleReasonCode"),"UTF-8");
                    }
                    if(request.getParameter("processCode") != null){
                        processCode = java.net.URLDecoder.decode(request.getParameter("processCode"),"UTF-8");
                    }

                    modelAndView.addObject("problemPartId", problemPartId);
                    modelAndView.addObject("problemPartCode", problemPartCode);
                    modelAndView.addObject("problemCodeId", problemCodeId);
                    modelAndView.addObject("troubleCode", troubleCode);
                    modelAndView.addObject("problemReasonId", problemReasonId);
                    modelAndView.addObject("troubleReasonCode", troubleReasonCode);
                    modelAndView.addObject("problemMethodId", problemMethodId);
                    modelAndView.addObject("processCode", processCode);
                    modelAndView.addObject("divname", divname);
                    modelAndView.addObject("isExistFault", isExistFault);
                    
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                
            }else{
                modelAndView.addObject("isExistFault", 0);
            }
            
            WsResultTogetherPersonsBean resultperson = workOrderService.dropdownTogetherPersons(beans.getWorkformId(), loginName);
            List<WsWorkformTogetherPersonBean> togetherPersonList = resultperson.getTogetherPerson();
            modelAndView.addObject("togetherPersonList", togetherPersonList);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("工单详细列表 报错:" + ex.toString());
        }
        // modelAndView.addObject("wxUrl", getWxUrl());
        return modelAndView;
    }

    /**
     * 确认纸质工单号
     * 
     * @Title: paperConfirm
     * @Description: 
     * @param:
     * @return void
     */
    @RequestMapping(value = "/cp/workOrder/paperConfirm")
    @ResponseBody
    public String paperConfirm(HttpServletRequest request, HttpServletResponse response) {
        ReturnData resultbean = null;
        try {
            WxUser wxUser = LoginUser.getSessionUser();
            String loginName = wxUser.getLoginName();
            String workformId = request.getParameter("workformId");// 工单ID
            String paperCode = request.getParameter("paperCode");// 纸质工单号
            logger.info("确认纸质工单号");
            startTime = new Date();
            resultbean = webPaperWorkOrderService.doBindPaperWorkform(workformId, paperCode, loginName, "1");
            logger.warn("{}调用确认纸质工单号接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
            if (resultbean.isFlag() == true) {
                resultbean.setFlag(true);
                resultbean.setMsg("确认成功");
            } else {
                resultbean.setFlag(false);
                resultbean.setMsg("确认失败,请重新确认");
            }
        } catch (Exception e) {
            logger.error("==确认纸质工单号异常信息" + e.getMessage(), e.getCause());
            resultbean = new ReturnData();
            resultbean.setFlag(false);
            resultbean.setMsg(e.getMessage());
        }
        return JSON.toJSONString(resultbean);
    }

    /**
     * 纸质工单更改页面
     * 
     * @return
     */
    @RequestMapping(value = "/cp/workOrder/faultPaper")
    public ModelAndView faultPaper(HttpServletRequest request, String poNumber, String paperId, String paperCode) {
        ModelAndView modelAndView = new ModelAndView("/workorder/faultPaper");
        try {
            Order order = orderService.findOrderBypoNumber(poNumber);
            WsPendingWorkformDetailBean beans = (WsPendingWorkformDetailBean) JSON.parseObject(order.getContent(),
                    WsPendingWorkformDetailBean.class);
            System.out.println(beans.getAbandonReason().getOptionValue().get(0).getOptionId());
            if (order != null) {
                request.setAttribute("workbean", beans);
            }
            modelAndView.addObject("paperId", paperId);
            modelAndView.addObject("paperCode", paperCode);
            modelAndView.addObject("poNumber", poNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 更新纸质工单号
     * 
     * @Title: paperUpdate
     * @Description: 
     * @param:
     * @return void
     */
    @RequestMapping(value = "/cp/workOrder/paperUpdate")
    @ResponseBody
    public String paperUpdate(HttpServletRequest request) {
        // String result = "";
        ReturnData returnData = null;
        try {
            WxUser wxUser = LoginUser.getSessionUser();
            String loginName = wxUser.getLoginName();
            // 获取用户名
            // String loginName = "drhua";
            String paperId = request.getParameter("paperId");// 纸质工单ID
            String paperCode = request.getParameter("paperCode");// 纸质工单号
            String updateReason = request.getParameter("updateReason");// 更新原因
            String paperRemark = request.getParameter("paperRemark");// 更新备注
            // 如果更改原因为未带，查询下一条
            // 如果更改原因为丢失或者污损，作废当前纸质工单,查询下一条
            if (updateReason.equals("0")) {
                // 未带
                logger.info("查询下一张纸质工单编号");
                returnData = webPaperWorkOrderService.generateNextPaperWorkFormCode(paperCode, loginName, "1");
            } else {
                // 丢失或者污损
                //logger.info("申请纸质工单作废");
                ReturnData applyResultbean = webPaperWorkOrderService.doApplyAbandonedToCheck(paperId,
                        updateReason == null ? "" : updateReason, paperRemark == null ? "" : paperRemark, loginName,
                        "1");
                if (applyResultbean.isFlag() == true) {
                    //logger.info("查询下一张纸质工单编号");
                    returnData = webPaperWorkOrderService.generateNextPaperWorkFormCode(paperCode, loginName, "1");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("==更新纸质工单号异常信息：" + e.getMessage(), e.getCause());
            returnData = new ReturnData();
            returnData.setFlag(false);
            returnData.setMsg(e.getMessage());
        }
        return JSON.toJSONString(returnData);
    }

    /**
     * 工单基本信息保存
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cp/workOrder/workDetailSave")
    public String workDetailSave(HttpServletRequest request, HttpServletResponse response) {
        ResultJson<String> result = null;
        WxUser wxUser = LoginUser.getSessionUser();
        String loginName = wxUser.getLoginName();
        String poNumber = request.getParameter("poNumber");

       //满意度
        String satisfactionId = "";
        String satisfactionName = "";
        
        String degreeSatisfaction = request.getParameter("degreeSatisfaction");
        if(degreeSatisfaction != null && degreeSatisfaction != ""){
            String[] arr = degreeSatisfaction.split(",");
            if(arr.length >=2){
                satisfactionId = arr[0];
                satisfactionName = arr[1]; 
            }
        }
        
        //同行人员
        String togetherPerson = request.getParameter("togetherPerson");
        WsWorkformTogetherPersonBean togetherPersonBean = null; 
        if(togetherPerson != null && togetherPerson != ""){
            String[] arr = togetherPerson.split(",");
            if(arr.length >=2){ 
                togetherPersonBean = new WsWorkformTogetherPersonBean();
                togetherPersonBean.setUserId(arr[0]);
                togetherPersonBean.setName(arr[1]);
            }
        }
        
        try {
            Order orderBean = orderService.findOrderBypoNumber(poNumber);
            WsPendingWorkformDetailBean workbean = (WsPendingWorkformDetailBean) com.alibaba.fastjson.JSON.parseObject(
                    orderBean.getContent(), WsPendingWorkformDetailBean.class);
            WorkOrderTools wot = new WorkOrderTools();

            wot.processWorkDetaiInfo(request, workbean);

            //设置满意度
            if(workbean.getDegreeSatisfaction() != null){
                workbean.getDegreeSatisfaction().setDefaultId(satisfactionId);
                workbean.getDegreeSatisfaction().setDefaultName(satisfactionName);
            }
            
            //设置同行人员
            if(togetherPersonBean != null){
                workbean.setTogetherPerson(togetherPersonBean);
            }
            
            JSONObject jObject = JSONObject.parseObject(JSON.toJSONString(workbean));

            orderBean.setLoginName(loginName);
            orderBean.setContent(jObject.toString());
            orderBean.setUpDate(new Date());
            orderService.updateOrder(orderBean);
            result = new ResultJson<String>(1, "保存成功", poNumber);

        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultJson<String>(0, "保存失败", e.getMessage());
            logger.error("工单保存失败：" + e.toString());
        }
        
        return JSON.toJSONString(result);
    }
    
    /**
     * 获取同行人员
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cp/workOrder/getTogetherPerson")
    public String getTogetherPerson(HttpServletRequest request, HttpServletResponse response)
    {
        ResultJson<List<WsWorkformTogetherPersonBean>> result = null;
        WxUser wxUser = LoginUser.getSessionUser();
        String loginName = wxUser.getLoginName();
        String workformId = request.getParameter("workformId");
        //logger.info("工单信息 ，调用获取同行人员下拉列表接口");
        WsResultTogetherPersonsBean resultperson = null;
        try {
            resultperson = workOrderService.dropdownTogetherPersons(workformId, loginName);
        } catch (Exception e) {
            ResultJson<String> result1 = null;
            result1 = new ResultJson<String>(0, "获取同行人员失败", e.getMessage());
            e.printStackTrace();
            logger.error("获取同行人员失败：" + e.toString());
            return JSON.toJSONString(result1);
        }
        List<WsWorkformTogetherPersonBean> togetherPerson = resultperson.getTogetherPerson();
        
        result = new ResultJson<List<WsWorkformTogetherPersonBean>>(1, "获取成功", togetherPerson);
        return JSON.toJSONString(result);
    }

    /**
     * 工单的提交
     * 
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/cp/workOrder/workDetailOrderSubmit")
    public String workDetailOrderSubmit(HttpServletRequest request, HttpServletResponse response) {
        //返回结果
        ResultJson<String> result = null;
        
        String poNumber = request.getParameter("poNumber");
        WxUser wxUser = LoginUser.getSessionUser();
        String loginName = wxUser.getLoginName();

        try {
            Order order = orderService.findOrderBypoNumber(poNumber);
            WsPendingWorkformDetailBean workbean = JSON.parseObject(order.getContent(),
                    WsPendingWorkformDetailBean.class);
            WorkOrderTools wot = new WorkOrderTools();
            wot.processWorkDetaiInfo(request, workbean);
            List<WsWorkTaskApplyBean> tasksubmitlists = new ArrayList<WsWorkTaskApplyBean>();

            List<WsPendingWorkTaskJsonListBean> taskjoinlist = workbean.getTaskJsonList();
            for (WsPendingWorkTaskJsonListBean taskbean : taskjoinlist) {
                WsWorkTaskApplyBean taskapplyBean = new WsWorkTaskApplyBean();
                taskapplyBean.setTaskId(taskbean.getTaskId());
                taskapplyBean.setTaskNo(taskbean.getTaskType());
                taskapplyBean.setTaskAttribute(taskbean.getInstallAndRemoveDeviceModel());
                WsWorkTaskModelBean modelbean = new WsWorkTaskModelBean();
                modelbean.setImportModel(taskbean.getImportModel());// 主要模块
                modelbean.setSubModel(taskbean.getSubModel()); // 零件模块
                modelbean.setDeviceHitch(taskbean.getDeviceHitch()); // 故障模块
                modelbean.setBeforSjModel(taskbean.getBeforSjModel()); // 升级前
                modelbean.setAfterSjModel(taskbean.getAfterSjModel()); // 升级后
                taskapplyBean.setTaskModel(modelbean);
                tasksubmitlists.add(taskapplyBean);
            }
            WsResultFinishWorkFormBean finishBean = null;
            try {

                // =========Start===========附件=================
                /*String attachmentStr = order.getAttachment();
                List<WSPhoneImageBean> wsPhoneImageBeans = null;
                JSONArray attachmentJsonArr = new JSONArray();
                if (attachmentStr != null) {
                    wsPhoneImageBeans = new ArrayList<WSPhoneImageBean>();
                    ;
                    JSONObject jsonAttachment = JSON.parseObject(attachmentStr);// JSONObject.fromObject(attachmentStr);
                    JSONArray jsonArr = jsonAttachment.getJSONArray("imgs");
                    attachmentJsonArr = jsonArr;
                }
                Date now = new Date();
                String userCode = wxUser.getUserCode();
                for (int i = 0; i < attachmentJsonArr.size(); i++) {
                    WSPhoneImageBean wsPhoneImageBean = new WSPhoneImageBean();
                    wsPhoneImageBean.setWorkformId(Integer.parseInt(workbean.getWorkformId()));
                    wsPhoneImageBean.setEquipmentId(Integer.parseInt(workbean.getEquipmentId()));
                    wsPhoneImageBean.setImagesUrl(attachmentJsonArr.getJSONObject(i).getString("path"));
                    wsPhoneImageBean.setImagesType(2);
                    wsPhoneImageBean.setCreateDate(now);
                    wsPhoneImageBean.setUserCode(userCode);
                    wsPhoneImageBeans.add(wsPhoneImageBean);
                }*/
                // =========End===========附件=================
                
                //满意度
                String satisfactionId = "";
                String satisfactionName = "";
                
                String degreeSatisfaction = request.getParameter("degreeSatisfaction");
                if(degreeSatisfaction != null && degreeSatisfaction != ""){
                    String[] arr = degreeSatisfaction.split(",");
                    if(arr.length >=2){
                        satisfactionId = arr[0];
                        satisfactionName = arr[1]; 
                      //设置满意度
                        if(workbean.getDegreeSatisfaction() != null){
                            workbean.getDegreeSatisfaction().setDefaultId(satisfactionId);
                            workbean.getDegreeSatisfaction().setDefaultName(satisfactionName);
                        }
                    }
                }
                
                //同行人员
                String togetherPerson = request.getParameter("togetherPerson");
                String togetherPersonId = null;
                String togetherPersonName = null;
                if(togetherPerson != null && togetherPerson != ""){
                    String[] arr = togetherPerson.split(",");
                    if(arr.length >=2){
                        togetherPersonId = arr[0];
                        togetherPersonName = arr[1]; 
                        WsWorkformTogetherPersonBean togetherPersonBean = new WsWorkformTogetherPersonBean(); 
                        togetherPersonBean.setUserId(arr[0]);
                        togetherPersonBean.setName(arr[1]);
                        workbean.setTogetherPerson(togetherPersonBean);
                    }
                }
                logger.info("调用工单提交接口，参数：" + "loginName:" + loginName + " workformId:" + workbean.getWorkformId() + " serialNumber:" +workbean.getSerialNumber());
                startTime = new Date();
                finishBean = workOrderService.doFinishWorkForm(workbean.getWorkformId(), workbean.getSerialNumber(),
                        workbean.getDegreeSatisfaction().getDefaultId(), workbean.getEquipmentId(), loginName, togetherPersonId, togetherPersonName, 
                        workbean.getAtmManager(), workbean.getReportTel(), 
                        workbean.getEquipmentHistoryProblemResultJson(), workbean.getWorkFormFeeJson(),
                        tasksubmitlists, Consts.CLIENT_TYPE, null, null);
                logger.warn("{}调用工单提交接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
                
            } catch (Exception e) {
                e.printStackTrace();
                result = new ResultJson<String>(0, "提交失败", e.toString());
                logger.error("loginName:" + loginName + "poNumber:" + poNumber + "调用工单提交接口报错：" + e.toString());
            }
            order.setLoginName(loginName);
            order.setContent(JSON.toJSONString(workbean));
            order.setUpDate(new Date());
            if (finishBean != null && finishBean.getStatus() == 1) {
                orderService.updateOrder(order);
                result = new ResultJson<String>(1, "提交成功", "");
            } else {
                wot.processWorkDetaiInfo(request, workbean);
                orderService.updateOrder(order);
                result = new ResultJson<String>(0, "提交失败", finishBean != null ? finishBean.getErrMsg() : "");
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultJson<String>(0, "提交失败", e.toString());
            logger.error("loginName:" + loginName + "poNumber:" + poNumber + "提交工单失败：" + e.toString());
        }
        return JSON.toJSONString(result);
    }

    /**
     * 工单：上传图片
     * 
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/cp/workOrder/upload")
    public String upload(HttpServletRequest request, HttpServletResponse response, String serialNumber)
            throws IOException {
        String op = request.getParameter("op");
        String equipmentId = request.getParameter("equipmentId");
        String workformId = request.getParameter("workformId");
        //logger.info("上传工单图片参数：workformId:"+workformId+" equipmentId:"+equipmentId);
        List<AttachmentBean> imgs = new LinkedList<AttachmentBean>();
        if ("save".equals(op)) {
            response.setContentType("text/html;charset=utf-8");// 设置中文问题
            String result = "";
            // 获取当前人会话，拿到用户姓名和登录名
            WxUser wxUser = LoginUser.getSessionUser();
            String longitude = request.getParameter("longitude");
            String latitude = request.getParameter("latitude");
            // String address = request.getParameter("address");
            String mediaId = request.getParameter("mediaId");
            String[] mIds = mediaId.split(",");
            WxCpService wxCpService = Wechat.getInstance().getWxCpService();
            List<CssPhoneImageBean> beanList = new ArrayList<CssPhoneImageBean>();
            List<byte[]> fileList = new ArrayList<byte[]>();
            List<String> urlList = new ArrayList<String>();
            FileInputStream input = null;
            
            //构造文件目录
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            Date date = new Date();
            String filePath = sdf.format(date);
            
            try {
                Integer cssUserId = 0;
                CssUser cssUser = userDAO.getUserByUserCode(wxUser.getUserCode());
                if(cssUser !=null){
                    cssUserId = cssUser.getUserId();
                }
                for (int i = 0; null != mIds && i < mIds.length; i++) {
                    File file = wxCpService.mediaDownload(mIds[i]);
                    if (null != file) {
                        String path = saveFileToServer(file,filePath);
                        urlList.add(WsConsts.AttachmentRootUrl+ WsConsts.AttachmentRootPath +path);
                        CssPhoneImageBean imageBean = new CssPhoneImageBean();
                        imageBean.setEquipmentId(Integer.valueOf(equipmentId));
                        imageBean.setWorkformId(Integer.valueOf(workformId));
                        imageBean.setImagesUrl(path);
                        imageBean.setLongitude(longitude);
                        imageBean.setLatitude(latitude);
                        imageBean.setCreateDate(new Date());
                        imageBean.setUserId(cssUserId);
                        imageBean.setImagesType(2);
                        beanList.add(imageBean);
                        input = new FileInputStream(file);
                        int length = input.available();
                        byte[] bt = new byte[length + 1024];
                        int count = 0;
                        int Len = 1024;
                        int off = 0;
                        while ((count = input.read(bt, off, Len)) != -1) {
                            off = off + count;
                        }
                        byte[] buff = new byte[off];
                        System.arraycopy(bt, 0, buff, 0, off);
                        fileList.add(buff);
                    }
                }
                WsEquipmentImageBean eib = new WsEquipmentImageBean();
                eib.setPhoneImageBeanList(beanList);
                eib.setFiles(fileList);
                startTime = new Date();
                result = imagesService.doCreateEquipmentImages(eib);
                logger.warn("{}调用上传图片接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
                //logger.info("上传工单图片结果：" + result);
                net.sf.json.JSONObject jsonResult = net.sf.json.JSONObject.fromObject(result);
                
                AttachmentBean attachmentBean = null;
                List<String> ids = new ArrayList<String>();
                if(jsonResult != null){
                    Object IdsObject = jsonResult.get("ids");
                    if(IdsObject != null && IdsObject != ""){
                        String[] idsStr = IdsObject.toString().split(",");
                        if (idsStr.length > 0) {
                            ids = Arrays.asList(idsStr);
                        }
                    }
                }
                for(int i = 0; i < urlList.size(); i++){
                    attachmentBean = new AttachmentBean();
                    if(i <=ids.size() -1){
                        attachmentBean.setImgId(ids.get(i));
                    }
                    attachmentBean.setPath(urlList.get(i));
                    imgs.add(attachmentBean);
                }
                
            } catch (WxErrorException e) {
                e.printStackTrace();
                result = "{\"returnResult\":\"false\",\"msg\":\"上传照片失败！\"}";
                logger.error("上传图片失败：" + e.toString());
            } finally {
                input.close();
            }
        }
        return JSON.toJSONString(imgs);
    }
    
    /**
     * 
     * @Title: saveFileToServer
     * @Description: 保存文件到服务器
     * @param file
     * @param filePath 文件子目录
     * @return 文件保存子路径
     */
    public String saveFileToServer(File file,String filePath) {
        String fileName = "";
        //最终路径
        String realPath = WsConsts.AttachmentRootPath + File.separator + filePath;
        try {
            File fileDir = new File(realPath);
            // 判断路径是否存在
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            // 定义文件输出流
            FileOutputStream fos = null;
            // 定义输入流，用于接受 URL连接获取的输入流
            InputStream input = null;
            // 遍历传进来的URL列表，批量下载文件
            if (null != file) {
                // 要下载的文件后缀
                String contentType = new MimetypesFileTypeMap()
                        .getContentType(file);
                String imgType = ".jpg";
                if ("image/jpeg".contains(contentType)) {
                    imgType = ".jpg";
                } else if ("image/png".contains(contentType)) {
                    imgType = ".png";
                } else if ("image/gif".contains(contentType)) {
                    imgType = ".gif";
                } else if ("image/bmp".contains(contentType)) {
                    imgType = ".bmp";
                }
                fileName = "qywx_ms" + DateUtil.getDate("yyyyMMddHHmmss")+NumberUtils.getRandom(4)+imgType;
                // 用于保存到数据库的附件信息
                fos = new FileOutputStream(realPath + File.separator  + fileName);
                long length = file.length();
                if (length > 0) {
                    input = new FileInputStream(file);
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = input.read(buffer)) > 0) // 循环读取文件到输出流
                    {
                        fos.write(buffer, 0, len); // 使用输出流输出文件。
                    }
                }
            }
            if (fos != null) {
                fos.flush();
                fos.close();
            }
            if (input != null) {
                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("图片保存本地服务器失败！"+e.toString());
        }
        return filePath + File.separator  + fileName;
    }
    
    /**
     * 工单：新增附件
     */
    @ResponseBody
    @RequestMapping(value = "/cp/workOrder/saveAttachment")
    public String saveAttachment(HttpServletRequest request, HttpServletResponse response) {
        String codeUrl = WechatAPIUtil.GET_MEDIA_URL;
        // String accessToken = WechatAccessUtil.getAccessToken();
        String accessToken = "";
        try {
            accessToken = Wechat.getInstance().getWxCpService().getAccessToken();
        } catch (WxErrorException e1) {
            e1.printStackTrace();
        }
        codeUrl = codeUrl.replace("ACCESS_TOKEN", accessToken);
        String mediaId = request.getParameter("mediaId");

        //logger.info("=====微信传过来的mediaId======" + mediaId);

        String[] mIds = mediaId.split(",");

        // 准备从微信下载文件的url列表
        List<String> urlList = new ArrayList<String>();
        for (int i = 0; i < mIds.length; i++) {
            urlList.add(codeUrl.replace("MEDIA_ID", mIds[i]));
        }
        WorkFormFileUtil downLoadFile = new WorkFormFileUtil();

        // 工单编号
        String poNumber = request.getParameter("poNumber");
        
        //经纬度
        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");

        String attachSrcStr = null;
        JSONObject attachSrcObj = null;
        JSONArray attachSrcArr = null;

        JSONObject attachAddObj = new JSONObject();
        JSONArray attachAddArr = null;

        Order order = null;
        order = orderService.findOrderBypoNumber(poNumber);
        attachSrcStr = order.getAttachment();
        if (attachSrcStr == null || attachSrcStr.equals("")) {
            attachSrcObj = new JSONObject();
            attachSrcArr = new JSONArray();
        } else {
            attachSrcObj = JSONObject.parseObject(attachSrcStr);
            attachSrcArr = attachSrcObj.getJSONArray("imgs");
        }
        attachAddArr = downLoadFile.downLoadFromUrl(urlList, longitude, latitude);
        attachSrcArr.addAll(attachAddArr);

        attachSrcObj.put("imgs", attachSrcArr);
        order.setAttachment(attachSrcObj.toString());
        orderService.updateOrder(order);

        String attachRoot = PropUtils.get("AttachmentRootUrl");

        List<AttachmentBean> imgs = new LinkedList<AttachmentBean>();
        for (int i = 0; i < attachAddArr.size(); i++) {
            AttachmentBean attachmentBean = new AttachmentBean();
            attachmentBean.setImgId(attachAddArr.getJSONObject(i).getString("imgId"));
            attachmentBean.setPath(attachRoot + attachAddArr.getJSONObject(i).getString("path"));
            attachmentBean.setLatitude(latitude);
            attachmentBean.setLongitude(longitude);
            imgs.add(attachmentBean);
        }

        attachAddObj.put("imgs", imgs);
        
        return JSON.toJSONString(attachAddObj);

    }
    
    /**
     * 删除图片
     */
    @ResponseBody
    @RequestMapping(value = "/cp/workOrder/deleteImage")
    public String deleteImage(HttpServletRequest request,
            HttpServletResponse response) {
        String id = request.getParameter("id");
        String result = imagesService.getPhoneImage(id);
        net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(result);
        if ("true".equals(json.getString("returnResult"))) {
            String path = json.getString("imagesUrl");
            File file = new File(WsConsts.AttachmentRootPath +path);
            if (file.exists()) {
                file.delete();
            }
            result = imagesService.deleteEquipmentImages(id);
        }
        return JSON.toJSONString(result);
    }

    /**
     * 工单：删除附件
     */
    @RequestMapping(value = "/cp/workOrder/deleteAttachment")
    public void deleteAttachment(HttpServletRequest request, HttpServletResponse response) {

        // 工单编号
        String poNumber = request.getParameter("poNumber");

        // 附件ID
        String imgId = request.getParameter("imgId");

        String attachmentStr = null;
        JSONObject jsonAttachment = null;
        Order order = null;
        try {
            order = orderService.findOrderBypoNumber(poNumber);
            attachmentStr = order.getAttachment();
            // 删除图片附件
            if (attachmentStr != null) {
                jsonAttachment = JSONObject.parseObject(attachmentStr);
                JSONArray jsonArr = jsonAttachment.getJSONArray("imgs");
                for (int i = 0; i < jsonArr.size(); i++) {
                    JSONObject json = jsonArr.getJSONObject(i);

                    if (json.getString("imgId").compareToIgnoreCase(imgId) == 0) {
                        String filePath = json.getString("path");
                        File file = new File(filePath);
                        if (file.exists()) {
                            file.delete();
                        }
                        jsonArr.remove(json);
                        //logger.info("=====更新数据库======");
                        order.setAttachment(jsonAttachment.toString());
                        orderService.updateOrder(order);
                    }
                }
            }
            if (jsonAttachment != null) {
                OutputStream output = response.getOutputStream();
                output.write(jsonAttachment.toString().getBytes());
                output.flush();
                output.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 检查二维码
     * @Title: checkTwoDimensionCode
     * @Description: 
     * @param:
     * @return void
     */
    @RequestMapping(value = "/cp/workOrder/checkTwoDimensionCode", method = RequestMethod.POST)
    @ResponseBody
    public String checkTwoDimensionCode(String message) {
        System.out.println("message:" + message);
        JSONObject jsonObjects = JSONObject.parseObject(message);
        //logger.info("-----------------二维码信息为" + jsonObjects.toString());
        JSONObject result = new JSONObject();
        com.alibaba.fastjson.JSONObject massage = new com.alibaba.fastjson.JSONObject();
        try {
            String workformId = jsonObjects.getString("workformId");// 设备ID
            JSONArray twoDimensionCodes = JSONArray.parseArray(jsonObjects.getJSONArray("twoDimensionCode")
                    .toJSONString());// 二维码数组

            String media = "";
            int number = 1;
            for (Iterator<?> iterator = twoDimensionCodes.iterator(); iterator.hasNext();) {
                JSONObject object = (JSONObject) iterator.next();
                String decode = URLDecoder.decode(object.getString("code"), "UTF-8");
                if (twoDimensionCodes.size() == Integer.parseInt(decode.substring(0, 2))
                        && Integer.parseInt(decode.substring(2, 4)) == number) {
                    String tempStr = decode.substring(4, decode.length());
                    if (number != twoDimensionCodes.size()) {
                        media += tempStr.substring(0, tempStr.lastIndexOf("\r\n"));
                    } else {
                        media += tempStr;
                    }
                }
                number++;

            }
            logger.info("调用设备二维码扫描接口");
            String msg = softwareMediaWSService.gatherMedia(Integer.parseInt(workformId), media, "CODE");
            massage = JSONObject.parseObject(msg.replaceAll("\n", "<br>"));
            if (!"success".equals(massage.get("status"))) {
            }
            result.put("status", "1");
            result.put("errMsg", "查询成功");
            result.put("SjMsg", massage.getString("message").replaceAll("<br>", "\n"));
        } catch (Exception e) {
            logger.error("==设备二维码扫描异常==" + e.toString(), e.getCause());
            result.put("status", "0");
            result.put("errMsg", "查询失败");
            result.put("SjMsg", massage.getString("message").replaceAll("<br>", "\n"));
        }
        return result.toString();
    }

    /**
     * 扫描二维码
     * @param twoDimensionCodeArr
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cp/workOrder/scanResult", method = RequestMethod.POST)
    public JSONObject scanResult(String twoDimensionCodeArr) {
        //logger.info("----得到扫描结果：------");
        JSONObject jsonObjects = JSONObject.parseObject(twoDimensionCodeArr);
        //logger.info(jsonObjects.toString());
        String scanResult;
        String poNumber = null;
        String txtResult = "";
        String[] twoDimensionCode = null;
        try {
            scanResult = URLDecoder.decode(jsonObjects.getString("scanResult"), "UTF-8");
            poNumber = jsonObjects.getString("poNumber");
            JSONArray arr = jsonObjects.getJSONArray("arr");
            twoDimensionCode = new String[arr.size()];
            for (int i = 0; i < arr.size(); i++) {
                twoDimensionCode[i] = URLDecoder.decode(arr.getJSONObject(i).getString("scanResult"), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            txtResult = "转码失败";
            return handlJson(false, txtResult);
        }
        int num;
        if (scanResult.length() < 4) {
            txtResult = "该二维码信息不属于设备序列号，请重新扫描其它二维码";
            return handlJson(false, txtResult);
        } else if (twoDimensionCode == null || twoDimensionCode.length == 0) {
            try {
                int length = Integer.valueOf(scanResult.substring(0, 2));
                num = Integer.valueOf(scanResult.substring(2, 4));
                if (num > length || length == 0 || num == 0) {
                    txtResult = "该二维码信息不属于设备序列号，请重新扫描其它二维码";
                    return handlJson(false, txtResult);
                }
                twoDimensionCode = new String[length];
            } catch (NumberFormatException e) {
                txtResult = "该二维码信息不属于设备序列号，请重新扫描其它二维码";
                return handlJson(false, txtResult);
            }
        } else {
            try {
                int length = Integer.valueOf(scanResult.substring(0, 2));
                if (length != twoDimensionCode.length) {
                    txtResult = "该二维码与之前扫描的二维码不属于同一设备，请重置扫描或者继续扫描其它二维码";
                    return handlJson(false, txtResult);
                }
                num = Integer.valueOf(scanResult.substring(2, 4));
                if (num > length || num == 0) {
                    txtResult = "该二维码信息不属于设备序列号，请重新扫描其它二维码";
                    return handlJson(false, txtResult);
                }

            } catch (NumberFormatException e) {
                txtResult = "该二维码信息不属于设备序列号，请重新扫描其它二维码";
                return handlJson(false, txtResult);
            }
        }
        twoDimensionCode[num - 1] = scanResult;
        String hasNone = "";
        for (int i = 0; i < twoDimensionCode.length; i++) {
            if (twoDimensionCode[i] == null || twoDimensionCode[i].length() < 4) {
                hasNone = hasNone + (i + 1) + "、";
            }
        }
        if (hasNone.length() < 1) {
            txtResult = "第" + num + "个二维码扫描成功！请提交二维码！";
        } else {
            txtResult = "第" + num + "个二维码扫描成功！请扫描第" + hasNone.substring(0, hasNone.length() - 1) + "个二维码！";
        }
        try {
            Order o = orderService.findOrderBypoNumber(poNumber);
            jsonObjects.remove("scanResult");
            jsonObjects.remove("poNumber");
            o.setQrCode(jsonObjects.toString());
            o.setUpDate(new Date());
            orderService.updateOrder(o);
        } catch (Exception e) {
            txtResult = "json保存出错";
            return handlJson(false, txtResult);
        }
        return handlJson(true, txtResult);
    }

    private JSONObject handlJson(boolean flg, String msg) {
        com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
        json.put("flg", flg);
        json.put("msg", msg);
        return json;
    }
}
