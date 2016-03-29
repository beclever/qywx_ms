package com.grgbanking.core.controller.wechat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import me.chanjar.weixin.common.util.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grgbanking.common.consts.Consts;
import com.grgbanking.common.utils.LoginUser;
import com.grgbanking.common.utils.PropUtils;
import com.grgbanking.core.entity.user.WxUser;
import com.grgbanking.core.service.user.WxUserService;

@Controller
public class CommonController {
    @Resource(name="wxUserService")
    private WxUserService wxUserService;
    /**
     * 设备未审核
     * @Title: index
     * @Description: 设备未审核，跳转提示页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/norights")
    public String index(HttpServletRequest request) {
        return "/common/norights";
    }
    /**
     * 会话过期
     * @Title: noSession
     * @Description: 会话过期，跳转提示页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/noSession")
    public String noSession(HttpServletRequest request){
        return "/common/noSession";
    }
    /**
     * 创建session
     * @Title: createSession
     * @param loginName
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value="createSession")
    public WxUser createSession(String loginName,HttpSession session){
        WxUser wxUser = this.wxUserService.getUserInfo(loginName);
        session.setAttribute(Consts.SESSION_USER, wxUser);
        if(wxUser != null){
            System.out.println("创建Session成功！姓名：【"+wxUser.getName()+"】登录名：【"+wxUser.getLoginName()+
                    "】手机：【"+wxUser.getMobilePhone()+
                    "】用户编号：【"+wxUser.getUserCode()+"】"); 
        }
        return wxUser;
    }
    
    /**
     * "更多"菜单页面
     * @Title: weChatMenuInfo
     * @return
     */
    @RequestMapping(value = "/cp/ouath/weChatMenuInfo")
    public String weChatMenuInfo(HttpServletRequest request){
        
        WxUser wxUser = LoginUser.getSessionUser();

        //试运行名单
        String testRunList = PropUtils.get("TestRunList");
        if(StringUtils.isNotBlank(testRunList)){
            if(!testRunList.contains(wxUser.getLoginName())){
                request.setAttribute("msg", "该功能还在试运行期间，敬请期待");
                return "/common/nocheck";
            }
        }else{
            request.setAttribute("msg", "该功能还在试运行期间，敬请期待");
            return "/common/nocheck";
        }
        return "/test/index-squares";
    }
}
