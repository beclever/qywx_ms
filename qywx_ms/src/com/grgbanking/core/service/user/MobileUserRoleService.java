package com.grgbanking.core.service.user;

import java.util.List;

import com.grgbanking.core.entity.user.MobileUserRole;

public interface MobileUserRoleService {

    /**
     * 根据角色ID查询用户
     * @Title: queryByRoleId
     * @Description: 根据角色ID查询用户
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
