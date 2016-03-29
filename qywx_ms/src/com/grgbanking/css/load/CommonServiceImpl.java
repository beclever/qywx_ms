package com.grgbanking.css.load;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grgbanking.css.bean.City;
import com.grgbanking.css.bean.CssUser;
import com.grgbanking.css.bean.CssUserBean;
import com.grgbanking.css.bean.DataDictionary;
import com.grgbanking.css.bean.Department;
import com.grgbanking.css.bean.DepartmentBean;
import com.grgbanking.css.bean.Downtown;
import com.grgbanking.css.bean.Province;
import com.grgbanking.css.bean.equipment.EquipmentInfo;
import com.grgbanking.css.common.Page;
import com.grgbanking.css.common.UserContext;
import com.grgbanking.css.dao.CityDAO;
import com.grgbanking.css.dao.CssUserDAO;
import com.grgbanking.css.dao.DataDictionaryDAO;
import com.grgbanking.css.dao.DepartmentDAO;
import com.grgbanking.css.dao.DowntownDAO;
import com.grgbanking.css.dao.EquipmentDAO;
import com.grgbanking.css.dao.ProvinceDAO;
import com.grgbanking.css.util.CommonConstants;
import com.grgbanking.css.util.DataDictionaryConstants;
import com.grgbanking.css.util.RoleConstants;


