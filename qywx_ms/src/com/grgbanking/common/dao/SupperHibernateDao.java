package com.grgbanking.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.grgbanking.common.utils.PageData;

@SuppressWarnings("all")
@Repository("SupperHibernateDao")
public class SupperHibernateDao extends HibernateDaoSupport{
	
	public List find(DetachedCriteria criteria, int firstResult, int maxResults) {
		return getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
	}
	
	public List find(DetachedCriteria criteria) {
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	public PageData findPage(DetachedCriteria detachedCriteria, int firstResult, int maxResults) {
		detachedCriteria.setProjection(Projections.rowCount());
		PageData pageData = new PageData(firstResult, maxResults);		
		pageData.setTotal((Integer) getHibernateTemplate().findByCriteria(detachedCriteria).get(0));
		
		detachedCriteria.setProjection(null);
		pageData.setData(getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults));
		return pageData;
	}
	//获得推送消息的最大id
	public Object getMaxPushId(){
		HibernateTemplate tmpl = getHibernateTemplate();
		return tmpl.execute(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery query = session.createSQLQuery("select max(tp.pushid) from T_MOBILE_PUSH_INFO tp");
				List result = query.list();
				Object  obj = result.get(0);
				if(null != obj && result.size()>0){
					return obj.toString();
				}
				return obj;
			}
		});
	}
	
	@SuppressWarnings("rawtypes")
	public List findByHql(String hqlString){
		return getHibernateTemplate().find(hqlString);
	}
	
//	public Object getMaxIdByUserId(final String userid){
//		HibernateTemplate tmpl = getHibernateTemplate();
//		return tmpl.execute(new HibernateCallback<Object>() {
//
//			public Object doInHibernate(Session session) throws HibernateException,
//					SQLException {
//				SQLQuery query = session.createSQLQuery("select * from T_MOBILE_PHONE_INFO t where t.user_id='"+userid+"' and t.is_disable='2'");
//				List result = query.list();
//				return result;
//			}
//		});
//	}
	
	
//	protected Class entityClass = null;
//	protected Class pkClass = null;

//	public SupperHibernateDao() {
//		entityClass = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//		pkClass = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
//	}
//
//	public int bulkUpdate(String queryString) throws DataAccessException {
//		return getHibernateTemplate().bulkUpdate(queryString);
//	}
//
//	public int bulkUpdate(String queryString, Object value)
//			throws DataAccessException {
//		return getHibernateTemplate().bulkUpdate(queryString, value);
//	}
//
//	public int bulkUpdate(String queryString, Object... values)
//			throws DataAccessException {
//		return getHibernateTemplate().bulkUpdate(queryString, values);
//	}
//
//	public void clear() throws DataAccessException {
//		getHibernateTemplate().clear();
//	}
//
//	public void closeIterator(Iterator arg0) throws DataAccessException {
//		getHibernateTemplate().closeIterator(arg0);
//	}
//
	public boolean contains(Object entity) throws DataAccessException {
		return getHibernateTemplate().contains(entity);
	}
