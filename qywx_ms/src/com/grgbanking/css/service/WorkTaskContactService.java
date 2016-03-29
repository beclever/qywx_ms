package com.grgbanking.css.service;

import java.util.List;

import com.grgbanking.css.bean.work.WorkTaskContactBean;


public interface WorkTaskContactService {

	public List<WorkTaskContactBean> listByWorkformId(Integer workformId);

}
