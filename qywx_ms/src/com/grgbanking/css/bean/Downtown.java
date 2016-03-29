package com.grgbanking.css.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * T_BASE_DOWNTOWN
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: cxhui
 * Date: 2010-9-28 下午01:54:58
 */
@Entity
@Table(name = "V_BASE_DOWNTOWN")
public class Downtown implements Serializable{

	private String idcardNo;
	private String idcardAreaName;
	
	@Id
	@Column(name="IDCARD_NO")
	public String getIdcardNo() {
		return idcardNo;
	}
	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}
	
	@Column(name="IDCARD_AREA_NAME")
	public String getIdcardAreaName() {
		return idcardAreaName;
	}
	public void setIdcardAreaName(String idcardAreaName) {
		this.idcardAreaName = idcardAreaName;
	}
}
