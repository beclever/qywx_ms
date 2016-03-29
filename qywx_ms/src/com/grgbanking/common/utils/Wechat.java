package com.grgbanking.common.utils;

import com.grgbanking.common.handle.WechatMessageHandle;

import me.chanjar.weixin.cp.api.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.api.WxCpMessageRouter;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;




public class Wechat {
	private WxCpInMemoryConfigStorage wxCpConfigStorage;
	private WxCpService wxCpService;
	private WxCpMessageRouter wxCpMessageRouter;
	private static Wechat instance;

	private Wechat() {
		wxCpConfigStorage = new WxCpInMemoryConfigStorage();
		wxCpConfigStorage.setCorpId(PropUtils.get("corpId"));
		wxCpConfigStorage.setCorpSecret(PropUtils.get("corpSecret"));
		wxCpConfigStorage.setAesKey(PropUtils.get("aesKey"));
		wxCpConfigStorage.setToken(PropUtils.get("token"));
		
	}

	public static Wechat getInstance() {
		if (instance == null) {
			instance = new Wechat();
		}
		return instance;
	}

	public WxCpInMemoryConfigStorage getWxCpInMemoryConfigStorage() {
		return this.wxCpConfigStorage;
	}

	public WxCpService getWxCpService() {
		wxCpService = new WxCpServiceImpl();
		wxCpService.setWxCpConfigStorage(getWxCpInMemoryConfigStorage());
		return wxCpService;
	}

	public WxCpMessageRouter getWxCpMessageRouter() {
	    wxCpMessageRouter = new WxCpMessageRouter(getWxCpService());
	    wxCpMessageRouter.rule().async(false) // 拦截所有消息,可支持多种规则,非异步
				.handler(new WechatMessageHandle()).end();
		return wxCpMessageRouter;
	}
}
