package com.grgbanking.core.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;

import com.grgbanking.common.dao.SupperHibernateDao;

@SuppressWarnings("all")
public class BaseService<T, PK extends Serializable> {
	@Resource(name = "SupperHibernateDao")
	protected SupperHibernateDao supperHibernateDao;

	protected Class<T> entityClass = null;
	protected Class<PK> pkClass = null;

	public BaseService() {
		if (this.getClass().getGenericSuperclass() instanceof ParameterizedType) {
			entityClass = (Class) ((ParameterizedType) this.getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
			pkClass = (Class) ((ParameterizedType) this.getClass()
					.getGenericSuperclass()).getActualTypeArguments()[1];
		}
	}

	@Deprecated
	public List<T> find(Criterion criteria) {
		return supperHibernateDao.getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(entityClass).add(criteria));
	}

	public List<T> getAll() {
		return supperHibernateDao.getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(entityClass));
	}

	public List<T> findByProperty(String prop, Object val) {
		return supperHibernateDao.getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(entityClass).add(
						Property.forName(prop).eq(val)));
	}

	public void save(Object entity) {
		supperHibernateDao.getHibernateTemplate().save(entity);
	}

	public void update(Object entity) {
		supperHibernateDao.getHibernateTemplate().update(entity);
	}

	public void saveOrUpdate(Object entity) {
		supperHibernateDao.getHibernateTemplate().saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<Object> entities) {
		supperHibernateDao.getHibernateTemplate().saveOrUpdateAll(entities);
	}

	public void delete(Object entity) {
		supperHibernateDao.getHibernateTemplate().delete(entity);
	}

	public void delete(PK id) {
		supperHibernateDao.getHibernateTemplate().delete(get(id));
	}

	public void deleteAll(Collection<Object> entities) {
		supperHibernateDao.getHibernateTemplate().deleteAll(entities);
	}

	public void deleteAll(String idName, PK[] ids) {
		List entities = supperHibernateDao.getHibernateTemplate()
				.findByCriteria(
						DetachedCriteria.forClass(entityClass).add(
								Property.forName(idName).in(ids)));
		supperHibernateDao.getHibernateTemplate().deleteAll(entities);
	}

	public T get(PK id) {
		return supperHibernateDao.getHibernateTemplate().get(entityClass, id);
	}

	public T get(String property, Object value) {
		return (T) supperHibernateDao.getSessionFactory().getCurrentSession()
				.createCriteria(entityClass)
				.add(Property.forName(property).eq(value)).uniqueResult();
	}
}
