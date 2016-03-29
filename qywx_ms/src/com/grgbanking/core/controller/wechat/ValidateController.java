package com.grgbanking.core.controller.wechat;

import javax.annotation.Resource;

import me.chanjar.weixin.common.exception.WxErrorException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grgbanking.core.entity.common.JsonEntity;
import com.grgbanking.core.entity.user.User;
import com.grgbanking.core.service.user.UserService;

/**
 * 
 * 版权所有：2015-GRGBANKING 项目名称：Qywx_ms
 * 
 * 类描述：用户验证 类名称：com.grgbanking.core.controller.wechat.ValidateController 创建人：TXH
 * 创建时间：2015-7-2 下午1:52:06 修改人： 修改时间：2015-7-2 下午1:52:06 修改备注：
 * 
 * @version V1.0
 */
@Controller
public class ValidateController extends AbstractWechat {
	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(value = "/cp/validate")
	public String validate(String code, String state, Model model)
			throws WxErrorException {
		String[] res = wxCpService.oauth2getUserInfo(code);
		String userId = res[0];
		String deviceId = res[1];
		User user = this.userService.getUser(userId);
		logger.info("userId【" + userId + "】,deviceId【" + deviceId
				+ "】----userInfo【loginName:" + user.getLoginName() + ",ID:"
				+ user.getId());

		return "/test/login";
	}

	@RequestMapping(value = "/cp/docheck")
	@ResponseBody
	public JsonEntity docheck(String loginName, String telephone)
			throws WxErrorException {
		JsonEntity json = new JsonEntity();
		json.setErrcode(1);
		json.setErrmsg("验证失败，请核对信息");
		if (loginName.equals("txhong1") && telephone.equals("13802520827")) {
			json.setErrcode(0);
			json.setErrmsg("验证成功，您已成功关注企业号");
			// 验证成功
			logger.info("验证成功");
			wxCpService.userAuthenticated(loginName);
		}
		return json;
	}
}
