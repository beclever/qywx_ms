package com.grgbanking.core.controller.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.grgbanking.common.consts.Consts;
import com.grgbanking.common.enums.FaultType;
import com.grgbanking.common.utils.EhcacheUtils;
import com.grgbanking.common.utils.LoginUser;
import com.grgbanking.core.controller.BaseController;
import com.grgbanking.core.entity.ResultJson;
import com.grgbanking.core.entity.stockbean.WsAocBaseMaterialBean;
import com.grgbanking.core.entity.user.Order;
import com.grgbanking.core.entity.user.WxUser;
import com.grgbanking.core.entity.workorder.WsPendingWorkTaskJsonListBean;
import com.grgbanking.core.entity.workorder.WsPendingWorkformDetailBean;
import com.grgbanking.core.entity.workorder.WsProblemPartBean;
import com.grgbanking.core.entity.workorder.WsResultCityBean;
import com.grgbanking.core.entity.workorder.WsResultFinishWorkFormBean;
import com.grgbanking.core.entity.workorder.WsResultProblemPartBean;
import com.grgbanking.core.entity.workorder.WsSjTaskReplaceBean;
import com.grgbanking.core.entity.workorder.WsWorkFormReplaceBean;
import com.grgbanking.core.entity.workorder.WsWorkformTogetherPersonBean;
import com.grgbanking.core.entity.ws.WsBorrowInfoBean;
import com.grgbanking.core.entity.ws.WsResultBean;
import com.grgbanking.core.entity.ws.WsResultFillWorkFormBean;
import com.grgbanking.core.entity.ws.WsResultWorkFormModuleReplaceOldBean;
import com.grgbanking.core.service.sparepart.SparepartService;
import com.grgbanking.core.service.user.OrderService;
import com.grgbanking.core.service.workorder.WorkFormEngineService;
import com.grgbanking.core.service.workorder.WorkOrderService;
import com.grgbanking.webservice.sparepart.WebInfoService;

