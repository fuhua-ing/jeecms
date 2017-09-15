package com.jeecms.cms.dao.main.impl;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.ActivitiesEnrollDao;
import com.jeecms.cms.entity.main.ActivitiesEnroll;
import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.entity.main.ContentExt;
import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.ProjectRelease;


@Repository
public class ActivitiesEnrollDaoImpl extends HibernateBaseDao<ActivitiesEnroll, Integer> implements ActivitiesEnrollDao {

	@Override
	protected Class<ActivitiesEnroll> getEntityClass() {
		return ActivitiesEnroll.class;
	}

	@Override
	public ActivitiesEnroll save(ActivitiesEnroll bean) {
		if (bean != null) {
			getSession().save(bean);
			getSession().flush();
		}
		return bean;
	}

	@Override
	public ActivitiesEnroll findById(Integer id) {
		if (id <= 0) {
			return null;
		}
		return get(id);
	}
	
	@Override
	public ActivitiesEnroll findByUserPhone(Integer contentId,String userphone) {
		if (StringUtils.isNotBlank(userphone)) {
			String hql = "from ActivitiesEnroll bean where bean.content.id=:contentId and bean.enrollPhone=:userphone";
			Query query = getSession().createQuery(hql);
			query.setParameter("contentId", contentId);
			query.setParameter("userphone", userphone);
			List list = query.list();
			if (list != null && list.size()>0){
				return (ActivitiesEnroll)list.get(0);
			}
		}
		return null;
	}

	@Override
	public ActivitiesEnroll deleteById(Integer id) {
		ActivitiesEnroll entity = get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;

	}

	@Override
	public Pagination getPage(String title, Integer enrollStatus, Integer pageNo, Integer pageSize) {
//		String sql = "select a.attr_value as enrollStatus, e.title as title,e.release_date as releaseDate from jc_content t left join jc_content_ext e "
//				+ "on t.content_id=e.content_id left join jc_content_attr a on t.content_id=a.content_id where t.model_id=4";
//		String sql = "select e.* ,t.* from jc_content t left join jc_content_ext e "
//				+ "on t.content_id=e.content_id left join jc_content_attr a on t.content_id=a.content_id where t.model_id=4";
		String hql = " select con from Content con where 1=1";
//				+ "left join con.contentExt ext left join con.attr with con.id=ext.id and con.model.id=4 where 1=1";
		Finder f = Finder.create(hql);
        f.append(" and con.model.id=:id").setParam("id", 4);
        
		if (StringUtils.isNotBlank(title)) {
			f.append(" and con.contentExt.title like :title").setParam("title", '%' + title + '%');
		}
		if (enrollStatus !=null){
			f.append(" and con.attr[:kenrollStatus]=:venrollStatus").setParam("kenrollStatus", "bmzt").setParam("venrollStatus", enrollStatus);
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
	public Pagination getPersonPage(Integer contentId, Integer pageNo, Integer pageSize) {
		String hql = " select bean from ActivitiesEnroll bean where 1=1";
        Finder f = Finder.create(hql);
		
		if (contentId != null && contentId != 0) {
			f.append(" and bean.content.id =:contentId").setParam("contentId", contentId);
		}else {
			f.append(" and 1=2");
			return find(f, pageNo, pageSize);
		}
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		return find(f, pageNo, pageSize);
	}

}
