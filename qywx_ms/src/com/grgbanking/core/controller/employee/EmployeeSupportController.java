package com.grgbanking.core.controller.employee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.common.utils.BaseUserRoleUtil;
import com.grgbanking.common.utils.DateUtil;
import com.grgbanking.common.utils.LoginUser;
import com.grgbanking.core.controller.BaseController;
import com.grgbanking.core.entity.user.BaseRoleUser;
import com.grgbanking.core.entity.user.UserSupportInfo;
import com.grgbanking.core.entity.user.WxUser;
import com.grgbanking.core.service.employee.EmployeeSupportService;
import com.grgbanking.core.service.user.BaseRoleUserService;
import com.grgbanking.css.common.Page;
import com.grgbanking.webservice.bean.WsResultBean;

/**
 * 人员支援控制器
 * 版权所有：2016-GRGBANKING
 * 项目名称：qywx_ms_pro   
 *
 * 类描述：
 * 类名称：com.grgbanking.core.controller.employee.EmployeeSupportController     
 * 创建人：yt
 * 创建时间：2016年1月18日 上午11:32:35   
 * 修改人：
 * 修改时间：2016年1月18日 上午11:32:35   
 * 修改备注：   
 * @version   V1.0
 */
@Controller
public class EmployeeSupportController extends BaseController {
    
    //人员支援服务
    @Resource(name = "employeeSupportService")
    private EmployeeSupportService employeeSupportService;
    
    //用户角色服务
    /*@Resource(name = "mobileUserRoleService")
    private MobileUserRoleService mobileUserRoleService;*/
    
    // 客服用户角色服务
    @Resource(name = "baseRoleUserService")
    private BaseRoleUserService baseRoleUserService;

    /**
     * 人员信息列表
     * @Title: workOrderPage
     * @return
     */
    @RequestMapping(value = "/cp/ouath/employee/employeeList")
    public ModelAndView employeeList() {
        //登录用户
        WxUser wxUser = LoginUser.getSessionUser();
        
        ModelAndView modelAndView = null;
        
        /*MobileUserRole mobileUserRole = new MobileUserRole();
        mobileUserRole.setRoleId(Long.parseLong(WsConsts.SERVICE_DIRECTOR));
        mobileUserRole.setUserId(wxUser.getId());
        
        //返回结果，false表示不是服务站主任，true则是。
        Boolean isExist = mobileUserRoleService.IsExistUserRole(mobileUserRole);*/
        List<BaseRoleUser> cssRoles = baseRoleUserService.queryByLoginName(wxUser.getLoginName());
        
        if(BaseUserRoleUtil.isExist(cssRoles, Long.parseLong(WsConsts.CssDirRole))){//具有服务站主任权限
            Page queryPage = new Page();
            queryPage.setPage(1);
            queryPage.setRows(200);
            WsResultBean resultBean = employeeSupportService.getEmployeeList(queryPage, wxUser.getUserCode());
            modelAndView = new ModelAndView("/employee/employeeList");
            
            //最终返回结果
            if(resultBean.getReturnResult()){
                List<UserSupportInfo> userList = new ArrayList<UserSupportInfo>();
                JSONObject jsonResult = JSONObject.fromObject(resultBean);
                String str = jsonResult.getString("returnMessage");
                JSONArray userJSONArr = JSONArray.fromObject(str);
                for(int i=0;i<userJSONArr.size();i++){
                    JSONObject json = userJSONArr.getJSONObject(i);
                    UserSupportInfo userSupportInfo = new UserSupportInfo();
                    userSupportInfo.setUserId(Long.parseLong(json.getString("userId")));
                    userSupportInfo.setLoginName(json.getString("loginName"));
                    userSupportInfo.setCompany(json.getString("company"));
                    userSupportInfo.setSegment(json.getString("segment"));
                    userSupportInfo.setDepartmentName(json.getString("departmentName"));
                    userSupportInfo.setBelongDepartmentName(json.getString("belongDepartmentName"));
                    userSupportInfo.setName(json.getString("name"));
                    userSupportInfo.setBelongDepartmentId(json.getLong("belongDepartmentId"));
                    userSupportInfo.setDepartmentId(json.getLong("departmentId"));
                    userList.add(userSupportInfo);
                }
                modelAndView.addObject("list", userList);
            }
            
            modelAndView.addObject("result", resultBean);
        }else{
            modelAndView = new ModelAndView("/common/nocheck"); 
            modelAndView.addObject("msg", "需配置服务站主任权限，请联系开发部.");
        }
        return modelAndView;
    }
    
