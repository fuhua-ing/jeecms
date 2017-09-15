package com.jeecms.core.manager.impl;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.IdentityEnum;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.CmsUserDao;
import com.jeecms.core.dao.InvestorQualificationDao;
import com.jeecms.core.dao.JcUserNoticeDao;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.JcUserNotice;
import com.jeecms.core.manager.JcUserNoticeMng;

@Service
@Transactional
public class JcUserNoticeMngImpl implements JcUserNoticeMng{
	
	@Autowired
	private JcUserNoticeDao jcUserNoticeDao;

	@Autowired
	private CmsUserDao cmsUserDao;
	
	@Autowired
	private InvestorQualificationDao investorQualificationDao;
	
	@Override
	public JcUserNotice saveUserNotice(CmsUser user,
			Integer result, Integer tag,String title,String content) {
		if (user != null && result != null) {
			JcUserNotice entity = new JcUserNotice();
			entity.setCmsUser(user);
			entity.setResult(result.byteValue());
			entity.setContent(content);
			entity.setReadStatus((byte)1);
			entity.setTag(tag.byteValue());
			entity.setCreateTime(new Date());
			return jcUserNoticeDao.save(entity);
		} else {
			return null;
		}
	}

	@Override
	public JcUserNotice readUserNotice(Integer... ids) {
		JcUserNotice temp = null;
		if (ids != null) {
			for (Integer id : ids) {
				if (id != null && id >0) {
					temp = jcUserNoticeDao.updateJcUserNotice(id, 2);
				}
			}
		}
		return temp;
	}

	@Override
	public Pagination getUserNoticePage(Integer userId,Integer pageNo,
			Integer pageSize) {
		return jcUserNoticeDao.getPage(userId, pageNo, pageSize);
	}

	@Override
	public void deleteUserNotice(Integer... ids) {
		if (ids != null){
			for (Integer id : ids) {
				if (id != null && id >0) {
					jcUserNoticeDao.deleteById(id);
				}
			}
		}
	}

	@Override
	public void recvSysNotice(String[] userType, String title,
			String content) {
		Set<Integer> memberTypeSet = new HashSet<Integer>();
		if (userType != null) {
			for (String type : userType) {
				switch(type){
				case "1":
					/**
					 * 普通会员
					 */
					memberTypeSet.add(IdentityEnum.Common_Member.getValue());
					break;
				case "2":
					/**
					 * 投资人
					 */
					memberTypeSet.add(IdentityEnum.Investor.getValue());
					memberTypeSet.add(IdentityEnum.Investor__Settled_Enterprise.getValue());
					memberTypeSet.add(IdentityEnum.Investor__External_Cooperation_Agencies.getValue());
					memberTypeSet.add(IdentityEnum.Investor__External_Cooperation_Agencies__External_Cooperation_Agencies.getValue());
					break;
				case "3":
					/**
					 * 入驻企业
					 */
					memberTypeSet.add(IdentityEnum.Settled_Enterprise.getValue());
					memberTypeSet.add(IdentityEnum.Investor__Settled_Enterprise.getValue());
					memberTypeSet.add(IdentityEnum.Settled_Enterprise__External_Cooperation_Agencies.getValue());
					memberTypeSet.add(IdentityEnum.Investor__External_Cooperation_Agencies__External_Cooperation_Agencies.getValue());
					break;
				case "4":
					/**
					 * 外部合作机构
					 */
					memberTypeSet.add(IdentityEnum.External_Cooperation_Agencies.getValue());
					memberTypeSet.add(IdentityEnum.Investor__External_Cooperation_Agencies.getValue());
					memberTypeSet.add(IdentityEnum.Settled_Enterprise__External_Cooperation_Agencies.getValue());
					memberTypeSet.add(IdentityEnum.Investor__External_Cooperation_Agencies__External_Cooperation_Agencies.getValue());
					break;
				default:
					break;
				}
			}
			sendSysNoticeToUser(memberTypeSet,title,content);
		}
	}
	
	/**
	 * 系统通知发送到用户系统通知
	 * @param userList	用户列表
	 * @param title		标题
	 * @param content	内容
	 */
	private void sendSysNoticeToUser(Set<Integer> memberTypeSet,String title,String content){
		Date now = new Date();
		JcUserNotice jcUserNotice = null;
		List<CmsUser> userList = cmsUserDao.getListByMemberType(memberTypeSet);
		if(userList != null) {
			for (CmsUser user : userList){
				jcUserNotice = new JcUserNotice();
				jcUserNotice.setCmsUser(user);
				jcUserNotice.setTitle(title);
				jcUserNotice.setContent(content);
				jcUserNotice.setTag((byte)0);
				jcUserNotice.setReadStatus((byte)1);
				jcUserNotice.setCreateTime(now);
				jcUserNoticeDao.save(jcUserNotice);
			}
		}
	}
}
