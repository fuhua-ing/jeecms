package com.jeecms.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.JcUserNoticeDao;
import com.jeecms.core.entity.JcUserNotice;

@Repository
public class JcUserNoticeDaoImpl extends
		HibernateBaseDao<JcUserNotice, Integer> implements
		JcUserNoticeDao {

	@Override
	public JcUserNotice save(JcUserNotice bean) {
		if (bean != null) {
			getSession().save(bean);
		}
		return bean;
	}

	@Override
	public JcUserNotice findById(Integer id) {
		if (id <= 0) {
			return null;
		}
		return get(id);
	}
	
	@Override
	public JcUserNotice deleteById(Integer id) {
		JcUserNotice entity = get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public JcUserNotice updateJcUserNotice(int id, int status) {
		JcUserNotice record = super.get(id);
		if (record != null && status>0) {
			record.setReadStatus((byte)status);
			getSession().update(record);
		}
		return record;
	}

	@Override
	public Pagination getPage(Integer userId, Integer pageNo, Integer pageSize) {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		Finder f = Finder.create("select bean from JcUserNotice bean where bean.cmsUser.id=:userId "
				+ "order by bean.readStatus,bean.createTime desc");
		f.setParam("userId", userId);
		return find(f, pageNo, pageSize);
	}

	@Override
	protected Class<JcUserNotice> getEntityClass() {
		return JcUserNotice.class;
	}
	
}
