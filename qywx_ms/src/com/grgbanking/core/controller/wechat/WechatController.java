package com.grgbanking.core.controller.wechat;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.util.crypto.WxCpCryptUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * 版权所有：2015-GRGBANKING 项目名称：Qywx_ms
 * 
 * 类描述： 类名称：com.grgbanking.core.controller.wechat.WechatController 创建人：TXH
 * 创建时间：2015-7-2 上午11:56:07 修改人： 修改时间：2015-7-2 上午11:56:07 修改备注：
 * 
 * @version V1.0
 */
@Controller
public class WechatController extends AbstractWechat {
    /**
     * 微信消息总入口 后台配置地址http://www.xxx.com/cp/wechat.do
     * 
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/cp/wechat", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String index(HttpServletRequest request) throws IOException {
        String msgSignature = request.getParameter("msg_signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        String echostr = request.getParameter("echostr");
        if (StringUtils.isNotBlank(echostr)) {
            if (!wxCpService.checkSignature(msgSignature, timestamp, nonce, echostr)) {
                // 消息签名不正确，说明不是公众平台发过来的消息
                return "非法请求";
            }
            WxCpCryptUtil cryptUtil = new WxCpCryptUtil(wxCpConfigStorage);
            String plainText = cryptUtil.decrypt(echostr);
            // 说明是一个仅仅用来验证的请求，回显echostr
            return plainText;
        }

        WxCpXmlMessage inMessage = WxCpXmlMessage.fromEncryptedXml(request.getInputStream(), wxCpConfigStorage,
                timestamp, nonce, msgSignature);
        WxCpXmlOutMessage outMessage = wxCpMessageRouter.route(inMessage);
        if (outMessage != null) {
            return outMessage.toEncryptedXml(wxCpConfigStorage);
        }

        return null;
    }
}
