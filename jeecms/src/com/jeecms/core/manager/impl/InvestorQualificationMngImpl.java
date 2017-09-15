package com.jeecms.core.manager.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.hibernate4.Updater.UpdateMode;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.CmsUserDao;
import com.jeecms.core.dao.CmsUserExtDao;
import com.jeecms.core.dao.InvestorQualificationDao;
import com.jeecms.core.dao.JcUserNoticeDao;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.CmsUserExt;
import com.jeecms.core.entity.InvestorQualification;
import com.jeecms.core.entity.JcUserNotice;
import com.jeecms.core.manager.InvestorQualificationMng;

@Service
@Transactional
public class InvestorQualificationMngImpl implements InvestorQualificationMng{

	@Autowired
	private InvestorQualificationDao investorQualificationDao;
	
	@Autowired
	private JcUserNoticeDao jcUserNoticeDao;
	
	@Autowired
	private CmsUserDao cmsUserDao;
	
	@Autowired
	private CmsUserExtDao cmsUserExtDao;
	
	@Override
	public InvestorQualification saveInvestorQualification(InvestorQualification bean,String userName) {
		CmsUser user = cmsUserDao.findByUsername(userName);
		CmsUserExt userExt= cmsUserExtDao.findById(user.getId());
		if (bean != null && user != null) {
			bean.setCheckStatus((byte)1);
			bean.setApplyTime(new Date());
			bean.setCmsUser(user);
			bean.setUserImg(userExt.getUserImg());
		}
		return investorQualificationDao.save(bean);
	}

	@Override
	public InvestorQualification findById(Integer id) {
		return investorQualificationDao.findById(id);
	}
	
	@Override
	public void deleteByIds(Integer... ids) {
		if (ids != null) {
			for (Integer id : ids) {
				if (id != null && id >0){
					investorQualificationDao.deleteById(id);
				}
			}
		}
	}

	@Override
	public InvestorQualification findByUserName(String userName) {
		return investorQualificationDao.findByUserName(userName);
	}

	@Override
	public InvestorQualification modifyInvestorQualificationMax(InvestorQualification bean) {
		bean.setCheckStatus((byte)1);
		bean.setApplyTime(new Date());
		Updater<InvestorQualification> updater = new Updater<InvestorQualification>(bean,UpdateMode.MAX);
		return investorQualificationDao.updateByUpdater(updater);
	}

	@Override
	public Pagination getPage(String userName, String realName, Date startTime,
			Date endTime, Integer checkStatus, Integer pageNo, Integer pageSize) {
		return investorQualificationDao.getPage(userName, 
				realName, startTime, endTime, checkStatus, pageNo, pageSize);
	}

	@Override
	public InvestorQualification checkInvestorQualification(int id, int status, String reason) {
		InvestorQualification entity = investorQualificationDao.checkInvestorQualification(id, status, reason);
		/**
		 * 保存记录
		 */
		if (entity.getCmsUser() != null) {
			JcUserNotice jcUserNotice = new JcUserNotice();
			jcUserNotice.setCmsUser(entity.getCmsUser());
			jcUserNotice.setResult((byte)status);
			jcUserNotice.setContent(reason);
			jcUserNotice.setReadStatus((byte)1);
			jcUserNotice.setTag((byte)1);
			jcUserNotice.setCreateTime(new Date());
		} 
		return entity;
	}

	@Override
	public Pagination getInteractionPage(String name, String industry,Byte checkStatus,
			Integer pageNo, Integer pageSize) {
		return investorQualificationDao.getInteractionPage(name, industry,checkStatus, pageNo, pageSize);
	}
}
