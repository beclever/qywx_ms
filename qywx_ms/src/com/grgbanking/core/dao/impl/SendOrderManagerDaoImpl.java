package com.grgbanking.core.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.grgbanking.core.dao.SendOrderManagerDao;
import com.grgbanking.core.entity.user.SendOrderBean;
import com.grgbanking.core.entity.ws.WsPage;
import com.grgbanking.core.service.BaseService;

/**
 * @ClassName: IsUpdateDaoImpl
 * @Description: 录单管理Dao
 * @author cflong
 * @date Copyright 2014 by Grgbanking All Rights Reserved.
 */
@Repository("sendOrderManagerDao")
public class SendOrderManagerDaoImpl extends BaseService<SendOrderBean, String>
		implements SendOrderManagerDao<SendOrderBean, String> {
	// Logger logger = Logger.getLogger(getClass());

	@Override
	public void doSaveIsUpdateOrderBean(SendOrderBean bean) throws Exception {
		supperHibernateDao.saveOrUpdate(bean);
	}

	@SuppressWarnings("rawtypes")
	public List findSendOrderBeanList(String userCode, final WsPage wsPage,
			String serialNumber, String branchName, String engineerUserCode)
			throws Exception {

		final StringBuffer sb = new StringBuffer();
		sb.append("from SendOrderBean s where s.userCode = '" + userCode
				+ "' and s.isSend ='0'");// "1"位一派出的工单,"0"位未派出的工单
		if (branchName != null && !"".equals(branchName)) {
			sb.append("and s.branchName LIKE '%" + branchName + "%'");
		}
		if (serialNumber != null && !"".equals(serialNumber)) {
			sb.append("and s.serialNumber = '" + serialNumber + "'");
		}
		if (engineerUserCode != null && !"".equals(engineerUserCode)) {
			sb.append("and s.engineerId = '" + engineerUserCode + "'");
		}
		sb.append("order by createTime desc");
		supperHibernateDao.findByHql(sb.toString());

		return supperHibernateDao.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						Query query = s.createQuery(sb.toString());
						query.setFirstResult(wsPage.getRows()
								* (wsPage.getPage() - 1));
						query.setMaxResults(wsPage.getRows());
						List list = query.list();
						return list;
					}
				});
	}

	@SuppressWarnings("unchecked")
	public SendOrderBean findSendOrderBean(String id) throws Exception {
		String hqlString = " from SendOrderBean t where t.id = '" + id + "' ";
		List<SendOrderBean> list = supperHibernateDao.findByHql(hqlString);
		SendOrderBean sendOrderBean = null;
		if (list != null && list.size() > 0)
			sendOrderBean = list.get(0);
		return sendOrderBean;
	}

}
