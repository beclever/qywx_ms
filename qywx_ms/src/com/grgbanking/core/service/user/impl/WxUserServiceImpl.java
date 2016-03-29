package com.grgbanking.core.service.user.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grgbanking.core.dao.WxUserDao;
import com.grgbanking.core.entity.user.WxUser;
import com.grgbanking.core.service.user.WxUserService;

@Service("wxUserService")
public class WxUserServiceImpl implements WxUserService {
	@Resource(name = "wxUserDao")
	private WxUserDao wxUserDao;

	@Override
	public WxUser getUserInfo(String loginName) {
		return this.wxUserDao.getUserInfo(loginName);
	}

}
