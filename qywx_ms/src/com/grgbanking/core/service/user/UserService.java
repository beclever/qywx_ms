package com.grgbanking.core.service.user;

import com.grgbanking.core.entity.user.User;

public interface UserService {
	public User getUser(String loginName);

	// 查
	public User queryById(Long id);
}
