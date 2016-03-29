package com.grgbanking.common.exception;

/**
 * 分页对象的分页大小未非正整数
 * @author hujz
 *
 */
public class ZorePageSizeException extends RuntimeException {

	private static final long serialVersionUID = 507913120894129133L;
	public ZorePageSizeException(int size) {
		super("分页大小为非正整数[" + size + "]！");
	}
}
