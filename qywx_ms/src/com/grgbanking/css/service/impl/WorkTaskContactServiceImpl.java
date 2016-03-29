package com.grgbanking.css.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grgbanking.css.bean.work.WorkTaskContact;
import com.grgbanking.css.bean.work.WorkTaskContactBean;
import com.grgbanking.css.dao.WorkTaskContactDao;
import com.grgbanking.css.service.WorkTaskContactService;


@Service("workTaskContactService")
public class WorkTaskContactServiceImpl implements WorkTaskContactService {

	@Autowired
	private WorkTaskContactDao<WorkTaskContact, Integer> workTaskContactDao;

	public List<WorkTaskContactBean> listByWorkformId(Integer workformId) {
		List<WorkTaskContact> list = workTaskContactDao.listByWorkformId(workformId);
		List<WorkTaskContactBean> result = new ArrayList<WorkTaskContactBean>();
		if (null != list && list.size() > 0) {
			WorkTaskContactBean bean = null;
			for (int i = 0; i < list.size(); i++) {
				bean = new WorkTaskContactBean();
				BeanUtils.copyProperties(list.get(i), bean);
				result.add(bean);

			}
		}
		return result;
	}
}
