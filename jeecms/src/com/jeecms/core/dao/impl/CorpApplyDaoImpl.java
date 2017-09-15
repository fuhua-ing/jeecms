package com.jeecms.core.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.core.dao.CorpApplyDao;
import com.jeecms.core.dao.CorpApplyShareholderDao;
import com.jeecms.core.entity.JcCorp;
import com.jeecms.core.entity.JcCorpShareholder;

public class CorpApplyDaoImpl extends HibernateBaseDao<JcCorp, Integer> implements CorpApplyDao
{

	private CorpApplyShareholderDao corpApplyShareholderDao;

	@Override
	protected Class<JcCorp> getEntityClass()
	{
		return JcCorp.class;
	}

	@Override
	public void saveEntity(JcCorp corpInfo, List<JcCorpShareholder> shareholderInfos)
	{

		Session session = getSession();
		Serializable result = session.save(corpInfo);
		Integer id = (Integer) result;
		if (null != shareholderInfos && shareholderInfos.size() > 0)
		{
			corpApplyShareholderDao.insertBatch(id, shareholderInfos);
		}
	}

	@Autowired
	public void setShareholderDao(CorpApplyShareholderDao corpApplyShareholderDao)
	{
		this.corpApplyShareholderDao = corpApplyShareholderDao;
	}

	@Override
	public void saveCooperation(JcCorp corpInfo)
	{
		Session session = getSession();
		session.save(corpInfo);
	}

	@Override
	public Pagination getPage(String applyUsername, String corpName, Integer applyStatus, Date applyTimeBegin,
			Date applyTimeEnd, Integer pageNo, Integer pageSize)
	{
		Finder f = createFinder(applyUsername, corpName, applyStatus, applyTimeBegin, applyTimeEnd);
		return find(f, pageNo, pageSize);
	}

	private Finder createFinder(String applyUsername, String corpName, Integer applyStatus, Date applyTimeBegin,
			Date applyTimeEnd)
	{
		String hql = "select bean  from JcCorp bean where 1=1 and bean.corpIsCooperation=0";
		Finder f = Finder.create(hql);
		if (applyUsername != null)
		{
			f.append(" and bean.corpApplyUsername like '%" + applyUsername + "%'");
		}
		if (corpName != null)
		{
			f.append(" and bean.corpName like '%" + corpName + "%'");
		}
		if (applyStatus != null)
		{
			f.append(" and bean.corpAudit=:corpAudit").setParam("corpAudit", applyStatus);
		}
		if (applyTimeBegin != null)
		{
			f.append(" and bean.corpApplyTime>=:applyTimeBegin").setParam("applyTimeBegin",
					DateUtils.getStartDate(applyTimeBegin));
		}
		if (applyTimeEnd != null)
		{
			f.append(" and bean.corpApplyTime<=:applyTimeEnd").setParam("applyTimeEnd",
					DateUtils.getFinallyDate(applyTimeEnd));
		}
		f.setCacheable(true);
		return f;
	}

	@Override
	public JcCorp findById(Integer ids)
	{
		return get(ids);
	}

	@Override
	public Pagination getPageOut(String applyUsername, String corpName, Integer applyStatus, Date applyTimeBegin,
			Date applyTimeEnd, int pageNo, int pageSize)
	{
		Finder f = createFinderOut(applyUsername, corpName, applyStatus, applyTimeBegin, applyTimeEnd);
		return find(f, pageNo, pageSize);
	}

	private Finder createFinderOut(String applyUsername, String corpName, Integer applyStatus, Date applyTimeBegin,
			Date applyTimeEnd)
	{
		String hql = "select bean  from JcCorp bean where 1=1 and bean.corpIsCooperation=1";
		Finder f = Finder.create(hql);
		if (applyUsername != null)
		{
			f.append(" and bean.corpApplyUsername like '%" + applyUsername + "%'");
		}
		if (corpName != null)
		{
			f.append(" and bean.corpName like '%" + corpName + "%'");
		}
		if (applyStatus != null)
		{
			f.append(" and bean.corpAudit=:corpAudit").setParam("corpAudit", applyStatus);
		}
		if (applyTimeBegin != null)
		{
			f.append(" and bean.corpApplyTime>=:applyTimeBegin").setParam("applyTimeBegin",
					DateUtils.getStartDate(applyTimeBegin));
		}
		if (applyTimeEnd != null)
		{
			f.append(" and bean.corpApplyTime<=:applyTimeEnd").setParam("applyTimeEnd",
					DateUtils.getFinallyDate(applyTimeEnd));
		}
		f.setCacheable(true);
		return f;
	}

	@Override
	public JcCorp queryCorpInnerByUsername(String username)
	{
		String hql = "select bean  from JcCorp bean where 1=1 and bean.corpIsCooperation=0 and bean.corpApplyUsername=?";
		Query query = getSession().createQuery(hql);
		List<JcCorp> list = query.setString(0, username).list();
		if (null != list && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public JcCorp queryCorpOutByUsername(String username)
	{
		String hql = "select bean  from JcCorp bean where 1=1 and bean.corpIsCooperation=1 and bean.corpApplyUsername=?";
		Query query = getSession().createQuery(hql);
		List<JcCorp> list = query.setString(0, username).list();
		if (null != list && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<JcCorp> findByUsername(String oldUsername)
	{
		String hql = "select bean  from JcCorp bean where 1=1 and bean.corpApplyUsername=?";
		Query query = getSession().createQuery(hql);
		List<JcCorp> list = query.setString(0, oldUsername).list();
		return list;
	}
}
