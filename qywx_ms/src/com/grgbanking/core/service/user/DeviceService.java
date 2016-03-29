package com.grgbanking.core.service.user;

import com.grgbanking.core.entity.user.Device;

public interface DeviceService {
	/**
	 * 插入
	 * 
	 * @Title: insert
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param device
	 * @return
	 */
	public void insert(Device device);

	/**
	 * 根据用户和设备查找是否有权限
	 * 
	 * @Title: findDevice
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param device
	 * @return
	 */
	public Device findDevice(String loginName, String deviceId);
}
