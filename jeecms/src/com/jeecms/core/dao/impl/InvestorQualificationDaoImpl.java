package com.jeecms.core.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.InvestorQualificationDao;
import com.jeecms.core.entity.InvestorQualification;

@Repository
public class InvestorQualificationDaoImpl extends
		HibernateBaseDao<InvestorQualification, Integer> implements
		InvestorQualificationDao {

	@Override
	public InvestorQualification save(InvestorQualification bean) {
		if (bean != null) {
			getSession().save(bean);
		}
		return bean;
	}

	@Override
	protected Class<InvestorQualification> getEntityClass() {
		return InvestorQualification.class;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public InvestorQualification findByUserName(String userName) {
		String hql = "from InvestorQualification i where i.cmsUser.username=:userName";
		Query query = getSession().createQuery(hql);
		query.setParameter("userName", userName);
		List list = query.list();
		if (list != null && list.size()>0){
			return (InvestorQualification)list.get(0);
		}
		return null;
	}

	@Override
	public InvestorQualification findById(Integer id) {
		if (id <= 0) {
			return null;
		}
		return get(id);
	}
	
	@Override
	public InvestorQualification deleteById(Integer id) {
		InvestorQualification entity = get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public Pagination getPage(String userName, String realName, Date startTime,
			Date endTime, Integer checkStatus, Integer pageNo, Integer pageSize) {
		String hql = " select bean from InvestorQualification bean where 1=1 ";
		Finder f = Finder.create(hql);
		if (StringUtils.isNotBlank(userName)) {
			f.append(" and bean.cmsUser.username =:userName").setParam("userName", userName);
		}
		if (StringUtils.isNotBlank(realName)) {
			f.append(" and bean.realName =:realName").setParam("realName", realName);
		}
		if (startTime != null) {
			f.append(" and bean.applyTime >=:startTime").setParam("startTime", startTime);
		}
		if (endTime != null) {
			f.append(" and bean.applyTime <=:endTime").setParam("endTime", endTime);
		}
		if (checkStatus !=null && checkStatus != 0){
			f.append(" and bean.checkStatus =:checkStatus").setParam("checkStatus", checkStatus);
		}
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		return find(f, pageNo, pageSize);
	}
	
	@Override
	public Pagination getInteractionPage(String name, String industry,Byte checkStatus,
			Integer pageNo, Integer pageSize) {
		String hql = " select bean from InvestorQualification bean where 1=1 ";
		Finder f = Finder.create(hql);
		if (StringUtils.isNotBlank(name)) {
			f.append(" and bean.realName like concat('%',:realName,'%')").setParam("realName", name);
		}
		if (StringUtils.isNotBlank(industry) && !"0".equals(industry)) {
			f.append(" and find_in_set(:intentionIndustry,bean.intentionIndustry)>0").setParam("intentionIndustry", industry);
		}
		if (checkStatus != null && checkStatus >0){
			f.append(" and bean.checkStatus = :checkStatus").setParam("checkStatus", checkStatus);
		}
		
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 3;
		}
		return find(f, pageNo, pageSize);
	}

	@Override
	public InvestorQualification checkInvestorQualification(int id, int status, String reason) {
		InvestorQualification entity = super.get(id);
		if (entity != null && status>0){
			entity.setCheckStatus((byte)status);
			entity.setReason(reason);
			entity.setCheckTime(new Date());
			getSession().update(entity);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvestorQualification> getHasChecked() {
		Finder f= Finder.create("select investor from InvestorQualification investor where investor.checkStatus=2");
		return find(f);
	}
}
