package com.grgbanking.core.entity.workorder;


import java.util.List;

//5.1.21.	选项信息
public class WsOptionsBean {

	private List<WsOptionValueBean> optionValue;// 选项值信息
												// Array(Y)，数组内数据结构详见5.1.22

	private String defaultId;// 默认值ID String(Y)
	private String defaultName;// 默认值名称 String(Y)

	public List<WsOptionValueBean> getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(List<WsOptionValueBean> optionValue) {
		this.optionValue = optionValue;
	}

	public String getDefaultId() {
		return defaultId;
	}

	public void setDefaultId(String defaultId) {
		this.defaultId = defaultId;
	}

	public String getDefaultName() {
		return defaultName;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

}
