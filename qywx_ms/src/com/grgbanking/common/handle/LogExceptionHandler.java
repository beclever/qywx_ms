package com.grgbanking.common.handle;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;

import com.alibaba.fastjson.JSON;
import com.grgbanking.common.utils.SysContent;
import com.grgbanking.core.entity.user.InterfaceLog;
import com.grgbanking.core.entity.user.WxUser;
import com.grgbanking.core.exception.WsInterfaceException;
import com.grgbanking.core.service.user.InterfaceLogService;
/**
 * 
 * 版权所有：2015-GRGBANKING
 * 项目名称：AtmMendService   
 *
 * 类描述：webservice接口异常日志记录（只记录接口产生的异常，其他异常不记录）
 * 类名称：com.grgbanking.core.handler.LogExceptionHandler     
 * 创建人：TXH 
 * 创建时间：2015-6-4 下午3:28:13   
 * 修改人：
 * 修改时间：2015-6-4 下午3:28:13   
 * 修改备注：   
 * @version   V1.0
 */
public class LogExceptionHandler implements ThrowsAdvice {
    @Resource(name = "interfaceLogService")
    private InterfaceLogService interfaceLogService;

    Logger logger = Logger.getLogger(getClass());

    @SuppressWarnings("unchecked")
    public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
        HttpServletRequest req = SysContent.getRequest();
        if (e instanceof WsInterfaceException) {
            InterfaceLog iLog = new InterfaceLog();
            WxUser wxUser = (WxUser) req.getSession().getAttribute("sessionUser");
            String methodName = ((WsInterfaceException) e).getErrMethod();
            Integer errCode = ((WsInterfaceException) e).getErrCode();
            String url = ((WsInterfaceException) e).getUrl();
            String loginName = wxUser.getLoginName();
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            int i = 1;
            for (Object o : args) {
                jsonMap.put("参数" + i, o);
                i++;
            }
            String requestJson = JSON.toJSONString(jsonMap);
            logger.debug("接口信息====>>【" + url + "】方法【" + methodName + "】");
            logger.debug("当前用户====>>【" + loginName + "】");
            logger.debug("异常通知====>>捕获到异常参数为【" + requestJson + "】");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            iLog.setWsInterfaceUrl(url);
            iLog.setIntfaceMethod(methodName);
            iLog.setLoginName(loginName);
            iLog.setExceptionMsg(sw.toString());
            iLog.setRequestJson(requestJson);
            if(errCode==-1){
                logger.debug("宕机=======================");
            }
            boolean flag = this.interfaceLogService.saveLog(iLog);
            logger.debug("保存日志记录:" + flag);
        } else {
            logger.debug("非接口异常，暂不做处理----------");
        }
    }

}
