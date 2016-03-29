package com.grgbanking.core.entity;

import java.util.List;
/**
 * 
 * 版权所有：2015-GRGBANKING
 * 项目名称：Qywx_ms   
 *
 * 类描述：统一页面数据返回类，1为正常，0为错误，为了统一错误码
 * 类名称：com.grgbanking.core.entity.PageJson     
 * 创建人：TXH 
 * 创建时间：2015-7-13 上午11:47:28   
 * 修改人：
 * 修改时间：2015-7-13 上午11:47:28   
 * 修改备注：   
 * @version   V1.0
 */
public class PageJson<T> {
    
    public PageJson(Integer errcode,String errmsg,List<T> pageData){
        this.errcode=errcode;
        this.errmsg=errmsg;
        this.pageData=pageData;
    }
    
    private Integer errcode;
    private String errmsg;
    public String getErrmsg() {
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    private List<T> pageData;
    public Integer getErrcode() {
        return errcode;
    }
    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }
    public List<T> getPageData() {
        return pageData;
    }
    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }
    
    
}
