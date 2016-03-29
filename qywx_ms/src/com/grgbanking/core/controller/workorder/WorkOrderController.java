package com.grgbanking.core.controller.workorder;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.grgbanking.common.consts.Consts;
import com.grgbanking.common.utils.DateUtils;
import com.grgbanking.common.utils.LoginUser;
import com.grgbanking.core.controller.BaseController;
import com.grgbanking.core.entity.PageJson;
import com.grgbanking.core.entity.ResultJson;
import com.grgbanking.core.entity.equipment.WsDeviceLocationBean;
import com.grgbanking.core.entity.user.Order;
import com.grgbanking.core.entity.user.WxUser;
import com.grgbanking.core.entity.workorder.WsPendingWorkformBean;
import com.grgbanking.core.entity.workorder.WsPendingWorkformDetailBean;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultDeviceInfoWorkFormBean;
import com.grgbanking.core.entity.ws.WsResultFillWorkFormBean;
import com.grgbanking.core.entity.ws.WsResultPendingWorkFormBean;
import com.grgbanking.core.entity.ws.WsResultUploadWorkFormTimeBean;
import com.grgbanking.core.service.user.OrderService;
import com.grgbanking.core.service.workorder.WorkFormEngineService;
import com.grgbanking.core.service.workorder.WorkOrderService;

/**
 * 
 * 版权所有：2015-GRGBANKING 项目名称：Qywx_ms
 * 
 * 类描述：工单控制器 类名称：com.grgbanking.core.controller.workorder.WorkOrderController
 * 创建人：TXH 创建时间：2015-7-6 下午3:08:45 修改人： 修改时间：2015-7-6 下午3:08:45 修改备注：
 * 
 * @version V1.0
 */
@Controller
public class WorkOrderController extends BaseController {
    
    //工单接口
    @Resource(name = "workOrderService")
    private WorkOrderService workOrderService;
    
    @Resource(name = "workFormEngineService")
    private WorkFormEngineService workFormEngineService;

    // 本地工单服务接口
    @Resource(name = "orderService")
    private OrderService orderService;

