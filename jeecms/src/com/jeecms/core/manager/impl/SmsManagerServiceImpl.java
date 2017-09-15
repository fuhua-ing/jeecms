package com.jeecms.core.manager.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.MessageTypeEnum;
import com.jeecms.common.email.GenerateMessage;
import com.jeecms.common.email.HttpClientUtilMessage;
import com.jeecms.common.hibernate4.Updater;
import com.jeecms.core.dao.CmsUserDao;
import com.jeecms.core.dao.CmsUserExtDao;
import com.jeecms.core.dao.CorpApplyDao;
import com.jeecms.core.dao.SmsManagerDao;
import com.jeecms.core.dao.UnifiedUserDao;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.CmsUserExt;
import com.jeecms.core.entity.JcCorp;
import com.jeecms.core.entity.JcMessageRecord;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.SmsManagerPlatform;

@Service
@Transactional
public class SmsManagerServiceImpl implements SmsManagerPlatform
{
	private static final long EFFECTIVE_TIME = 420000;

	private SmsManagerDao smsManagerDao;

	private UnifiedUserDao dao;

	private CmsUserDao cmsUserDao;

	private CmsUserExtDao cmsUserExtDao;

	private CorpApplyDao corpApplyDao;

	private UnifiedUserDao unifiedUserDao;

	@Autowired
	public void setSmsManagerDao(SmsManagerDao smsManagerDao)
	{
		this.smsManagerDao = smsManagerDao;
	}

	@Autowired
	public void setDao(UnifiedUserDao dao)
	{
		this.dao = dao;
	}

	@Autowired
	public void setCmsUserDao(CmsUserDao cmsUserDao)
	{
		this.cmsUserDao = cmsUserDao;
	}

	@Autowired
	public void setCmsUserExtDao(CmsUserExtDao cmsUserExtDao)
	{
		this.cmsUserExtDao = cmsUserExtDao;
	}

	@Autowired
	public void setCorpApplyDao(CorpApplyDao corpApplyDao)
	{
		this.corpApplyDao = corpApplyDao;
	}

	@Autowired
	public void setUnifiedUserDao(UnifiedUserDao unifiedUserDao)
	{
		this.unifiedUserDao = unifiedUserDao;
	}

	@Override
	public void sendMessage(String username, MessageTypeEnum messageTypeEnum)
	{
		String messageId = GenerateMessage.getMessageId();

		JcMessageRecord jcMessageRecord = smsManagerDao.getBeanByUsernameAndType(username, messageTypeEnum.getValue());

		long currentTimeMillis = System.currentTimeMillis();
		long endTime = currentTimeMillis + EFFECTIVE_TIME;
		Timestamp eventApplyTime = new Timestamp(currentTimeMillis);
		Timestamp endTimes = new Timestamp(endTime);

		jcMessageRecord.setMessageApplyTime(eventApplyTime);
		jcMessageRecord.setMessageEndTime(endTimes);

		jcMessageRecord.setMessageCode(messageId);

		jcMessageRecord.setMessageType(messageTypeEnum.getValue());

		jcMessageRecord.setUserName(username);

		HttpClientUtilMessage.sendMeassage(username, messageId);

		smsManagerDao.saveEntity(jcMessageRecord);
	}

	@Override
	public boolean validatorExsitor(String username, String messageCode, MessageTypeEnum messageTypeEnum)
	{
		boolean flag = false;
		long currentTimeMillis = System.currentTimeMillis();
		Timestamp currTime = new Timestamp(currentTimeMillis);
		flag = smsManagerDao.validatorrCode(username, messageCode, currTime, messageTypeEnum);
		return flag;
	}

	@Override
	public void changeUsername(String userId, String username)
	{
		int id = Integer.parseInt(userId);

		UnifiedUser unifiedUser = dao.findById(id);

		String oldUsername = unifiedUser.getUsername();

		unifiedUser.setUsername(username);
		Updater<UnifiedUser> updaterJo = new Updater<UnifiedUser>(unifiedUser);
		dao.updateByUpdater(updaterJo);

		CmsUser cmsUser = cmsUserDao.findById(id);
		cmsUser.setUsername(username);
		Updater<CmsUser> updaterCmsUser = new Updater<CmsUser>(cmsUser);
		cmsUserDao.updateByUpdater(updaterCmsUser);

		CmsUserExt cmsUserExt = cmsUserExtDao.findById(id);
		cmsUserExt.setPhone(username);
		Updater<CmsUserExt> updaterCmsUserExt = new Updater<CmsUserExt>(cmsUserExt);
		cmsUserExtDao.updateByUpdater(updaterCmsUserExt);

		List<JcCorp> list = corpApplyDao.findByUsername(oldUsername);
		if (null != list && list.size() > 0)
		{
			for (JcCorp jcCorp : list)
			{
				jcCorp.setCorpApplyUsername(username);
				Updater<JcCorp> updater = new Updater<JcCorp>(jcCorp);
				corpApplyDao.updateByUpdater(updater);
			}
		}
	}

	@Override
	public void updateEmailBymessageValidator(String username, String email)
	{
		CmsUser user = cmsUserDao.findByUsername(username);
		user.setEmail(email);
		Updater<CmsUser> updater = new Updater<CmsUser>(user);
		cmsUserDao.updateByUpdater(updater);

		UnifiedUser unifiedUser = unifiedUserDao.getByUsername(username);
		unifiedUser.setEmail(email);
		Updater<UnifiedUser> updater2 = new Updater<UnifiedUser>(unifiedUser);
		unifiedUserDao.updateByUpdater(updater2);
	}

	@Override
	public void changeUsernameByOldUsername(String oldUsername, String username)
	{

		UnifiedUser unifiedUser = dao.getByUsername(oldUsername);

		CmsUserExt cmsUserExt = cmsUserExtDao.findByUsername(oldUsername);
		CmsUser cmsUser = cmsUserDao.findByUsername(oldUsername);
		List<JcCorp> list = corpApplyDao.findByUsername(oldUsername);

		cmsUserExt.setPhone(username);
		Updater<CmsUserExt> updaterCmsUserExt = new Updater<CmsUserExt>(cmsUserExt);
		cmsUserExtDao.updateByUpdater(updaterCmsUserExt);

		unifiedUser.setUsername(username);
		Updater<UnifiedUser> updaterJo = new Updater<UnifiedUser>(unifiedUser);
		dao.updateByUpdater(updaterJo);

		cmsUser.setUsername(username);
		Updater<CmsUser> updaterCmsUser = new Updater<CmsUser>(cmsUser);
		cmsUserDao.updateByUpdater(updaterCmsUser);

		if (null != list && list.size() > 0)
		{
			for (JcCorp jcCorp : list)
			{
				jcCorp.setCorpApplyUsername(username);
				Updater<JcCorp> updater = new Updater<JcCorp>(jcCorp);
				corpApplyDao.updateByUpdater(updater);
			}
		}
	}
}
