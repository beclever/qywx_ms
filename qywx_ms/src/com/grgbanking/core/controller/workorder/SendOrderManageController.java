package com.grgbanking.core.controller.workorder;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.common.util.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.grgbanking.common.consts.Consts;
import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.common.utils.AjaxUtils;
import com.grgbanking.common.utils.BaseUserRoleUtil;
import com.grgbanking.common.utils.DateUtil;
import com.grgbanking.common.utils.LoginUser;
import com.grgbanking.core.controller.BaseController;
import com.grgbanking.core.entity.user.BaseRoleUser;
import com.grgbanking.core.entity.user.Order;
import com.grgbanking.core.entity.user.SendOrderBean;
import com.grgbanking.core.entity.user.WxUser;
import com.grgbanking.core.entity.workorder.WsPendingWorkformDetailBean;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultFillWorkFormBean;
import com.grgbanking.core.service.employee.EmployeeService;
import com.grgbanking.core.service.equipment.WebEquipmentService;
import com.grgbanking.core.service.user.BaseRoleUserService;
import com.grgbanking.core.service.user.OrderService;
import com.grgbanking.core.service.workorder.SendOrderManagerService;
import com.grgbanking.core.service.workorder.WorkFormEngineService;
import com.grgbanking.core.service.workorder.WorkOrderService;
import com.grgbanking.core.service.workorder.WorkTaskService;
import com.grgbanking.css.bean.work.WorkTaskBean;
import com.grgbanking.css.service.CssWorkFormWsService;
import com.grgbanking.webservice.bean.WorkFormMonitorBean;

/**
 * 派单管理
 * 版权所有：2016-GRGBANKING
 * 项目名称：qywx_ms_pro   
 *
 * 类描述：
 * 类名称：com.grgbanking.core.controller.workorder.WorkOrderManageController     
 * 创建人：WSH
 * 创建时间：2016年1月21日 下午4:02:49   
 * 修改人：
 * 修改时间：2016年1月21日 下午4:02:49   
 * 修改备注：   
 * @version   V1.0
 */
@Controller
public class SendOrderManageController extends BaseController {

    @Autowired
    private WebEquipmentService equipmentService;// 设备序列号查询

    // 工单接口
    @Resource(name = "workOrderService")
    private WorkOrderService workOrderService;// 查询任务类型 查询派单是否符合规则 派单

    @Autowired
    private EmployeeService employeeService;// 人员Service

    @Autowired
    private WebEquipmentService webEquipmentService;// 设备保修联系人接口

    @Resource(name = "sendOrderManagerService")
    private SendOrderManagerService sendOrderManagerService;// 派单管理

    @Autowired
    private WorkFormEngineService workFormEngineService; // 工单管理
    
    @Autowired
    private CssWorkFormWsService cssWorkFormWsService;

    
    //@Autowired private IOrderSendNoticeService noticeService;// 派单成功后发送通知

    // 用户角色服务
    /*@Resource(name = "mobileUserRoleService")
    private MobileUserRoleService mobileUserRoleService;*/
    
    // 客服用户角色服务
    @Resource(name = "baseRoleUserService")
    private BaseRoleUserService baseRoleUserService;
    
    // 现场服务 服务接口
    @Resource(name = "workTaskService")
    private WorkTaskService workTaskService;

    // 本地工单服务接口
    @Resource(name = "orderService")
    private OrderService orderService;

