package com.jeecms.core.manager.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.CmsUserDao;
import com.jeecms.core.dao.JcFollowProjectDao;
import com.jeecms.core.dao.JcReleaseTransferDao;
import com.jeecms.core.dao.JcUserNoticeDao;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.JcReleaseTransfer;
import com.jeecms.core.entity.JcUserNotice;
import com.jeecms.core.manager.JcReleaseTransferMng;

@Service
@Transactional
public class JcReleaseTransferMngImpl implements JcReleaseTransferMng{

	@Autowired
	private JcReleaseTransferDao jcReleaseTransferDao;
	
	@Autowired
	private JcUserNoticeDao jcUserNoticeDao;
	
	@Autowired
	private CmsUserDao cmsUserDao;
	
	@Autowired
	private JcFollowProjectDao jcFollowProjectDao;
	
	@Override
	public JcReleaseTransfer save(JcReleaseTransfer bean,String userName) {
		CmsUser user = cmsUserDao.findByUsername(userName);
		if (bean != null && user != null) {
			bean.setCheckStatus((byte)1);
			bean.setShowStatus((byte)2);
			bean.setApplyTime(new Date());
			bean.setCmsUser(user);
		}
		return jcReleaseTransferDao.save(bean);
	}

	@Override
	public JcReleaseTransfer findById(Integer id) {
		return jcReleaseTransferDao.findById(id);
	}
	
	@Override
	public JcReleaseTransfer findByUserName(String userName) {
		return jcReleaseTransferDao.findByUserName(userName);
	}

	@Override
	public void deleteByIds(Integer... ids) {
		if (ids != null){
			for (Integer id : ids) {
				if (id != null && id >0) {
					jcReleaseTransferDao.deleteById(id);
					jcFollowProjectDao.deleteByProjectId(id, 2);
				}
			}
		}
	}

	@Override
	public JcReleaseTransfer updateCheckStatus(int id,
			Integer checkStatus, String reason,String title) {
		JcReleaseTransfer entity = jcReleaseTransferDao.updateStatus(id, checkStatus, null, reason);
		if (entity.getCmsUser() != null && checkStatus != null) {
			JcUserNotice record = new JcUserNotice();
			record.setCmsUser(entity.getCmsUser());
			record.setResult(checkStatus.byteValue());
			record.setTitle(title);
			record.setContent(reason);
			record.setReadStatus((byte)1);
			record.setTag((byte)2);
			record.setCreateTime(new Date());
			jcUserNoticeDao.save(record);
		}
		return entity;
	}
	
	@Override
	public JcReleaseTransfer updateShowStatus(int id, Integer showStatus) {
		return jcReleaseTransferDao.updateStatus(id, null, showStatus, null);
	}

	@Override
	public Pagination getPage(String userName, Integer stage,
			Integer industry, Integer checkStatus, Date startTime,
			Date endTime, Integer pageNo, Integer pageSize) {
		return jcReleaseTransferDao.getPage(userName,
				stage, industry, checkStatus, startTime, endTime, pageNo, pageSize);
	}
	
	@Override
	public Pagination getInteractionPage(String money,String radio,String position,
			String name,Integer pageNo,Integer pageSize) {
		return jcReleaseTransferDao.getInteractionPage(money, radio, position, name, pageNo, pageSize);
	}

	@Override
	public JcReleaseTransfer updateByUpdater(JcReleaseTransfer bean) {
		Updater<JcReleaseTransfer> updater = new Updater<JcReleaseTransfer>(bean);
		return jcReleaseTransferDao.updateByUpdater(updater);
	}
}