    /**
     * 人员支援
     * @Title: employeeSupport
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/cp/employee/employeeSupport")
    public ModelAndView employeeSupport(HttpServletRequest request) {
        ModelAndView modelAndView = null;
        String loginName = request.getParameter("loginName");
        String name = request.getParameter("name");
        String belongDepartmentId = request.getParameter("belongDepartmentId");
        String departmentId = request.getParameter("departmentId");
        String belongDepartmentName = request.getParameter("belongDepartmentName");
        String departmentName = request.getParameter("departmentName");
        //跳转取消支援/支援页面
        if(name.contains("支援")){
            modelAndView = new ModelAndView("/employee/cancelSupport");
        }else{
            modelAndView = new ModelAndView("/employee/employeeSupport");
        }
        
        modelAndView.addObject("loginName", loginName);
        modelAndView.addObject("name", name);
        modelAndView.addObject("belongDepartmentId", belongDepartmentId);
        modelAndView.addObject("departmentId", departmentId);
        modelAndView.addObject("belongDepartmentName", belongDepartmentName);
        modelAndView.addObject("departmentName", departmentName);
        return modelAndView;
    }
    
    /**
     * 部门列表
     * @Title: getDepartment
     * @param request
     * @return
     */
    @RequestMapping(value = "/cp/employee/getDepartment")
    public ModelAndView getDepartment(HttpServletRequest request) {
        //登录用户
        WxUser wxUser = LoginUser.getSessionUser();
        
        ModelAndView modelAndView = null;
        
        String childNum = request.getParameter("childNum");
        
        String departmentId = request.getParameter("departmentId");
        //被支援人员登录名与用户名
        String supportedLoginName = request.getParameter("supportedLoginName");
        String supportedUserName = request.getParameter("supportedUserName");
        //原属部门
        String belongDepartmentId = request.getParameter("belongDepartmentId");
        String belongDepartmentName = request.getParameter("belongDepartmentName");
        
        WsResultBean resultBean = employeeSupportService.getDepartments(wxUser.getLoginName(), departmentId);
        
        if(StringUtils.isNotBlank(childNum) && Integer.parseInt(childNum) > 0){
            String departmentName = request.getParameter("departmentName");
           modelAndView = new ModelAndView("/employee/departmentSubList");
           modelAndView.addObject("departmentName", departmentName);
        }else{
            modelAndView = new ModelAndView("/employee/departmentList");
        }
        
        if(resultBean != null){
            JSONObject jsonResult = JSONObject.fromObject(resultBean);
            if(resultBean.getReturnResult()){
                String str = jsonResult.getString("returnMessage");
                JSONArray jArr = JSONArray.fromObject(str);
                modelAndView.addObject("list", jArr);
            }
        }
        modelAndView.addObject("resultBean", resultBean);
        modelAndView.addObject("supportedLoginName", supportedLoginName);
        modelAndView.addObject("supportedUserName", supportedUserName);
        modelAndView.addObject("belongDepartmentId", belongDepartmentId);
        modelAndView.addObject("belongDepartmentName", belongDepartmentName);

        return modelAndView;
    }
    
    /**
     * 支援
     * @Title: getDepartment
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cp/employee/doSaveSupport")
    public String doSaveSupport(HttpServletRequest request) {
        //登录用户
        WxUser wxUser = LoginUser.getSessionUser();
        
        String departmentIdNew = request.getParameter("departmentIdNew");
        String departmentNameNew = request.getParameter("departmentNameNew");
        String supportedLoginName = request.getParameter("supportedLoginName");
        String _startDate = request.getParameter("startDate");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = format.format(DateUtil.parseDate(_startDate, "yyyy-MM-dd"));
        WsResultBean resultBean = employeeSupportService.doUpdateEmployeeStationHisSupport(wxUser.getLoginName(), departmentIdNew, departmentNameNew, supportedLoginName, startDate);

        return JSON.toJSONString(resultBean);
    }
    
    /**
     * 取消支援
     * @Title: doCancelSupport
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cp/employee/doCancelSupport")
    public String doCancelSupport(HttpServletRequest request) {
        //登录用户
        WxUser wxUser = LoginUser.getSessionUser();
        
        String supportedLoginName = request.getParameter("supportedLoginName");
        //String _endDate = request.getParameter("endDate");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //String endDate = format.format(DateUtil.parseDate(_endDate, "yyyy-MM-dd"));
        String endDate = format.format(new Date());
        WsResultBean resultBean = employeeSupportService.doCancelEmployeeStationHisSupport(wxUser.getLoginName(), supportedLoginName, endDate);

        return JSON.toJSONString(resultBean);
    }

}
