package com.grgbanking.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.grgbanking.core.entity.user.BaseRoleUser;

public class BaseUserRoleUtil {

    public static Boolean isExist(List<BaseRoleUser> cssRoles, Long roleId)
    {
        List<Long> cssRoleIds = new ArrayList<Long>();
        if(cssRoles != null && cssRoles.size() > 0){
            for (int i = 0; i < cssRoles.size(); i++) {
                cssRoleIds.add(cssRoles.get(i).getRoleId());
            } 
        }else{
            return false;
        }
        if(cssRoleIds.contains(roleId)){
            return true;
        }
        return false;
    }
}
