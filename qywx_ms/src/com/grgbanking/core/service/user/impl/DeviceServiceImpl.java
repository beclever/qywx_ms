package com.grgbanking.core.service.user.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grgbanking.core.dao.DeviceDao;
import com.grgbanking.core.entity.user.Device;
import com.grgbanking.core.service.user.DeviceService;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
	@Resource(name = "deviceDao")
	private DeviceDao deviceDao;

	@Override
	public void insert(Device device) {
		device.setCreateTime(new Date());
		device.setUpdateTime(new Date());
		device.setTicket("MS");
		this.deviceDao.insert(device);
	}

	@Override
	public Device findDevice(String loginName, String deviceId) {
		// TODO Auto-generated method stub
		Device device = new Device();
		device.setLoginName(loginName);
		device.setDeviceId(deviceId);
		return this.deviceDao.findDevice(device);
	}

}
