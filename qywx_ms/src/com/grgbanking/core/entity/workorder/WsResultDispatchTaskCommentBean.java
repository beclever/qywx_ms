package com.grgbanking.core.entity.workorder;

import java.util.List;

/**
 * 
* @ClassName: WsResultDispatchTaskComment 
* @Description: WebService的任务单审批情况  
* @author boonLay
* @date 2014-3-13 上午11:59:05
 */
public class WsResultDispatchTaskCommentBean {
	
	private Integer status;// int(Y) 1：成功。0：失败。
	private String errMsg;// String(Y) 提示错误信息
    private List<DispatchTaskComment> dispatchTaskComment;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public List<DispatchTaskComment> getDispatchTaskComment() {
		return dispatchTaskComment;
	}
	public void setDispatchTaskComment(List<DispatchTaskComment> dispatchTaskComment) {
		this.dispatchTaskComment = dispatchTaskComment;
	}
    
}