package com.grgbanking.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.grgbanking.core.dao.MobileUserRoleDao;
import com.grgbanking.core.entity.user.MobileUserRole;
import com.grgbanking.core.service.BaseService;

@Repository("mobileUserRoleDao")
public class MobileUserRoleDaoImpl extends BaseService<MobileUserRole, Long> implements MobileUserRoleDao {

    @Override
    public List<MobileUserRole> queryByRoleId(Long roleId) {
        @SuppressWarnings("unchecked")
        List<MobileUserRole> list = supperHibernateDao.find(DetachedCriteria.forClass(
                entityClass).add(Property.forName("roleId").eq(roleId)));
        return list;
    }

    @Override
	public Boolean IsExistUserRole(MobileUserRole mobileUserRole)
	{
        @SuppressWarnings("unchecked")
        List<MobileUserRole> list = supperHibernateDao.find(DetachedCriteria.forClass(
                entityClass).add(Property.forName("roleId").eq(mobileUserRole.getRoleId()))
                .add(Property.forName("userId").eq(mobileUserRole.getUserId())));
        if (list != null && list.size() > 0){
            return true;
        }
        else{
            return false;
        }
	}

}
