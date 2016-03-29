package com.grgbanking.core.entity.stockbean;

import java.util.ArrayList;
import java.util.List;

public class WsBorrowDetailBean {
	private String userName;	//备件借用人姓名
		
	private String borrowDep;	//备件借用人部门
	
	private int total;	        //借用备件记录的总条数
	
	private List<WsBorrowDetailInfoBean> wsBorrowDetailInfoBeans = new ArrayList<WsBorrowDetailInfoBean>();

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the borrowDep
	 */
	public String getBorrowDep() {
		return borrowDep;
	}

	/**
	 * @param borrowDep the borrowDep to set
	 */
	public void setBorrowDep(String borrowDep) {
		this.borrowDep = borrowDep;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the wsBorrowDetailInfoBeans
	 */
	public List<WsBorrowDetailInfoBean> getWsBorrowDetailInfoBeans() {
		return wsBorrowDetailInfoBeans;
	}

	/**
	 * @param wsBorrowDetailInfoBeans the wsBorrowDetailInfoBeans to set
	 */
	public void setWsBorrowDetailInfoBeans(
			List<WsBorrowDetailInfoBean> wsBorrowDetailInfoBeans) {
		this.wsBorrowDetailInfoBeans = wsBorrowDetailInfoBeans;
	}
	
	
    
	

}