    /**
     * 新建工单页面
     * 
     * @return
     */
    @RequestMapping(value = "/cp/ouath/sendManage/createWorkOrder")
    public ModelAndView createWorkOrder(HttpServletRequest request, HttpServletResponse response) {

        //登录用户
        WxUser wxUser = LoginUser.getSessionUser();
        /*MobileUserRole mobileUserRole = new MobileUserRole();
        mobileUserRole.setRoleId(Long.parseLong(WsConsts.SERVICE_DIRECTOR));
        mobileUserRole.setUserId(wxUser.getId());
        //返回结果，false表示不是服务站主任，true则是。
        Boolean isExist = mobileUserRoleService.IsExistUserRole(mobileUserRole);*/
        List<BaseRoleUser> cssRoles = baseRoleUserService.queryByLoginName(wxUser.getLoginName());
        ModelAndView modelAndView = null;
        if(BaseUserRoleUtil.isExist(cssRoles, Long.parseLong(WsConsts.CssDirRole))){
            modelAndView = new ModelAndView("/workorderSendManage/createWorkOrder");
            modelAndView.addObject("presentTime", DateUtil.formatDate(new Date(), "HH:mm"));
            // 获取工单的任务类型
            try {
                String taskTypeStr = cssWorkFormWsService.getTaskType();
                modelAndView.addObject("taskTypeStr", JSONObject.fromObject("{taskTypeStr:" + taskTypeStr + "}"));
            } catch (Exception e) {
                e.printStackTrace();
                AjaxUtils.renderFailure("新建工单页面,获取工单任务类型出错", "", response);
            }
        }else{
            modelAndView = new ModelAndView("/common/nocheck"); 
            modelAndView.addObject("msg", "需配置服务站主任权限，请联系开发部.");
        }
        return modelAndView;
    }

