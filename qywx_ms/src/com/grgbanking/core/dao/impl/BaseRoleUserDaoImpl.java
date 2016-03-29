package com.grgbanking.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.grgbanking.core.dao.BaseRoleUserDao;
import com.grgbanking.core.entity.user.BaseRoleUser;
import com.grgbanking.core.service.BaseService;

@Repository("baseRoleUserDao")
public class BaseRoleUserDaoImpl extends BaseService<BaseRoleUser, Long> implements BaseRoleUserDao {

    @Override
    public List<BaseRoleUser> query(BaseRoleUser baseRoleUser) {
        @SuppressWarnings("unchecked")
        List<BaseRoleUser> list = supperHibernateDao.find(DetachedCriteria.forClass(
                entityClass).add(Property.forName("userId").eq(baseRoleUser.getUserId())));
        return list;
    }

}
