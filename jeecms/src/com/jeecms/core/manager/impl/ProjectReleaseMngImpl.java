package com.jeecms.core.manager.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.CmsUserDao;
import com.jeecms.core.dao.JcFollowProjectDao;
import com.jeecms.core.dao.JcUserNoticeDao;
import com.jeecms.core.dao.ProjectReleaseDao;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.JcUserNotice;
import com.jeecms.core.entity.ProjectRelease;
import com.jeecms.core.manager.ProjectReleaseMng;

public class ProjectReleaseMngImpl implements ProjectReleaseMng{

	@Autowired
	private ProjectReleaseDao projectReleaseDao;
	
	@Autowired
	private JcUserNoticeDao jcUserNoticeDao;
	
	@Autowired
	private CmsUserDao cmsUserDao;
	
	@Autowired
	private JcFollowProjectDao jcFollowProjectDao;
	
	@Override
	public ProjectRelease save(ProjectRelease bean, String userName) {
		CmsUser user = cmsUserDao.findByUsername(userName);
		if (bean != null && user != null) {
			bean.setCheckStatus((byte)1);
			bean.setShowStatus((byte)2);
			bean.setCreateTime(new Date());
			bean.setCmsUser(user);
		}
		return projectReleaseDao.save(bean);
	}

	@Override
	public ProjectRelease findById(Integer id) {
		return projectReleaseDao.findById(id);
	}

	@Override
	public ProjectRelease findByUserName(String userName) {
		return projectReleaseDao.findByUserName(userName);
	}

	@Override
	public void deleteByIds(Integer... ids) {
		if (ids != null){
			for (Integer id : ids) {
				if (id != null && id >0) {
					projectReleaseDao.deleteById(id);
					jcFollowProjectDao.deleteByProjectId(id, 1);
				}
			}
		}		
	}

	@Override
	public ProjectRelease updateCheckStatus(int id, Integer checkStatus, String reason) {
		ProjectRelease entity = projectReleaseDao.updateStatus(id, checkStatus, null, reason);
		if (entity.getCmsUser() != null && checkStatus != null) {
			JcUserNotice record = new JcUserNotice();
			record.setCmsUser(entity.getCmsUser());
			record.setResult(checkStatus.byteValue());
			record.setContent(reason);
			record.setReadStatus((byte)1);
			record.setTag((byte)2);
			record.setCreateTime(new Date());
			jcUserNoticeDao.save(record);
		}
		return entity;
	}

	@Override
	public ProjectRelease updateShowStatus(int id, Integer showStatus) {
		return projectReleaseDao.updateStatus(id, null, showStatus, null);
	}

	@Override
	public Pagination getPage(String userName, Integer stage, Integer industry, Integer checkStatus, Date startTime,
			Date endTime, Integer pageNo, Integer pageSize) {
		return projectReleaseDao.getPage(userName,
				stage, industry, checkStatus, startTime, endTime, pageNo, pageSize);
	}

	@Override
	public ProjectRelease updateByUpdater(ProjectRelease bean) {
		Updater<ProjectRelease> updater = new Updater<ProjectRelease>(bean);
		return projectReleaseDao.updateByUpdater(updater);
	}

	@Override
	public Pagination getInteractionPage(String money, String radio,
			String position, String name, Integer pageNo, Integer pageSize) {
		return projectReleaseDao.getInteractionPage(money, radio, position, name,pageNo, pageSize);
	}

}
