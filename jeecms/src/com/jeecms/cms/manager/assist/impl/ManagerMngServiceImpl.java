package com.jeecms.cms.manager.assist.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.CalculateIdentityUtil;
import com.jeecms.cms.IdentityEnum;
import com.jeecms.cms.manager.assist.ManagerMng;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.CmsUserDao;
import com.jeecms.core.dao.CorpApplyDao;
import com.jeecms.core.dao.CorpApplyShareholderDao;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.JcCorp;
import com.jeecms.core.entity.JcCorpShareholder;

@Service
@Transactional
public class ManagerMngServiceImpl implements ManagerMng
{
	private CorpApplyDao corpApplyDao;

	private CorpApplyShareholderDao corpApplyShareholderDao;

	private CmsUserDao cmsUserDao;

	@Autowired
	public void setCorpApplyDao(CorpApplyDao corpApplyDao)
	{
		this.corpApplyDao = corpApplyDao;
	}

	@Autowired
	public void setCorpApplyShareholderDao(CorpApplyShareholderDao corpApplyShareholderDao)
	{
		this.corpApplyShareholderDao = corpApplyShareholderDao;
	}

	@Autowired
	public void setCmsUserDao(CmsUserDao cmsUserDao)
	{
		this.cmsUserDao = cmsUserDao;
	}

	@Override
	public Pagination getPage(String applyUsername, String corpName, Integer applyStatus, Date applyTimeBegin,
			Date applyTimeEnd, Integer pageNo, Integer pageSize)
	{
		Pagination page = corpApplyDao.getPage(applyUsername, corpName, applyStatus, applyTimeBegin, applyTimeEnd,
				pageNo, pageSize);
		return page;
	}

	@Override
	public JcCorp findById(Integer ids)
	{
		return corpApplyDao.findById(ids);
	}

	@Override
	public List<JcCorpShareholder> findShareById(Integer ids)
	{
		return corpApplyShareholderDao.findShareById(ids);
	}

	@Override
	public void passCheck(String username, Integer id, Boolean pass)
	{
		JcCorp jcCorp = corpApplyDao.findById(id);
		String corpApplyUsername = jcCorp.getCorpApplyUsername();
		if (pass)
		{
			if (jcCorp.getCorpIsCooperation())
			{
				addIdentityByUsername(corpApplyUsername, IdentityEnum.External_Cooperation_Agencies);
			}
			addIdentityByUsername(corpApplyUsername, IdentityEnum.Settled_Enterprise);
		}

		jcCorp.setCorpAudit(pass ? 1 : 2);
		jcCorp.setCorpAuditPerson(username);
		Timestamp value = new Timestamp(System.currentTimeMillis());
		jcCorp.setCorpAuditTime(value);
		Updater<JcCorp> updater = new Updater<JcCorp>(jcCorp);
		corpApplyDao.updateByUpdater(updater);
	}

	@Override
	public Pagination getPageOut(String applyUsername, String corpName, Integer applyStatus, Date applyTimeBegin,
			Date applyTimeEnd, int pageNo, int pageSize)
	{
		Pagination page = corpApplyDao.getPageOut(applyUsername, corpName, applyStatus, applyTimeBegin, applyTimeEnd,
				pageNo, pageSize);
		return page;
	}

	@Override
	public void addIdentityById(Integer userId, IdentityEnum identityEnum)
	{
		CmsUser cmsUser = cmsUserDao.findById(userId);

		int identity = CalculateIdentityUtil.addIdentity(cmsUser, identityEnum);
		cmsUser.setMemberType(identity);
		Updater<CmsUser> updater = new Updater<CmsUser>(cmsUser);
		cmsUserDao.updateByUpdater(updater);
	}

	@Override
	public void addIdentityByUsername(String username, IdentityEnum identityEnum)
	{
		CmsUser cmsUser = cmsUserDao.findByUsername(username);

		int identity = CalculateIdentityUtil.addIdentity(cmsUser, identityEnum);
		cmsUser.setMemberType(identity);
		Updater<CmsUser> updater = new Updater<CmsUser>(cmsUser);
		cmsUserDao.updateByUpdater(updater);
	}
}
