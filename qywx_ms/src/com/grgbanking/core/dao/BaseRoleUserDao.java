package com.grgbanking.core.dao;

import java.util.List;

import com.grgbanking.core.entity.user.BaseRoleUser;

/**
 * 
 * 版权所有：2016-GRGBANKING
 * 项目名称：qywx_ms_pro   
 *
 * 类描述：
 * 类名称：com.grgbanking.core.dao.BaseRoleUserDao     
 * 创建人：WSH
 * 创建时间：2016年3月15日 下午2:15:21   
 * 修改人：
 * 修改时间：2016年3月15日 下午2:15:21   
 * 修改备注：   
 * @version   V1.0
 */
public interface BaseRoleUserDao{

	/**
	 * 查询用户角色列表
	 * @Title: query
	 * @Description: 查询用户角色列表
	 * @param baseRoleUser
	 * @return
	 */
	public List<BaseRoleUser> query(BaseRoleUser baseRoleUser);
}
