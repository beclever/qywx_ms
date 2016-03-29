package com.grgbanking.common.handle;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpMessageHandler;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;


@Component
public class WechatMessageHandle implements WxCpMessageHandler {
	Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public WxCpXmlOutMessage handle(WxCpXmlMessage wxCpXmlMessage, Map<String, Object> map, WxCpService wxCpService,
            WxSessionManager wxSessionManager) throws WxErrorException {
        // TODO Auto-generated method stub
        if(wxCpXmlMessage.getMsgType().equals(WxConsts.XML_MSG_EVENT)){
            //事件消息
            if(wxCpXmlMessage.getEvent().equals(WxConsts.EVT_SUBSCRIBE)){
                //关注事件
                logger.info("关注事件");
            }else if(wxCpXmlMessage.getEvent().equals(WxConsts.EVT_UNSUBSCRIBE)){
                //取消关注时间
                logger.info("取消关注事件");
            }
        }
        return null;
    }
	

}
