package com.grgbanking.core.dao;

import com.grgbanking.core.entity.user.Device;

/**
 * 
 * 版权所有：2015-GRGBANKING 项目名称：Qywx_ms
 * 
 * 类描述：设备检查 类名称：com.grgbanking.core.entity.user.DeviceDao 创建人：TXH 创建时间：2015-7-6
 * 上午10:39:49 修改人： 修改时间：2015-7-6 上午10:39:49 修改备注：
 * 
 * @version V1.0
 */
public interface DeviceDao {
    /**
     * 插入
     * @Title: insert
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param device
     * @return
     */
    public void insert(Device device);
    /**
     * 查找
     * @Title: findDevice
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param device
     * @return
     */
    public Device findDevice(Device device);
}
