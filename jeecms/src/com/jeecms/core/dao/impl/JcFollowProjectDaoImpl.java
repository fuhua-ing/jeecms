package com.jeecms.core.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate4.Finder;
import com.jeecms.common.hibernate4.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.JcFollowProjectDao;
import com.jeecms.core.entity.JcFollowProject;

@Repository
public class JcFollowProjectDaoImpl extends
		HibernateBaseDao<JcFollowProject, Integer> implements
		JcFollowProjectDao {

	@Override
	public JcFollowProject save(JcFollowProject bean) {
		if (bean != null) {
			getSession().save(bean);
		}
		return bean;
	}

	@Override
	public JcFollowProject findById(Integer id) {
		if (id <= 0) {
			return null;
		}
		return get(id);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public JcFollowProject findById(Integer userId,Integer projectId,Integer tag) {
		String hql = "from JcFollowProject bean where bean.cmsUser.id=:userId "
				+ "and bean.projectId=:projectId and bean.tag=:tag";
		Query query = getSession().createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("projectId", projectId);
		query.setParameter("tag", tag);
		List list = query.list();
		if (list != null && list.size()>0){
			return (JcFollowProject)list.get(0);
		}
		return null;
	}

	@Override
	public JcFollowProject deleteById(Integer id) {
		JcFollowProject entity = get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	public JcFollowProject delete(JcFollowProject jcFollowProject) {
		getSession().delete(jcFollowProject);
		return jcFollowProject;
	}

	@Override
	public Pagination getPage(Integer pageNo, Integer pageSize, Integer uid) {
		Finder f = Finder.create("select bean from JcFollowProject bean where 1=1 ");
		if (uid != null && uid > 0) {
			f.append(" and bean.cmsUser.id = :uid").setParam("uid", uid);
		}
		f.append(" order by bean.createTime desc");
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		
		return find(f, pageNo, pageSize);
	}

	@Override
	protected Class<JcFollowProject> getEntityClass() {
		return JcFollowProject.class;
	}

	@Override
	public void deleteByProjectId(Integer projectId, Integer tag) {
		String hql = "delete JcFollowProject bean where bean.projectId=:projectId and bean.tag=:tag";
		Query query = getSession().createQuery(hql);
		query.setParameter("projectId", projectId);
		query.setParameter("tag", tag);
		query.executeUpdate();
	}

}
