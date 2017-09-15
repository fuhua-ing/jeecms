package com.jeecms.core.manager.impl;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.JcFollowProjectDao;
import com.jeecms.core.dao.JcReleaseTransferDao;
import com.jeecms.core.dao.ProjectReleaseDao;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.JcFollowProject;
import com.jeecms.core.manager.JcFollowProjectMng;

@Service
@Transactional
public class JcFollowProjectMngImpl implements JcFollowProjectMng{

	@Autowired
	private JcFollowProjectDao jcFollowProjectDao;
	
	@Autowired
	private JcReleaseTransferDao jcReleaseTransferDao;
	
	@Autowired
	private ProjectReleaseDao projectReleaseDao;
	
	@Override
	public JcFollowProject save(JcFollowProject bean) {
		if (bean != null){
			bean.setCreateTime(new Date());
		}
		return jcFollowProjectDao.save(bean);
	}
	
	@Override
	public JcFollowProject save(CmsUser user,Integer projectId,Integer tag) {
		if (user == null) {
			return null;
		}
		JcFollowProject entity = new JcFollowProject();
		entity.setCmsUser(user);
		entity.setProjectId(projectId);
		entity.setTag(tag);
		entity.setCreateTime(new Date());
		return jcFollowProjectDao.save(entity);
	}

	@Override
	public JcFollowProject findById(Integer id) {
		if(id != null && id>0) {
			return jcFollowProjectDao.findById(id);
		}
		return null;
	}
	

	@Override
	public JcFollowProject findById(Integer userId, Integer projectId,
			Integer tag) {
		return jcFollowProjectDao.findById(userId, projectId, tag);
	}

	@Override
	public JcFollowProject deleteById(Integer id) {
		if(id != null && id>0) {
			return jcFollowProjectDao.deleteById(id);
		}
		return null;
	}
	
	@Override
	public void deleteByIds(Integer... ids) {
		if (ids != null) {
			for (Integer id : ids) {
				if(id != null && id>0) {
					jcFollowProjectDao.deleteById(id);
				}
			}
		}
	}
	
	@Override
	public JcFollowProject delete(JcFollowProject jcFollowProject) {
		if(jcFollowProject != null) {
			return jcFollowProjectDao.delete(jcFollowProject);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagination getPage(Integer pageNo, Integer pageSize, Integer uid) {
		Pagination pagination = jcFollowProjectDao.getPage(pageNo, pageSize, uid);
		 
		List<JcFollowProject> list = (List<JcFollowProject>)pagination.getList();
		for(JcFollowProject jcFollowProject :list) {
			if(jcFollowProject.getTag() == 1) {
				jcFollowProject.setProjectRelease(projectReleaseDao.findById(jcFollowProject.getProjectId()));
			} else if (jcFollowProject.getTag() == 2) {
				jcFollowProject.setProjectTransfer(jcReleaseTransferDao.findById(jcFollowProject.getProjectId()));
			}
		}
		
		return pagination;
	}
	
}
