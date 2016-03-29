package com.grgbanking.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.grgbanking.core.dao.DeviceDao;
import com.grgbanking.core.entity.user.Device;
import com.grgbanking.core.service.BaseService;

/**
 * 
 * 版权所有：2015-GRGBANKING 项目名称：Qywx_ms
 * 
 * 类描述：设备检查 类名称：com.grgbanking.core.entity.user.DeviceDao 创建人：TXH 创建时间：2015-7-6
 * 上午10:39:49 修改人： 修改时间：2015-7-6 上午10:39:49 修改备注：
 * 
 * @version V1.0
 */
@Repository("deviceDao")
public class DeviceDaoImpl extends BaseService<Device, Long> implements
		DeviceDao {

	@Override
	public void insert(Device device) {
		supperHibernateDao.saveOrUpdate(device);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Device findDevice(Device device) {
		Device d = null;
		List<Device> list = supperHibernateDao.find(DetachedCriteria
				.forClass(entityClass)
				.add(Property.forName("loginName").eq(device.getLoginName()))
				.add(Property.forName("deviceId").eq(device.getDeviceId())));
		if (list != null && list.size() > 0)
			d = list.get(0);
		return d;
	}
}