    /**
     * 
     * @Title: workOrderPage
     * @Description: TODO(工单列表视图)
     * @param model
     * @return
     */
    /*@RequestMapping(value = "/cp/ouath/workOrder/default")
    public String workOrderPage(Model model) {
        logger.info("【待处理工单】页面");
        return "/workorder/workorder_list";
    }*/
    @RequestMapping(value = "/cp/ouath/workOrder/default")
    public ModelAndView workOrderPage(Integer pageNum) {
        pageNum = pageNum == null ? 1 : pageNum;
        logger.info("拉取【待处理工单】数据,页码" + pageNum);
        WxUser wxUser = LoginUser.getSessionUser();
        WsPage page = new WsPage();
        page.setRows(Consts.DEFAULT_ROWS);
        page.setPage(pageNum);
        WsResultPendingWorkFormBean result = null;
        PageJson<WsPendingWorkformBean> pageJson = null;
        try {
            startTime = new Date();
            result = this.workOrderService.getPending(wxUser.getLoginName(), page);
            logger.warn("{}调用工单列表接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
            pageJson = new PageJson<WsPendingWorkformBean>(result.getStatus(), result.getErrMsg(),
                    result.getPendingWorkform());
        } catch (Exception e) {
            // 报错了
            pageJson = new PageJson<WsPendingWorkformBean>(0, "系统繁忙，请稍后重试", null);
            logger.error("获取工单列表数据出错:" + e.toString());
            e.printStackTrace();
        }
        ModelAndView modelAndView = new ModelAndView("/workorder/workorderList");
        modelAndView.addObject("result",pageJson);
        return modelAndView;
    }

    /**
     * 
     * @Title: workOrderList
     * @Description: TODO(工单列表ajax分页数据)
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cp/workOrder/pageList")
    public String workOrderList(Integer pageNum) {
        logger.info("拉取【待处理工单】数据,页码" + pageNum);
        WxUser wxUser = LoginUser.getSessionUser();
        WsPage page = new WsPage();
        page.setRows(Consts.DEFAULT_ROWS);
        page.setPage(pageNum);
        WsResultPendingWorkFormBean result = null;
        PageJson<WsPendingWorkformBean> pageJson = null;
        try {
            startTime = new Date();
            result = this.workOrderService.getPending(wxUser.getLoginName(), page);
            logger.warn("{}调用工单列表接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
            pageJson = new PageJson<WsPendingWorkformBean>(result.getStatus(), result.getErrMsg(),
                    result.getPendingWorkform());
        } catch (Exception e) {
            // 报错了
            pageJson = new PageJson<WsPendingWorkformBean>(0, "系统繁忙，请稍后重试", null);
            logger.error("获取工单列表数据出错:" + e.toString());
            e.printStackTrace();
        }
        return JSON.toJSONString(pageJson);
    }

    /**
     * 
     * @Title: workOrderDetail
     * @Description: TODO(工单详细)
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/cp/workOrder/detail")
    public String workOrderDetail(String equipmentId, String workformId, String poNumber, Model model) {
        int flag = 1;// 是否可以修改序列号，默认为可以
        WxUser wxUser = LoginUser.getSessionUser();
        WsResultFillWorkFormBean wsResultFillWorkFormBean = null;
        try {
            logger.info("调用接口获取工单详细数据:【{}】,【{}】,【{}】,【{}】",wxUser.getLoginName(), equipmentId,workformId,poNumber);
            startTime = new Date();
            wsResultFillWorkFormBean = this.workOrderService.getWorkFormDetail(wxUser.getLoginName(), equipmentId,
                    workformId, poNumber,"1");
            logger.warn("{}调用工单详情接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
        } catch (Exception e) {
            logger.error("获取工单详细数据出错:" + e.getMessage());
            e.printStackTrace();
        }
        Order existOrder = this.orderService.findOrderBypoNumber(poNumber);// 不为空，说明已经录过单了，不允许修改序列号
        if (existOrder != null) {
            flag = 0;
        }
        model.addAttribute("flag", flag);// 序列号修改标志,1可修改，0不可修改
        model.addAttribute("workOrder",
                wsResultFillWorkFormBean == null ? null : wsResultFillWorkFormBean.getWsPendingWorkformDetailBean());
        return "/workorder/workorder_detail";
    }

    /**
     * 
     * @Title: queryserialNumber
     * @Description: TODO(工单基本界面更新序列号)
     * @param serialNumber
     * @param workformId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cp/workOrder/queryserialNumber")
    public String queryserialNumber(String serialNumber, String workformId) {
        WsResultDeviceInfoWorkFormBean resultbean = null;
        WsResultFillWorkFormBean result = null;
        ResultJson<WsPendingWorkformDetailBean> jsonData = null;
        try {
            resultbean = this.workOrderService.queryDeviceInfo(serialNumber, workformId);
            if (resultbean.getDeviceInfo() == null) {
                // 如果设备为空
                jsonData = new ResultJson<WsPendingWorkformDetailBean>(resultbean.getStatus(), resultbean.getErrMsg(),
                        null);
            } else {
                // 获取设备的equipmentId来更新工单绑定的序列号设备
                result = this.workOrderService.doEditSerialNumberWorkForm(LoginUser.getSessionUser().getLoginName(),
                        workformId, resultbean.getDeviceInfo().getEquipmentId());
                jsonData = new ResultJson<WsPendingWorkformDetailBean>(result.getStatus(), result.getErrMsg(),
                        result.getWsPendingWorkformDetailBean());
            }
        } catch (Exception e) {
            logger.error("查询设备信息或更新序列号出错:" + e.toString());
            e.printStackTrace();
            jsonData = new ResultJson<WsPendingWorkformDetailBean>(0, "系统繁忙，请稍后重试", null);
        }

        return JSON.toJSONString(jsonData);
    }

    /**
     * 
     * @Title: uploadTime
     * @Description: TODO(时间上传，接受、出发、到达、完成等)
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cp/workOrder/uploadTime")
    public String uploadTime(HttpServletRequest request) {
        String latlonStr = request.getParameter("latlonStr");
        String installAddress = request.getParameter("installAddress");
        String workformId = request.getParameter("workformId");
        String timeType = request.getParameter("timeType");
        WsDeviceLocationBean GPSInfo = new WsDeviceLocationBean("-1", "-1", "无");
        ResultJson<String> jsonData = null;
        if (StringUtils.isNotEmpty(latlonStr)) {
            String[] latlonArray = latlonStr.split(",");
            GPSInfo.setLatitude(latlonArray[0]);
            GPSInfo.setLongitude(latlonArray[1]);
        }
        if (StringUtils.isNotEmpty(installAddress)) {
            GPSInfo.setLocation(installAddress);
        }
        // 判断时间间隔

        if ("4".equals(timeType)) {// 完成时间类型为4
            String startTime = request.getParameter("startWorkTime");
            if (DateUtils.timeDifference(startTime) < 5) {
                jsonData = new ResultJson<String>(0, "请五分钟之后再操作", null);
                return JSON.toJSONString(jsonData);
            }
        }

        WsResultUploadWorkFormTimeBean result = null;
        WxUser wxUser = LoginUser.getSessionUser();
        //logger.info("上传时间点参数【latlonStr:" + latlonStr + ",workformId:" + workformId + ",timeType:" + timeType + "】");
        try {
            result = this.workOrderService.doUploadWorkFormTimeGPS(wxUser.getLoginName(), workformId, GPSInfo, timeType, 0l);
            jsonData = new ResultJson<String>(result.getStatus(), result.getErrMsg(), result.getTime());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error("调用上传时间接口出错:" + e.toString());
            jsonData = new ResultJson<String>(0, "系统繁忙，请稍后重试", null);
        }

        return JSON.toJSONString(jsonData);
    }

    /**
     * 
     * @Title: saveWorkOrder
     * @Description: TODO(保存工单)
     * @return
     */
    public String saveWorkOrder() {
        return "";
    }

    /**
     * 
     * @Title: submitWorkOrder
     * @Description: TODO(提交工单)
     * @return
     */
    public String submitWorkOrder() {
        return "";
    }
}
