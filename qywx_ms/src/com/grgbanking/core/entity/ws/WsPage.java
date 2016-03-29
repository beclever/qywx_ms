package com.grgbanking.core.entity.ws;

import org.apache.commons.lang.StringUtils;

/**
 * 分页信息
 * 
 * @author yt
 * 
 */

public class WsPage {

	private Integer rows;// 每页显示记录 Integer（N）
	private Integer page = 1;// 开始查询的页，默认从第一页开始查询 Integer（N）
	private Integer recordCount;// 总记录数(返回参数) Integer（N）

	private String sort;// 排序的字段(提交参数) String（N）
	private String order;// 排序方向(asc|desc) String（N）

	/**
	 * 无参构造函数
	 */
	public WsPage() {
	}

	/**
	 * 分页信息有参构造函数
	 * 
	 * @param rows
	 *            每页显示行数
	 * @param page
	 *            查询页码
	 */
	public WsPage(Integer rows, String page) {
		this.rows = rows;
		if (StringUtils.isNotBlank(page)) {
			this.page = Integer.valueOf(page);
		} else {
			this.page = 1;
		}
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
