package com.grgbanking.core.controller.wechat;

import me.chanjar.weixin.cp.api.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.api.WxCpMessageRouter;
import me.chanjar.weixin.cp.api.WxCpService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.grgbanking.common.utils.Wechat;

public class AbstractWechat {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected WxCpService wxCpService = Wechat.getInstance().getWxCpService();

    protected WxCpInMemoryConfigStorage wxCpConfigStorage = Wechat.getInstance().getWxCpInMemoryConfigStorage();

    protected WxCpMessageRouter wxCpMessageRouter = Wechat.getInstance().getWxCpMessageRouter();
}
