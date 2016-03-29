package com.grgbanking.core.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grgbanking.core.dao.BaseRoleUserDao;
import com.grgbanking.core.entity.user.BaseRoleUser;
import com.grgbanking.core.service.user.BaseRoleUserService;
import com.grgbanking.css.bean.CssUser;
import com.grgbanking.css.dao.CssUserDAO;

@Service("baseRoleUserService")
public class BaseRoleUserServiceImpl implements BaseRoleUserService {
    
	@Resource(name = "baseRoleUserDao")
	private BaseRoleUserDao baseRoleUserDao;
	
	@Autowired
    private CssUserDAO<CssUser, Integer> userDAO;

    @Override
    public List<BaseRoleUser> queryByLoginName(String loginName) {
        CssUser cssUser = userDAO.getUserByLoginName(loginName);
        BaseRoleUser baseRoleUser = new BaseRoleUser();
        baseRoleUser.setUserId(cssUser.getUserId().longValue());
        return baseRoleUserDao.query(baseRoleUser);
    }
    
}
