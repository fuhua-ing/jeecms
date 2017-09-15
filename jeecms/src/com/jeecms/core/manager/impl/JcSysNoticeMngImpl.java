package com.jeecms.core.manager.impl;


import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.CmsUserDao;
import com.jeecms.core.dao.JcSysNoticeDao;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.JcSysNotice;
import com.jeecms.core.manager.JcSysNoticeMng;

@Service
@Transactional
public class JcSysNoticeMngImpl implements JcSysNoticeMng{
	
	@Autowired
	private JcSysNoticeDao jcSysNoticeDao;

	@Autowired
	private CmsUserDao cmsUserDao;
	
	@Override
	public JcSysNotice save(JcSysNotice bean, String userName) {
		CmsUser user = cmsUserDao.findByUsername(userName);
		if (bean != null && user != null) {
			bean.setCreateTime(new Date());
			bean.setCmsUser(user);
		}
		return jcSysNoticeDao.save(bean);
	}
	
	@Override
	public JcSysNotice save(Integer id,String[] userType,String title,String content,CmsUser user) {
		JcSysNotice bean = null;
		if(id != null && id>0){
			bean = jcSysNoticeDao.findById(id);
			bean.setTitle(title);
			bean.setContent(content);
			bean.setRecvUserType(StringUtils.join(userType,","));
			bean.setCmsUser(user);
			bean.setCreateTime(new Date());
			return updateByUpdater(bean);
		} else {
			bean = new JcSysNotice();
			bean.setTitle(title);
			bean.setContent(content);
			bean.setRecvUserType(StringUtils.join(userType,","));
			bean.setCmsUser(user);
			bean.setCreateTime(new Date());
			return jcSysNoticeDao.save(bean);
		}
	}

	@Override
	public JcSysNotice findById(Integer id) {
		if(id != null && id >0) {
			return jcSysNoticeDao.findById(id);
		}
		return null;
	}

	@Override
	public void deleteByIds(Integer... ids) {
		if(ids != null) {
			for(Integer id: ids) {
				if (id != null && id >0){
					jcSysNoticeDao.deleteById(id);
				}
			}
		}
	}

	@Override
	public Pagination getPage(String title, Integer pageNo, Integer pageSize) {
		return jcSysNoticeDao.getPage(pageNo, pageSize, title);
	}

	@Override
	public JcSysNotice updateByUpdater(JcSysNotice bean) {
		Updater<JcSysNotice> updater = new Updater<JcSysNotice>(bean);
		return jcSysNoticeDao.updateByUpdater(updater);
	}
}