//
//	public void delete(Object entity) throws DataAccessException {
//		getHibernateTemplate().delete(entity);
//	}
//
//	public void delete(Object entity, LockMode lockMode)
//			throws DataAccessException {
//		getHibernateTemplate().delete(entity, lockMode);
//	}
//
//	public void delete(String entityName, Object entity)
//			throws DataAccessException {
//		getHibernateTemplate().delete(entityName, entity);
//	}
//
//	public void delete(String entityName, Object entity, LockMode lockMode)
//			throws DataAccessException {
//		getHibernateTemplate().delete(entityName, entity, lockMode);
//	}
//
//	public void deleteAll(Collection entities) throws DataAccessException {
//		getHibernateTemplate().deleteAll(entities);
//	}
//
//	public Filter enableFilter(String filterName) throws IllegalStateException {
//		return getHibernateTemplate().enableFilter(filterName);
//	}
//
//	public void evict(Object entity) throws DataAccessException {
//		getHibernateTemplate().evict(entity);
//	}
//
//	public <T> T execute(HibernateCallback<T> arg0) throws DataAccessException {
//		return getHibernateTemplate().execute(arg0);
//	}
//
//	public List executeFind(HibernateCallback<?> arg0)
//			throws DataAccessException {
//		return getHibernateTemplate().executeFind(arg0);
//	}
//
//	public List find(String hql) throws DataAccessException {
//		return getHibernateTemplate().find(hql);
//	}
//
//	public List find(String hql, Object value) throws DataAccessException {
//		return getHibernateTemplate().find(hql, value);
//	}
//
//	public List find(String hql, Object... values) throws DataAccessException {
//		return getHibernateTemplate().find(hql, values);
//	}
//
//	public List findByCriteria(DetachedCriteria arg0)
//			throws DataAccessException {
//		return getHibernateTemplate().findByCriteria(arg0);
//	}
//
//	public List findByCriteria(DetachedCriteria criteria, int firstResult, int maxResults)
//			throws DataAccessException {
//		return getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults);
//	}
//
//	public List findByExample(Object exampleEntity) throws DataAccessException {
//		return getHibernateTemplate().findByExample(exampleEntity);
//	}
//
//	public List findByExample(String entityName, Object exampleEntity)
//			throws DataAccessException {
//		return getHibernateTemplate().findByExample(entityName, exampleEntity);
//	}
//
//	public List findByExample(Object exampleEntity, int firstResult, int maxResults)
//			throws DataAccessException {
//		return getHibernateTemplate().findByExample(exampleEntity, firstResult, maxResults);
//	}
//
//	public List findByExample(String entityName, Object exampleEntity, int firstResult, int maxResults)
//			throws DataAccessException {
//		return getHibernateTemplate().findByExample(entityName, exampleEntity, firstResult, maxResults);
//	}
//
//	public List findByNamedParam(String queryString, String paramName, Object value)
//			throws DataAccessException {
//		return getHibernateTemplate().findByNamedParam(queryString, paramName, value);
//	}
//
//	public List findByNamedParam(String queryString, String[] paramNames, Object[] values)
//			throws DataAccessException {
//		return getHibernateTemplate().findByNamedParam(queryString, paramNames, values);
//	}
//
//	public List findByNamedQuery(String queryName) throws DataAccessException {
//		return getHibernateTemplate().findByNamedQuery(queryName);
//	}
//
//	public List findByNamedQuery(String queryName, Object value)
//			throws DataAccessException {
//		return getHibernateTemplate().findByNamedQuery(queryName, value);
//	}
//
//	public List findByNamedQuery(String queryName, Object... values)
//			throws DataAccessException {
//		return getHibernateTemplate().findByNamedQuery(queryName, values);
//	}
//
//	public List findByNamedQueryAndNamedParam(String queryName, String paramName,
//			Object value) throws DataAccessException {
//		return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramName, value);
//	}
//
//	public List findByNamedQueryAndNamedParam(String queryName, String[] paramNames,
//			Object[] values) throws DataAccessException {
//		return getHibernateTemplate().findByNamedQueryAndNamedParam(queryName, paramNames, values);
//	}
//
//	public List findByNamedQueryAndValueBean(String queryName, Object valueBean)
//			throws DataAccessException {
//		return getHibernateTemplate().findByNamedQueryAndValueBean(queryName, valueBean);
//	}
//
//	public List findByValueBean(String queryString, Object valueBean)
//			throws DataAccessException {
//		return getHibernateTemplate().findByValueBean(queryString, valueBean);
//	}
//
//	public void flush() throws DataAccessException {
//		getHibernateTemplate().flush();
//	}
//
	public <T> T get(Class<T> entityClass, Serializable id)
			throws DataAccessException {
		return getHibernateTemplate().get(entityClass, id);
	}
