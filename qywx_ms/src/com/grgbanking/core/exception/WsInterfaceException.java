package com.grgbanking.core.exception;

import javax.xml.ws.WebServiceException;


/**
 * 
 * 版权所有：2015-GRGBANKING 项目名称：AtmMendService
 * 
 * 类描述：webservice接口自定义异常 类名称：com.grgbanking.core.exception.WsInterfaceException
 * 创建人：TXH 创建时间：2015-5-28 下午2:08:45 修改人： 修改时间：2015-5-28 下午2:08:45 修改备注：
 * 
 * @version V1.0
 */
public class WsInterfaceException extends Exception {
    /**
     * @Fields serialVersionUID : TODO(序列)
     */
    private static final long serialVersionUID = -8442741012000830188L;

    private String url;// 异常抛出所用接口地址

    private String errMethod;// 异常抛出所在方法
    
    private Integer errCode;
    
    public String getUrl() {
        return url;
    }

    public String getErrMethod() {
        return errMethod;
    }
    
    public Integer getErrCode() {
        return errCode;
    }
    public WsInterfaceException(Exception e, String url, String method) {
        super(e);
        this.errMethod = method;
        this.url = url;
        if(e instanceof WebServiceException&&e.getMessage().equals("Could not send Message.")){
            this.errCode=-1;
        }else{
            this.errCode=1;
        }
    }
}
