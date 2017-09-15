package com.jeecms.core.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.core.dao.JcEventDao;
import com.jeecms.core.entity.JcEvent;

@Repository
public class JcEventDaoImpl extends HibernateBaseDao<JcEvent, Integer> implements JcEventDao
{

	@Override
	protected Class<JcEvent> getEntityClass()
	{
		return JcEvent.class;
	}

	@Override
	public void saveEntity(JcEvent jcEvent)
	{
		Session session = getSession();
		session.save(jcEvent);
	}

	@Override
	public JcEvent queryByUuid(String reset)
	{
		String hql = "select bean from JcEvent bean where bean.eventUuid=?";
		Session session = getSession();
		List<JcEvent> list = session.createQuery(hql).setString(0, reset).list();
		if (null != list && !list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean exsitByUuidAndUserId(String userId, String eventUuid)
	{
		String hql = "select count(*) from JcEvent bean where bean.eventUuid=? and bean.eventUserId=?";
		Session session = getSession();
		Long result = (Long) session.createQuery(hql).setString(0, eventUuid).setString(1, userId).uniqueResult();
		return result > 0 ? true : false;
	}

	@Override
	public JcEvent getByIdAndType(Integer id, String changeType)
	{
		String hql = "select bean from JcEvent bean where bean.eventUserId=? and bean.eventType=?";
		Session session = getSession();
		List<JcEvent> list = session.createQuery(hql).setInteger(0, id).setString(1, changeType).list();
		if (null != list && !list.isEmpty())
		{
			return list.get(0);
		}
		return new JcEvent();
	}
}
