package com.grgbanking.core.entity.ws;

/**
 * 返回结果
 * 
 * @author yt
 * 
 */
public class WsResultBean {
	private boolean returnResult;// false-调用失败；true-调用成功
	private String returnMessage; // 调用成功后的返回值
	private String errorMessage;// 调用失败的原因

	public boolean isReturnResult() {
		return returnResult;
	}

	public void setReturnResult(boolean returnResult) {
		this.returnResult = returnResult;
	}

	public boolean getReturnResult() {
		return returnResult;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
