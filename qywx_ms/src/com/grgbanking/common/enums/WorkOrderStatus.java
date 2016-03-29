package com.grgbanking.common.enums;
/**
 * 
 * 版权所有：2015-GRGBANKING
 * 项目名称：Qywx_ms   
 *
 * 类描述：工单状态枚举类型
 * 类名称：com.grgbanking.common.enums.WorkOrderStatus     
 * 创建人：TXH 
 * 创建时间：2015-7-6 下午2:55:09   
 * 修改人：
 * 修改时间：2015-7-6 下午2:55:09   
 * 修改备注：   
 * @version   V1.0
 */
public enum WorkOrderStatus {
	// 利用构造函数传参
		/**
		 * 主任审核
		 */
        DIRECTOR_REVIEW("主任审核"),
		/**
		 * 库管员审核
		 */
        WAREHOUSE_KEEPER_REVIEW("库管员审核");

		// 定义私有变量

		private String name;

		// 构造函数，枚举类型只能为私有

		private WorkOrderStatus(String name) {

			this.name = name;

		}

		@Override
		public String toString() {

			return String.valueOf(this.name);

		}
}
