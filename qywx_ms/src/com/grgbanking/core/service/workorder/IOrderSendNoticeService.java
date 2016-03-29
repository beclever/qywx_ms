package com.grgbanking.core.service.workorder;

import com.grgbanking.core.entity.ws.WsWorkFormBean;



/**
 * 派单通知服务
 * @author zytao1
 *
 */
public interface IOrderSendNoticeService {
    /**
     * 派单成功单后给工程师和主任发微信消息
     * @param wsWorkFormBean
     * @param clientType
     */
    public void doSendEngineerAndChiefSMS(WsWorkFormBean wsWorkFormBean);
}
