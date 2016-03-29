package com.grgbanking.core.entity.common;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.alibaba.fastjson.JSON;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class JsonEntity {
	/**
	 * 返回代码
	 */
	private Integer errcode;
	/**
	 * 返回的消息
	 */
	private String errmsg;

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