    /**
     * 页面加载按钮获取设备序列号相关信息
     * 
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/cp/sendManage/loadOrder")
    public String loadOrder(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获取当前人会话，拿到用户姓名和登录名
            String loginName = LoginUser.getSessionUser().getLoginName();
            String serialNumber = request.getParameter("serialNumber");// 序列号
            com.grgbanking.webservice.bean.WsResultBean eqResultBean = null;
            try{
                eqResultBean = workTaskService.getEquipmentInfo(loginName, serialNumber, Consts.CLIENT_TYPE);
            } catch (Exception e) {
                e.printStackTrace();
                map.put("equipmentResult", false);
                map.put("equipmentMsg", e.getMessage().replace("com.grgbanking.framework.exception.ServiceException:", ""));
                return JSON.toJSONString(map);
            }
            if(eqResultBean != null){
                JSONObject jsonResult = JSONObject.fromObject(eqResultBean);
                if(eqResultBean.getReturnResult()){
                    map.put("equipment", JSONObject.fromObject(jsonResult.getString("returnMessage")));
                    map.put("equipmentResult", true);
                }else{
                    map.put("equipment", "");
                    map.put("equipmentResult", false);
                    map.put("equipmentMsg", eqResultBean.getErrorMessage());
                }
            }else{
                map.put("equipment", "");
                map.put("equipmentResult", false);
                map.put("equipmentMsg", "系统繁忙，获取设备信息失败！");
            }

           // 获取设备报修人
            String equipmentContact = webEquipmentService.getEquipmentContact(serialNumber);
            JSONArray data = JSONArray.fromObject(equipmentContact);
            map.put("equipmentContact", data);
            try {
                // 获取可派单工程师
                com.grgbanking.webservice.bean.WsResultBean resultBean = workTaskService.getDepartmentUserList(loginName, Consts.CLIENT_TYPE);
                if (resultBean.getReturnResult()) {
                    if (resultBean.getReturnMessage() == null || "".equals(resultBean.getReturnMessage())) {
                        map.put("returnResult", false);
                        map.put("errorMessage", "没有找到对应的工程师.请输检查设备序列号.");
                    } else {
                        map.put("returnResult", true);
                        JSONArray datas = JSONArray.fromObject(resultBean.getReturnMessage());
                        map.put("returnMessage", datas);
                    }
                } else {
                    map.put("returnResult", false);
                    map.put("errorMessage", resultBean.getErrorMessage());
                }

            } catch (Exception e) {
                e.printStackTrace();
                map.put("returnResult", false);
                map.put("errorMessage", e.getMessage().replace("com.grgbanking.framework.exception.ServiceException:", ""));
                return JSON.toJSONString(map);
            }
            //未完成列表
            com.grgbanking.webservice.bean.WsResultBean toDoWorkTaskResult = workTaskService.getToDoWorkTask(loginName, serialNumber, Consts.CLIENT_TYPE);
            if(toDoWorkTaskResult != null){
                JSONObject jsonResult = JSONObject.fromObject(toDoWorkTaskResult);
                if(toDoWorkTaskResult.getReturnResult()){
                    map.put("toDoWorkTask", JSONArray.fromObject(jsonResult.getString("returnMessage")));
                    map.put("toDoWorkTaskResult",true);
                }else{
                    map.put("toDoWorkTask", "");
                    map.put("toDoWorkTaskResult",false);
                    map.put("toDoWorkTaskMsg",toDoWorkTaskResult.getErrorMessage());
                }
            }else{
                map.put("toDoWorkTask", "");
                map.put("toDoWorkTaskResult",false);
            }
            return JSON.toJSONString(map);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("equipmentResult", false);
            map.put("equipmentMsg", e.getMessage().replace("com.grgbanking.framework.exception.ServiceException:", ""));
            return JSON.toJSONString(map);
        }

    }

    /**
     * 保存工单
     * 
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/cp/sendManage/saveOrder")
    public String saveOrder(String isSend, HttpServletRequest request, HttpServletResponse response) {
        PrintWriter pw = null;
        try {
            response.setContentType("text/html;charset=utf-8");// 设置中文问题
            pw = response.getWriter();
        } catch (IOException Ioex) {
            logger.error(Ioex.toString());
        }
        try {
            String serialNumber = request.getParameter("serialNumber");// 序列号
            String equipmentModel = request.getParameter("equipmentModel");// 序列号
            String customsName = request.getParameter("customsName");// 序列号
            String branchName = request.getParameter("branchName");// 序列号
            String installAddress = request.getParameter("installAddress");// 序列号
            String branchPrincipal = request.getParameter("branchPrincipal");// 序列号
            String branchPrincipalTel = request.getParameter("branchPrincipalTel");// 序列号
            String reportTime = request.getParameter("reportTime");// 序列号
            String appointmentDate = request.getParameter("appointmentDate");// 序列号
            String taskType = request.getParameter("taskType");// 序列号
            String taskTypeName = request.getParameter("taskTypeName");// 序列号
            String engineerId = request.getParameter("engineerId");// 序列号
            String engineerName = request.getParameter("engineerName");// 序列号
            String taskContent = request.getParameter("taskContent");// 序列号
            String id = request.getParameter("id");// 序列号
            String deviceStatus = request.getParameter("deviceStatus");// 序列号
            String createTime = request.getParameter("createTime");// 序列号
            String tempSerialNumber = request.getParameter("tempSerialNumber");
            Long newId = null;
            Date newCreateTime = null;
            if (id != null && !"".equals(id)) {
                newId = Long.parseLong(id);
            }
            if (isSend == null || "".equals(isSend)) {
                isSend = "0";// "1"为一派出的工单,"0"为未派出的工单
            }
            String userCode = LoginUser.getSessionUser().getUserCode();
            Date newReportTime = DateUtil.parseDate(reportTime, "yyyy-MM-dd HH:mm");
            Date newAppointmentDate = DateUtil.parseDate(appointmentDate, "yyyy-MM-dd HH:mm");
            if (createTime == null || "".equals(createTime)) {
                newCreateTime = new Date();
            } else {
                newCreateTime = DateUtil.parseDate(createTime, "yyyy-MM-dd HH:mm");
            }
            SendOrderBean sendOrderBean = new SendOrderBean(newId, isSend, userCode, serialNumber, equipmentModel,
                    customsName, branchName, installAddress, branchPrincipal, branchPrincipalTel, newReportTime,
                    newAppointmentDate, taskType, taskTypeName, engineerId, engineerName, taskContent, newCreateTime,
                    deviceStatus, tempSerialNumber);
            sendOrderManagerService.doSaveIsUpdateOrderBean(sendOrderBean);
            Map<String, String> map = new HashMap<String, String>();
            map.put("isSave", "save");
            JSONObject jObject = JSONObject.fromObject(map);
            response.setHeader("Content-type", "application/json");
            pw.print(jObject);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
            AjaxUtils.renderFailure("保存工单报错", "", response);
            return null;
        }
        return null;
    }
    
    /**
     * 待派工单页面
     * 
     * @return
     */
    @RequestMapping(value = "/cp/ouath/sendManage/waitOrderList")
    public ModelAndView waitList(HttpServletRequest request, HttpServletResponse response) {
      //登录用户
        WxUser wxUser = LoginUser.getSessionUser();
        /*MobileUserRole mobileUserRole = new MobileUserRole();
        mobileUserRole.setRoleId(Long.parseLong(WsConsts.SERVICE_DIRECTOR));
        mobileUserRole.setUserId(wxUser.getId());
        //返回结果，false表示不是服务站主任，true则是。
        Boolean isExist = mobileUserRoleService.IsExistUserRole(mobileUserRole);*/
        List<BaseRoleUser> cssRoles = baseRoleUserService.queryByLoginName(wxUser.getLoginName());
        ModelAndView modelAndView = null;
        if(BaseUserRoleUtil.isExist(cssRoles, Long.parseLong(WsConsts.CssDirRole))){
            modelAndView = new ModelAndView("/workorderSendManage/waitOrderList");
        }else{
            modelAndView = new ModelAndView("/common/nocheck");
            modelAndView.addObject("msg", "需配置服务站主任权限，请联系开发部.");
        }
        return modelAndView;
        
    }

