package com.grgbanking.core.service.user.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grgbanking.core.dao.UserDao;
import com.grgbanking.core.entity.user.User;
import com.grgbanking.core.service.user.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource(name = "userDao")
	private UserDao userDao;

	@Override
	public User getUser(String loginName) {
		return this.userDao.getUser(loginName);
	}

	@Override
	public User queryById(Long id) {
		return this.userDao.queryById(id);
	}

}
