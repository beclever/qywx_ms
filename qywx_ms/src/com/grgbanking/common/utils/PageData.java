package com.grgbanking.common.utils;

import java.util.List;

import com.grgbanking.common.exception.ZorePageSizeException;

/**
 * 分页数据
 * @author hujz
 *
 * @param <T>
 */
public class PageData<T> {
	private List<T> data;
	private int total;
	private int start;
	private int limit;

	public PageData(int limit) {
		if (limit <= 0)
			throw new ZorePageSizeException(limit);
		
		this.limit = limit;
	}

	public PageData(int start, int limit) {
		if (limit <= 0)
			throw new ZorePageSizeException(limit);
		
		this.start = start;
		this.limit = limit;
	}

	public List<T> getData() {
		return data;
	}
	
	public int getTotalPage() {
		return total % limit == 0 ? total / limit : (total / limit + 1);
	}
	
	public int getCurrPage() {
		return (start + 1) % limit == 0 ? (start + 1) / limit : ((start + 1) / limit + 1);
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
