package com.jeecms.core.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.core.dao.CmsUserExtDao;
import com.jeecms.core.entity.CmsUserExt;

@Repository
public class CmsUserExtDaoImpl extends HibernateBaseDao<CmsUserExt, Integer> implements CmsUserExtDao
{
	@Override
	public CmsUserExt findById(Integer id)
	{
		CmsUserExt entity = get(id);
		return entity;
	}

	@Override
	public CmsUserExt save(CmsUserExt bean)
	{
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<CmsUserExt> getEntityClass()
	{
		return CmsUserExt.class;
	}

	@Override
	public CmsUserExt findByUsername(String username)
	{
		String hql = "select bean from CmsUserExt bean left join bean.user user where user.username=?";
		Query query = getSession().createQuery(hql);
		List<CmsUserExt> list = query.setString(0, username).list();
		if (null != list && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}
}