package com.grgbanking.core.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_MOBILE_USER_ROLE", uniqueConstraints = @UniqueConstraint(columnNames = { "USER_ID","ROLE_ID" }))
public class MobileUserRole {

    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 角色Id
     */
    private Long roleId;

    @Id
    @Column(name = "USER_ID", nullable = false, unique = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "ROLE_ID", nullable = false, unique = true)
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
