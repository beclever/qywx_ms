package com.grgbanking.core.entity;
/**
 * 
 * 版权所有：2015-GRGBANKING
 * 项目名称：Qywx_ms   
 *
 * 类描述：ajax统一单数据消息返回 1成功 0 失败 data为携带数据
 * 类名称：com.grgbanking.core.entity.ResultJson     
 * 创建人：TXH 
 * 创建时间：2015-7-14 上午11:06:27   
 * 修改人：
 * 修改时间：2015-7-14 上午11:06:27   
 * 修改备注：   
 * @version   V1.0
 */
public class ResultJson<T> {
    /*
     * 1 表示成功；0 表示失败
     */
    private int errcode;
    /*
     * 提示消息
     */
    private String errmsg;
    private T data;
    
    public ResultJson(int errcode,String errmsg,T data){
        this.errcode=errcode;
        this.errmsg=errmsg;
        this.data=data;
    }
    
    public int getErrcode() {
        return errcode;
    }
    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
    public String getErrmsg() {
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    
}
