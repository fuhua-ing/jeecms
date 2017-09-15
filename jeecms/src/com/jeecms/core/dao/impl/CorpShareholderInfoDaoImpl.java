package com.jeecms.core.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.core.dao.CorpApplyShareholderDao;
import com.jeecms.core.entity.JcCorpShareholder;

public class CorpShareholderInfoDaoImpl extends HibernateBaseDao<JcCorpShareholder, Integer> implements
		CorpApplyShareholderDao
{

	//	private static class CorpShareholderInfoDaoImplHolder
	//	{
	//		private static CorpShareholderInfoDaoImpl instance = new CorpShareholderInfoDaoImpl();
	//	}
	//
	@Override
	protected Class<JcCorpShareholder> getEntityClass()
	{
		return JcCorpShareholder.class;
	}

	//
	//	private CorpShareholderInfoDaoImpl()
	//	{
	//
	//	}
	//
	//	public static CorpShareholderInfoDaoImpl getInstance()
	//	{
	//		return CorpShareholderInfoDaoImplHolder.instance;
	//	}
	//
	//	public void insertBatch(Integer id, List<JcCorpShareholder> shareholderInfos)
	//	{
	//		Session session = getSession();
	//		for (JcCorpShareholder jcCorpShareholder : shareholderInfos)
	//		{
	//			jcCorpShareholder.setCorpId(id);
	//		}
	//		session.saveOrUpdate(shareholderInfos);
	//	}

	@Override
	public void insertBatch(Integer id, List<JcCorpShareholder> shareholderInfos)
	{
		Session session = getSession();
		for (JcCorpShareholder jcCorpShareholder : shareholderInfos)
		{
			jcCorpShareholder.setCorpId(id);
			session.save(jcCorpShareholder);
		}
	}

	@Override
	public List<JcCorpShareholder> findShareById(Integer ids)
	{
		Session session = getSession();
		String hql = "from JcCorpShareholder where corpId = ?";
		Query createQuery = session.createQuery(hql);
		createQuery.setInteger(0, ids);
		List<JcCorpShareholder> list = createQuery.list();
		return list;
	}
}