    /**
     * 待派工单
     * 
     * @author
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cp/sendManage/pageWaitOrderlist")
    public String pageWaitOrderlist(String pageNum, String serialNumber, String branchName, HttpServletRequest request, HttpServletResponse response) {

        WsPage wsPage = new WsPage(Consts.DEFAULT_ROWS, pageNum);
        JSONObject result = new JSONObject();
        JSONArray list = new JSONArray();
        Integer pageCount = 0;
        try {
            WorkTaskBean queryBean = new WorkTaskBean();
            String[] sa = {"未分配","待处理"};
            queryBean.setStatusArray(sa);
            
            if(StringUtils.isNotBlank(serialNumber)){
                queryBean.setSerialNumber(serialNumber);
            }
            if(StringUtils.isNotBlank(branchName)){
                queryBean.setBranchName(branchName);
            }
            com.grgbanking.webservice.bean.WsResultBean resultBean = workTaskService.queryWorkTask(LoginUser.getSessionUser().getLoginName(), wsPage, queryBean, Consts.CLIENT_TYPE);
            if(resultBean.getReturnResult()){
                JSONObject jsonResult = JSONObject.fromObject(resultBean);
                if(null != jsonResult){
                    String rs = jsonResult.getString("returnMessage");
                    JSONObject jn = JSONObject.fromObject(rs);
                    if(null != jn){
                        list = jn.getJSONArray("list");
                        pageCount = jn.getInt("pageCount");
                    }
                    
                }
                result.put("list", list);
                result.put("pageCount", pageCount);
            }
            result.put("returnResult", resultBean.getReturnResult());
            result.put("errorMessage", resultBean.getErrorMessage());
            
        } catch (Exception e) {
            e.printStackTrace();
            result.put("returnResult", false);
            result.put("errorMessage", e.getMessage());
        }
        return JSON.toJSONString(result);
    }

    /**
     * 待派工单基本信息页面
     * 
     * @return
     * @throws Exception
     * @throws IOException
     */
    @RequestMapping(value = "/cp/sendManage/waitOrderDetail")
    public ModelAndView waitOrderDetail(HttpServletRequest request, HttpServletResponse response) {
        
        WxUser wxUser = LoginUser.getSessionUser();
        ModelAndView modelAndView = new ModelAndView("/workorderSendManage/waitOrderDetail");
        
        Map<String, Object> result = new HashMap<String, Object>();
        
        JSONObject taskInfo = new JSONObject();
        taskInfo.element("serialNumber", request.getParameter("serialNumber"));
        taskInfo.element("customerName", request.getParameter("customerName"));
        taskInfo.element("branchName", request.getParameter("branchName"));
        taskInfo.element("installAddress", request.getParameter("installAddress"));
        taskInfo.element("equipmentId", request.getParameter("equipmentId"));
        taskInfo.element("equipmentDeptId", request.getParameter("equipmentDeptId"));
        taskInfo.element("taskId", request.getParameter("taskId"));
        taskInfo.element("taskType", request.getParameter("taskType"));
        taskInfo.element("taskLevel", request.getParameter("taskLevel"));
        taskInfo.element("engineerId", request.getParameter("engineerId"));
        taskInfo.element("engineerName", request.getParameter("engineerName"));
        taskInfo.element("taskContent", request.getParameter("taskContent"));
        taskInfo.element("reportTime", request.getParameter("reportTime"));
        taskInfo.element("appointmentTime", request.getParameter("appointmentTime"));
        taskInfo.element("taskSource", request.getParameter("taskSource"));
        taskInfo.element("repairsContactId", request.getParameter("repairsContactId"));
        taskInfo.element("repairsManName", request.getParameter("repairsManName"));
        taskInfo.element("repairsMoblie", request.getParameter("repairsMoblie"));
        taskInfo.element("repairsTelephone", request.getParameter("repairsTelephone"));
        taskInfo.element("receiveContactId", request.getParameter("receiveContactId"));
        taskInfo.element("receiveManName", request.getParameter("receiveManName"));
        taskInfo.element("receiveMoblie", request.getParameter("receiveMoblie"));
        taskInfo.element("receiveTelephone", request.getParameter("receiveTelephone"));
        
        result.put("taskInfo", taskInfo);
        /*String serialNumber = request.getParameter("serialNumber");
        String customerName = request.getParameter("customerName");
        String branchName = request.getParameter("branchName");
        String installAddress = request.getParameter("installAddress");
        String taskId = request.getParameter("taskId");
        String taskType = request.getParameter("taskType");
        String reportTime = request.getParameter("reportTime");
        String appointmentTime = request.getParameter("appointmentTime");
        String taskSource = request.getParameter("taskSource");*/

        /*String serialNumber = request.getParameter("serialNumber");
        String customerName = request.getParameter("customerName");
        String branchName = request.getParameter("branchName");
        String installAddress = request.getParameter("installAddress");
        String taskId = request.getParameter("taskId");
        String taskType = request.getParameter("taskType");
        String reportTime = request.getParameter("reportTime");
        String appointmentTime = request.getParameter("appointmentTime");
        String taskSource = request.getParameter("taskSource");*/
        
        // 加载工程师
        try {
            // 获取可派单工程师
            com.grgbanking.webservice.bean.WsResultBean resultBean = workTaskService.getDepartmentUserList(wxUser.getLoginName(), Consts.CLIENT_TYPE);
            if (resultBean.getReturnResult()) {
                if (resultBean.getReturnMessage() == null || "".equals(resultBean.getReturnMessage())) {
                    result.put("engineerResult", false);
                    result.put("engineerErrorMessage", "没有找到对应的工程师.请输检查设备序列号.");
                } else {
                    result.put("engineerResult", true);
                    JSONArray datas = JSONArray.fromObject(resultBean.getReturnMessage());
                    result.put("engineerList", datas);
                }
            } else {
                result.put("engineerResult", false);
                result.put("engineerResult", resultBean.getErrorMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.put("engineerResult", false);
            result.put("engineerResult", e.getMessage());
        }

        
        // 获取设备保修人
        String equipmentContact = webEquipmentService.getEquipmentContact(request.getParameter("serialNumber"));
        JSONArray data = JSONArray.fromObject(equipmentContact);
        result.put("equipmentContact", data);
        
        //未完成列表
        com.grgbanking.webservice.bean.WsResultBean toDoWorkTaskResult = workTaskService.getToDoWorkTask(wxUser.getLoginName(), request.getParameter("serialNumber"), Consts.CLIENT_TYPE);
        if(toDoWorkTaskResult != null){
            JSONObject jsonResult = JSONObject.fromObject(toDoWorkTaskResult);
            if(toDoWorkTaskResult.getReturnResult()){
                result.put("toDoWorkTask", JSONArray.fromObject(jsonResult.getString("returnMessage")));
                result.put("toDoWorkTaskResult",true);
            }else{
                result.put("toDoWorkTask", "");
                result.put("toDoWorkTaskResult",false);
                result.put("toDoWorkTaskMsg",toDoWorkTaskResult.getErrorMessage());
            }
        }else{
            result.put("toDoWorkTask", "");
            result.put("toDoWorkTaskResult",false);
        }

        modelAndView.addObject("result", result);
        

        return modelAndView;

    }

    /**
     * 立即派单
     * @Title: sendOrder
     * @param isSend
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cp/sendManage/sendOrder")
    public String sendOrder(String isSend,HttpServletRequest request, HttpServletResponse response) {
        
        WxUser user = LoginUser.getSessionUser();
        String[] taskIds = null;
        if(StringUtils.isNotBlank(request.getParameter("taskIds"))){
            taskIds = request.getParameter("taskIds").split(",");
        }
        //是否立即派单
        String needCreateWorkform = "Y";
        
        //任务bean
        WorkTaskBean workTaskBean = new WorkTaskBean();
        workTaskBean.setEngineerId(Integer.parseInt(request.getParameter("engineerId")));
        workTaskBean.setEngineerName(request.getParameter("engineerName"));
        workTaskBean.setSerialNumber(request.getParameter("serialNumber"));
        workTaskBean.setEquipmentId(Integer.parseInt(request.getParameter("equipmentId")));
        workTaskBean.setTaskType(request.getParameter("taskType"));
        workTaskBean.setBranchName(request.getParameter("branchName"));
        workTaskBean.setInstallAddress(request.getParameter("installAddress"));
        workTaskBean.setEquipmentDeptId(Integer.parseInt(request.getParameter("equipmentDeptId")));
        //报修人接待人
//        workTaskBean.setRepairsContactId(20916);
        workTaskBean.setRepairsManName(request.getParameter("repairsManName"));
        workTaskBean.setRepairsTelephone(request.getParameter("repairsTelephone"));
        workTaskBean.setRepairsMoblie(request.getParameter("repairsMoblie"));
//        workTaskBean.setReceiveContactId(351827);
        workTaskBean.setReceiveManName(request.getParameter("receiveManName"));
        workTaskBean.setReceiveMoblie(request.getParameter("receiveMoblie"));
        workTaskBean.setReceiveTelephone(request.getParameter("receiveTelephone"));
        workTaskBean.setTaskContent(request.getParameter("taskContent"));
        workTaskBean.setTaskLevel(request.getParameter("taskLevel"));
        
        workTaskBean.setAppointmentTime(DateUtil.parseDate(request.getParameter("appointmentDate"), "yyyy-MM-dd HH:mm:ss"));
        workTaskBean.setReportTime(DateUtil.parseDate(request.getParameter("reportTime"), "yyyy-MM-dd HH:mm:ss"));
        
        com.grgbanking.webservice.bean.WsResultBean resultBean =new com.grgbanking.webservice.bean.WsResultBean();
        try {
            resultBean = workTaskService.doSaveWorktask(user.getLoginName(),workTaskBean, 
                            needCreateWorkform, taskIds, Consts.CLIENT_TYPE);
        } catch (Exception e) {
            logger.error("调用派单接口报错："+e.getMessage());
            resultBean.setErrorMessage(e.getMessage().replace("com.grgbanking.framework.exception.ServiceException:", ""));
            resultBean.setReturnResult(false);
        }
        
        return JSON.toJSONString(resultBean);
    }
    
    /**
     * 立即分配任务
     * 
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/cp/sendManage/sendDispatch")
    public String sendDispatch(String isSend,HttpServletRequest request, HttpServletResponse response) {
        WxUser user = LoginUser.getSessionUser();
        String[] taskIds = null;
        if(StringUtils.isNotBlank(request.getParameter("taskIds"))){
            taskIds = request.getParameter("taskIds").split(",");
        }
        //是否派单
        String needCreateWorkform = "Y";
        
        //任务bean
        WorkTaskBean workTaskBean = new WorkTaskBean();
        workTaskBean.setEngineerId(Integer.parseInt(request.getParameter("engineerId")));
        workTaskBean.setEngineerName(request.getParameter("engineerName"));
        workTaskBean.setSerialNumber(request.getParameter("serialNumber"));
        workTaskBean.setEquipmentId(Integer.parseInt(request.getParameter("equipmentId")));
        workTaskBean.setTaskType(request.getParameter("taskType"));
        workTaskBean.setTaskId(Integer.parseInt(request.getParameter("taskId")));
        workTaskBean.setBranchName(request.getParameter("branchName"));
        workTaskBean.setInstallAddress(request.getParameter("installAddress"));
        workTaskBean.setEquipmentDeptId(Integer.parseInt(request.getParameter("equipmentDeptId")));
        //报修人接待人
//        workTaskBean.setRepairsContactId(20916);
        workTaskBean.setRepairsManName(request.getParameter("repairsManName"));
        workTaskBean.setRepairsTelephone(request.getParameter("repairsTelephone"));
        workTaskBean.setRepairsMoblie(request.getParameter("repairsMoblie"));
//        workTaskBean.setReceiveContactId(351827);
        workTaskBean.setReceiveManName(request.getParameter("receiveManName"));
        workTaskBean.setReceiveMoblie(request.getParameter("receiveMoblie"));
        workTaskBean.setReceiveTelephone(request.getParameter("receiveTelephone"));
        workTaskBean.setTaskContent(request.getParameter("taskContent"));
        workTaskBean.setTaskLevel(request.getParameter("taskLevel"));
        
        workTaskBean.setAppointmentTime(DateUtil.parseDate(request.getParameter("appointmentDate"), "yyyy-MM-dd HH:mm"));
        workTaskBean.setReportTime(DateUtil.parseDate(request.getParameter("reportTime"), "yyyy-MM-dd HH:mm"));
        
        com.grgbanking.webservice.bean.WsResultBean resultBean =new com.grgbanking.webservice.bean.WsResultBean();
        try {
            resultBean = workTaskService.doSaveDispatch(user.getLoginName(), workTaskBean, needCreateWorkform, request.getParameter("engineerId"), taskIds, request.getParameter("appointmentDate"), Consts.CLIENT_TYPE);
            //doSaveWorktask(user.getLoginName(),workTaskBean, 
                            //needCreateWorkform, taskIds, Consts.CLIENT_TYPE);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            resultBean.setErrorMessage(e.getMessage().replace("com.grgbanking.framework.exception.ServiceException:", ""));
            resultBean.setReturnResult(false);
        }
        
        return JSON.toJSONString(resultBean);
        
    }

    /**
     * 删除工单
     * 
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/cp/sendManage/deleteOrder")
    public String deleteOrder(HttpServletRequest request, HttpServletResponse response) {
        com.grgbanking.webservice.bean.WsResultBean wsResult = null;
        try {
            wsResult = workTaskService.doDeletWorkTask(LoginUser.getSessionUser().getLoginName(), 
                    Integer.parseInt(request.getParameter("taskId")), Consts.CLIENT_TYPE);
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除工单报错！");
        }
        return JSON.toJSONString(wsResult);
    }

    /**
     * 已派工单页面 -----------------------
     * 
     * @return
     */
    @RequestMapping(value = "/cp/ouath/sendManage/sentOrderList")
    public ModelAndView sentOrderList(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = null;
        WxUser wxUser = LoginUser.getSessionUser();
        /*MobileUserRole mobileUserRole = new MobileUserRole();
        mobileUserRole.setRoleId(Long.parseLong(WsConsts.SERVICE_DIRECTOR));
        mobileUserRole.setUserId(wxUser.getId());
        //返回结果，false表示不是服务站主任，true则是。
        Boolean isExist = mobileUserRoleService.IsExistUserRole(mobileUserRole);*/
        List<BaseRoleUser> cssRoles = baseRoleUserService.queryByLoginName(wxUser.getLoginName());
        if(BaseUserRoleUtil.isExist(cssRoles, Long.parseLong(WsConsts.CssDirRole))){
            modelAndView = new ModelAndView("/workorderSendManage/sentOrderList");
            // 获取可派单工程师
            com.grgbanking.webservice.bean.WsResultBean resultBean = workTaskService.getDepartmentUserList(wxUser.getLoginName(), Consts.CLIENT_TYPE);
            if (resultBean.getReturnResult()) {
                if (resultBean.getReturnMessage() == null || "".equals(resultBean.getReturnMessage())) {
                    modelAndView.addObject("returnResult", false);
                    modelAndView.addObject("errorMessage", "没有找到对应的工程师.请输检查设备序列号.");
                } else {
                    modelAndView.addObject("returnResult", true);
                    JSONArray datas = JSONArray.fromObject(resultBean.getReturnMessage());
                    modelAndView.addObject("engineerList", datas);
                }
            } else {
                modelAndView.addObject("returnResult", false);
                modelAndView.addObject("errorMessage", resultBean.getErrorMessage());
            }
        }else{
            modelAndView = new ModelAndView("/common/nocheck");
            modelAndView.addObject("msg", "需配置服务站主任权限，请联系开发部.");
        }
        return modelAndView;
    }

    /**
     * 已派工单查询结果(根据参数查询已派工单列表)
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cp/sendManage/orderListByParam")
    public String orderListByParam(HttpServletRequest request, HttpServletResponse response) {
        String loginName = LoginUser.getSessionUser().getLoginName();
        String pageNum = request.getParameter("pageNum"); 
        String serialNumber = request.getParameter("serialNumber"); 
        String poNumber = request.getParameter("poNumber"); 
        String branchName = request.getParameter("branchName");
        String workFormStatus = request.getParameter("workFormStatus");
        String engineerId = request.getParameter("engineerId");
        WsPage wsPage = new WsPage(Consts.DEFAULT_ROWS, pageNum);
        WorkFormMonitorBean queryBean = new WorkFormMonitorBean();
        queryBean.setPoNumbers(poNumber);
        queryBean.setStatus(workFormStatus);
        queryBean.setEqtBranchName(branchName);
        queryBean.setSerialNumber(serialNumber);
        queryBean.setEngineerId(engineerId);
        queryBean.setCreateType("CSS");
        com.grgbanking.webservice.bean.WsResultBean resultBean  = null;
        JSONObject result = new JSONObject();
        try {
            resultBean = workTaskService.getWorkformMonitorList(loginName, wsPage, queryBean);
            if(resultBean != null){
                JSONObject jsonResult = JSONObject.fromObject(resultBean);
                if(null != jsonResult){
                    String rs = jsonResult.getString("returnMessage");
                    JSONObject jn = JSONObject.fromObject(rs);
                    if(null != jn){
                        result.element("list", jn.getJSONArray("list"));
                        result.element("pageCount", jn.getInt("pageCount"));
                    }
                    
                }
                result.element("returnResult", resultBean.getReturnResult());
                result.element("returnMessage", resultBean.getReturnMessage());
                result.element("errorMessage", resultBean.getErrorMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.element("errorMessage", e.getMessage());
        }
        return JSON.toJSONString(result);
    }

    /**
     * 工单基本信息页面
     * 
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/cp/sendManage/sentOrderDetail")
    public ModelAndView sentOrderDetail(HttpServletRequest request, HttpServletResponse response) {
        String loginName = LoginUser.getSessionUser().getLoginName();
        String equipmentId = request.getParameter("equipmentId");
        String workformId = request.getParameter("workformId");
        String poNumber =request.getParameter("poNumber");
        String warningCtrl ="";
        try {
            if(request.getParameter("warningCtrl") != null){
                warningCtrl = java.net.URLDecoder.decode(request.getParameter("warningCtrl"),"UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView("/workorderSendManage/sentOrderDetail");
//        JSONObject result = new JSONObject();
        try {
            Order order = orderService.findOrderBypoNumber(poNumber);
            WsPendingWorkformDetailBean bean = new WsPendingWorkformDetailBean();
            if (order != null) {// && !detailType.equals("2") && !detailType.equals("4")
                bean = (WsPendingWorkformDetailBean) JSON.parseObject(order.getContent(),
                        WsPendingWorkformDetailBean.class);
                modelAndView.addObject("status", 1);
            }else{
                WsResultFillWorkFormBean wsBean = workOrderService.getWorkFormDetail(loginName, equipmentId, workformId, poNumber,"2");
                modelAndView.addObject("status", wsBean.getStatus());
                modelAndView.addObject("errMsg", wsBean.getErrMsg());
                if(wsBean != null){
                    bean = wsBean.getWsPendingWorkformDetailBean();
                }
            }
            modelAndView.addObject("workdetail", bean);
            modelAndView.addObject("warningCtrl", warningCtrl);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("工单详情查询报错:" + e.toString());
            modelAndView.addObject("errMsg", e.getMessage());
        }
//        modelAndView.addObject("result", result);
        return modelAndView;
    }
}
