
package com.grgbanking.css.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.grgbanking.css.util.HqlHelper;


/**
 * 提供数据访问层公用方法
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: zhangzhi
 * Date: 2010-5-29 上午11:39:27
 */
public class CssBaseDAOImpl<T,ID extends Serializable> extends HibernateDaoSupport implements CssBaseDAO<T, ID>{
	
	Logger log = Logger.getLogger(getClass());
	/**
	 * 注入sessionFactory
	 * @param sessionFactory
	 */
	@Autowired
	public void initSessionFactory(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	/**
	 * 获取实体类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass(){
		 return (Class<T>) ((ParameterizedType) getClass()
                 .getGenericSuperclass()).getActualTypeArguments()[0];

	}
	
	/**
	 * 获取实体类型名称
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String getEntityName(){
		 return ((Class<T>) ((ParameterizedType) getClass()
                 .getGenericSuperclass()).getActualTypeArguments()[0]).getName();
	}
	
	


	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.framework.base.BaseDAO#find()
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(){
		return this.getHibernateTemplate().find("from "+this.getEntityName());
	}


	/**
	 * 根据SQL语句查询数组对象列表
	 * @param sql SQL语句
	 * @param param SQL参数，可以不传，可以传多个
	 * @return List<Object[]>
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findBySQL(final String sql,final Object ... params){
		for (Object object : params) {
			log.debug(object);
		}
		
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,SQLException {
				SQLQuery sqlQuery=session.createSQLQuery(sql);
				if(params!=null&&params.length>0){
					for (int i = 0; i < params.length; i++) {
						sqlQuery.setParameter(i, params[i]);
					}
				}
				return sqlQuery.list();
			}
		});
	}
	
	/**
	 * SQL分页查询
	 * @param sql
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findBySQL(final String sql, final Page page,final Object ... params) {
		if(page==null){
			return this.findBySQL(sql);
		}
		Integer totalCount=this.countBySQL(this.convertToCountSql(sql));
		
		page.setRecordCount(totalCount);
		if(totalCount==0){
			return new ArrayList<Object[]>();
		}
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				if(params!=null&&params.length>0){
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				return query.setFirstResult(page.getFirstResult()).setMaxResults(page.getRows()).list();
			}
		});
	}
	
	
	/**
	 * 根据HQL语句查询实体对象列表
	 * @param sql SQL语句
	 * @param params SQL参数，可以不传，可以传多个
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql,Object ... params){
		return this.getHibernateTemplate().find(hql, params);
	}
	
	
	
	/**
	 * 保存实体对象并返回主键,如果是CssBaseEntity的子类，则会自动填充共同基本信息。
	 * @param entity
	 * @return
	 */ 
	/*@SuppressWarnings("unchecked")
	public ID save(T entity){
		if(entity instanceof CssBaseEntity){
			((CssBaseEntity) entity).setCreateDate(new Date());
			((CssBaseEntity) entity).setVersion(0);
			((CssBaseEntity) entity).setModifyDate(new Date());
			((CssBaseEntity) entity).setDeleted(CommonConstants.FLAG_N);
			if(UserContext.getUser()!=null){
				((CssBaseEntity) entity).setCreateUserId(UserContext.getUser().getUserId());
				((CssBaseEntity) entity).setModifyUserId(UserContext.getUser().getUserId());
			}
			
		}
		return (ID) this.getHibernateTemplate().save(entity);
	}*/
	
	/*public void saveBatch(T entity){
		if(entity instanceof CssBaseEntity){
			((CssBaseEntity) entity).setCreateDate(new Date());
			((CssBaseEntity) entity).setVersion(0);
			((CssBaseEntity) entity).setModifyDate(new Date());
			((CssBaseEntity) entity).setDeleted(CommonConstants.FLAG_N);
			if(UserContext.getUser()!=null){
				((CssBaseEntity) entity).setCreateUserId(UserContext.getUser().getUserId());
				((CssBaseEntity) entity).setModifyUserId(UserContext.getUser().getUserId());
			}
			
		}
		this.getHibernateTemplate().save(entity);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}*/
	
	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.framework.base.BaseDAO#delete(ID[])
	 */
	/*public void delete(ID ... idArray){
		if(idArray!=null&&idArray.length>0){
			for (int i = 0; i < idArray.length; i++) {
				this.getHibernateTemplate().delete(this.get(idArray[i]));
				//超过20次则flush一下
				if(i%20==0){
					this.getHibernateTemplate().flush();
				}
			}
		}
	}*/
	
	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.framework.base.BaseDAO#get(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public T get(ID id){
		if(id==null){
			return null;
		}
		return (T) this.getHibernateTemplate().get(this.getEntityName(), id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.framework.base.BaseDAO#update(java.lang.Object)
	 */
	/*public void update(T entity){
		if(entity instanceof CssBaseEntity){
			((CssBaseEntity) entity).setModifyDate(new Date());
			if(UserContext.getUser()!=null){
				((CssBaseEntity) entity).setModifyUserId(UserContext.getUser().getUserId());
			}
		}
		this.getHibernateTemplate().update(entity);
	}*/
	
	
	/**
	 * SQL分页查询
	 * @param sql
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findBySQL(final String sql, final Page page) {
		if(page==null){
			return this.findBySQL(sql);
		}
		Integer totalCount=this.countBySQL(this.convertToCountSql(sql));
		
		page.setRecordCount(totalCount);
		if(totalCount==0){
			return new ArrayList<Object[]>();
		}
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				return query.setFirstResult(page.getFirstResult()).setMaxResults(page.getRows()).list();
			}
		});
	}
	
	/**
	 * HqlHelper分页查询
	 * @param hql
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(final HqlHelper hqlHelper) {
		log.debug("HQL:"+hqlHelper.getHQL());
		log.debug("params:"+hqlHelper.getParams());
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				
				Query query = session.createQuery(hqlHelper.getHQL());
				//注册查询参数
				if(hqlHelper.getParams().size()>0){
					int i=0;
					for (Object param : hqlHelper.getParams()) {
						query.setParameter(i,param);
						i++;
					}
				}
				
				//分页处理
				if(hqlHelper.getQueryPage()!=null){
					Query countQuery=session.createQuery("select count(*) "+removeOrder(hqlHelper.getHQL()));
					//注册查询参数
					if(hqlHelper.getParams().size()>0){
						int i=0;
						for (Object param : hqlHelper.getParams()) {
							countQuery.setParameter(i,param);
							i++;
						}
					}
					int totalCount=Integer.valueOf(countQuery.uniqueResult().toString());
					if(totalCount==0){
						return new ArrayList<T>();
					}
					hqlHelper.getQueryPage().setRecordCount(totalCount);
					query.setFirstResult(hqlHelper.getQueryPage().getFirstResult());
					query.setMaxResults(hqlHelper.getQueryPage().getRows());
				}
				
				return query.list();
			}
		});
	}
	
	
	/**
	 * HqlHelper分页查询
	 * @param hql
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List find(final Page page,final String hql,final Object[] params) {
		log.debug("HQL:"+hql);
		log.debug("params:"+params);
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				
				Query query = session.createQuery(hql);
				//注册查询参数
				if(params.length>0){
					int i=0;
					for (Object param : params) {
						query.setParameter(i,param);
						i++;
					}
				}
				
				//分页处理
				if(page!=null){
					Query countQuery=session.createQuery("select count(*) "+removeOrder(removeSelect(hql)));
					//注册查询参数
					if(params.length>0){
						int i=0;
						for (Object param : params) {
							countQuery.setParameter(i,param);
							i++;
						}
					}
					int totalCount=Integer.valueOf(countQuery.uniqueResult().toString());
					page.setRecordCount(totalCount);
					query.setFirstResult(page.getFirstResult());
					query.setMaxResults(page.getRows());
				}
				
				return query.list();
			}
		});
	}
	

	/**
	 * HQL分页查询
	 * @param hql
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(final String hql,final Page page) {
		if(page==null){
			return this.find(hql);
		}
		Integer totalCount=this.countByHQL(this.convertToCountSql(hql));
		page.setRecordCount(totalCount);
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				return query.setFirstResult(page.getFirstResult()).setMaxResults(page.getRows()).list();
			}
		});
	}

	/**
	 * 根据SQL查询总记录数
	 * @param sql
	 * @return 总记录数
	 */
	public int countBySQL(final String sql,final Object ... params) {
		Object count =  this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						if(params != null && params.length>0){
							int i=0;
							for (Object param : params) {
								query.setParameter(i,param);
								i++;
							}
						}
						return query.uniqueResult();
					}
				});
		return Integer.valueOf(count.toString());
	}
	
	/**
	 * 根据SQL查询总记录数
	 * @param sql
	 * @return 总记录数
	 */
	public int countBySQLAddScalar(final String sql,final Object ... params) {
		Object count =  this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						query.addScalar("count", Hibernate.LONG);
						if(params != null && params.length>0){
							int i=0;
							for (Object param : params) {
								query.setParameter(i,param);
								i++;
							}
						}
						return query.uniqueResult();
					}
				});
		return Integer.valueOf(count.toString());
	}
	
	/**
	 * 根据HQL查询总记录数
	 * @param hql
	 * @return 总记录数
	 */
	public int countByHQL(final String hql,final Object ... params) {
		Object count =  this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Query query=session.createQuery(hql);
						if(params.length>0){
							int i=0;
							for (Object param : params) {
								query.setParameter(i,param);
								i++;
							}
						}
						return query.uniqueResult();
					}
				});
		if (count==null) return 0;
		return Integer.valueOf(count.toString());
	}
	
	/**
	 * 执行sql语句
	 * @param sql
	 * @return 返回影响数据表行数
	 */
	public int executeSql(final String sql) {
		return (Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createSQLQuery(sql).executeUpdate();
					}
		});
	}
	
	/**
	 * 将SQL语句转换成查询count的SQL语句
	 * @param sql
	 * @return
	 */
	private String convertToCountSql(String sql){
		return "select count(*) from ("+sql+" )";
//		return "select count(*) "+sql.substring(sql.toUpperCase().indexOf("FROM"));
	}
	
	
	/**
	 * 查询唯一对象
	 * @param hql HQL语句
	 * @param params 参数数组
	 * @return
	 */
	public T get(String hql,Object ... param){
		List<T> list=this.find(hql, param);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.framework.base.BaseDAO#saveLog(java.lang.String)
	 */
	/*public int saveLog(String content) {
		LogInfo log=new LogInfo();
		log.setContent(content);
		log.setCreateDate(new Date());
		if(UserContext.getUser()!=null){
			log.setCreateUserId(UserContext.getUser().getUserId());
		}
		this.getHibernateTemplate().save(log);
		return log.getLogId();
	}*/
	
	/**
	 * 根据实体类，属性查找某一对象
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public Object findUniqueBy(Class entityClass, String propertyName,
			Object value) {
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(propertyName, value));
		return criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.framework.base.BaseDAO#delete(java.lang.Object)
	 */
	/*public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}*/

	
	/**
	 * 根据客户ID，查询该客户下所有子客户ID
	 * @param customerId
	 * @return
	 */
	public List<Integer> getAllCustomerByParentId(Integer customerId) {
		List<Integer> customerList=new ArrayList<Integer>();
		String sql="select t.id from v_crm_customer_info t start with t.parent_custimer_id="+customerId
			+" connect by prior t.id=t.parent_custimer_id";
		List list=this.findBySQL(sql);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			BigDecimal obj = (BigDecimal) iterator.next();
			customerList.add(obj.intValue());
		}
		customerList.add(customerId);
		return customerList;
	}
	
	/**
	 * 根据部门ID查询其所有下级部门（包含自身ID）
	 * @param departmentId
	 * @return
	 */
	/**
	 * @param departmentId
	 * @return
	 */
	public List<Integer> getChildDepartmentId(Integer departmentId) {
		List<Integer> equipmentIdList=new ArrayList<Integer>();
		String sql="select t.department_id,t.DEPARTMENT_NAME from V_base_department t start with t.department_id=?"
			+" connect by prior t.department_id=t.parent_id";
		List<Object[]> list=this.findBySQL(sql,departmentId);
		for (Object[] obj : list) {
			equipmentIdList.add(((BigDecimal)obj[0]).intValue());
		}
		return equipmentIdList;
	}
	
	/**
	 * 根据省平台ID查询其所有下级部门（包含自身ID）
	 * @param departmentId
	 * @return
	 */
	public List<Integer> getChildDepartmentIdForSegment(Integer departmentId){
		List<Integer> deptIdList=new ArrayList<Integer>();
		String sql="select d.department_id,d.DEPARTMENT_NAME "
			+"from V_base_department d "
			+"where d.province_platform='N' and d.province_platform_id=?";
		List<Object[]> list=this.findBySQL(sql,departmentId);
		for (Object[] obj : list) {
			deptIdList.add(((BigDecimal)obj[0]).intValue());
		}
		deptIdList.add(departmentId);
		return deptIdList;
	}
	
	/**
	 * 
	 * @createDate 2011-4-12
	 * @author GuaBin
	 * @param objectClass
	 * @param sql
	 * @param page
	 * @return List<Object>
	 * @description
	 * 通过SQL语句查询数据，并封装数据到实体对象中
	 */
	public  List findEntityListBySQL(final String sql, final Page page) {
		
		Query query = this.getSession().createSQLQuery(sql).addEntity(this.getEntityClass());
		if(null == page)
			return query.list();
		
		Integer totalCount=this.countBySQL(this.convertToCountSql(sql));
		page.setRecordCount(totalCount);
		
		return query.setFirstResult(page.getFirstResult()).setMaxResults(page.getRows()).list();
	}
	
	/**
	 * 
	 * @createDate 2011-4-12
	 * @author GuaBin
	 * @param objectClass
	 * @param sql
	 * @param page
	 * @return List<Object>
	 * @description
	 * 通过SQL语句查询数据，并封装数据到实体对象中
	 */
	public  List findEntityListBySQL(final String sql,Class entityClass, final Page page) {
		
		Query query = getSession().createSQLQuery(sql).addEntity(entityClass);
		if(null == page)
			return query.list();
		
		Integer totalCount=this.countBySQL(this.convertToCountSql(sql));
		page.setRecordCount(totalCount);
		
		return query.setFirstResult(page.getFirstResult()).setMaxResults(page.getRows()).list();
	}
	
	/**
	 * 
	 * @author:guabin 
	 * @createDate 2012-6-11
	 * @return List<Map>
	 * @description
	 * 通过SQL语句查询数据，并封装数据到Map中
	 * 注意：map的key值是大写
	 */
	public List<Map> findMapResultBySql(String sql){
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	
	/**
	 * 
	 * @author:guabin 
	 * @createDate 2012-6-11
	 * @return List<Map>
	 * @description
	 * 通过SQL语句查询数据，并封装数据到Map中
	 * 注意：map的key值是大写
	 */
	public List<Map> findMapResultBySql(String sql,Page page){
		
		Integer totalCount=this.countBySQL(this.convertToCountSql(sql));
		
		if(null != page)
			page.setRecordCount(totalCount);
		
		if(totalCount==0){
			return new ArrayList<Map>();
		}
		
		SQLQuery query = getSession().createSQLQuery(sql);
		
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		if(null != page)
			return query.setFirstResult(page.getFirstResult()).setMaxResults(page.getRows()).list();
		else
			return query.list();
	}
	
	/**
	 * 去掉HQL语句前面的select
	 * 
	 * @param hql
	 * @return
	 */
	private String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		if(beginPos!=-1){
			return hql.substring(beginPos);
		}
		return hql;
	}
	
	/**
	 * 去掉HQL语句前面的select
	 * 
	 * @param hql
	 * @return
	 */
	private String removeOrder(String hql) {
		int endPos = hql.toLowerCase().indexOf("order by");
		if(endPos!=-1){
			return hql.substring(0,endPos);
		}
		return hql;
	}

	/*
	 * (non-Javadoc)
	 * @see com.grgbanking.framework.base.BaseDAO#delete(java.util.List)
	 */
	/*public void delete(List<T> entityList) {
		for (T t : entityList) {
			this.delete(t);
		}
	}*/
	
	/**
	 * 
	 * @author:guabin 
	 * @createDate 2012-12-20
	 * @return T
	 * @description
	 * 返回查询列表中的第一个值
	 */
	protected T listFirstResult(List<T> list){
		if(list.size() > 0 )
			return list.get(0);
		else
			return null;
	}
	
	/**
	 * 
	 * @param entityClass
	 * @param sre
	 * @return
	 */
	public Object get(Class entityClass,Serializable sre){
		return  getSession().get(entityClass,sre);
	}

	
}
