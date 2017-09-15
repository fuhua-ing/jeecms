package com.jeecms.core.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import com.jeecms.cms.MessageTypeEnum;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.core.dao.SmsManagerDao;
import com.jeecms.core.entity.JcMessageRecord;

public class SmsManagerDaoImpl extends HibernateBaseDao<JcMessageRecord, Integer> implements SmsManagerDao
{

	@Override
	protected Class<JcMessageRecord> getEntityClass()
	{
		return JcMessageRecord.class;
	}

	@Override
	public void saveEntity(JcMessageRecord jcMessageRecord)
	{
		Session session = getSession();
		session.saveOrUpdate(jcMessageRecord);
	}

	@Override
	public JcMessageRecord getBeanByUsernameAndType(String username, String value)
	{
		Session session = getSession();
		String hql = "select bean from JcMessageRecord bean where bean.userName=? and bean.messageType=?";
		List<JcMessageRecord> list = session.createQuery(hql).setString(0, username).setString(1, value).list();
		if (null != list && list.size() > 0)
		{
			return list.get(0);
		}
		return new JcMessageRecord();
	}

	@Override
	public boolean validatorrCode(String username, String messageCode, Timestamp currTime,
			MessageTypeEnum changeUsername)
	{
		String hql = "select count(*) from JcMessageRecord bean where bean.userName=? and bean.messageCode=? and bean.messageType=? and ? between bean.messageApplyTime and bean.messageEndTime";
		Session session = getSession();
		Long result = (Long) session.createQuery(hql).setString(0, username).setString(1, messageCode)
				.setString(2, changeUsername.getValue()).setTimestamp(3, currTime).uniqueResult();
		return result > 0 ? true : false;
	}
}
