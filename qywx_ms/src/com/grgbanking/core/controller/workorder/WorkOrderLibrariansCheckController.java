package com.grgbanking.core.controller.workorder;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.grgbanking.common.consts.Consts;
import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.common.utils.BaseUserRoleUtil;
import com.grgbanking.common.utils.LoginUser;
import com.grgbanking.core.controller.BaseController;
import com.grgbanking.core.entity.user.BaseRoleUser;
import com.grgbanking.core.entity.user.Order;
import com.grgbanking.core.entity.user.WxUser;
import com.grgbanking.core.entity.workorder.WsPendingWorkformDetailBean;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.entity.ws.WsResultBean;
import com.grgbanking.core.entity.ws.WsResultFillWorkFormBean;
import com.grgbanking.core.entity.ws.WsResultPendingWorkFormBean;
import com.grgbanking.core.service.user.BaseRoleUserService;
import com.grgbanking.core.service.user.OrderService;
import com.grgbanking.core.service.workorder.WorkFormEngineService;
import com.grgbanking.core.service.workorder.WorkOrderService;

/**
 * 库管员待审核工单
 * 版权所有：2016-GRGBANKING
 * 项目名称：qywx_ms_pro   
 *
 * 类描述：
 * 类名称：com.grgbanking.core.controller.workorder.OrderLibrariansCheckController     
 * 创建人：WSH
 * 创建时间：2016年1月12日 下午5:30:27   
 * 修改人：
 * 修改时间：2016年1月12日 下午5:30:27   
 * 修改备注：   
 * @version   V1.0
 */
@Controller
public class WorkOrderLibrariansCheckController extends BaseController {

    @Autowired
    WorkFormEngineService workFormEngineService;

    // 本地工单服务接口
    @Resource(name = "orderService")
    private OrderService orderService;
    
    //工单接口
    @Resource(name = "workOrderService")
    private WorkOrderService workOrderService;
    
    // 用户角色服务
    /*@Resource(name = "mobileUserRoleService")
    private MobileUserRoleService mobileUserRoleService;*/
    
    // 客服用户角色服务
    @Resource(name = "baseRoleUserService")
    private BaseRoleUserService baseRoleUserService;

	/**
	 * 工单待办页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cp/ouath/worklibrarianscheck/list")
	public ModelAndView list(HttpServletRequest request,Integer pageNum) {
	    pageNum = pageNum == null ? 1 : pageNum;
        WxUser wxUser = LoginUser.getSessionUser();
        WsPage wsPage = new WsPage(Consts.DEFAULT_ROWS, pageNum.toString());
        WsResultPendingWorkFormBean result = null;
        logger.info("拉取【库管员待审核工单】数据,页码" + pageNum);
        String status = "库管员审核";
        /*MobileUserRole mobileUserRole = new MobileUserRole();
        mobileUserRole.setRoleId(Long.parseLong(WsConsts.SERVICE_LIBRARY));
        mobileUserRole.setUserId(wxUser.getId());
        //返回结果，false表示不是服务站主任，true则是。
        Boolean isExist = mobileUserRoleService.IsExistUserRole(mobileUserRole);*/
        List<BaseRoleUser> cssRoles = baseRoleUserService.queryByLoginName(wxUser.getLoginName());
        ModelAndView modelAndView = null;
        if(BaseUserRoleUtil.isExist(cssRoles, Long.parseLong(WsConsts.CssLibRole))){
            try {
                startTime = new Date();
                result = workOrderService.getCheckPendingWorkForm(wxUser.getLoginName(), status, wsPage, Consts.CREATE_TYPE);
                logger.warn("{}调用库管员待审核工单接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("调用库管员待审核工单接口报错:" + e.toString());
                result = new WsResultPendingWorkFormBean();
                result.setStatus(0);
                result.setErrMsg(WsConsts.SocWorkformWsServiceUrl
                        + ",getCheckPendingWorkForm"+","+e.getMessage());
            }
            modelAndView = new ModelAndView("/workorderlibcheck/workOrderListLibCheck");
            modelAndView.addObject("result",result);
        }else{
            modelAndView = new ModelAndView("/common/nocheck");
            modelAndView.addObject("msg", "需配置服务站库管员权限，请联系开发部！");
        }
        
        return modelAndView;
	}