/**
 * 
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-5-31 下午05:28:14
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService{
	@Autowired
	private DataDictionaryDAO<DataDictionary, Integer> dataDictionaryDAO;
	@Autowired
	private EquipmentDAO<EquipmentInfo,Integer> equipmentDAO;
	@Autowired
	private DepartmentDAO<Department,Integer> departmentDAO;
	@Autowired
	private ProvinceDAO<Province, Integer> provinceDAO;
	@Autowired
	private CityDAO<City, Integer> cityDAO;
	@Autowired
	private DowntownDAO<Downtown, Integer> downtownDAO;
	@Autowired
	private CssUserDAO<CssUser,Integer> userDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.service.system.CommonService#findDataDictionary(com.grgbanking.framework.core.Page, com.grgbanking.soc.entity.common.DataDictionary)
	 */
	public List<DataDictionary> findDataDictionary(Page queryPage,DataDictionary dataDictionaryBean) {
		
		return dataDictionaryDAO.findDataDictionaryList(queryPage,dataDictionaryBean);
	}
	
	/* (non-Javadoc)
	 * @see com.grgbanking.soc.service.common.CommonService#getAllDepartment()
	 */
	public List<DepartmentBean> getAllDepartment() {
		List<DepartmentBean> departmentBeanList=new ArrayList<DepartmentBean>();
		//得到封装有所有所有部门的实体的集合
		List<Department> list=departmentDAO.getAllRootDepartment();
		
		//对得到的部门列表迭代并拼成要求的JSON
		for (Department dept : list) {
			DepartmentBean bean=new DepartmentBean();
			bean.setDepartmentId(dept.getDepartmentId());
			bean.setDepartmentName(dept.getDepartmentName());
			bean.setParentId(0);
			departmentBeanList.add(bean);
			
			//判断部门是否为根目录，为根目录  则让其找子节点！有父节点和子节点的、有父节点 没有子节点过滤掉！
			//此方法里有递归的方法，此判断是为了去除重复的数据
			if(dept.getChildren()!=null && dept.getChildren().size()>0){
				departmentBeanList.addAll(this.getChildrenNode(dept.getChildren()));
			}
		}
		return departmentBeanList;
	}
	
	
	
	public List<DepartmentBean> getAllDepartmentIgnore() {
		List<DepartmentBean> departmentBeanList=new ArrayList<DepartmentBean>();
		//得到封装有所有所有部门的实体的集合
		List<Department> list=departmentDAO.getAllRootDepartment();
		//对得到的部门列表迭代并拼成要求的JSON
		for (Department dept : list) {
			DepartmentBean bean=new DepartmentBean();
			bean.setDepartmentId(dept.getDepartmentId());
			bean.setDepartmentName(dept.getDepartmentName());
			bean.setParentId(0);
			departmentBeanList.add(bean);
			
			//判断部门是否为根目录，为根目录  则让其找子节点！有父节点和子节点的、有父节点 没有子节点过滤掉！
			//此方法里有递归的方法，此判断是为了去除重复的数据
			if(dept.getChildren()!=null && dept.getChildren().size()>0){
				bean.setChildrenNumber(dept.getChildren().size());
				departmentBeanList.addAll(this.getChildrenNode(dept.getChildren()));
			}else{
				bean.setChildrenNumber(0);
			}
		}
		return departmentBeanList;
	}

	/**
	 * 得到子节点的方法，为getAllDepartment()方法需要调用的一个得到子节点的方法
	 * 
	 * @param children
	 * @return
	 */
	private List<DepartmentBean> getChildrenNode(List<Department> children) {
		List<DepartmentBean> resultList=new ArrayList<DepartmentBean>();
		for (Department dept : children) {
			if(dept.getDeleted().equals("N")){
				DepartmentBean bean=new DepartmentBean();
				bean.setDepartmentId(dept.getDepartmentId());
				bean.setDepartmentName(dept.getDepartmentName());
				bean.setDepartmentType(dept.getDepartmentType());
				bean.setParentId(dept.getParent().getDepartmentId());
				bean.setParentName(dept.getParent().getDepartmentName());
				resultList.add(bean);
				//判断部门是否有子节点
				if(dept.getChildren()!=null&&dept.getChildren().size()>0){
					//有子节点，样式设为folder，如有子节点的话，还要递归看看还有没有子节点
					bean.setChildrenNumber(dept.getChildren().size());
					resultList.addAll(this.getChildrenNode(dept.getChildren()));
				}else{
					bean.setChildrenNumber(0);
				}
			}
			
		}
		return resultList;
	}
	

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.service.common.CommonService#findAllCity()
	 */
	public List<City> findAllCity() {
		
		return this.cityDAO.find();
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.service.common.CommonService#findAllProvince()
	 */
	public List<Province> findAllProvince() {
		return this.provinceDAO.find();
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.service.common.CommonService#getDepartmentList()
	 */
	public List<DepartmentBean> getDepartmentList() {
		List<DepartmentBean> deptList = new ArrayList<DepartmentBean>();
		List<Department> list = departmentDAO.getAllDepartment();
		if(list.size()>0){
			for(Department dept:list){
				DepartmentBean deptBean = new DepartmentBean();
				deptBean.setDepartmentId(dept.getDepartmentId());
				deptBean.setDepartmentName(dept.getDepartmentName());
				//找父部门
				if(dept.getParent()!= null){
					deptBean.setParentId(dept.getParent().getDepartmentId());
					deptBean.setParentName(dept.getParent().getDepartmentName());
				}
				//找子部门
				if(dept.getChildren()!= null){
					List<DepartmentBean> deptChildrenList = getChildrenList(dept.getChildren());
					deptBean.setChildren(deptChildrenList);
				}
				deptList.add(deptBean);
			}
		}
		return deptList;
	}
	
	
	private List<DepartmentBean> getChildrenList(List<Department> children){
		List<DepartmentBean> deptChildrenList = new ArrayList<DepartmentBean>();
		//循环迭代子部门
		for(Department dp : children){
			DepartmentBean deptChildrenBean = new DepartmentBean();
			deptChildrenBean.setDepartmentId(dp.getDepartmentId());
			deptChildrenBean.setDepartmentName(dp.getDepartmentName());
			//继续判断子部门还有没有子部门
			if(dp.getChildren()!=null){
				List<DepartmentBean> deptChildrenList2 = getChildrenList(dp.getChildren());
				deptChildrenBean.setChildren(deptChildrenList2);
			}
			deptChildrenList.add(deptChildrenBean);
		}
		return deptChildrenList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.service.common.CommonService#getRegionStation()
	 */
	public List<DepartmentBean> getRegionStation() {
		List<Department> list=null;
		List<DepartmentBean> departmentList = new ArrayList<DepartmentBean>();
		if(CommonConstants.DEPT_TYPE_HEAD.equals(UserContext.getUser().getDepartmentType())){
			list=departmentDAO.getAllRegion();
		}else{
			list=departmentDAO.getSAModeDepartmentAll(UserContext.getUser().getDepartmentId());
			if(list==null || list.size()==0){
				Department department;
				if(CommonConstants.DEPT_TYPE_SEGMENT.equals(UserContext.getUser().getDepartmentType()) || UserContext.hasAuthority(RoleConstants.RILE_STATION_PROCESS_REGION)){
					Department de=departmentDAO.getParentDepartment(UserContext.getUser().getDepartmentId());
					if(de!=null){
						department=departmentDAO.get(de.getDepartmentId());
					}else{
						department=departmentDAO.get(UserContext.getUser().getDepartmentId());
					}
				}else{
					department=departmentDAO.get(UserContext.getUser().getDepartmentId());
				}
				list=new ArrayList<Department>();
				list.add(department);
			}
			
		}
		//对得到的部门列表迭代并拼成要求的JSON
		for (Department dept : list) {
			DepartmentBean bean=new DepartmentBean();
			bean.setDepartmentId(dept.getDepartmentId());
			bean.setDepartmentName(dept.getDepartmentName());
			bean.setDepartmentType(dept.getDepartmentType());
			bean.setParentId(0);
			departmentList.add(bean);
			//判断部门是否为根目录，为根目录  则让其找子节点！有父节点和子节点的、有父节点 没有子节点过滤掉！
			//此方法里有递归的方法，此判断是为了去除重复的数据
			if(dept.getChildren()!=null && dept.getChildren().size()>0){
				bean.setChildrenNumber(dept.getChildren().size());
				departmentList.addAll(this.getChildrenNode(dept.getChildren()));
			}else{
				bean.setChildrenNumber(0);
			}
		}
		return departmentList;
	}
	
	/* (non-Javadoc)
	 * @see com.grgbanking.soc.service.common.CommonService#getSegmentStation()
	 */
	public List<DepartmentBean> getSegmentStation() {
		List<Department> list=null;
		List<DepartmentBean> departmentList = new ArrayList<DepartmentBean>();
		list=departmentDAO.getSAModeDepartmentAll(UserContext.getUser().getDepartmentId());
		if(list==null || list.size()==0){
			list=departmentDAO.getDepartmentBySegmentId(UserContext.getUser().getDepartmentId());
		}
		
		//对得到的部门列表迭代并拼成要求的JSON
		for (Department dept : list) {
			DepartmentBean bean=new DepartmentBean();
			bean.setDepartmentId(dept.getDepartmentId());
			bean.setDepartmentName(dept.getDepartmentName());
			bean.setParentId(0);
			departmentList.add(bean);
			//判断部门是否为根目录，为根目录  则让其找子节点！有父节点和子节点的、有父节点 没有子节点过滤掉！
			//此方法里有递归的方法，此判断是为了去除重复的数据
			if(dept.getChildren()!=null && dept.getChildren().size()>0){
				departmentList.addAll(this.getChildrenNode(dept.getChildren()));
			}
		}
		return departmentList;
	}
	

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.service.common.CommonService#getRegionList()
	 */
	public List<Department> getRegionList() {
		return departmentDAO.getAllRegion();
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.service.common.CommonService#getEquipmentSerial(java.lang.Integer)
	 */
	public String getEquipmentSerial(Integer id) {
		List equipmentlist = equipmentDAO.getEquipmentByCustomerId(id);
		if (equipmentlist.size() < 1) return null;
		Map<String,StringBuffer> equiptype = new HashMap<String,StringBuffer>();
		for (int i = 0; i < equipmentlist.size(); i++){
			StringBuffer root = null;
			Object[] obj = (Object[])equipmentlist.get(i); 
			String equipmentType = DataDictionaryLoad.getText(DataDictionaryConstants.EQUIPMENT_TYPE, (String) obj[0]);
			String equipmentModel = DataDictionaryLoad.getText(DataDictionaryConstants.EQUIPMENT_MODEL, (String) obj[1]);
			String serialNumber = (String) obj[2];
			if (equiptype.get(equipmentType+equipmentModel) == null ){
				root=new StringBuffer("{\"id\":"+i+",\"text\":\"【"+equipmentType+"】"+equipmentModel
				               +"\",\"iconCls\":\"folder\""+",\"children\":[{\"id\":"+(i+10000)+",\"text\":\""+serialNumber
				               +"\",\"iconCls\":\"file\"}");
			} else {
				root=equiptype.get(equipmentType+equipmentModel);
				root.append(",{\"id\":"+(i+10000)+",\"text\":\""+serialNumber+"\",\"iconCls\":\"file\"}");
			}
			equiptype.put(equipmentType+equipmentModel, root);
		}
		StringBuffer r = new StringBuffer("[");
		for (StringBuffer sb : equiptype.values()) {
			sb.append("]}");
			if (r.length() <= 1) r.append(sb);
			else {
				r.append(",");
				r.append(sb);
			}
		}
		r.append("]");
		return r.toString();
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.service.common.CommonService#getAllDataDictionaryList(com.grgbanking.framework.core.Page, com.grgbanking.soc.bean.common.DataDictionaryBean)
	 */
	public List<DataDictionary> getAllDataDictionaryList(Integer...integers) {
		List<DataDictionary> dataDic = dataDictionaryDAO.getAllDataDictionaryList(integers);
		return dataDic;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.service.system.CommonService#findDataDictionary(com.grgbanking.framework.core.Page, com.grgbanking.soc.entity.common.DataDictionary)
	 */
	public List<DataDictionary> findDowntown(Page queryPage,DataDictionary dataDictionaryBean) {
		return dataDictionaryDAO.findDataDictionaryList(queryPage,dataDictionaryBean);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.soc.service.system.CommonService#findDataDictionary(com.grgbanking.framework.core.Page, com.grgbanking.soc.entity.common.DataDictionary)
	 */
	public List<Downtown> findAllDowntown() {
		return this.downtownDAO.find();
	}

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.service.common.CommonService#getAllUser()
	 */
	public List<CssUserBean> getAllUser() {
		//List<DepartmentBean> departmentBeanList=new ArrayList<DepartmentBean>();
		List<CssUserBean> userBeanList=new ArrayList<CssUserBean>();
		//得到封装有所有所有部门的实体的集合
		List<Department> list=null;
		if(CommonConstants.DEPT_TYPE_HEAD.equals(UserContext.getUser().getDepartmentType())){
			list=departmentDAO.getAllRootDepartment();
		}else{
			Department department=departmentDAO.get(UserContext.getUser().getDepartmentId());
			list=new ArrayList<Department>();
			list.add(department);
		}
		
		//对得到的部门列表迭代并拼成要求的JSON
		for (Department dept : list) {
			
			CssUserBean userBean2=new CssUserBean();
			userBean2.setParUserId("US"+dept.getDepartmentId());
			userBean2.setName(dept.getDepartmentName());
			userBean2.setParentId("0");
			userBeanList.add(userBean2);
			
			List<CssUser> userList=userDAO.getUserListByDepartmentId(dept.getDepartmentId());
			for(CssUser us:userList){
				CssUserBean uBean=new CssUserBean();
				uBean.setParUserId(us.getUserId().toString());
				uBean.setName(us.getName());
				uBean.setParentId("US"+dept.getDepartmentId());
				userBeanList.add(uBean);
			}
				
			//判断部门是否为根目录，为根目录  则让其找子节点！有父节点和子节点的、有父节点 没有子节点过滤掉！
			//此方法里有递归的方法，此判断是为了去除重复的数据
			if(dept.getChildren()!=null && dept.getChildren().size()>0){
				userBeanList.addAll(this.getUseChildrenNode(dept.getChildren()));
			}
		}
		return userBeanList;
	}
	
	private List<CssUserBean> getUseChildrenNode(List<Department> departList) {
		List<CssUserBean> resultList=new ArrayList<CssUserBean>();
		for(Department dept:departList){
			
			CssUserBean usBean=new CssUserBean();
			usBean.setParUserId("US"+dept.getDepartmentId());
			usBean.setName(dept.getDepartmentName());
			usBean.setParentId("US"+dept.getParent().getDepartmentId());
			resultList.add(usBean);
			
			List<CssUser> userList=userDAO.getUserListByDepartmentId(dept.getDepartmentId());
			for(CssUser us:userList){
				CssUserBean uBean=new CssUserBean();
				uBean.setParUserId(us.getUserId().toString());
				uBean.setName(us.getName());
				uBean.setParentId("US"+dept.getDepartmentId());
				resultList.add(uBean);
			}
			
			
			//判断部门是否有子节点
			if(dept.getChildren()!=null&&dept.getChildren().size()>0){
				//有子节点，样式设为folder，如有子节点的话，还要递归看看还有没有子节点
				resultList.addAll(this.getUseChildrenNode(dept.getChildren()));
			}
			
		}
		
		return resultList;
	}

	public List<DepartmentBean> findDepatmentByParent(Integer departmentId) {
		List<DepartmentBean> list = new ArrayList<DepartmentBean>();
		Department parent = departmentDAO.get(departmentId);
		List<Department> children = parent.getChildren();
		for (Department department : children) {
			DepartmentBean bean = new DepartmentBean();
			BeanUtils.copyProperties(department, bean,new String[] {"parent","directorUserId","children"});
			list.add(bean);
		}
		DepartmentBean bean = new DepartmentBean();
		BeanUtils.copyProperties(parent, bean,new String[] {"parent","directorUserId","children"});
		list.add(bean);
		return list;
	}

	
}
