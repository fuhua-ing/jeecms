package com.jeecms.core.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.JcReleaseTransferDao;
import com.jeecms.core.entity.JcReleaseTransfer;

@Repository
public class JcReleaseTransferDaoImpl extends
		HibernateBaseDao<JcReleaseTransfer, Integer> implements
		JcReleaseTransferDao {

	@Override
	public JcReleaseTransfer save(JcReleaseTransfer bean) {
		if (bean != null) {
			getSession().save(bean);
		}
		return bean;
	}

	@Override
	public JcReleaseTransfer findById(Integer id) {
		if (id <= 0) {
			return null;
		}
		return get(id);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public JcReleaseTransfer findByUserName(String userName) {
		if (StringUtils.isNotBlank(userName)) {
			String hql = "from JcReleaseTransfer bean where bean.cmsUser.username=:userName";
			Query query = getSession().createQuery(hql);
			query.setParameter("userName", userName);
			List list = query.list();
			if (list != null && list.size()>0){
				return (JcReleaseTransfer)list.get(0);
			}
		}
		return null;
	}

	@Override
	public JcReleaseTransfer deleteById(Integer id) {
		JcReleaseTransfer entity = get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	public JcReleaseTransfer updateStatus(int id, Integer checkStatus,
			Integer showStatus,String reason) {
		JcReleaseTransfer entity = get(id);
		if (entity != null) {
			if (checkStatus != null && checkStatus > 0) {
				entity.setCheckStatus(checkStatus.byteValue());
				entity.setCheckTime(new Date());
			}
			if (showStatus != null && showStatus > 0) {
				entity.setShowStatus(showStatus.byteValue());
			}
			if (StringUtils.isNotBlank(reason)) {
				entity.setReason(reason);
			}
			getSession().update(entity);
		}
		return entity;
	}

	@Override
	public Pagination getPage(String userName, Integer stage, Integer industry,
			Integer checkStatus, Date startTime, Date endTime, Integer pageNo,
			Integer pageSize) {
		String hql = " select bean from JcReleaseTransfer bean where 1=1 ";
		Finder f = Finder.create(hql);
		
		if (StringUtils.isNotBlank(userName)) {
			f.append(" and bean.cmsUser.username =:userName").setParam("userName", userName);
		}
		if (stage !=null && stage != 0){
			f.append(" and bean.stage =:stage").setParam("stage", String.valueOf(stage));
		}
		if (industry !=null && industry != 0){
			f.append(" and bean.industry =:industry").setParam("industry", String.valueOf(industry));
		}
		if (checkStatus !=null && checkStatus != 0){
			f.append(" and bean.checkStatus =:checkStatus").setParam("checkStatus", checkStatus.byteValue());
		}
		if (startTime != null) {
			f.append(" and bean.applyTime >=:startTime").setParam("startTime", startTime);
		}
		if (endTime != null) {
			f.append(" and bean.applyTime <=:endTime").setParam("endTime", endTime);
		}
		f.append(" order by bean.applyTime desc");
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		return find(f, pageNo, pageSize);
	}

	@Override
	protected Class<JcReleaseTransfer> getEntityClass() {
		return JcReleaseTransfer.class;
	}

	@Override
	public Pagination getInteractionPage(String money, String radio,
			String position, String name, Integer pageNo, Integer pageSize) {
		String hql = " select bean from JcReleaseTransfer bean where bean.showStatus = 1 and checkStatus=2";
		Finder f = Finder.create(hql);
		
		/**
		 * 转让份额
		 * 1:不限
		 * 2:500万（含）以下
		 * 3:500万-1000万（含）
		 * 4:1000万-5000万（含）
		 * 5:5000万以上
		 */
		if (StringUtils.isNotBlank(money)) {
			if ("2".equals(money)){
				f.append(" and bean.transferStockRatio <=500");
			} else if ("3".equals(money)) {
				f.append(" and bean.transferStockRatio <=1000 and bean.transferStockRatio>500");
			} else if ("4".equals(money)) {
				f.append(" and bean.transferStockRatio <=5000 and bean.transferStockRatio>1000");
			} else if ("5".equals(money)) {
				f.append(" and bean.transferStockRatio >5000");
			}
		}
		/**
		 * 股权占比
		 * 1:不限
		 * 2:5%（含）以下
		 * 3:5%-10%（含）
		 * 4:10%-20%（含）
		 * 5:20%-50%（含）
		 * 6:50%以上
		 */
		if (StringUtils.isNotBlank(radio)) {
			if ("2".equals(radio)){
				f.append(" and bean.holdStockRatio <=5");
			} else if ("3".equals(radio)) {
				f.append(" and bean.holdStockRatio <=10 and bean.holdStockRatio>5");
			} else if ("4".equals(radio)) {
				f.append(" and bean.holdStockRatio <=20 and bean.holdStockRatio>10");
			} else if ("5".equals(radio)) {
				f.append(" and bean.holdStockRatio <=50 and bean.holdStockRatio>20");
			} else if ("6".equals(radio)) {
				f.append(" and bean.holdStockRatio >50");
			}
		}
		
		/**
		 * 市
		 * 2:绍兴市 330600
		 * 3:杭州市 330100
		 * 4:宁波市 330200
		 * 5:嘉兴市 330400
		 * 6:温州市 330300
		 * 7:湖州市 330500
		 */
		if (StringUtils.isNotBlank(position) && !"1".equals(position)) {
			f.append(" and bean.addressC = :positionParam").setParam("positionParam", position);
		}
		
		/**
		 * 项目名称
		 */
		if (StringUtils.isNotBlank(name)) {
			f.append(" and bean.projectName like concat('%',:projectName,'%')").setParam("projectName", name);
		}
		
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 3;
		}
		return find(f, pageNo, pageSize);
	}
}
