package com.grgbanking.common.enums;

/**
 * 
 * 版权所有：2015-GRGBANKING 项目名称：Qywx_ms
 * 
 * 类描述： 类名称：com.grgbanking.common.enums.FaultType 创建人：TXH 创建时间：2015-7-17
 * 下午2:32:37 修改人： 修改时间：2015-7-17 下午2:32:37 修改备注：
 * 
 * @version V1.0
 */
public enum FaultType {
    // 利用构造函数传参
    /**
     * 故障部位:1
     */
    FAULT_PART("1"),
    /**
     * 故障描述:2
     */
    FAULT_DESC("2"),
    /**
     * 故障原因:3
     */
    FAULT_REASON("3"),
    /**
     * 处理方法:4
     */
    FAULT_FUNC("4");

    // 定义私有变量

    private String name;

    // 构造函数，枚举类型只能为私有

    private FaultType(String name) {

        this.name = name;

    }

    @Override
    public String toString() {

        return String.valueOf(this.name);

    }
}
