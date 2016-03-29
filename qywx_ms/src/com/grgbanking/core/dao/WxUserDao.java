package com.grgbanking.core.dao;

import com.grgbanking.core.entity.user.WxUser;

public interface WxUserDao {

    public WxUser getUserInfo(String loginName);
}
