package com.grgbanking.core.entity.workorder;

import java.util.Date;

/**
 * 
* @ClassName: DispatchTaskComment 
* @Description:
* @author boonLay
* @date 2014-3-13 上午10:35:54
 */
public class DispatchTaskComment {
	
    private Long commentId;		//审批ID
    private Long dispatchId;	//任务工单ID
    private Long commentUserId;	//审批人ID
    private String commentUserName; //审批人
    private String remark;		//备注
    private Date commentTime;	//审批时间

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

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}