package com.grgbanking.core.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grgbanking.core.dao.MobileUserRoleDao;
import com.grgbanking.core.entity.user.MobileUserRole;
import com.grgbanking.core.service.user.MobileUserRoleService;

@Service("mobileUserRoleService")
public class MobileUserRoleServiceImpl implements MobileUserRoleService {
	@Resource(name = "mobileUserRoleDao")
	private MobileUserRoleDao mobileUserRoleDao;

    @Override
    public List<MobileUserRole> queryByRoleId(Long roleId) {
        return mobileUserRoleDao.queryByRoleId(roleId);
    }
    
    @Override
    public Boolean IsExistUserRole(MobileUserRole mobileUserRole)
    {
        return mobileUserRoleDao.IsExistUserRole(mobileUserRole);
    }

}
