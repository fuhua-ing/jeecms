package com.jeecms.cms.manager.main.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.cms.dao.main.ActivitiesEnrollDao;
import com.jeecms.cms.dao.main.ContentDao;
import com.jeecms.cms.dao.main.ContentExtDao;
import com.jeecms.cms.entity.main.ActivitiesEnroll;
import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.entity.main.ContentExt;
import com.jeecms.cms.manager.main.ActivitiesEnrollMng;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;

public class ActivitiesEnrollMngImpl implements ActivitiesEnrollMng{

	@Autowired
	private ContentDao conDao;
	@Autowired
	private ContentExtDao conExtDao;
	@Autowired
	private ActivitiesEnrollDao activitiesEnrollDao;
	
	@Override
	public ActivitiesEnroll save(ActivitiesEnroll bean, Integer contentId) {
		activitiesEnrollDao.save(bean);
		return bean;
	}

	@Override
	public ActivitiesEnroll findById(Integer id) {
		return activitiesEnrollDao.findById(id);
	}
	
	@Override
	public ActivitiesEnroll findByUserPhone(Integer contentId,String userphone) {
		return activitiesEnrollDao.findByUserPhone(contentId,userphone);
	}

	@Override
	public void deleteByIds(Integer... ids) {
		if (ids != null){
			for (Integer id : ids) {
				if (id != null && id >0) {
					activitiesEnrollDao.deleteById(id);
				}
			}
		}	
	}

	@Override
	public Pagination getPage(String title, Integer enrollStatus, Integer pageNo, Integer pageSize) {
		return activitiesEnrollDao.getPage(title, enrollStatus, pageNo, pageSize);
	}

	@Override
	public Pagination getPersonPage(Integer contentId, Integer pageNo, Integer pageSize) {
		return activitiesEnrollDao.getPersonPage(contentId, pageNo, pageSize);
	}

	@Override
	public ActivitiesEnroll updateByUpdater(ActivitiesEnroll bean) {
		Updater<ActivitiesEnroll> updater = new Updater<ActivitiesEnroll>(bean);
		return activitiesEnrollDao.updateByUpdater(updater);
	}


}
