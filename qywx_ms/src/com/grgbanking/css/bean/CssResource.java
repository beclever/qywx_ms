package com.grgbanking.css.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 菜单，功能实体类
 * @Author zhang zhi
 * @Version 1.0
 * @Date 2010-2-28 下午12:03:25
 */
@Entity
@Table(name = "V_BASE_RESOURCE")
public class CssResource implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7125050223903204674L;
	/*主键ID*/
	private Integer resourceId;
	/*资源名称*/
	private String name;
	/*资源类型MENU,FUNCTION*/
	private String type;
	/*资源值URL或FUNCTION的唯一标识KEY*/
	private String value;
	/*资源描述信息*/
	private String description;
	/*显示图标*/
	private String icon;
	
	/*优先显示顺序*/
	private Integer sort;
	
	private List<CssResource> children = new ArrayList<CssResource>(0);
	
	/*用于菜单项，菜单间的上下级关系*/
	
	private CssResource parent;
	
	/*该资源所对应的角色*/
	private Set<CssRole> roles;
	/**
	 * @return the resourceId
	 */
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="GENERATOR_SEQ_BASE_RESOURCE")    
	@SequenceGenerator(name="GENERATOR_SEQ_BASE_RESOURCE", sequenceName="SEQ_BASE_RESOURCE",initialValue=1,allocationSize=1)
	@Column(name="RESOURCE_ID")
	public Integer getResourceId() {
		return resourceId;
	}
	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
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
	 * @return the type
	 */
	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the value
	 */
	@Column(name="VALUE")
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
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
	 * @return the sort
	 */
	@Column(name="SORT")
	public Integer getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	/**
	 * @return the roles
	 */
	@ManyToMany(targetEntity = CssRole.class, fetch = FetchType.LAZY)
    @JoinTable(name = "T_BASE_ROLE_RESOURCE", joinColumns = @JoinColumn(name = "RESOURCE_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	@Fetch(FetchMode.SUBSELECT)
	public Set<CssRole> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<CssRole> roles) {
		this.roles = roles;
	}
	/**
	 * @return the parent
	 */
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch=FetchType.LAZY )
    @JoinColumn(name = "PARENT_ID")
	public CssResource getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(CssResource parent) {
		this.parent = parent;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	@OrderBy("sort asc")
	public List<CssResource> getChildren() {
		return children;
	}
	public void setChildren(List<CssResource> children) {
		this.children = children;
	}
	@Column(name="ICON")
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
