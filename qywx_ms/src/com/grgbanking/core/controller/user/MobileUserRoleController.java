package com.grgbanking.core.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.grgbanking.common.consts.Consts;
import com.grgbanking.common.consts.WsConsts;
import com.grgbanking.common.utils.LoginUser;
import com.grgbanking.core.controller.BaseController;
import com.grgbanking.core.entity.ResultJson;
import com.grgbanking.core.entity.user.MobileUserRole;
import com.grgbanking.core.entity.user.WxUser;
import com.grgbanking.core.service.user.MobileUserRoleService;

/**
 * 用户角色控制器
 * 版权所有：2015-GRGBANKING
 * 项目名称：qywx_ms_pro   
 * 类描述：
 * 类名称：com.grgbanking.core.controller.workorder.MobileUserRoleController     
 * 创建人：WSH
 * 创建时间：2015年12月29日 下午4:41:41   
 * 修改人：
 * 修改时间：2015年12月29日 下午4:41:41   
 * 修改备注：   
 * @version   V1.0
 */
@Controller
public class MobileUserRoleController extends BaseController {
    
    //用户角色服务
    @Resource(name = "mobileUserRoleService")
    private MobileUserRoleService mobileUserRoleService;

    /**
     * 
     * @Title: workOrderPage
     * @Description: TODO(工单列表视图)
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cp/ouath/mobileUserRole/queryByRoleId")
    public ModelAndView workOrderPage() {
        WxUser wxUser = LoginUser.getSessionUser();
        //返回结果，errcode=0表示不是服务站主任，1则是。
        ResultJson<String> jsonData = new ResultJson<String>(0, "", null);
        MobileUserRole mobileUserRole = new MobileUserRole();
        mobileUserRole.setRoleId(Long.parseLong(WsConsts.SERVICE_DIRECTOR));
        mobileUserRole.setUserId(wxUser.getId());
        Boolean isExist = mobileUserRoleService.IsExistUserRole(mobileUserRole);
        if(isExist){
            jsonData.setErrcode(1);
            jsonData.setData(WsConsts.EquipmentCharge);
        }
        /*List<MobileUserRole> mobileUserRoleList = mobileUserRoleService.queryByRoleId(Consts.SERVICE_DIRECTOR);
        if(mobileUserRoleList != null && mobileUserRoleList.size() > 0)
        {
            for (MobileUserRole item : mobileUserRoleList) {
                if(item.getUserId() == wxUser.getId()){
                    jsonData.setErrcode(1);
                    break;
                }
            }
        }*/
        ModelAndView modelAndView = new ModelAndView("/user/mobileUserRole");
        modelAndView.addObject("result",jsonData);
        return modelAndView;
    }

}
