package com.grgbanking.core.dao;

import com.grgbanking.core.entity.user.User;

/**
 * 
 * 版权所有：2015-GRGBANKING 项目名称：Qywx_ms
 * 
 * 类描述：用户持久 类名称：com.grgbanking.core.dao.UserDao 创建人：TXH 创建时间：2015-7-2 上午10:58:09
 * 修改人： 修改时间：2015-7-2 上午10:58:09 修改备注：
 * 
 * @version V1.0
 */
public interface UserDao {
	public User getUser(String loginName);

	// 查
	public User queryById(Long id);
}
