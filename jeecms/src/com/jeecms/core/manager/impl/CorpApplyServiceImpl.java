package com.jeecms.core.manager.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.core.dao.CmsUserDao;
import com.jeecms.core.dao.CorpApplyDao;
import com.jeecms.core.dao.JcEventDao;
import com.jeecms.core.dao.UnifiedUserDao;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.JcCorp;
import com.jeecms.core.entity.JcCorpShareholder;
import com.jeecms.core.entity.JcEvent;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.CorpApplyService;

@Service
@Transactional
public class CorpApplyServiceImpl implements CorpApplyService
{

	private CorpApplyDao corpApplyDao;

	private JcEventDao jcEventDao;

	private CmsUserDao cmsUserDao;

	private UnifiedUserDao unifiedUserDao;

	@Override
	public void saveInfo(String username, JcCorp corpInfo, List<JcCorpShareholder> shareholderInfos)
	{
		corpInfo.setCorpApplyUsername(username);
		Timestamp corpApplyTime = new Timestamp(System.currentTimeMillis());
		corpInfo.setCorpApplyTime(corpApplyTime);
		corpInfo.setCorpAudit(0);
		corpInfo.setCorpIsCooperation(false);
		corpApplyDao.saveEntity(corpInfo, shareholderInfos);
	}

	@Autowired
	public void setCorpApplyDao(CorpApplyDao corpApplyDao)
	{
		this.corpApplyDao = corpApplyDao;
	}

	@Autowired
	public void setJcEventDao(JcEventDao jcEventDao)
	{
		this.jcEventDao = jcEventDao;
	}

	@Autowired
	public void setCmsUserDao(CmsUserDao cmsUserDao)
	{
		this.cmsUserDao = cmsUserDao;
	}

	@Autowired
	public void setUnifiedUserDao(UnifiedUserDao unifiedUserDao)
	{
		this.unifiedUserDao = unifiedUserDao;
	}

	@Override
	public void saveCooperation(String username, JcCorp corpInfo)
	{
		corpInfo.setCorpApplyUsername(username);
		Timestamp corpApplyTime = new Timestamp(System.currentTimeMillis());
		corpInfo.setCorpApplyTime(corpApplyTime);
		corpInfo.setCorpAudit(0);
		corpInfo.setCorpIsCooperation(true);
		corpApplyDao.saveCooperation(corpInfo);
	}

	@Override
	public JcCorp queryCorpInnerByUsername(String username)
	{
		return corpApplyDao.queryCorpInnerByUsername(username);
	}

	@Override
	public JcCorp queryCorpOutByUsername(String username)
	{
		return corpApplyDao.queryCorpOutByUsername(username);
	}

	@Override
	public JcEvent resetInfo(String reset, Timestamp requestTime)
	{
		JcEvent jcEvent = jcEventDao.queryByUuid(reset);
		if (null == jcEvent)
		{
			return null;
		}
		Timestamp eventApplyTime = jcEvent.getEventApplyTime();
		Timestamp eventEndTime = jcEvent.getEventEndTime();
		if (requestTime.before(eventApplyTime) || requestTime.after(eventEndTime))
		{
			return null;
		}

		return jcEvent;

	}

	@Override
	public boolean resetNewEmail(String userId, String eventUuid, String email)
	{
		boolean flag = false;
		if (jcEventDao.exsitByUuidAndUserId(userId, eventUuid))
		{
			int id = Integer.parseInt(userId);
			CmsUser user = cmsUserDao.findById(id);
			user.setEmail(email);
			Updater<CmsUser> updater = new Updater<CmsUser>(user);
			cmsUserDao.updateByUpdater(updater);

			UnifiedUser unifiedUser = unifiedUserDao.findById(id);
			unifiedUser.setEmail(email);
			Updater<UnifiedUser> updater2 = new Updater<UnifiedUser>(unifiedUser);
			unifiedUserDao.updateByUpdater(updater2);
			flag = true;
		}
		return flag;
	}
}
