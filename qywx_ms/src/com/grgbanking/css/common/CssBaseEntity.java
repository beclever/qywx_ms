/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhang zhi
 * Date: 2010-4-19 上午10:58:02
 */
package com.grgbanking.css.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.grgbanking.css.util.CommonConstants;
/**
 * 所有业务entity都必须继承此类 如果需要增加一些公用字段，只需在此类中添加即可
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-5-29 上午11:41:21
 */
@MappedSuperclass
public class CssBaseEntity implements Serializable{
	// 创建日期
	protected Date createDate=new Date();
	// 更新日期
	protected Date modifyDate=new Date();
	// 创建人
	protected Integer createUserId;
	// 更新人
	protected Integer modifyUserId;
	// 版本号
	protected Integer version;
	// 是否删除
	protected String deleted=CommonConstants.FLAG_N;
	/**
	 * @return the createDate
	 */
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the modifyDate
	 */
	@Column(name = "MODIFY_DATE")
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	/**
	 * @return the createUserId
	 */
	@Column(name = "CREATE_USER_ID")
	public Integer getCreateUserId() {
		return createUserId;
	}
	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * @return the modifyUserId
	 */
	@Column(name = "MODIFY_USER_ID")
	public Integer getModifyUserId() {
		return modifyUserId;
	}
	/**
	 * @param modifyUserId the modifyUserId to set
	 */
	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	/**
	 * @return the version
	 */
	@Version
	@Column(name = "VERSION")
	public Integer getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	/**
	 * @return the deleted
	 */
	@Column(name = "DELETED")
	public String getDeleted() {
		return deleted;
	}
	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
}
