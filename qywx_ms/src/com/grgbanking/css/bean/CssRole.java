/**
 * Role.java
 * Product:Grgbanking
 * Version:1.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 */
package com.grgbanking.css.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 角色实体类
 * @Project TempusMember
 * @Author Administrator
 * @Version 1.0
 * @Date 2010-2-28 下午12:03:19
 */
@Entity
@Table(name = "V_BASE_ROLE")
public class CssRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4404213853366157267L;
	/**
	 * 角色ID
	 */
	private Integer roleId;
	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 角色代码
	 */
	private String code;
	/**
	 * 角色描述
	 */
	private String description;
	
	/**
	 * 该角色对应的资源菜单
	 */
	private Set<CssResource> resources;
	
	/**
	 * 该角色对应的用户集合
	 */
	private List<CssUser> users= new ArrayList<CssUser>(0);
	
	/**
	 * 角色等级，如：‘A’代表总部；‘B’代表区域；‘C’代表服务站
	 */
	private String roleLevel;
	

	/**
	 * @return the roleId
	 */
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="GENERATOR_SEQ_BASE_ROLE")    
	@SequenceGenerator(name="GENERATOR_SEQ_BASE_ROLE", sequenceName="SEQ_BASE_ROLE",initialValue=1,allocationSize=1)
	@Column(name="ROLE_ID")
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the name
	 */
	@Column(name="NAME")
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code
	 */
	@Column(name="CODE")
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the resources
	 */
	@ManyToMany(targetEntity = CssResource.class, fetch = FetchType.LAZY)
    @JoinTable(name = "T_BASE_ROLE_RESOURCE", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "RESOURCE_ID"))
	@Fetch(FetchMode.SUBSELECT)
	public Set<CssResource> getResources() {
		return resources;
	}
	
	/**
	 * @param resources the resources to set
	 */
	public void setResources(Set<CssResource> resources) {
		this.resources = resources;
	}

	/**
	 * 这个很重要，方便获取角色的CODE
	 */
	@Override
	public String toString() {
		return code;
	}
	
	/**
	 * @return the users
	 */
	@ManyToMany(targetEntity = CssUser.class, fetch = FetchType.LAZY)
	@JoinTable(name = "T_BASE_ROLE_USER", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID"))
	@Fetch(FetchMode.SUBSELECT)
	public List<CssUser> getUsers() {
		return users;
	}
	
	/**
	 * @param users the users to list
	 */
	public void setUsers(List<CssUser> users) {
		this.users = users;
	}
	
	/**
	 * @return the ROLE_LEVEL
	 */
	@Column(name="ROLE_LEVEL")
	public String getRoleLevel() {
		return roleLevel;
	}
	
	/**
	 * @param roleLevel the roleLevel to set
	 */
	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}
	
	

	
}
