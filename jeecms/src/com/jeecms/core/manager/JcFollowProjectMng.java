package com.jeecms.core.manager;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.JcFollowProject;


public interface JcFollowProjectMng {
	/**
	 * 保存
	 * @param bean
	 * @return
	 */
	JcFollowProject save(JcFollowProject bean);
	
	/**
	 * 保存关注信息
	 * @param user
	 * @param projectId
	 * @param tag
	 * @return
	 */
	JcFollowProject save(CmsUser user,Integer projectId,Integer tag);
	
	/**
	 * 根据id记录
	 * @param id
	 * @return
	 */
	JcFollowProject findById(Integer id);
	
	/**
	 * 根据id查询
	 * @param userId  用户id 
	 * @param projectId 项目id
	 * @param tag 标识
	 * @return
	 */
	JcFollowProject findById(Integer userId,Integer projectId,Integer tag);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	JcFollowProject deleteById(Integer id);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	void deleteByIds(Integer... ids);
	
	/**
	 * 删除
	 * @param jcFollowProject
	 * @return
	 */
	JcFollowProject delete(JcFollowProject jcFollowProject);
	
	/**
	 * 获取系统消息分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Pagination getPage(Integer pageNo,Integer pageSize,Integer uid);
}