//
//	public Object get(String entityName, Serializable id)
//			throws DataAccessException {
//		return getHibernateTemplate().get(entityName, id);
//	}
//
//	public <T> T get(Class<T> entityClass, Serializable id, LockMode lockMode)
//			throws DataAccessException {
//		return getHibernateTemplate().get(entityClass, id, lockMode);
//	}
//
//	public Object get(String entityName, Serializable id, LockMode lockMode)
//			throws DataAccessException {
//		return getHibernateTemplate().get(entityName, id, lockMode);
//	}
//
//	public void initialize(Object arg0) throws DataAccessException {
//		getHibernateTemplate().initialize(arg0);
//	}
//
//	public Iterator iterate(String queryString) throws DataAccessException {
//		return getHibernateTemplate().iterate(queryString);
//	}
//
//	public Iterator iterate(String queryString, Object value)
//			throws DataAccessException {
//		return getHibernateTemplate().iterate(queryString, value);
//	}
//
//	public Iterator iterate(String queryString, Object... values)
//			throws DataAccessException {
//		return getHibernateTemplate().iterate(queryString, values);
//	}
//
//	public <T> T load(Class<T> entityClass, Serializable id)
//			throws DataAccessException {
//		return getHibernateTemplate().load(entityClass, id);
//	}
//
//	public Object load(String entityName, Serializable id)
//			throws DataAccessException {
//		return getHibernateTemplate().load(entityName, id);
//	}
//
//	public void load(Object entity, Serializable id) throws DataAccessException {
//		getHibernateTemplate().load(entity, id);
//	}
//
//	public <T> T load(Class<T> entityClass, Serializable id, LockMode lockMode)
//			throws DataAccessException {
//		return getHibernateTemplate().get(entityClass, id, lockMode);
//	}
//
//	public Object load(String entityName, Serializable id, LockMode lockMode)
//			throws DataAccessException {
//		return getHibernateTemplate().load(entityName, id, lockMode);
//	}
//
//	public <T> List<T> loadAll(Class<T> entityClass) throws DataAccessException {
//		return getHibernateTemplate().loadAll(entityClass);
//	}
//
//	public void lock(Object entity, LockMode lockMode) throws DataAccessException {
//		getHibernateTemplate().lock(entity, lockMode);
//	}
//
//	public void lock(String entityName, Object entity, LockMode lockMode)
//			throws DataAccessException {
//		getHibernateTemplate().lock(entityName, entity, lockMode);
//	}
//
//	public <T> T merge(T entity) throws DataAccessException {
//		return getHibernateTemplate().merge(entity);
//	}
//
//	public <T> T merge(String entityName, T entity) throws DataAccessException {
//		return getHibernateTemplate().merge(entityName, entity);
//	}
//
//	public void persist(Object entity) throws DataAccessException {
//		getHibernateTemplate().persist(entity);
//	}
//
//	public void persist(String entityName, Object entity) throws DataAccessException {
//		getHibernateTemplate().persist(entityName, entity);
//	}
//
//	public void refresh(Object entity) throws DataAccessException {
//		getHibernateTemplate().refresh(entity);
//	}
//
//	public void refresh(Object entity, LockMode lockMode) throws DataAccessException {
//		getHibernateTemplate().refresh(entity, lockMode);
//	}
//
//	public void replicate(Object entity, ReplicationMode replicationMode)
//			throws DataAccessException {
//		getHibernateTemplate().replicate(entity, replicationMode);
//	}
//
//	public void replicate(String entityName, Object entity, ReplicationMode replicationMode)
//			throws DataAccessException {
//		getHibernateTemplate().replicate(entityName, entity, replicationMode);
//	}
//
	public Serializable save(Object entity) throws DataAccessException {
	    return getHibernateTemplate().save(entity);
	}
//
//	public Serializable save(String entityName, Object entity)
//			throws DataAccessException {
//		return getHibernateTemplate().save(entityName, entity);
//	}
//
	public void saveOrUpdate(Object entity) throws DataAccessException {
		getHibernateTemplate().saveOrUpdate(entity);
	}
//
//	public void saveOrUpdate(String entityName, Object entity)
//			throws DataAccessException {
//		getHibernateTemplate().saveOrUpdate(entityName, entity);
//	}
//
//	public void saveOrUpdateAll(Collection entities) throws DataAccessException {
//		getHibernateTemplate().saveOrUpdateAll(entities);
//	}
//
	public void update(Object entity) throws DataAccessException {
		getHibernateTemplate().update(entity);
	}
	
	
	public int getRecordCount(String entityName){
		Long count  = (Long)this.getHibernateTemplate().find("select count(*) from "+entityName).get(0);
		return count.intValue();
	}
	
	
//
//	public void update(Object entity, LockMode lockMode) throws DataAccessException {
//		getHibernateTemplate().update(entity, lockMode);
//	}
//
//	public void update(String entityName, Object entity) throws DataAccessException {
//		getHibernateTemplate().update(entityName, entity);
//	}
//
//	public void update(String entityName, Object entity, LockMode lockMode)
//			throws DataAccessException {
//		getHibernateTemplate().update(entityName, entity, lockMode);
//	}

}
