package com.jeecms.core.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.JcSysNoticeDao;
import com.jeecms.core.entity.JcSysNotice;

@Repository
public class JcSysNoticeDaoImpl extends
		HibernateBaseDao<JcSysNotice, Integer> implements
		JcSysNoticeDao {

	@Override
	public JcSysNotice save(JcSysNotice bean) {
		if (bean != null) {
			getSession().save(bean);
		}
		return bean;
	}

	@Override
	public JcSysNotice findById(Integer id) {
		if (id <= 0) {
			return null;
		}
		return get(id);
	}

	@Override
	public JcSysNotice deleteById(Integer id) {
		JcSysNotice entity = get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public Pagination getPage(Integer pageNo, Integer pageSize, String title) {
		Finder f = Finder.create("select bean from JcSysNotice bean where 1=1 ");
		if (StringUtils.isNotBlank(title)) {
			f.append(" and bean.title = :queryTitle").setParam("queryTitle", title);
		}
		f.append(" order by bean.createTime desc");
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		return find(f, pageNo, pageSize);
	}

	@Override
	protected Class<JcSysNotice> getEntityClass() {
		return JcSysNotice.class;
	}
	
}