	/**
	 * 获取库管员待审核工单列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cp/worklibrarianscheck/pageList")
	public WsResultPendingWorkFormBean pageList(String pageNum) {
	    WxUser wxUser = LoginUser.getSessionUser();
		String status = "库管员审核";
		WsPage wsPage = new WsPage(Consts.DEFAULT_ROWS, pageNum);
		WsResultPendingWorkFormBean result = null;
		try {
            startTime = new Date();
            result = workOrderService.getCheckPendingWorkForm(wxUser.getLoginName(), status, wsPage, Consts.CREATE_TYPE);
            logger.warn("{}调用库管员待审核工单接口消耗时间：{}s", wxUser.getLoginName(), getDiffTime(startTime));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("取待审核工单列表 页面 报错:" + e.toString());
			result = new WsResultPendingWorkFormBean();
			result.setStatus(-1);
            result.setErrMsg(WsConsts.SocWorkformWsServiceUrl
                    + ",getCheckPendingWorkForm"+","+e.getMessage());
		}
		return result;
	}

	/**
	 * 工单审核(主任、库管员审核)
	 * 
	 * @param poNumber
	 *            工单号
	 * @param arriveTime
	 *            到达现场时间
	 * @param workStartDate
	 *            工作开始时间
	 * @param workFinishDate
	 *            工作完成时间
	 * @param buttonType
	 *            (pass 通过,back 退回)
	 * @param checkUserCode
	 *            审核人员
	 * @param checkRemark
	 *            审核备注
	 * @param targetStoreId
	 *            目标仓库
	 * @param clientType
	 *            方式(SA/MP)
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cp/worklibrarianscheck/checkWorkformStepInStoreInfo")
	public String checkWorkformStepInStoreInfo(String poNumber,
			String buttonType, String checkRemark, String targetStoreId) {
		WsResultBean bean = null;
		WxUser wxUser = LoginUser.getSessionUser();
		String checkUserCode = wxUser.getUserCode();
		try {
			bean = workFormEngineService.checkWorkformStepInStoreInfo(
					poNumber, "", "", "", buttonType, checkUserCode,
					checkRemark, targetStoreId, Consts.CLIENT_TYPE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("checkWorkformStepInStoreInfo出错" + e.toString());
			bean = new WsResultBean();
			bean.setReturnResult(false);
            bean.setErrorMessage(WsConsts.SocWorkFormEngineUrl + ",checkWorkformStepInStoreInfo" + e.getMessage());
		}
		return JSONObject.fromObject(bean).toString();
	}

	@RequestMapping(value = "/cp/worklibrarianscheck/orderReturn")
	public String orderReturn(String poNumber, String equipmentId, String workformId, HttpServletRequest request) {
        try {
            Order orderBean = orderService.findOrderBypoNumber(poNumber);
            WsPendingWorkformDetailBean beans = null;
            if(orderBean != null){
                beans = (WsPendingWorkformDetailBean)JSON.parseObject(orderBean.getContent(), WsPendingWorkformDetailBean.class);
            }else{
                WxUser wxUser = LoginUser.getSessionUser();
                WsResultFillWorkFormBean wsBean =workOrderService.beginFillInWorkForm(wxUser.getLoginName(), equipmentId, workformId, poNumber);
                if(wsBean != null){
                    beans = wsBean.getWsPendingWorkformDetailBean();
                }
            }
            
            request.setAttribute("workbean", beans);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("orderReturn报错:" + e.toString());
        }
		return "/workorderlibcheck/workOrderReturnLibCheck";
	}
}
