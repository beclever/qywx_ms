package com.grgbanking.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.grgbanking.core.dao.UserDao;
import com.grgbanking.core.entity.user.User;
import com.grgbanking.core.service.BaseService;

@SuppressWarnings("all")
@Repository("userDao")
public class UserDaoImpl extends BaseService<User, Long> implements UserDao {

	@Override
	public User getUser(String loginName) {
		User userInfo = null;
		List<User> list = supperHibernateDao.find(DetachedCriteria.forClass(
				entityClass).add(Property.forName("loginId").eq(loginName)));
		if (list != null && list.size() > 0)
			userInfo = list.get(0);
		return userInfo;
	}

	@Override
	public User queryById(Long id) {
		User user = null;
		List<User> list = supperHibernateDao.find(DetachedCriteria.forClass(
				entityClass).add(Property.forName("id").eq(id)));
		if (list != null && list.size() > 0)
			user = list.get(0);
		return user;
	}

}