/**
 * 
 * 版权所有：2015-GRGBANKING 项目名称：Qywx_ms
 * 
 * 类描述：模块弹出、零件弹出、故障部位弹出、升级前升级后等控制前
 * 类名称：com.grgbanking.core.controller.common.CommonController 创建人：TXH
 * 创建时间：2015-7-16 下午5:20:03 修改人： 修改时间：2015-7-16 下午5:20:03 修改备注：
 * 
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/cp")
public class TaskCommonController extends BaseController {
    @Resource(name = "workOrderService")
    private WorkOrderService workOrderService;
    
    @Resource(name = "workFormEngineService")
    private WorkFormEngineService workFormEngineService;

    @Resource(name = "sparepartService")
    private SparepartService sparepartService;
    
    @Resource(name = "webInfoService")
    private WebInfoService webInfoService;

    @Resource(name = "orderService")
    private OrderService orderService;

    /**
     * 模块弹出窗
     */
    @RequestMapping(value = "/module/worksparepath")
    public String orderSpareReplace(Model model, String serialNumber, HttpServletRequest request){
        String divname = request.getParameter("divname");// 要显示的值
        //String oldSerialName = request.getParameter("oldSerialName");// 原物料名称
        String oldSerialName=null;// 原物料名称
        try {
            if(request.getParameter("oldSerialName") != null){
                oldSerialName = java.net.URLDecoder.decode(request.getParameter("oldSerialName"),"UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String oldMaterialCode = request.getParameter("oldMaterialCode");// 物料编码
        String oldSerialNumber = request.getParameter("oldSerialNumber");// 条码编码
        String oldHardwareVersion = request.getParameter("oldHardwareVersion");// 旧硬件版本
        String oldSoftwareVersion = request.getParameter("oldSoftwareVersion");// 旧软件版本
        String oldModuleType = request.getParameter("oldModuleType"); // 旧模块类型
        String equipmentConfigId = request.getParameter("equipmentConfigId");// 设备备件ID
        //String newSerialName = request.getParameter("newSerialName");// 新物料名称
        String newSerialName=null;// 新物料名称
        try {
            if(request.getParameter("newSerialName") != null){
                newSerialName = java.net.URLDecoder.decode(request.getParameter("newSerialName"),"UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String newMaterialCode = request.getParameter("newMaterialCode");// 新物料编码
        String newSerialNumber = request.getParameter("newSerialNumber");// 条形编码
        String newHardwareVersion = request.getParameter("newHardwareVersion");// 新硬件版本
        String newSoftwareVersion = request.getParameter("newSoftwareVersion");// 新软件版本
        String newModuleType = request.getParameter("newModuleType"); // 新模块类型
        String workformId = request.getParameter("workformId");
        String poNumber = request.getParameter("poNumber");
        
        model.addAttribute("oldSerialName", oldSerialName);

        model.addAttribute("serialNumber", serialNumber);// 序列号
        model.addAttribute("oldMaterialCode", oldMaterialCode);
        model.addAttribute("oldSerialNumber", oldSerialNumber);
        model.addAttribute("oldHardwareVersion", oldHardwareVersion);
        model.addAttribute("oldSoftwareVersion", oldSoftwareVersion);
        model.addAttribute("oldModuleType", oldModuleType);
        model.addAttribute("newModuleType", newModuleType);
        model.addAttribute("equipmentConfigId", equipmentConfigId);
        model.addAttribute("newSerialName", newSerialName);
        model.addAttribute("newMaterialCode", newMaterialCode);
        model.addAttribute("newSerialNumber", newSerialNumber);
        model.addAttribute("newHardwareVersion", newHardwareVersion);
        model.addAttribute("newSoftwareVersion", newSoftwareVersion);
        model.addAttribute("newModuleType", newModuleType);
        model.addAttribute("divname", divname);
        model.addAttribute("workformId", workformId);
        model.addAttribute("poNumber", poNumber);
        logger.debug("模块替换——新模块——物料名称【" + newSerialName + "】");

        return "/workorder/workOrderspare1";
    }

    /**
     * 旧模块
     * 
     * @throws Exception
     */

    @RequestMapping(value = "/module/oldsparepath")
    public String orderSpareInfo(Model model, String serialNumber, String divname, String workformId, HttpServletRequest request) {
        WxUser user = LoginUser.getSessionUser();
        WsResultWorkFormModuleReplaceOldBean result = null;
        List<WsWorkFormReplaceBean> oldWorkFormReplaceList = null;
        try {
            startTime = new Date();
            result = this.workOrderService.queryWorkFormModuleReplaceOld(user.getLoginName(), serialNumber);
            logger.warn("{}调用旧模块接口消耗时间：{}s", user.getLoginName(), getDiffTime(startTime));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用旧模块接口queryWorkFormModuleReplaceOld出错"+e.toString());
        }
        if (result != null) {
            oldWorkFormReplaceList = result.getOldWorkFormReplaceList();
        }
        String poNumber = request.getParameter("poNumber");
        model.addAttribute("divname", divname);
        model.addAttribute("oldWorkFormReplaceList", oldWorkFormReplaceList);
        model.addAttribute("workformId", workformId);
        model.addAttribute("poNumber", poNumber);
        model.addAttribute("serialNumber", serialNumber);// 序列号
        model.addAttribute("result", result);
        return "/workorder/oldspare1";
    }
    
    /**
     * 旧模块
     * 
     * @throws Exception
     */
    /*@ResponseBody
    @RequestMapping(value = "/module/oldsparepath")
    public String orderSpareInfo(Model model, String serialNumber, String divname, String workformId) {
        WxUser user = LoginUser.getSessionUser();
        WsResultWorkFormModuleReplaceOldBean result = null;
        List<WsWorkFormReplaceBean> list = null;
        try {
            result = this.workOrderService.queryWorkFormModuleReplaceOld(user.getLoginName(), serialNumber);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("queryWorkFormModuleReplaceOld出错");
        }
        if (result != null) {
            list = result.getOldWorkFormReplaceList();
        }
        model.addAttribute("divname", divname);
        model.addAttribute("oldWorkFormReplaceList", list);
        model.addAttribute("workformId", workformId);
        model.addAttribute("serialNumber", serialNumber);// 序列号
        model.addAttribute("result", result);
        return JSON.toJSONString(list);
    }*/


    /**
     * 新模块
     * 
     * @throws Exception
     */
    /*@ResponseBody
    @RequestMapping(value = "/module/newsparepath")
    public String newSpareInfo(HttpServletRequest request, Model model) throws Exception {
        String oldSerialName = request.getParameter("oldSerialName");// 原物料
        String oldMaterialCode = request.getParameter("oldMaterialCode");// 物料编码
        String oldSerialNumber = request.getParameter("oldSerialNumber");// 条码编码
        String oldHardwareVersion = request.getParameter("oldHardwareVersion");// 旧硬件版本
        String oldSoftwareVersion = request.getParameter("oldSoftwareVersion");// 旧软件版本
        String equipmentConfigId = request.getParameter("equipmentConfigId");// 设备备件ID
        String oldModuleType = request.getParameter("oldModuleType"); // 旧模块类型
        String workformId = request.getParameter("workformId");
        String serialNumber = request.getParameter("serialNumber");

        String divname = request.getParameter("divname");
        String[] moduleLevels = { "A", "B" };// 因模块与零备件互相替换，导致数据错乱,现在不支持交叉录入
        String userCode = LoginUser.getSessionUser().getUserCode();
        List<WsBorrowInfoBean> list = null;
        try {
            list = this.workFormEngineService.findBorrowInfoList(userCode, moduleLevels, Integer.parseInt(workformId), "PHONE");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("aocFindBorrowInfoList(getUserInfo(session).getLoginName(), moduleLevels, CommonCast.client_phone)出错");
            throw e;
        }
        model.addAttribute("newsparelist", list);
        model.addAttribute("oldSerialName", oldSerialName);
        model.addAttribute("oldMaterialCode", oldMaterialCode);
        model.addAttribute("oldSerialNumber", oldSerialNumber);
        model.addAttribute("oldHardwareVersion", oldHardwareVersion);
        model.addAttribute("oldSoftwareVersion", oldSoftwareVersion);
        model.addAttribute("equipmentConfigId", equipmentConfigId);
        model.addAttribute("oldModuleType", oldModuleType);
        model.addAttribute("divname", divname);
        model.addAttribute("workformId", workformId);
        model.addAttribute("serialNumber", serialNumber);
        return JSON.toJSONString(list);
    }*/
    
    /**
     * 新模块
     * 
     * @throws Exception
     */
    @RequestMapping(value = "/module/newsparepath")
    public String newSpareInfo(HttpServletRequest request, Model model) throws Exception {
        String oldSerialName = request.getParameter("oldSerialName");// 原物料
        String oldMaterialCode = request.getParameter("oldMaterialCode");// 物料编码
        String oldSerialNumber = request.getParameter("oldSerialNumber");// 条码编码
        String oldHardwareVersion = request.getParameter("oldHardwareVersion");// 旧硬件版本
        String oldSoftwareVersion = request.getParameter("oldSoftwareVersion");// 旧软件版本
        String equipmentConfigId = request.getParameter("equipmentConfigId");// 设备备件ID
        String oldModuleType = request.getParameter("oldModuleType"); // 旧模块类型
        String workformId = request.getParameter("workformId");
        String serialNumber = request.getParameter("serialNumber");
        String poNumber = request.getParameter("poNumber");

        String divname = request.getParameter("divname");
        String[] moduleLevels = { "A", "B" };// 因模块与零备件互相替换，导致数据错乱,现在不支持交叉录入
        String userCode = LoginUser.getSessionUser().getUserCode();
        List<WsBorrowInfoBean> brrowlist = null;
        try {
            WxUser wxUser = LoginUser.getSessionUser();
            startTime = new Date();
            brrowlist = this.workFormEngineService.findBorrowInfoList(userCode, moduleLevels, Integer.parseInt(workformId), "PHONE");
            logger.warn("{}调用新模块aocFindBorrowInfoList接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用新模块aocFindBorrowInfoList(getUserInfo(session).getLoginName(), moduleLevels, CommonCast.client_phone)出错"+e.toString());
            throw e;
        }
        model.addAttribute("newsparelist", brrowlist);
        model.addAttribute("oldSerialName", oldSerialName);
        model.addAttribute("oldMaterialCode", oldMaterialCode);
        model.addAttribute("oldSerialNumber", oldSerialNumber);
        model.addAttribute("oldHardwareVersion", oldHardwareVersion);
        model.addAttribute("oldSoftwareVersion", oldSoftwareVersion);
        model.addAttribute("equipmentConfigId", equipmentConfigId);
        model.addAttribute("oldModuleType", oldModuleType);
        model.addAttribute("divname", divname);
        model.addAttribute("workformId", workformId);
        model.addAttribute("serialNumber", serialNumber);
        model.addAttribute("poNumber", poNumber);
        return "/workorder/newspare1";
    }

    /**
     * 零件弹出窗
     */

    @RequestMapping(value = "/element/elementsparepath")
    public String orderelement(HttpServletRequest request, Model model) {
        String divname = request.getParameter("divname");
        String newSerialName=null;
        try {
            if(request.getParameter("newSerialName") != null){
                newSerialName = java.net.URLDecoder.decode(request.getParameter("newSerialName"),"UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String newMaterialCode = request.getParameter("newMaterialCode");// 物料编码
        String oldSerialName=null;
        try {
            if(request.getParameter("oldSerialName") != null){
                oldSerialName = java.net.URLDecoder.decode(request.getParameter("oldSerialName"),"UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String oldMaterialCode = request.getParameter("oldMaterialCode");
        String quantity = request.getParameter("quantity");// 数量
        String newHardwareVersion = request.getParameter("newHardwareVersion");
        String newSoftwareVersion = request.getParameter("newSoftwareVersion");
        String sparepartNum = request.getParameter("sparepartNum"); // 新备件 实际数量
        String workformId = request.getParameter("workformId");
        String flags = request.getParameter("flag");
        String flag = flags == null ? "1" : flags;
        model.addAttribute("newSerialName", newSerialName);
        model.addAttribute("oldSerialName", oldSerialName);
        model.addAttribute("newMaterialCode", newMaterialCode);
        model.addAttribute("oldMaterialCode", oldMaterialCode);
        model.addAttribute("quantity", quantity);
        model.addAttribute("divname", divname);
        model.addAttribute("newHardwareVersion", newHardwareVersion);
        model.addAttribute("newSoftwareVersion", newSoftwareVersion);
        model.addAttribute("flag", flag);
        model.addAttribute("sparepartNum", sparepartNum);
        model.addAttribute("workformId", workformId);
        model.addAttribute("poNumber", request.getParameter("poNumber"));
        return "/workorder/workOrderelement";
    }

    /**
     * 旧零件
     * 
     * @throws Exception
     */

    /*@RequestMapping(value = "/element/oldelement")
    public String oldelementInfo(HttpServletRequest request, Model model) throws Exception {
        String divname = request.getParameter("divname");
        String flag = request.getParameter("flag");
        String newSerialName=null;
        try {
            if(request.getParameter("newSerialName") != null){
                newSerialName = java.net.URLDecoder.decode(request.getParameter("newSerialName"),"UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String oldSerialName = request.getParameter("oldSerialName");
        String newMaterialCode = request.getParameter("newMaterialCode");
        String quantity = request.getParameter("quantity");
        List<WsAocBaseMaterialBean> lists = null;
        try {
            lists = this.sparepartService.aocFindThreeLevelSptForWorkForm(newMaterialCode, "PHONE");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("aocFindThreeLevelSptForWorkForm出错");
            throw e;
        }
        // 防止零件替换中物料名称中带有双引号
        if (lists != null) {
            for (WsAocBaseMaterialBean bean : lists) {
                if (bean.getMaterialName().indexOf("\"") > -1) {
                    bean.setMaterialName(bean.getMaterialName().replace("\"", ""));
                }
            }
        }
        model.addAttribute("oldSerialName", oldSerialName);
        model.addAttribute("newSerialName", newSerialName);
        model.addAttribute("newMaterialCode", newMaterialCode);
        model.addAttribute("flag", flag);
        model.addAttribute("quantity", quantity);
        model.addAttribute("oldelementlist", lists);
        model.addAttribute("divname", divname);
        model.addAttribute("poNumber", request.getParameter("poNumber"));
        return "/workorder/oldelement";
    }*/
    /**
     * 旧零件
     * 
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/element/oldelement")
    public String oldelementInfo(HttpServletRequest request, Model model) throws Exception {
        String divname = request.getParameter("divname");
        String flag = request.getParameter("flag");
        String newSerialName=null;
        try {
            if(request.getParameter("newSerialName") != null){
                newSerialName = java.net.URLDecoder.decode(request.getParameter("newSerialName"),"UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String oldSerialName = request.getParameter("oldSerialName");
        String newMaterialCode = request.getParameter("newMaterialCode");
        String quantity = request.getParameter("quantity");
        List<WsAocBaseMaterialBean> lists = null;
        try {
            WxUser wxUser = LoginUser.getSessionUser();
            startTime = new Date();
            lists = this.sparepartService.aocFindThreeLevelSptForWorkForm(newMaterialCode, "PHONE");
            logger.warn("{}调用旧零件aocFindThreeLevelSptForWorkForm接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用旧零件aocFindThreeLevelSptForWorkForm出错"+e.toString());
            throw e;
        }
        // 防止零件替换中物料名称中带有双引号
        if (lists != null) {
            for (WsAocBaseMaterialBean bean : lists) {
                if (bean.getMaterialName().indexOf("\"") > -1) {
                    bean.setMaterialName(bean.getMaterialName().replace("\"", ""));
                }
            }
        }
        model.addAttribute("oldSerialName", oldSerialName);
        model.addAttribute("newSerialName", newSerialName);
        model.addAttribute("newMaterialCode", newMaterialCode);
        model.addAttribute("flag", flag);
        model.addAttribute("quantity", quantity);
        model.addAttribute("oldelementlist", lists);
        model.addAttribute("divname", divname);
        model.addAttribute("poNumber", request.getParameter("poNumber"));
        return JSON.toJSONString(lists);
    }

    /**
     * 新零件(增加)
     * 
     * @return
     * @throws Exception
     */
    /*@RequestMapping(value = "/element/newelement")
    public String newelementInfo(HttpServletRequest request, Model model) throws Exception {
        String divname = request.getParameter("divname");
        String flag = request.getParameter("flag");
        String workformId = request.getParameter("workformId");
        // String[] moduleLevels = { "A", "B", "C" };
        // 因模块与零备件互相替换，导致数据错乱,现在不支持交叉录入
        String[] moduleLevels = { "C" };
        List<WsBorrowInfoBean> brrowlist = null;
        String userCode = LoginUser.getSessionUser().getUserCode();
        try {
            brrowlist = this.workFormEngineService.findBorrowInfoList(userCode, moduleLevels, Integer.parseInt(workformId), "PHONE");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("aocFindBorrowInfoList(super.getUserInfo(session).getLoginName(), moduleLevels,CommonCast.client_phone)出错");
            throw e;
        }
        // 防止零件替换中物料名称中带有双引号
        if (brrowlist != null) {
            for (WsBorrowInfoBean bean : brrowlist) {
                if (bean.getMaterialName().indexOf("\"") > -1) {
                    bean.setMaterialName(bean.getMaterialName().replace("\"", ""));
                }
            }
        }
        model.addAttribute("brrowlist", brrowlist);
        model.addAttribute("divname", divname);
        model.addAttribute("flag", flag);
        model.addAttribute("poNumber", request.getParameter("poNumber"));
        return "/workorder/newelement";

    }*/
    /**
     * 新零件(增加)
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/element/newelement")
    public String newelementInfo(HttpServletRequest request, Model model) throws Exception {
        String divname = request.getParameter("divname");
        String flag = request.getParameter("flag");
        String workformId = request.getParameter("workformId");
        // String[] moduleLevels = { "A", "B", "C" };
        // 因模块与零备件互相替换，导致数据错乱,现在不支持交叉录入
        String[] moduleLevels = { "C" };
        List<WsBorrowInfoBean> brrowlist = null;
        String userCode = LoginUser.getSessionUser().getUserCode();
        try {
            WxUser wxUser = LoginUser.getSessionUser();
            startTime = new Date();
            brrowlist = this.workFormEngineService.findBorrowInfoList(userCode, moduleLevels, Integer.parseInt(workformId), "PHONE");
            logger.warn("{}调用工单列表接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用新零件（增加）aocFindBorrowInfoList(super.getUserInfo(session).getLoginName(), moduleLevels,CommonCast.client_phone)出错"+e.toString());
            throw e;
        }
        // 防止零件替换中物料名称中带有双引号
        if (brrowlist != null) {
            for (WsBorrowInfoBean bean : brrowlist) {
                if (bean.getMaterialName().indexOf("\"") > -1) {
                    bean.setMaterialName(bean.getMaterialName().replace("\"", ""));
                }
            }
        }
        model.addAttribute("brrowlist", brrowlist);
        model.addAttribute("divname", divname);
        model.addAttribute("flag", flag);
        model.addAttribute("poNumber", request.getParameter("poNumber"));
        return JSONArray.toJSONString(brrowlist);

    }

    /**
     * 新零件(替换)
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/element/newelementRp")
    public String newerelementReplaceInfo(HttpServletRequest request, Model model) throws Exception {
        String divname = request.getParameter("divname");
        String flag = request.getParameter("flag");
        String newSerialName = request.getParameter("newSerialName");
        // 因模块与零备件互相替换，导致数据错乱,现在不支持交叉录入
        // String[] moduleLevels = { "A", "B", "C" };
        String[] moduleLevels = { "C" };
        // String [] moduleLevels={"A","B","C"};
        //List<WsAocBorrowInfoBean> brrowlist = null;
        List<WsBorrowInfoBean> brrowlist = null;
        try {
            WxUser wxUser = LoginUser.getSessionUser();
            startTime = new Date();
            //brrowlist = this.sparepartService.aocFindBorrowInfoList(LoginUser.getSessionUser().getLoginName(), moduleLevels, "");
            brrowlist = this.webInfoService.findBorrowInfoList(LoginUser.getSessionUser().getLoginName(), moduleLevels, Consts.CLIENT_TYPE);
            logger.warn("{}调用新零件（替换）aocFindBorrowInfoList接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("调用新零件（替换）aocFindBorrowInfoList(super.getUserInfo(session).getLoginName(), moduleLevels,CommonCast.client_phone)出错"+e.toString());
            throw e;
        }
        model.addAttribute("newSerialName", newSerialName);
        model.addAttribute("brrowlist", brrowlist);
        model.addAttribute("flag", flag);
        model.addAttribute("divname", divname);
        model.addAttribute("poNumber", request.getParameter("poNumber"));
        return "/workorder/newelementReplace";

    }

    /**
     * 升级前
     */
    @RequestMapping(value = "/upGrade/upGradeBefore")
    public String upGradeBefore(HttpServletRequest request, Model model) {
        String taskId = request.getParameter("taskId");
        String poNumber = request.getParameter("poNumber");
        try {
            Order orderBean = this.orderService.findOrderBypoNumber(poNumber);
            WsPendingWorkformDetailBean workbean = (WsPendingWorkformDetailBean) com.alibaba.fastjson.JSON.parseObject(
                    orderBean.getContent(), WsPendingWorkformDetailBean.class);
            List<WsPendingWorkTaskJsonListBean> taskJsonList = workbean.getTaskJsonList();
            for (WsPendingWorkTaskJsonListBean bean : taskJsonList) {
                if (bean.getTaskId().equals(taskId)) {
                    model.addAttribute("beforSjModel", bean.getBeforSjModel());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/workorder/upGradeBefore";
    }

    /**
     * 升级后
     */
    @RequestMapping(value = "/upGrade/upGradeAfter")
    public String upGradeAfter(HttpServletRequest request, Model model) {
        String taskId = request.getParameter("taskId");
        String poNumber = request.getParameter("poNumber");
        String isDisable = request.getParameter("isDisable");
        if (isDisable != null && !isDisable.equals("")) {
            isDisable = "Y";
        } else {
            isDisable = "N";
        }
        Order orderBean = null;
        orderBean = this.orderService.findOrderBypoNumber(poNumber);

        WsPendingWorkformDetailBean workbean = (WsPendingWorkformDetailBean) com.alibaba.fastjson.JSON.parseObject(
                orderBean.getContent(), WsPendingWorkformDetailBean.class);
        List<WsPendingWorkTaskJsonListBean> taskJsonList = workbean.getTaskJsonList();

        model.addAttribute("poNumber", poNumber);
        model.addAttribute("taskId", taskId);
        model.addAttribute("isDisable", isDisable);
        for (WsPendingWorkTaskJsonListBean bean : taskJsonList) {
            if (bean.getTaskType().equals("SJ") && bean.getTaskId().equals(taskId)) {
                model.addAttribute("sjbean", bean);
                model.addAttribute("afterSjModel", bean.getAfterSjModel());
                model.addAttribute("beforSjModel", bean.getBeforSjModel());
                break;
            }
        }
        return "/workorder/upGradeAfter";
    }

    /**
     * 保存升级后数据
     * 
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/AfterGradeSave")
    public ResultJson<String> afterGradeSave(HttpServletRequest request) throws IOException {
        String loginName = LoginUser.getSessionUser().getLoginName();
        String taskId = request.getParameter("taskId");
        String poNumber = request.getParameter("poNumber");
        String operationSystem = request.getParameter("operationSystem");
        String operationSystemId = request.getParameter("operationSystemId");
        String osVersion = request.getParameter("osVersion");
        String atmCName = request.getParameter("atmCName");
        String atmCNameId = request.getParameter("atmCNameId");
        String atmCVersion = request.getParameter("atmCVersion");
        String atmCSpVersion = request.getParameter("atmCSpVersion");
        WsSjTaskReplaceBean afterBean = new WsSjTaskReplaceBean();
        afterBean.setOperationSystem(operationSystem);
        afterBean.setOperationSystemId(operationSystemId);
        afterBean.setOsVersion(osVersion);
        afterBean.setAtmCName(atmCName);
        afterBean.setAtmCNameId(atmCNameId);
        afterBean.setAtmCVersion(atmCVersion);
        afterBean.setAtmCSpVersion(atmCSpVersion);

        Order orderBean = this.orderService.findOrderBypoNumber(poNumber);

        WsPendingWorkformDetailBean workbean = (WsPendingWorkformDetailBean) JSON.parseObject(orderBean.getContent(),
                WsPendingWorkformDetailBean.class);
        List<WsPendingWorkTaskJsonListBean> taskJsonList = workbean.getTaskJsonList();
        for (WsPendingWorkTaskJsonListBean taskbean : taskJsonList) {
            if (taskbean.getTaskId().equals(taskId)) {
                taskbean.setAfterSjModel(afterBean);
            }
        }
        String jObject = JSON.toJSONString(workbean);

        orderBean.setLoginName(loginName);
        orderBean.setContent(jObject);
        orderBean.setUpDate(new Date());
        this.orderService.updateOrder(orderBean);
        return new ResultJson<String>(1, "保存成功！", "");
    }

    /**
     * 
     * @Title: saveFaultInfos
     * @Description: TODO(存取故障信息，此处需要修改，不要存在session)
     * @param arg1
     * @param arg2
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/fault/saveFaultInfos")
    public String saveFaultInfos(String arg1, String arg2) {
        saveFaultInfosToSession(arg1);
        saveFaultInfosToSession(arg2);
        return "success";
    }

    private void saveFaultInfosToSession(String arg) {
        String[] args = arg.split("=");
        saveFaultInfosToSession(args);
    }

    private void saveFaultInfosToSession(String... args) {
        if (null == args || args.length < 2)
            return;
        if (args[0] == null || args[1] == null)
            return;
        ConcurrentHashMap<String, String> faultInfos = getFaultInfos();
        faultInfos.put(args[0], args[1]);
        // SysContent.getRequest().getSession().setAttribute(Consts.FAUL_TINFO_KEY,
        // faultInfos);
        // 缓存存取
        EhcacheUtils.update("session", Consts.FAUL_TINFO_KEY + "_" + LoginUser.getSessionUser().getLoginName(),
                faultInfos);
    }

    @SuppressWarnings("unchecked")
    private ConcurrentHashMap<String, String> getFaultInfos() {
        // Object object =
        // SysContent.getRequest().getSession().getAttribute(Consts.FAUL_TINFO_KEY);
        Object object = EhcacheUtils.get("session", Consts.FAUL_TINFO_KEY + "_"
                + LoginUser.getSessionUser().getLoginName());
        if (null == object) {
            ConcurrentHashMap<String, String> result = new ConcurrentHashMap<String, String>(8);
            // SysContent.getRequest().getSession().setAttribute(Consts.FAUL_TINFO_KEY,
            // result);
            // 缓存存取
            EhcacheUtils
                    .put("session", Consts.FAUL_TINFO_KEY + "_" + LoginUser.getSessionUser().getLoginName(), result);
            return result;
        }
        return (ConcurrentHashMap<String, String>) object;
    }

    /**
     * 故障模块
     * 
     * @return
     */
    @RequestMapping(value = "/fault/faultIndex")
    public String faultIndex(String serialNumber, String divname, String equipmentModel, Model model, String poNumber) {
        if (isNotNull(true, serialNumber, divname, equipmentModel)) {
            EhcacheUtils.remove("session", Consts.FAUL_TINFO_KEY + "_" + LoginUser.getSessionUser().getLoginName());
            saveFaultInfosToSession("serialNumber", serialNumber);
            saveFaultInfosToSession("divname", divname);
            saveFaultInfosToSession("equipmentModel", equipmentModel);
            saveFaultInfosToSession("poNumber", poNumber);
        }
        ConcurrentHashMap<String, String> faultInfos = getFaultInfos();
        Set<Map.Entry<String, String>> valueKeys = faultInfos.entrySet();
        for (Map.Entry<String, String> valueKey : valueKeys) {
            model.addAttribute(valueKey.getKey(), valueKey.getValue());
        }
        return "/workorder/faultIndex1";
    }

    /**
     * 故障部位(type=1)
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fault/faultInfo")
    public String faultInfo(String equipmentModel, String keyword, String problemPartId, Model model) throws Exception {
        String layer = "0";
        if ( problemPartId != null && !problemPartId.equals("")) {
            if (problemPartId.equals("undefined")) {
                layer = "0";
            } else {
                layer = problemPartId;
            }
        }
        WsResultProblemPartBean resultbean = null;
        try {
            WxUser wxUser = LoginUser.getSessionUser();
            startTime = new Date();
            resultbean = this.workOrderService.dropdownProblemPartOption(keyword, equipmentModel,
                    Integer.valueOf(layer), FaultType.FAULT_PART.toString());
            logger.warn("{}调用故障部位接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        List<WsProblemPartBean> problemlist = resultbean.getRows();
        model.addAttribute("problemlist", problemlist);
        model.addAttribute("equipmentModel", equipmentModel);
        model.addAttribute("layer", layer); // 页面到第几层节点
        model.addAttribute("keyword", keyword); // 查询条件
        return "/workorder/faultInfo";
    }

    /**
     * 故障描述(type=2)故障部位id(problemCodeId)
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fault/faultRemark")
    public String faultRemark(String problemCodeId, String keyword, String problemPartId, Model model) throws Exception {
        Integer layer = 0;
        if (problemCodeId != null && !problemCodeId.equals("")) {
            if (!problemCodeId.equals("undefined")) {
                layer = Integer.valueOf(problemCodeId);
            } else {
                layer = 0;
                problemCodeId = null;
            }
        }
        if (problemPartId.equals("undefined")) {
            problemPartId = null;
        }
        WsResultProblemPartBean resultbean = null;
        try {
            WxUser wxUser = LoginUser.getSessionUser();
            startTime = new Date();
            resultbean = this.workOrderService.dropdownProblemPartOption(keyword, problemPartId, layer,
                    FaultType.FAULT_DESC.toString());
            logger.warn("{}调用故障描述dropdownProblemPartOption接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("故障描述dropdownProblemPartOption(keyword, problemPartId, layer,type)出错");
            throw e;
        }
        List<WsProblemPartBean> problemlist = resultbean.getRows();
        model.addAttribute("problemlist", problemlist);
        model.addAttribute("problemPartId", problemPartId);
        model.addAttribute("layer", layer); // 页面到第几层节点
        model.addAttribute("keyword", keyword); // 查询条件
        return "/workorder/faultRemark";
    }

    /**
     * 故障原因(type=3)
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fault/faultReason")
    public String faultReason(String keyword, String problemReasonId, Model model) throws Exception {
        String layer = problemReasonId != "" && problemReasonId != null ? problemReasonId : "0";
        WsResultProblemPartBean resultbean = null;
        try {
            WxUser wxUser = LoginUser.getSessionUser();
            startTime = new Date();
            resultbean = this.workOrderService.dropdownProblemPartOption(keyword, null, Integer.valueOf(layer),
                    FaultType.FAULT_REASON.toString());
            logger.warn("{}调用故障原因dropdownProblemPartOption接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("故障原因dropdownProblemPartOption(keyword, null,Integer.valueOf(layer), type)出错");
            throw e;
        }
        List<WsProblemPartBean> problemlist = resultbean.getRows();
        model.addAttribute("problemlist", problemlist);
        return "/workorder/faultReason";
    }

    /**
     * 故障处理方法(type=4)
     * 
     * @throws Exception
     */
    @RequestMapping(value = "/fault/faultProcess")
    public String faultProcess(String keyword, String problemMethodId, Model model) throws Exception {
        // 处理方法
        String layer = problemMethodId != "" && problemMethodId != null ? problemMethodId : "0";
        WsResultProblemPartBean resultbean = null;
        try {
            WxUser wxUser = LoginUser.getSessionUser();
            startTime = new Date();
            resultbean = this.workOrderService.dropdownProblemPartOption(keyword, null, Integer.valueOf(layer),
                    FaultType.FAULT_FUNC.toString());
            logger.warn("{}调用故障处理方法dropdownProblemPartOption接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("故障处理方法dropdownProblemPartOption()出错" + e.toString());
            throw e;
        }
        List<WsProblemPartBean> problemlist = resultbean.getRows();
        model.addAttribute("problemlist", problemlist);
        return "/workorder/faultProcess";
    }

    /**
     * 
     * @Title: cityInfo
     * @Description: TODO(城市下拉)
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/city/cityInfo")
    public String cityInfo(String provinceId) throws Exception {
        if (StringUtils.isNotEmpty(provinceId)) {
            String[] provinces = provinceId.split(",");
            WsResultCityBean resultbean = null;
            try {
                resultbean = this.workOrderService.selectCityList(provinces[0]);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
            return JSONArray.toJSONString(resultbean);
        }
        return null;
    }

    /**
     * 
     * @Title: getRemoveTaskWork
     * @Description: TODO(移除任务弹出)
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/getRemoveTaskWork")
    public String getRemoveTaskWork(HttpServletRequest request, Model model) {
        String workformId = request.getParameter("workformId"); // 工单ID
        String workTaskId = request.getParameter("workTaskId"); // 任务ID
        String workTaskType = request.getParameter("workTaskType"); // 任务类型
        String poNumber = request.getParameter("poNumber"); // 工单编号(用于移除任务成功之后，返回11中任务单页面)
        model.addAttribute("workformId", workformId);
        model.addAttribute("workTaskId", workTaskId);
        model.addAttribute("workTaskType", workTaskType);
        model.addAttribute("poNumber", poNumber);
        return "/workorder/taskOrderMove";
    }

    /**
     * 移除任务
     * 
     * @throws
     * @Title: removeTaskWork
     */
    @RequestMapping(value = "/removeTaskWork")
    @ResponseBody
    public String removeTaskWork(HttpServletRequest request) throws IOException {
        String loginName = LoginUser.getSessionUser().getLoginName(); // 用户名
        String userCode = LoginUser.getSessionUser().getUserCode();//用户编号
        // String loginName ="drhua";
        String workformId = request.getParameter("workformId"); // 工单ID
        String workTaskId = request.getParameter("workTaskId"); // 任务ID
        String remark = request.getParameter("remark"); // 移除说明
        String poNumber = request.getParameter("poNumber"); // 工单编号(用于移除任务成功之后，删除工单任务但保存的TXT文件)
        
        WsResultFinishWorkFormBean result = new WsResultFinishWorkFormBean();
        result.setErrMsg("移除失败");
        result.setStatus(0);
        
        WsResultBean wsResultBean = null;
        //JSONObject jo = null;
        try {
            // 执行移除任务方法
            //result = this.workOrderService.doRemoveTaskWorkForm(loginName, workformId, workTaskId, remark);
            //String resultStr = this.workFormEngineService.doDeleteTask(workTaskId);
            startTime = new Date();
            WxUser wxUser = LoginUser.getSessionUser();
            wsResultBean = this.workFormEngineService.doDeleteTask(poNumber,workTaskId, userCode, "phone");
            logger.warn("{}调用移除任务接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
            if(wsResultBean != null){
                if(wsResultBean.isReturnResult()){
                    result.setErrMsg("移除成功");
                    result.setStatus(1);
                }else{
                    result.setStatus(0);
                    result.setErrMsg(wsResultBean.getErrorMessage());
                }
            }
            //String resultStr = "";
            /*jo = JSON.parseObject(resultStr);
            if(jo != null){
                if(jo.get("msg") != null){
                    result.setErrMsg(jo.get("msg").toString());
                }
                if(jo.get("returnResult") != null){
                    if(jo.get("returnResult").toString().equals("true")){
                        result.setStatus(1);
                    }else{
                        result.setStatus(0);
                    }
                }
            }*/
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("调用移除任务接口报错:" + ex.toString());
            return null;
        }
        // --------------------------以下用于重写保存TXT文件的参数定义---------------------------

        String personids = "";
        String personName = "";

        // 同行人员
        WsWorkformTogetherPersonBean togetherbean = new WsWorkformTogetherPersonBean();
        togetherbean.setUserId(personids);
        togetherbean.setName(personName);

        // 读取TXT文件中的数据

        try {
            Order orderBean = this.orderService.findOrderBypoNumber(poNumber);

            WsPendingWorkformDetailBean txtBean = (WsPendingWorkformDetailBean) com.alibaba.fastjson.JSON.parseObject(
                    orderBean.getContent(), WsPendingWorkformDetailBean.class);
            // 设备型号
            String equipmentModel = txtBean.getEquipmentModel();
            String branchName = txtBean.getBranchName();
            String installAddress = txtBean.getInstallAddress();
            String atmManagerTel = txtBean.getAtmManagerTel();
            String atmManager = txtBean.getAtmManager();
            String serialNumber = txtBean.getSerialNumber();
            String equipmentId = txtBean.getEquipmentId(); // 设备ID
            togetherbean = txtBean.getTogetherPerson();
            // 满意度
            String defaultId = txtBean.getDegreeSatisfaction().getDefaultId();
            String defaultName = txtBean.getDegreeSatisfaction().getDefaultName();
            // ModelAndView modelAndView= null;
            // 如果移除任务成功，则删除工单任务但保存的TXT文件
            if (result.getStatus() == 1) {

                // File file = new File(fileUrl);
                // file.delete(); // 删除工单任务但保存的TXT文件

                try {
                    WxUser wxUser = LoginUser.getSessionUser();
                    startTime = new Date();
                    WsResultFillWorkFormBean serviceBean = this.workOrderService.getWorkFormDetail(loginName,
                            equipmentId, workformId, "","1");
                    logger.warn("{}调用工单详情接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
                    // file = new File(fileUrl);

                    WsPendingWorkformDetailBean bean = serviceBean.getWsPendingWorkformDetailBean();
                    bean.setEquipmentModel(equipmentModel);
                    bean.setBranchName(branchName);
                    bean.setInstallAddress(installAddress);
                    bean.setAtmManagerTel(atmManagerTel);
                    bean.setAtmManager(atmManager);
                    bean.setSerialNumber(serialNumber);
                    bean.setTogetherPerson(togetherbean);
                    bean.getDegreeSatisfaction().setDefaultId(defaultId);
                    bean.getDegreeSatisfaction().setDefaultName(defaultName);
                    String jsonStr = JSON.toJSONString(bean);

                    orderBean.setLoginName(loginName);
                    orderBean.setContent(jsonStr);
                    orderBean.setUpDate(new Date());
                    this.orderService.updateOrder(orderBean);
                    ResultJson<WsPendingWorkformDetailBean> resultJson = new ResultJson<WsPendingWorkformDetailBean>(
                            result.getStatus(), result.getErrMsg(), bean);
                    return JSON.toJSONString(resultJson);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    logger.error("调用工单详情接口报错:" + ex.toString());
                    return null;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResultJson<Object> json = new ResultJson<Object>(result.getStatus(), result.getErrMsg(), null);
        return JSON.toJSONString(json);
    }
}
