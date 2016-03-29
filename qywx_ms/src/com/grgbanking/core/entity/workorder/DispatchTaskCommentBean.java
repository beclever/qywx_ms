package com.grgbanking.core.entity.workorder;

/**
 * 
* @ClassName: DispatchTaskCommentBean 
* @Description:任务单审批实体 
* @author boonLay
* @date 2014-3-13 上午10:35:54
 */

public class DispatchTaskCommentBean {
	
	private Long commentId;

    private Long dispatchId;

    private Long commentUserId;

    private String commentUserName;

    private String remark;

    private String commentTime;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(Long dispatchId) {
		this.dispatchId = dispatchId;
	}

	public Long getCommentUserId() {
		return commentUserId;
	}

	public void setCommentUserId(Long commentUserId) {
		this.commentUserId = commentUserId;
	}

	public String getCommentUserName() {
		return commentUserName;
	}

	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
 
}
