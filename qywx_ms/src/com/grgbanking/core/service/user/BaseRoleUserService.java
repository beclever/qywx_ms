package com.grgbanking.core.service.user;

import java.util.List;

import com.grgbanking.core.entity.user.BaseRoleUser;

public interface BaseRoleUserService {

    /**
     * 查询用户角色列表
     * @Title: queryByLoginName
     * @Description: 查询用户角色列表
     * @param loginName
     * @return
     */
    public List<BaseRoleUser> queryByLoginName(String loginName);
}
