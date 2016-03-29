package com.grgbanking.core.dao;

import java.util.List;

import com.grgbanking.core.entity.user.MobileUserRole;

/**
 * 
 * 版权所有：2015-GRGBANKING
 * 项目名称：qywx_ms   
 *
 * 类描述：
 * 类名称：com.grgbanking.core.dao.MobileUserRoleDao     
 * 创建人：WSH
 * 创建时间：2015年12月29日 下午4:11:37   
 * 修改人：
 * 修改时间：2015年12月29日 下午4:11:37   
 * 修改备注：   
 * @version   V1.0
 */
public interface MobileUserRoleDao{

	/**
	 * 根据角色ID查询用户列表
	 * @Title: queryByRoleId
	 * @Description: 根据角色ID查询用户列表
	 * @param roleId
	 * @return
	 */
	public List<MobileUserRole> queryByRoleId(Long roleId);
	
	/**
	 * 判断指定用户是否具有角色权限
	 * @Title: IsExistUserRole
	 * @Description: 判断指定用户是否具有角色权限
	 * @param mobileUserRole
	 * @return
	 */
	public Boolean IsExistUserRole(MobileUserRole mobileUserRole);
}
