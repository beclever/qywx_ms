package com.grgbanking.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.grgbanking.core.dao.WxUserDao;
import com.grgbanking.core.entity.user.WxUser;
import com.grgbanking.core.service.BaseService;

@Repository("wxUserDao")
public class WxUserDaoImpl extends BaseService<WxUser, Long> implements
		WxUserDao {

	@SuppressWarnings("unchecked")
	@Override
	public WxUser getUserInfo(String loginName) {
		WxUser userInfo = null;
		List<WxUser> list = supperHibernateDao.find(DetachedCriteria.forClass(
				entityClass).add(Property.forName("loginName").eq(loginName)));
		if (list != null && list.size() > 0)
			userInfo = list.get(0);
		return userInfo;
	}

}
