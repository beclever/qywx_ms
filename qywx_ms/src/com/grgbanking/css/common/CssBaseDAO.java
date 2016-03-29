package com.grgbanking.css.common;

import java.util.List;
import java.util.Map;


/**
 *
 * 公用DAO方法
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-5-29 下午01:53:11
 */
public interface CssBaseDAO<T,ID> {
	
	
	/**
	 * 根据实体类查询所有数据列表
	 * @return
	 */
	public List<T> find();
	
	/**
	 * 保存实体类，返回主键ID
	 * @param entity 实体类
	 * @return
	 */
	//public ID save(T entity);
	
	//public void saveBatch(T entity);
	
	/**
	 * 根据主键ID加载该实体对象
	 * @param id 主键ID
	 * @return
	 */
	public T get(ID id);
	
	
	/**
	 * 物理删除指定ID的实体类 （支持数据）
	 * @param ids
	 */
	//public void delete(ID ... ids);
	
	/**
	 * 删除集合
	 * @param entityList
	 */
	//public void delete(List<T> entityList);
	
	/**
	 * 删除实体类
	 * @param entity
	 */
	//public void delete(T entity);
	
	/**
	 * 更新实体类
	 * @param entity
	 */
	//public void update(T entity);
	
	/**
	 * 保存系统操作日志
	 * @param content
	 */
	//public int saveLog(String content);
	

	/**
	 * 根据客户ID，查询该客户下所有子客户ID
	 * @param customerId
	 * @return
	 */
	public List<Integer> getAllCustomerByParentId(Integer customerId);

	/**
	 * 
	 * @author:guabin 
	 * @createDate 2012-6-11
	 * @return List<Map>
	 * @description
	 * 通过SQL语句查询数据，并封装数据到Map中
	 * 注意：map的key值是大写
	 */
	public List<Map> findMapResultBySql(String sql);
	
	/**
	 * 
	 * @author:guabin 
	 * @createDate 2012-6-11
	 * @return List<Map>
	 * @description
	 * 通过SQL语句查询数据，并封装数据到Map中
	 * 注意：map的key值是大写
	 */
	public List<Map> findMapResultBySql(String sql,Page page);
	
	/**
	 * 执行SQL
	 * @param sql
	 */
	public int executeSql(final String sql);
}
